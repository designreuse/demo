package com.ozh.web;


import com.ozh.core.entity.SysUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对session，request,application的attribute进行修改
 * User: lxd
 * Date: 2010-12-26
 * Time: 19:04:01
 */
public interface IWebContext {
    HttpServletRequest getRequest();
    HttpServletResponse getResponse();

    //取得出发地城市

    void setSessionAttr(String key, Object value);
    Object getSessionAttr(String key);
    void removeSessionAttr(String key);

    void setRequestAttr(String key, Object value);
    Object getRequestAttr(String key);
    void removeRequestAttr(String key);

    String getHost();
    String getRemoteAddr();

    Cookie getCookie(String cookieName);
    String getCookieValue(String cookieName);
    Cookie[] getCookies();
    void setCookie(Cookie c);
    void expireCookie(String cookieName);

    void setFrontEndUser(SysUser user);
    SysUser getFrontEndUser();

    Integer getFrontEndUserId();


}
