package com.webflux.WebfluxDemo.handler;

import com.webflux.WebfluxDemo.entity.EmployeeEntity;
import com.webflux.WebfluxDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;

    public Mono<ServerResponse> getEmployees(ServerRequest serverRequest) {
        Flux<EmployeeEntity> employeeEntityFlux = employeeService.getEmployees();
        return ServerResponse.
                ok().body(employeeEntityFlux, EmployeeEntity.class);

    }
    public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest) {
        Mono<EmployeeEntity> mono = serverRequest.bodyToMono(EmployeeEntity.class);
        return ServerResponse.ok().body(mono.flatMap(emp-> employeeService.saveEmployee(emp)), EmployeeEntity.class);
    }

    public Mono<ServerResponse> getEmployeeById(ServerRequest serverRequest) {
        Mono<EmployeeEntity> employeeEntityMono = employeeService.getEmployeeById(Integer.valueOf(serverRequest.pathVariable("id")));
        return ServerResponse.ok().body(employeeEntityMono, EmployeeEntity.class);

    }

    public Mono<ServerResponse> deleteEmployeeById(ServerRequest serverRequest) {
        Mono<Void> voidMono = employeeService.deleteEmployeeById(Integer.valueOf(serverRequest.pathVariable("id")));
        return ServerResponse.ok().body(voidMono, Void.class);
    }

}
