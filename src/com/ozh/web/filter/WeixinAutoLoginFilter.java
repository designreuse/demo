package com.ozh.web.filter;

import com.ozh.common.utils.OAuthAPI;
import com.ozh.core.entity.SysUser;
import com.ozh.web.WebContextFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ozh on 2016/4/18.
 */
public class WeixinAutoLoginFilter extends AbstractExcludeUrlFilter {
    private static final long serialVersionUID = 5454135885035635342L;


    @Override
    public void realFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            SysUser sysUser = WebContextFactory.getWebContext().getFrontEndUser();

            String userAgent = httpRequest.getHeader("User-Agent");
            String microMessenger = "MicroMessenger/";
            if(userAgent.indexOf(microMessenger) > 0&&sysUser==null){
                OAuthAPI.OAuthIfNecessary(httpRequest, httpResponse);
            }
        }
        filterChain.doFilter(request, response);
    }
}
