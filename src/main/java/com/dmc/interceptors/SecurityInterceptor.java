package com.dmc.interceptors;

import com.dmc.model.RestResp;
import com.dmc.model.SessionInfo;
import com.dmc.util.AppConst;
import com.dmc.util.JsonUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 权限拦截器
 */
@Configuration
public class SecurityInterceptor implements HandlerInterceptor {


    private List<String> patterns = new ArrayList<>();// 不需要拦截的资源

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

    /**
     * 完成页面的render后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

    }

    /**
     * 在调用controller具体方法后拦截
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在调用controller具体方法前拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());

        // 如果要访问的资源是不需要验证的
        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern);
            if(p.matcher(url).find()) {
                return true;
            }
        }

        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(AppConst.SESSION_NAME);
        if (sessionInfo == null || sessionInfo.getId().equalsIgnoreCase("")) {// 如果没有登录或登录超时
            request.getRequestDispatcher("/error/noSession.html").forward(request, response);
            return false;
        }
        if (!sessionInfo.getResourceList().contains(url)) {// 如果当前用户没有访问此资源的权限
            RestResp resp = new RestResp(AppConst.NO_PERMESSION, "没有权限！");
            response.setCharacterEncoding("utf-8");
            response.setHeader("contentType", "application/json; charset=utf-8");
            response.getWriter().println(JsonUtil.toJsonString(resp));
            return false;
        }

        return true;
    }

    public SecurityInterceptor ignore(String url) {
        patterns.add(url);
        return this;
    }
}
