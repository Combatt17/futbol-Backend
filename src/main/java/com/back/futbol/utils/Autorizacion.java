package com.back.futbol.utils;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class Autorizacion implements Filter{

    public static final String KEY="ajksdlkdsanlclajskjds";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;

                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
                httpServletResponse.setHeader("Access-Control-Allow-Headers","Authorization, Content-Type");

                String url = httpServletRequest.getRequestURI();  //http://localhost:8080
                //Rutas publicas 
                if(url.contains("/api/usuarios/login")||url.contains("/api/usuarios") || url.contains("index")|| url.contains(".js")|| url.contains(".css")|| url.contains(".ico")|| url.contains("assets")|| url.contains("#")){
                    chain.doFilter(request, response);
                }else{
                    String hash = httpServletRequest.getHeader("Authorization");
                    if(hash==null || hash.trim().equals("")){
                        response.setContentType("application/json");
                        String body= "{\"autorizacion \":\"NO\"}";
                        response.getWriter().write(body);
                    }

                    try {
                        Jws<Claims> claims=Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
                        //Rutas privadas
                            if(url.contains("/api/equipos")||url.contains("/api/partidos")||url.contains("/api/verificar")){
                                chain.doFilter(request, response);
                             }
                        
                        
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
        
    }
    
   
    
}
