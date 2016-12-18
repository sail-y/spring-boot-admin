package com.dmc.controller;

import com.dmc.model.Menu;
import com.dmc.model.Resource;
import com.dmc.model.RestResp;
import com.dmc.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源控制器
 *
 * @author yangfan
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;


    /**
     * 获得菜单
     * <p>
     * 通过用户ID判断，他能看到的菜单
     *
     * @return
     */
    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public List<Menu> menus() {
        return resourceService.menus();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource getById(@PathVariable("id") Long id) {
        return resourceService.get(id);
    }

    /**
     * 获得资源树(包括所有资源类型)
     * <p>
     * 通过用户ID判断，他能看到的资源
     *
     * @return
     */
    @RequestMapping(value = "/allMenus", method = RequestMethod.POST)
    public List<Menu> allMenus() {

        return resourceService.allMenus();
    }

    /**
     * 添加资源
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Resource resource) {
        resourceService.add(resource);
        return RestResp.ok("添加成功");
    }

    /**
     * 编辑资源
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RestResp edit(@RequestBody Resource resource) {
        resourceService.edit(resource);

        return RestResp.ok("编辑成功");
    }

    /**
     * 删除资源
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("id") Long id) {
        resourceService.delete(id);

        return RestResp.ok("删除成功");
    }


    /**
     * tree型列表
     * @return
     */
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public List<Resource> treeList() {
        return resourceService.treeList();
    }
}
