package com.abhishikha.controller;

import com.abhishikha.repository.EmployeeRepository;
import com.abhishikha.assembler.EmployeeModelAssembler;
import com.abhishikha.exception.EmployeeNotFoundException;
import com.abhishikha.model.Employee;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler assembler;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler assembler) {
        this.employeeRepository = employeeRepository;
        this.assembler = assembler;
    }

//    @GetMapping("/employees")
//    public List<Employee> allEmployees() {
//        return employeeRepository.findAll();
//    }

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> allEmployees() {
//        List<EntityModel<Employee>> employees = employeeRepository.findAll()
//                .stream()
//                .map(employee -> EntityModel.of(employee,
//                        linkTo(methodOn(EmployeeController.class).getEmployeeById(employee.getId())).withSelfRel(),
//                        linkTo(methodOn(EmployeeController.class).allEmployees()).withRel("employees"))).toList();
//        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).allEmployees()).withSelfRel());

        List<EntityModel<Employee>> employees = employeeRepository.findAll()
                .stream().map(assembler::toModel)
                .toList();

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).allEmployees()).withSelfRel());
    }

//    @PostMapping("/employees")
//    public Employee newEmployee(@RequestBody Employee newEmployee) {
//        return employeeRepository.save(newEmployee);
//    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
        EntityModel<Employee> entityModel = assembler.toModel(employeeRepository.save(newEmployee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> getEmployeeById(@PathVariable Long id) {
//        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
//        return EntityModel.of(employee, linkTo(methodOn(EmployeeController.class).getEmployeeById(id)).withSelfRel(),
//                linkTo(methodOn(EmployeeController.class).allEmployees()).withRel("employees"));
        return assembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        Employee updatedEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(newEmployee);
                }).orElseGet(() -> employeeRepository.save(newEmployee));

        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
