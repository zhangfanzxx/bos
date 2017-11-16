package com.itheima.bos.dao;

import com.itheima.base.domain.FixedArea;
import com.itheima.base.domain.SubArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/9 19:55 <br/>
 * Author zzff
 */
public interface SubAreaRepository extends JpaRepository<SubArea,Long>,JpaSpecificationExecutor<SubArea> {


    List<SubArea> findByFixedAreaIsNull();

    List<SubArea> findByFixedArea(FixedArea fixedArea);

    @Modifying
    @Query("update SubArea set fixedArea = null where fixedArea=?1")
    void setSubAreaFixedAreaBeNullByFixedArea(FixedArea fixedArea);
    @Modifying
    @Query("update SubArea set fixedArea = ?1 where id=?2")
    void updateSubAreaByFixedAreaAndSubAreaId(FixedArea fixedArea上, Long sid);
}
