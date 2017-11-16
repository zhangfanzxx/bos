package com.itheima.bos.service;

import com.itheima.base.domain.SubArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 11:39 <br/>
 * Author zzff
 */
public interface SubAreaService {
    List<SubArea> findAll();

    List<SubArea> findAllIsCanUse();

    Page<SubArea> pageQuery(Specification<SubArea> specification, Pageable pageable);

    void save(SubArea model);

    List<SubArea> find_subAreaByFixedAreaIdIsNull();

    List<SubArea> find_subAreaByFixedAreaId(Long fixedAreadId);

    void associationSubAreaToFixedArea(Long fixedAreaId, List<Long> subAreaIds);
}
