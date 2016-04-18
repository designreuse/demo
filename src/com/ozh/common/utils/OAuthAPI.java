package com.ozh.common.utils;

import com.ozh.core.entity.SysUser;
import com.ozh.module.vmall.vo.WeixinConfig;
import com.ozh.utils.UtilDateTime;
import com.ozh.web.WebContextFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OAuthAPI {

    public static void OAuthIfNecessary(HttpServletRequest request,HttpServletResponse response) throws IOException {
        SysUser loginUser = WebContextFactory.getWebContext().getFrontEndUser();
        //已经登录，则不再做任何操作了
        if(loginUser!=null){
            return;
        }

        System.out.println("执行时间="+ UtilDateTime.convertDateTimeToString(new Date()));
        boolean isValidCode = true;
        String code = request.getParameter("code");
        System.out.println();
        System.out.println("获得code，执行登录，code="+code);

        String serviceUrl = request.getRequestURL().toString();
        if(StringUtils.isNotBlank(request.getQueryString())){
            serviceUrl = serviceUrl +  "?" + request.getQueryString();
            System.out.println("登录成功返回的路径="+serviceUrl);
        }

        //检查是否已验证或者验证是否通过
        if (code == null) {
            isValidCode = false;
        }

        //未授权，重定向到授权页面
        if (!isValidCode) {
            StringBuffer url = request.getRequestURL();
            String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
            WeixinConfig weixinConfig = null;//todo 得出微信的appid  配置

            StringBuilder oauth_url = new StringBuilder();
            oauth_url.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
            oauth_url.append("appid=").append(weixinConfig.getAppId());
            oauth_url.append("&redirect_uri=").append(java.net.URLEncoder.encode(tempContextUrl+"auth/codeToLoginServlet","GB2312"));
            oauth_url.append("&response_type=code");
            oauth_url.append("&scope=snsapi_base");
            oauth_url.append("&state=").append(serviceUrl);
            oauth_url.append("#wechat_redirect");
            System.out.println("请求code地址="+oauth_url.toString());
            System.out.println("执行请求code时间="+ UtilDateTime.convertDateTimeToString(new Date()));
            response.sendRedirect(oauth_url.toString());
            return;
        }

        //获得code，执行登录
        doLoginAction(code, request, response);
    }

    private static void doLoginAction(String code ,HttpServletRequest request,HttpServletResponse response){
        System.out.println("开始获取Token时间="+ UtilDateTime.convertDateTimeToString(new Date()));
        Map responseMap = OAuthAPI.getAccessToken(code);
        String openId = (String)responseMap.get("openid");
        System.out.println("获取到的openId="+openId);
        System.out.println("完成获取Token时间="+ UtilDateTime.convertDateTimeToString(new Date()));
        if(StringUtils.isBlank(openId)){
            return;
        }

        List<SysUser> sysUserList = null;//todo vuserId 根据oppingId 查询用户列表
        System.out.println();
        System.out.println("通过openId找到="+sysUserList.size()+"个用户");
        System.out.println("openId找到用户时间="+ UtilDateTime.convertDateTimeToString(new Date()));
        System.out.println();

        //todo 创建帐号
        /*如果没有关注，则自动创建帐号*/
//        if(sysUserList.isEmpty()){
//            ServiceManager.sysUserService.checkAndCreateSysUserByOpenId(openId);
//        }
//        if(sysUserList.size()>=2){
//            //若是合并了，则取合并后的账户
//            for(SysUser sysUser : sysUserList){
//                if(sysUser.getIsDelete().equals(BoolCodeEnum.NO.toCode())){
//                    System.out.println("合并登录，用户ID="+sysUser.getSysUserId());
//                    System.out.println("合并登录，用户openID=" + sysUser.getVuserId());
//                    WebContextFactory.getWebContext().setFrontEndUser(sysUser);
//                    break;
//                }
//            }
//        }else{
//            //未合并则取微信账户，loginId就是openId
//            SysUser sysUser = ServiceManager.sysUserService.getByLoginId(openId);
//            System.out.println("微信帐号登录，用户ID=" + sysUser.getSysUserId());
//            System.out.println("微信帐号登录，用户openID=" + sysUser.getVuserId());
//            WebContextFactory.getWebContext().setFrontEndUser(sysUser);
//        }
        System.out.println("返回原路径="+request.getParameter("state"));
        try {
            response.sendRedirect(request.getParameter("state"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取授权令牌
     * */
    private static Map getAccessToken(String code) {

//        WeixinConfig weixinConfig = ServiceManager.objectEnginService.getObject(ObjectEngineTypeCodeEnum.VMALL_BASE_CONFIG, Global.OBJECT_ENGIN_WEIXIN_BASE_INFO_ID, WeixinConfig.class);
        WeixinConfig weixinConfig = null;//todo 得出微信的appid  配置
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(urlStr)
                .append("?appid=").append(weixinConfig.getAppId())
                .append("&secret=").append(weixinConfig.getAppSecret())
                .append("&code=").append(code)
                .append("&grant_type=authorization_code");

        String result = null;
        /*HttpClient httpClient = new HttpClient();
        GetMethod method = new GetMethod(stringBuilder.toString());
        try {
            httpClient.executeMethod(method);
            result = new String(method.getResponseBody(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpclient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            System.out.println("*********HttpEntity*********************="+result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭流并释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return JsonBinder.buildNormalBinder().toBean(result, HashMap.class);
    }

}