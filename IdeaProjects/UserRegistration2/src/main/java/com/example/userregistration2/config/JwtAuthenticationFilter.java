package com.example.userregistration2.config;

import com.example.userregistration2.service.CustomUserDataService;
import com.example.userregistration2.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDataService customUserDataService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //get jwt
        //Bearer
        // Validate
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // Null and format (length of bearer and space = 7)
        if( requestTokenHeader != null && requestTokenHeader.startsWith("Bearer"))
        {
            jwtToken = requestTokenHeader.substring(7);
            try{
                username = this.jwtUtil.getUserNameFromToken(jwtToken);
                System.out.println("username is :" + username);
            }catch(Exception e)
            {
                e.printStackTrace();
            }

            UserDetails userDetails = this.customUserDataService.loadUserByUsername(username);

            // Security
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                if(jwtUtil.validateToken(jwtToken, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
                }
            } else
            {
                System.out.println("Token Invalid");
            }
        }
        filterChain.doFilter(request,response);
    }
}
