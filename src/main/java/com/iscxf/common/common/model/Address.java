package com.iscxf.common.common.model;

import lombok.Data;

/**
 * @author chenxf
 * Created on 2020/4/3
 */
@Data
public class Address implements Comparable<Address>{

    private Integer id;

    private String country;

    private String province;

    private String city;

    /**
     * 重写compareTo方法，根据id排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Address o) {
        return this.getId().compareTo(o.getId());
    }

//    /**
//     * 重写compareTo方法，根据country排序
//     * @param o
//     * @return
//     */
//    @Override
//    public int compareTo(Address o) {
//        return this.getCountry().compareTo(o.getCountry());
//    }

}
