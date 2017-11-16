package com.itheima.bos.service;

import com.itheima.base.domain.Courier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 11:27 <br/>
 * Author zzff
 */

public interface CourierService {
    Page<Courier> pageQuery(Specification<Courier> specification, Pageable pageable);

    List<Courier> findAllIsCanUse();

    List<Courier> findAll();

    void save(Courier model);

    void delCouriers(String id, Character deltag);
}
