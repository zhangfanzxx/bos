package com.itheima.bos.dao.system;

import com.itheima.system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName:${CLASS_NAME} <br/>
 * Function: <br/>
 * Date:2017/11/14 19:30 <br/>
 * Author zzff
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
