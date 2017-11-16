package com.itheima.bos.service.take_delivery;

import com.itheima.take_delivery.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/11 17:59 <br/>
 * Author zzff
 */
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public interface OrderService  {
    @POST
    @Path("/order")
    public void addOrder(Order order);

    Page<Order> pageQuery(Specification<Order> specification, Pageable pageable);

    List<Order> findAllIsCanUse();

    List<Order> findAll();

    Order findByOrderNum(String orderNum);
}
