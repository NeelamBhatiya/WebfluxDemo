package com.webflux.WebfluxDemo.service;

import com.webflux.WebfluxDemo.entity.EmployeeEntity;
import com.webflux.WebfluxDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Flux<EmployeeEntity> getEmployees() {
        return employeeRepository.findAll();
    }

    public Mono<EmployeeEntity> saveEmployee(EmployeeEntity employeeEntityMono) {
   return employeeRepository.save(employeeEntityMono);

    }

    public Mono<EmployeeEntity> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Mono<Void> deleteEmployeeById(Integer id) {
        return employeeRepository.deleteById(id);
    }


}
