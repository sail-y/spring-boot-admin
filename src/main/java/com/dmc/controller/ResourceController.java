package com.dmc.controller;

import com.dmc.model.RestResp;
import com.dmc.model.Resource;
import com.dmc.model.SessionInfo;
import com.dmc.model.Menu;
import com.dmc.service.ResourceService;
import com.dmc.util.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 资源控制器
 *
 * @author yangfan
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    /**
     * 获得菜单
     * <p>
     * 通过用户ID判断，他能看到的菜单
     *
     * @param session
     * @return
     */
    @RequestMapping("/menus")
    @ResponseBody
    public List<Menu> menus(HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        return resourceService.menus(sessionInfo);
    }

    /**
     * 获得资源树(包括所有资源类型)
     * <p>
     * 通过用户ID判断，他能看到的资源
     *
     * @param session
     * @return
     */
    @RequestMapping("/allTree")
    @ResponseBody
    public List<Menu> allTree(HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        return resourceService.allTree(sessionInfo);
    }

    /**
     * 跳转到资源管理页面
     *
     * @return
     */
    @RequestMapping("/manager")
    public String manager() {
        return "/admin/resource";
    }

    /**
     * 添加资源
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public void add(Resource resource, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        resourceService.add(resource, sessionInfo);
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public RestResp edit(Resource resource) {
        RestResp j = new RestResp();
        resourceService.edit(resource);

        j.setMsg("编辑成功！");
        return j;
    }

    /**
     * 获得资源列表
     * <p>
     * 通过用户ID判断，他能看到的资源
     *
     * @return
     */
    @RequestMapping("/treeGrid")
    @ResponseBody
    public List<Resource> treeGrid(HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        return resourceService.treeGrid(sessionInfo);
    }

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public RestResp delete(String id) {
        RestResp j = new RestResp();
        resourceService.delete(id);
        j.setMsg("删除成功！");

        return j;
    }

}
