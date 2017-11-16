package com.itheima.dao;

import com.itheima.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.ws.rs.QueryParam;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 21:08 <br/>
 * Author zzff
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT fixedAreaId from  Customer where address=?1")
    Long findFixedAreaIdByAddress(String address);

}
