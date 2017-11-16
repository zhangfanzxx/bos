package com.itheima.bos.service;

import com.itheima.base.domain.GoodType;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 19:06 <br/>
 * Author zzff
 */
public interface GoodTypeService {
    void save(GoodType model);

    List<GoodType> findAll();
}
