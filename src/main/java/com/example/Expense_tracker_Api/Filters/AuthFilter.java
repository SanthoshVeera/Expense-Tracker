package com.example.Expense_tracker_Api.Filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        Logger logger = LoggerFactory.getLogger(AuthFilter.class);

        String authHeader = httpRequest.getHeader("Authorization");
        logger.info(authHeader);

        String autharr [] = authHeader.split(" ");
        String token = autharr[1];
        logger.info(autharr[0]);
        logger.info(autharr[1]);

        Claims claims = Jwts.parser().setSigningKey("API_SECRET_KEY")
                .parseClaimsJws(token).getBody();
        httpRequest.setAttribute("id", Integer.parseInt(claims.get("id").toString()));

        filterChain.doFilter(servletRequest,servletResponse);

    }
}