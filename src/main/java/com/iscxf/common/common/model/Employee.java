package com.iscxf.common.common.model;

import lombok.Data;

/**
 * @author CXF
 * Created on 2020/7/18
 */
@Data
public class Employee {

    private String employeeNo;

    private String department;

    private String name;

    private Integer status;


    @Override
    public boolean equals(Object o) {
        if (o instanceof Employee) {
            Employee employee = (Employee) o;
            return this.employeeNo.equals(employee.employeeNo)
                    && this.department.equals(employee.department)
                    && this.name.equals(employee.name)
                    && this.status.equals(employee.status);
        }
        return super.equals(o);
    }

}
