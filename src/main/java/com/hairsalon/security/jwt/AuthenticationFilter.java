package com.hairsalon.security.jwt;

import com.hairsalon.entity.Customer;
import com.hairsalon.entity.Role;
import com.hairsalon.entity.User;
import com.hairsalon.respository.imp.UserDetailServiceImp;
import com.hairsalon.respository.imp.UserImp;
import com.hairsalon.service.CustomRoleService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailServiceImp userDetailsService;
    @Autowired
    UserImp userRepositoryImpl;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private JSONObject object;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = CustomRoleService.getTokenFromRequest(request);
            UserDetails userDetails = null;
            if (token != null && jwtUtils.validateJwtToken(token)) {
                String username = jwtUtils.getUserNameFromJwtToken(token);
                try {
                    userDetails = userDetailsService.loadUserByUsername(username);
                } catch (Exception e) {
                    logger.error("", e);
                }
            } else {
                if (!userRepositoryImpl.checkExistingUserByUsername("johnDoe")) {
                    User user = new User();
                    user.setUserName(object.getString("email"));
                   // user.setEmail(object.getString("email"));
                    Role role = new Role();
                    role.setRoleId(2);
                    user.setRole(role);
                    userRepositoryImpl.add(user);
                }
                userDetails = userDetailsService.loadUserByUsername(object.getString("email"));
            }
            // Store user information
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}