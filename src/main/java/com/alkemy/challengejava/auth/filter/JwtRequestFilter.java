package com.alkemy.challengejava.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alkemy.challengejava.auth.service.JwtUtils;
import com.alkemy.challengejava.auth.service.UserDetailsCustomSerivce;

// Extiendo de OncePerRequestFilter porque se ejecuetara para cada peticion
// que llegue al servidor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsCustomSerivce service;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Obtengo el Header de autentificacion
        final String authHeader = request.getHeader("Authorization");

        // Declaro las variables de Username y del Token JWT
        String username = null;
        String jwtToken = null;

        // Bearer es el tipo de Token que se utiliza, y va generalmente
        // en el Header de Authorization (por eso verifico que este en header)
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7); // Le digo que el Token esat luego de "Bearer " (7 caracteres) 
            username = jwtUtils.getUsernameFromToken(jwtToken); // Y del Token que obteng el Username
        }

        // - Si en la cabezera habia un Username (!= null) 
        // - Y si en el contexto no hay autenticacion para el usuario (== null) 
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Cargo los UserDetails del usuario desde la Base de datos
            UserDetails userDetails = service.loadUserByUsername(username); 

            if(jwtUtils.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
                Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(auth);    
            }
        }

        filterChain.doFilter(request, response);
    }

}
