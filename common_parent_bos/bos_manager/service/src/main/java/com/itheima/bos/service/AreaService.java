package com.itheima.bos.service;

import com.itheima.base.domain.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 19:54 <br/>
 * Author zzff
 */
public interface AreaService {
    Page<Area> pageQuery(Specification<Area> specification, Pageable pageable);

    List<Area> findAll();

    void save(List<Area> list);

    List<Area> findAllByQ(String q);
}
