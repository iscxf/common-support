package com.iscxf.common.controller;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author CXF
 * Created on 2020/7/18
 */
public class SyncService {

    @Data
    static class Employee {
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

    public void syncEmployee(List<Employee> allEmployees){
        //从数据库拿出之前同步的数据
        List<Employee> oldEmployees = new ArrayList<>();
        oldEmployees.add(new Employee());

        Map<String, Employee> map = allEmployees.stream().collect(Collectors.toMap(Employee::getEmployeeNo, p -> p , (x, y) -> x));
        //更新的
        List<Employee> updateEmployees = new ArrayList<>();
        //离职的
        List<Employee> delEmployees = new ArrayList<>();
        //新增的
        List<Employee> addEmployees = new ArrayList<>();
        for (Employee e : oldEmployees){
            if (!map.containsKey(e.getEmployeeNo())){
                addEmployees.add(map.get(e.getEmployeeNo()));
                continue;
            }
            if (!oldEmployees.contains(e)){
                if (e.getStatus() == 0){
                    delEmployees.add(e);
                }else {
                    updateEmployees.add(e);
                }
            }
        }
        //数据库操作。。。
    }
}
