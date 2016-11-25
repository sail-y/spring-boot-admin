package com.dmc.controller;

import com.dmc.model.JsonModel;
import com.dmc.model.Resource;
import com.dmc.model.SessionInfo;
import com.dmc.model.Tree;
import com.dmc.service.ResourceService;
import com.dmc.service.ResourceTypeService;
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

    @Autowired
    private ResourceTypeService resourceTypeService;

    /**
     * 获得资源树(资源类型为菜单类型)
     * <p>
     * 通过用户ID判断，他能看到的资源
     *
     * @param session
     * @return
     */
    @RequestMapping("/tree")
    @ResponseBody
    public List<Tree> tree(HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        return resourceService.tree(sessionInfo);
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
    public List<Tree> allTree(HttpSession session) {
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
    public JsonModel add(Resource resource, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        JsonModel j = new JsonModel();
        resourceService.add(resource, sessionInfo);
        j.setSuccess(true);
        j.setMsg("添加成功！");
        return j;
    }

    /**
     * 编辑资源
     *
     * @param resource
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public JsonModel edit(Resource resource) {
        JsonModel j = new JsonModel();
        resourceService.edit(resource);
        j.setSuccess(true);
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
    public JsonModel delete(String id) {
        JsonModel j = new JsonModel();
        resourceService.delete(id);
        j.setMsg("删除成功！");
        j.setSuccess(true);
        return j;
    }

}
