package com.giggle;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by enda on 15/12/15.
 */
@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletResponse response=(HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}

}
