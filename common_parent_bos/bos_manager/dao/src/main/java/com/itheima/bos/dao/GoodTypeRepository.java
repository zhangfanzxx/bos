package com.itheima.bos.dao;

import com.itheima.base.domain.Area;
import com.itheima.base.domain.GoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 19:55 <br/>
 * Author zzff
 */
public interface GoodTypeRepository extends JpaRepository<GoodType,Long>,JpaSpecificationExecutor<GoodType> {
}
