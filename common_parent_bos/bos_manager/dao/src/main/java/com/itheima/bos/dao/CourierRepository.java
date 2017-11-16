package com.itheima.bos.dao;

import com.itheima.base.domain.Courier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 11:27 <br/>
 * Author zzff
 */
public interface CourierRepository extends JpaRepository<Courier,Long>,
        JpaSpecificationExecutor<Courier> {

    List<Courier> findByDeltagIsNull();

    @Modifying
    @Query("update Courier set deltag=?1 where id=?2")
    void updateDeltagById(Character deltag, Long id);
}
