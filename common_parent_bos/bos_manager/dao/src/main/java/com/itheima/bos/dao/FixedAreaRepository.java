package com.itheima.bos.dao;

import com.itheima.base.domain.FixedArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/10 20:46 <br/>
 * Author zzff
 */
public interface FixedAreaRepository extends JpaRepository<FixedArea,Long>,JpaSpecificationExecutor<FixedArea> {
}
