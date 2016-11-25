package com.dmc.interceptors;

import com.alibaba.fastjson.JSON;
import com.dmc.model.JsonModel;
import com.dmc.model.SessionInfo;
import com.dmc.util.AppConst;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限拦截器
 */
@Configuration
public class SecurityInterceptor implements HandlerInterceptor {


    private List<String> excludeUrls = new ArrayList<>();// 不需要拦截的资源

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        // logger.info(url);

        if (excludeUrls.contains(url)) {// 如果要访问的资源是不需要验证的
            return true;
        }

        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(AppConst.SESSION_NAME);
        if (sessionInfo == null || sessionInfo.getId().equalsIgnoreCase("")) {// 如果没有登录或登录超时
            request.getRequestDispatcher("/error/noSession.html").forward(request, response);
            return false;
        }
        if (!sessionInfo.getResourceList().contains(url)) {// 如果当前用户没有访问此资源的权限
            JsonModel json = new JsonModel();
            json.setSuccess(false);
            json.setMsg("没有权限！");
            response.getWriter().println(JSON.toJSONString(json));
            return false;
        }

        return true;
    }

    public SecurityInterceptor ignore(String url) {
        excludeUrls.add(url);
        return this;
    }
}
