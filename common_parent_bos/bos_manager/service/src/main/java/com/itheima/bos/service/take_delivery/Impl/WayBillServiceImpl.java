package com.itheima.bos.service.take_delivery.Impl;

import com.itheima.bos.dao.take_delivery.WayBillRepository;
import com.itheima.bos.service.take_delivery.WayBillService;
import com.itheima.take_delivery.domain.WayBill;
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
 * Date:2017/11/13 9:47 <br/>
 * Author zzff
 */
@Service
@Transactional
public class WayBillServiceImpl implements WayBillService {
    @Autowired
    private WayBillRepository wayBillRepository;

    @Override
    public Page<WayBill> pageQuery(Specification<WayBill> specification, Pageable pageable) {
        return wayBillRepository.findAll(specification,pageable);
    }

    @Override
    public List<WayBill> findAllIsCanUse() {
        return null;
    }

    @Override
    public List<WayBill> findAll() {
        return wayBillRepository.findAll();
    }

    @Override
    public void save(WayBill model) {
        wayBillRepository.save(model);
    }

    @Override
    public WayBill findByWayBillNum(String wayBillNum) {
        return wayBillRepository.findByWayBillNum(wayBillNum);
    }

}
