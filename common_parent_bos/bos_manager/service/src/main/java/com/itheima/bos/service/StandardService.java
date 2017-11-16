package com.itheima.bos.service;

import com.itheima.base.domain.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 10:54 <br/>
 * Author zzff
 */
public interface StandardService {
    Page<Standard> pageQuery(Pageable pageable);

    void save(Standard model);

    List<Standard> findAll();
}
