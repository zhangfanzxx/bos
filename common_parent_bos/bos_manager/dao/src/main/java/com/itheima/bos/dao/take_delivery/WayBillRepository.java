package com.itheima.bos.dao.take_delivery;

import com.itheima.take_delivery.domain.WayBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 9:48 <br/>
 * Author zzff
 */
public interface WayBillRepository extends JpaRepository<WayBill,Long> ,JpaSpecificationExecutor<WayBill>{

    WayBill findByWayBillNum(String wayBillNum);
}
