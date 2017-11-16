package com.itheima.bos.service.impl;

import com.itheima.base.domain.Standard;
import com.itheima.bos.dao.StandardRepository;
import com.itheima.bos.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 10:54 <br/>
 * Author zzff
 */
@Service
@Transactional
public class StandardServiceImpl implements StandardService {
    @Autowired
    private StandardRepository standardRepository;
    @Override
    public Page<Standard> pageQuery(Pageable pageable) {
         return  standardRepository.findAll(pageable);
    }

    @Override
    public void save(Standard model) {
        Integer maxLength = model.getMaxLength();
        Integer maxWeight = model.getMaxWeight();
        Integer minLength = model.getMinLength();
        Integer minWeight = model.getMinWeight();
        if(maxLength<minLength||maxWeight<minWeight||minLength<0||minWeight<0){
            return;
        }
        standardRepository.save(model);
    }

    @Override
    public List<Standard> findAll() {
        return standardRepository.findAll();
    }
}
