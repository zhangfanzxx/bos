package com.itheima.bos.service.impl;

import com.itheima.base.domain.Courier;
import com.itheima.bos.dao.CourierRepository;
import com.itheima.bos.service.CourierService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * Date:2017/11/9 11:27 <br/>
 * Author zzff
 */
@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierRepository courierRepository;
    @RequiresPermissions("courier:delete")
    @Override
    public Page<Courier> pageQuery(Specification<Courier> specification, Pageable pageable) {
        return  courierRepository.findAll(specification,pageable);
    }

    @Override
    public List<Courier> findAllIsCanUse() {
        return courierRepository.findByDeltagIsNull();
    }

    @Override
    public List<Courier> findAll() {
        return   courierRepository.findAll();
    }

    @Override
    public void save(Courier model) {
        courierRepository.save(model);
    }

    @Override
    public void delCouriers(String ids, Character deltag) {
        String[] split = ids.split(",");
        if (split!=null){
            for (String s: split) {
                Long id =Long.parseLong(s);
                courierRepository.updateDeltagById(deltag,id);
            }
        }

    }
}
