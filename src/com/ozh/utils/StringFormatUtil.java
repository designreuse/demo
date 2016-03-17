package com.ozh.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: lxd
 * Date: 11-5-27
 * Time: 下午2:31
 */
public class StringFormatUtil {

    private StringFormatUtil(){}


    public static String substitute(String tepl, Map<String, String> params) {
        String text = tepl;
        for(Map.Entry<String, String> entry:params.entrySet()){
            String key = entry.getKey();
            if(entry.getValue()!=null){
                    text = text.replaceAll("\\{" + key + "}", safeRegexReplacement(entry.getValue()));
                  }else {
                       text = text.replaceAll("\\{" + key + "}", "");
                  }
        }
        return text;
    }

    private static String safeRegexReplacement(String replacement) {
        if (replacement == null) {
            return replacement;
        }

        return replacement.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$", "\\\\\\$");
    }

    public static Map<String,String> getParams(HttpServletRequest request){
        Map<String,String> params = new HashMap();
        //获得POST 过来参数设置到新的params中
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = request.getParameterValues(name);
            StringBuffer valueStr =new StringBuffer();
            for (int i = 0; i < values.length; i++) {
                valueStr.append((i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",");
            }
            params.put(name, valueStr.toString());
        }
        return params;
    }
}
