package com.ozh.web.filter;

import com.ozh.common.Global;
import com.ozh.core.entity.SysUser;
import com.ozh.web.DefaultWebContext;
import com.ozh.web.WebContextFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WebContextFactory设置
 * User: lxd
 * Date: 2009-12-26
 * Time: 19:54:30
 */
public class LoginFilter  implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        DefaultWebContext ctx = (DefaultWebContext) WebContextFactory.getWebContext();
        SysUser sysUser = (SysUser) ctx.getSessionAttr(Global.ADMIN);
        if(sysUser!=null) {
            chain.doFilter(request, response);
        }
        String uri = request.getRequestURI();
        if(uri.contains("/admin/login")){
            chain.doFilter(request, response);
        }
        //没有登录，跳转到登录页面
        response.sendRedirect(request.getContextPath() + "/admin/login.ac");
    }


    public void destroy() {

    }
}
