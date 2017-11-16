package com.itheima.bos.service;

import com.itheima.base.domain.TakeTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/11 10:27 <br/>
 * Author zzff
 */
public interface TakeTimeService {
    Page<TakeTime> pageQuery(Specification<TakeTime> specification, Pageable pageable);

    List<TakeTime> findAllIsCanUse();

    List<TakeTime> findAll();

    void save(TakeTime model);

    void delTakeTime(String ids);
}
