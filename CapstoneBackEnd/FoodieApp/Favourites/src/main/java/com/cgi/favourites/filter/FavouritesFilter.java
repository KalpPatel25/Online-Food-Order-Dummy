package com.cgi.favourites.filter;

import io.jsonwebtoken.*;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FavouritesFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,DELETE,OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","*");

        if(httpServletRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())){
            chain.doFilter(httpServletRequest,httpServletResponse);
        }

        else {

            String authhead = httpServletRequest.getHeader("Authorization");
            System.out.println(authhead);
            if(authhead==null||(!authhead.startsWith("Bearer")))
            {
                throw new ServletException("JWT token missing");
            }

            String mytoken = authhead.substring(7);

            try{
                JwtParser jwtparser = Jwts.parser().setSigningKey("foodie");
                Jwt jwtObject = jwtparser.parse(mytoken);
                Claims claimObj = (Claims) jwtObject.getBody();
                System.out.println("welcome"+claimObj.getSubject());
            }
            catch(SignatureException sigex){
                throw new ServletException("signature mismatch");
            }
            catch(MalformedJwtException malform){
                throw new ServletException("Token is modified by unauthorized user");
            }

        }
        chain.doFilter(httpServletRequest,httpServletResponse);

    }

//    @Override
//    public void destroy() {
//        super.destroy();
//    }
}
