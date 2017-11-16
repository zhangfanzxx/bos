package com.itheima.bos.service.system.impl;

import com.itheima.bos.dao.system.PermissionResipotory;
import com.itheima.bos.service.system.PermissionService;
import com.itheima.system.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:com.itheima.bos.service.system.impl <br/>
 * Function: <br/>
 * Date:2017/11/15 21:34 <br/>
 * Author zzff
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionResipotory permissionResipotory;

    @Override
    public Page<Permission> pageQuery(Specification<Permission> specification, Pageable pageable) {
        return permissionResipotory.findAll(specification,pageable);
    }

    @Override
    public List<Permission> findAllIsCanUse() {
        return null;
    }

    @Override
    public List<Permission> findAll() {
        return permissionResipotory.findAll();
    }

    @Override
    public void save(Permission model) {
        permissionResipotory.save(model);
    }
}
