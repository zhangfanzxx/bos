package com.itheima.bos.dao.take_delivery;

import com.itheima.take_delivery.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/11 18:01 <br/>
 * Author zzff
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByOrderNum(String orderNum);
}
