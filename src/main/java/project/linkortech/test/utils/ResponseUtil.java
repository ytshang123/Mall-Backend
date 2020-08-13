package project.linkortech.test.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于response的处理，如springboot的interceptor中
 */
public class ResponseUtil {

    public static void setCross(HttpServletResponse response, HttpServletRequest request){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, accept, authorization, content-type");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        // 携带cookie, 但是origin不能是*  ！！！
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

    public static void setDefaultRest(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }
}
