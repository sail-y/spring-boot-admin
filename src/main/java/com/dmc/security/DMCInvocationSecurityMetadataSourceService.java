/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.dmc.security;

import com.dmc.model.Resource;
import com.dmc.model.Role;
import com.dmc.service.ResourceService;
import com.dmc.service.RoleService;
import com.dmc.util.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by YangFan on 2016/11/28 上午11:33.
 * <p/>
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 此类在初始化时，应该取到所有资源及其对应角色的定义。
 */
@Component
public class DMCInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;


    private static Map<Resource, Collection<ConfigAttribute>> resourceMap = null;

    public DMCInvocationSecurityMetadataSourceService() {

    }

    private void loadResourceDefine() {

        /*
         * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
         * sparta
         */
        List<Role> roles = roleService.allRole();
        resourceMap = new HashMap<>();

        for (Role role : roles) {
            ConfigAttribute ca = new SecurityConfig(role.getName());

            Map<String, Object> params = new HashMap<>();
            params.put("roleId", role.getId());
            params.put("type", AppConst.RESOURCE_TYPE_METHOD);
            List<Resource> resources = resourceService.getResourceList(params);

            for (Resource resource : resources) {

                /*
                 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
                 * sparta
                 */
                if (resourceMap.containsKey(resource)) {

                    Collection<ConfigAttribute> value = resourceMap.get(resource);
                    value.add(ca);
                    resourceMap.put(resource, value);
                } else {
                    Collection<ConfigAttribute> atts = new ArrayList<>();
                    atts.add(ca);
                    resourceMap.put(resource, atts);
                }

            }

        }

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
        return null;
    }

    // 根据URL，找到相关的权限配置。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {

        FilterInvocation filterInvocation = (FilterInvocation) object;
        for (Resource resource : resourceMap.keySet()) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resource.getUrl());
            HttpServletRequest httpRequest = filterInvocation.getHttpRequest();
            String method = httpRequest.getMethod();

            if (requestMatcher.matches(httpRequest) && method.equalsIgnoreCase(resource.getMethod())) {
                return resourceMap.get(resource);
            }
        }

        return null;

    }

    @Override
    public boolean supports(Class<?> arg0) {

        return true;
    }

}