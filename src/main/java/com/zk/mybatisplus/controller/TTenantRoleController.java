package com.zk.mybatisplus.controller;


import com.zk.mybatisplus.mapper.TTenantRoleMapper;
import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.service.ITTenantRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/role")
public class TTenantRoleController {
    @Resource
    private ITTenantRoleService service;


    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<TTenantRole> findAll(){
        return  service.findAll();
    }
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public TTenantRole findById(@RequestParam(value = "id") int id){
        return  service.findById(id);
    }

}
