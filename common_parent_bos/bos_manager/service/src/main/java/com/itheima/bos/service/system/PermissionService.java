package com.itheima.bos.service.system;

import com.itheima.system.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:com.itheima.bos.service.system <br/>
 * Function: <br/>
 * Date:2017/11/15 21:34 <br/>
 * Author zzff
 */
public interface PermissionService {
    Page<Permission> pageQuery(Specification<Permission> specification, Pageable pageable);

    List<Permission> findAllIsCanUse();

    List<Permission> findAll();

    void save(Permission model);
}
