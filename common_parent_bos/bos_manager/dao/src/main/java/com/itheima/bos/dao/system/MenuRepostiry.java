package com.itheima.bos.dao.system;

import com.itheima.system.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * ClassName:com.itheima.bos.dao.system <br/>
 * Function: <br/>
 * Date:2017/11/15 19:25 <br/>
 * Author zzff
 */
public interface MenuRepostiry extends JpaRepository<Menu,Long>,JpaSpecificationExecutor<Menu> {
    List<Menu> findByPageIsNull();
}
