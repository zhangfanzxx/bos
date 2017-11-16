package com.itheima.bos.service.impl;

import com.itheima.base.domain.Courier;
import com.itheima.base.domain.FixedArea;
import com.itheima.base.domain.TakeTime;
import com.itheima.bos.dao.CourierRepository;
import com.itheima.bos.dao.FixedAreaRepository;
import com.itheima.bos.dao.TakeTimeRepository;
import com.itheima.bos.service.FixedAreaService;
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
 * Date:2017/11/10 20:45 <br/>
 * Author zzff
 */
@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
    @Autowired
    private FixedAreaRepository fixedAreaRepository;
    @Autowired
    private TakeTimeRepository takeTimeRepository;
    @Autowired
    private CourierRepository courierRepository;

    @Override
    public Page<FixedArea> pageQuery(Specification<FixedArea> specification, Pageable pageable) {
        return fixedAreaRepository.findAll(specification,pageable);
    }

    @Override
    public List<FixedArea> findAllIsCanUse() {
        return null;
        // return fixedAreaRepository.findAllIsCanUse();
    }

    @Override
    public List<FixedArea> findAll() {
        return fixedAreaRepository.findAll();
    }

    @Override
    public void save(FixedArea model) {
        fixedAreaRepository.save(model);
    }

    @Override
    public void associationCourierToFixedArea(Long id, Long courierId, Long takeTimeId) {
        FixedArea fixedArea = fixedAreaRepository.findOne(id);
        TakeTime takeTime = takeTimeRepository.findOne(takeTimeId);
        Courier courier = courierRepository.findOne(courierId);
        fixedArea.getCouriers().add(courier);
        courier.setTakeTime(takeTime);
    }
}
