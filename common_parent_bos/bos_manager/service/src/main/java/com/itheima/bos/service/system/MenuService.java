package com.itheima.bos.service.system;

import com.itheima.system.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:com.itheima.bos.service.system <br/>
 * Function: <br/>
 * Date:2017/11/15 18:15 <br/>
 * Author zzff
 */
public interface MenuService {
    void save(Menu model);

    List<Menu> findAll();

    Page<Menu> pageQuery(Specification<Menu> specification, Pageable pageable);

    List<Menu> findAllIsCanUse();

    List<Menu> findMenuParent();
}
