package com.itheima.bos.service;

import com.itheima.base.domain.FixedArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 20:45 <br/>
 * Author zzff
 */
public interface FixedAreaService {
    Page<FixedArea> pageQuery(Specification<FixedArea> specification, Pageable pageable);

    List<FixedArea> findAllIsCanUse();

    List<FixedArea> findAll();

    void save(FixedArea model);

    void associationCourierToFixedArea(Long id, Long courierId, Long takeTimeId);
}
