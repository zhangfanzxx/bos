package com.itheima.bos.service.impl;

import com.itheima.base.domain.GoodType;
import com.itheima.bos.dao.GoodTypeRepository;
import com.itheima.bos.service.GoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 19:07 <br/>
 * Author zzff
 */
@Service
@Transactional
public class GoodTypeServiceImpl implements GoodTypeService {
    @Autowired
    private GoodTypeRepository goodTypeRepository;
    @Override
    public void save(GoodType model) {
        goodTypeRepository.save(model);
    }

    @Override
    public List<GoodType> findAll() {
        return goodTypeRepository.findAll();
    }
}
