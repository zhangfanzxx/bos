package com.itheima.bos.dao.system;

import com.itheima.system.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName:com.itheima.bos.dao.system <br/>
 * Function: <br/>
 * Date:2017/11/15 21:35 <br/>
 * Author zzff
 */
public interface PermissionResipotory extends JpaSpecificationExecutor<Permission>,
        JpaRepository<Permission, Long> {
}
