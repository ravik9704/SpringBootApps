package com.controler;

import com.data.Employee;
import org.springframework.web.bind.annotation.RequestMapping;

public class EmployeControler {

    @RequestMapping("/")
    public String getEmployeData(){
        Employee employee = new Employee();
        employee.setEmpId("12345");
        employee.setLocation("Manchester");
        return "Hello World";
    }
}
