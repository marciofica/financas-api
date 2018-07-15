package com.serviconanuvem.financas.filters;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class FinancasFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Nothing to do
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String queryString = ((HttpServletRequest)servletRequest).getQueryString();
        String array[] = new String[2];
        if(queryString != null){
            array = queryString.split("=");
            System.out.println(array[0]);
            System.out.println(array[1]);
            if(!array[1].isEmpty()){
                FinancasResolver.setPlanoDeContas(Long.parseLong(array[1]));
            }
        }

        if(SecurityContextHolder.getContext().getAuthentication() != null){
            FinancasResolver.setUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //Nothing to do
    }
}
