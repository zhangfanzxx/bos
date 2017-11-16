package com.itheima.bos.service.impl;

import com.itheima.base.domain.TakeTime;
import com.itheima.bos.dao.TakeTimeRepository;
import com.itheima.bos.service.TakeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/11 10:27 <br/>
 * Author zzff
 */
@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {
    @Autowired
    private TakeTimeRepository takeTimeRepository;


    @Override
    public Page<TakeTime> pageQuery(Specification<TakeTime> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<TakeTime> findAllIsCanUse() {
        return null;
    }

    @Override
    public List<TakeTime> findAll() {
        return takeTimeRepository.findAll();
    }

    @Override
    public void save(TakeTime model) {
        takeTimeRepository.save(model);
    }

    @Override
    public void delTakeTime(String ids) {
        String[] split = ids.split(",");
        try{
            for (String s: split) {
                takeTimeRepository.delete(Long.parseLong(s));
            }
        }catch (Exception e){
        }
    }
}
