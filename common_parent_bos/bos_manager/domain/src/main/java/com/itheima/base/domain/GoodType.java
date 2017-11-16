package com.itheima.base.domain;

import javax.persistence.*;

/**
 * ClassName:common_parent_bos <br/>
 * Function: <br/>
 * Date:2017/11/13 19:03 <br/>
 * Author zzff
 */
@Entity
@Table(name = "T_GOODTYPE")
public class GoodType {
    @Id
    @GeneratedValue
    @Column(name = "G_ID")
    private Long id;
    @Column(name = "G_NAME")
    private String Name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
