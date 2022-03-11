package com.example.bookstorebackend.config;

import com.example.bookstorebackend.service.CustomUserDataService;
import com.example.bookstorebackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDataService customUserDataService;

    @Autowired
    private JwtUtil jwtUtil;

    // Causing problem -- every method needing authentication

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
                                    , FilterChain filterChain) throws ServletException, IOException{
        // Get jwt
        // Bearer
        // Validate
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // If tokenheader is not null and format (length of bearer and space = 7)
        if( requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){
            jwtToken = requestTokenHeader.substring(7);
            try{
                username = this.jwtUtil.getUserNameFromToken(jwtToken);
                System.out.println("Username is: "+ username);
            } catch(Exception e)
            {
                e.printStackTrace();
            }
            UserDetails userDetails =this.customUserDataService.loadUserByUsername(username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }else{
                System.out.println("The given token is invalid");
            }
        }
        filterChain.doFilter(request, response);
    }

}