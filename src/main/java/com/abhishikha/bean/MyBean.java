package com.abhishikha.bean;

import com.abhishikha.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class MyBean {
    public String getFullNameViaBean(Employee employee){
        return employee.getFirstName().concat(" ").concat(employee.getLastName());
    }
}
