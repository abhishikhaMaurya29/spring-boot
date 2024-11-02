package com.abhishikha.service;

import com.abhishikha.model.Employee;
import com.abhishikha.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findByExample(String role) {
        Employee exampleEmployee = new Employee();
        exampleEmployee.setRole(role);

        Example<Employee> example = Example.of(exampleEmployee);
        return employeeRepository.findAll(example);
    }

    public List<Employee> findByFirstNameExample(String firstName) {
        Employee exampleEmployee = new Employee();
        exampleEmployee.setFirstName(firstName);

        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("id")
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.ignoreCase().contains());

        Example<Employee> example = Example.of(exampleEmployee, matcher);

        return employeeRepository.findAll(example);
    }

    public List<Employee> findEmployeeByFirstNameAndRole(String firstName, String role) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setRole(role);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "isActiveEmployee")
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("role", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Employee> example = Example.of(employee, matcher);
        return employeeRepository.findAll(example);
    }
}
