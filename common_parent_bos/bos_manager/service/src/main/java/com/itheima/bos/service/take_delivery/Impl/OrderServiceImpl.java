package com.itheima.bos.service.take_delivery.Impl;

import com.itheima.base.domain.Area;
import com.itheima.base.domain.Courier;
import com.itheima.base.domain.FixedArea;
import com.itheima.base.domain.SubArea;
import com.itheima.bos.dao.AreaRepository;
import com.itheima.bos.dao.FixedAreaRepository;
import com.itheima.bos.dao.take_delivery.OrderRepository;
import com.itheima.bos.dao.take_delivery.WorkBillRepository;
import com.itheima.bos.service.take_delivery.OrderService;
import com.itheima.take_delivery.domain.Order;
import com.itheima.take_delivery.domain.WorkBill;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.omg.CORBA.ORB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/11 18:00 <br/>
 * Author zzff
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FixedAreaRepository fixedAreaRepository;
    @Autowired
    private WorkBillRepository workBillRepository;
    @Override
    public void addOrder(Order order) {
        Area sendArea = order.getSendArea();
        if(sendArea!=null){
            Area sendAreaDB = areaRepository.findByAndProvinceAndCityAndDistrict(sendArea.getProvince(), sendArea.getCity(), sendArea.getDistrict());
            order.setSendArea(sendAreaDB);
        }
        Area recArea = order.getRecArea();
        if(recArea!=null){
            Area recAreaDB = areaRepository.findByAndProvinceAndCityAndDistrict(recArea.getProvince(), recArea.getCity(), recArea.getDistrict());
            order.setRecArea(recAreaDB);
        }
        order.setOrderTime(new Date());
        order.setOrderType("人工调度");
        order.setOrderNum(RandomStringUtils.randomNumeric(32));
        orderRepository.save(order);
        //根据客户的详细地址去查找
        String address = order.getSendAddress();
        Long fixedAreaId = WebClient.create("http://localhost:8020/crm/webservice/customerService/findFixedAreaIdByAddress")
                .encoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .query("address", address)
                .get(Long.class);
        if(fixedAreaId!=null&&fixedAreaId!=0){
            setOrderFixedArea(fixedAreaRepository.findOne(fixedAreaId),order);
            return;
        }
        //根据分区关键字去查找
        if(order.getSendArea()!=null){
            Set<SubArea> subareas = order.getSendArea().getSubareas();
            for (SubArea s: subareas) {
                if(address.contains(s.getKeyWords())||address.contains(s.getAssistKeyWords())){
                    setOrderFixedArea(s.getFixedArea(),order);
                    return;
                }
            }
        }

    }

    @Override
    public Page<Order> pageQuery(Specification<Order> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<Order> findAllIsCanUse() {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findByOrderNum(String orderNum) {
        return orderRepository.findByOrderNum(orderNum);
    }


    private void setOrderFixedArea(FixedArea fixedArea,Order order) {
        Set<Courier> couriers = fixedArea.getCouriers();
        if(couriers.size()>0){
            Courier courier = couriers.iterator().next();
            order.setCourier(courier);
            order.setOrderType("自动分配");
            WorkBill workBill =new WorkBill();
            order.getWorkBills().add(workBill);

            workBill.setAttachbilltimes(0);
            workBill.setBuildtime(new Date());
            workBill.setCourier(courier);
            workBill.setOrder(order);
            workBill.setRemark(order.getRemark());
            workBill.setSmsNumber("123");
            workBill.setPickstate("待取");
            workBill.setType("新");
            workBillRepository.save(workBill);
        }
    }
}
