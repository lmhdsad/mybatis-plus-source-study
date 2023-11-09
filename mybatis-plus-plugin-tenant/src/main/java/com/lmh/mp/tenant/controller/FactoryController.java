package com.lmh.mp.tenant.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lmh.mp.tenant.config.LoginContext;
import com.lmh.mp.tenant.entity.Factory;
import com.lmh.mp.tenant.mapper.FactoryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/factory")
public class FactoryController {

    @Resource
    private FactoryMapper factoryMapper;

    /**
     * 租户1就只能查看到租户1的信息，不能查看到其他租户的信息
     *
     * 租户id在实际生产的过程中，从登录用户的上下文中获取以及存放。这里接口存放是为了方便测试
     */
    @GetMapping("/list/{tenantId}")
    public String list(@PathVariable("tenantId") String tenantId) {
        LoginContext.setTenantId(tenantId);
        try {
            List<Factory> factories = factoryMapper.selectList(new LambdaQueryWrapper<>());
            return JSON.toJSONString(factories);
        } finally {
            LoginContext.removeTenantId();
        }
    }

    @GetMapping("insert/{tenantId}")
    public String insert(@PathVariable("tenantId") String tenantId,
                         @RequestParam("factoryName") String factoryName,
                         @RequestParam("userInfo") String userInfo) {
        LoginContext.setTenantId(tenantId);
        try {
            Factory factory = new Factory();
            factory.setFactoryName(factoryName);
            factory.setUserInfo(userInfo);
            factoryMapper.insert(factory);
        } finally {
            LoginContext.removeTenantId();
        }
        return "success";
    }
}
