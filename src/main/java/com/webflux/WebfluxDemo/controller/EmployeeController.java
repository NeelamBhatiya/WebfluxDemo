package com.webflux.WebfluxDemo.controller;

import com.webflux.WebfluxDemo.entity.EmployeeEntity;
import com.webflux.WebfluxDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
@Autowired
    private EmployeeService employeeService;
    @GetMapping
    public Flux<EmployeeEntity> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Mono<EmployeeEntity> getEmployeeById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/list")
    public List<EmployeeEntity> getEmployeesList(){
        return employeeService.getEmployeesList();
    }

    @PostMapping
    public Mono<EmployeeEntity> saveEmployees(@RequestBody EmployeeEntity employeeEntity){
        return  employeeService.saveEmployee(employeeEntity);

    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEmployeeById(@PathVariable Integer id){
        return employeeService.deleteEmployeeById(id);
    }

}
