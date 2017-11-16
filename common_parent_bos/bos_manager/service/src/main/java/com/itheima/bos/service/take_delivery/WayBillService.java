package com.itheima.bos.service.take_delivery;

import com.itheima.take_delivery.domain.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 9:47 <br/>
 * Author zzff
 */
public interface WayBillService {
    Page<WayBill> pageQuery(Specification<WayBill> specification, Pageable pageable);

    List<WayBill> findAllIsCanUse();

    List<WayBill> findAll();

    void save(WayBill model);

    WayBill findByWayBillNum(String wayBillNum);
}
