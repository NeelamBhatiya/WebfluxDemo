package com.webflux.WebfluxDemo.handler;

import com.webflux.WebfluxDemo.entity.EmployeeEntity;
import com.webflux.WebfluxDemo.service.EmployeeService;
import com.webflux.WebfluxDemo.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Component
public class EmployeeHandler {
    @Autowired
    private EmployeeValidator employeeValidator;

    @Autowired
    private EmployeeService employeeService;

    public Mono<ServerResponse> getEmployees(ServerRequest serverRequest) {
        Flux<EmployeeEntity> employeeEntityFlux = employeeService.getEmployees();
        return ServerResponse.
                ok().body(employeeEntityFlux, EmployeeEntity.class);

    }
    public Mono<ServerResponse> saveEmployee(ServerRequest serverRequest) {
        System.out.println("In saveEmp");
        Mono<EmployeeEntity> mono = serverRequest.bodyToMono(EmployeeEntity.class);
            return mono.flatMap((empMono -> {
                return Mono.just(empMono)
                        .then(employeeValidator.validateInputAgainstSchema(empMono))
                        .flatMap(emp -> employeeService.saveEmployee(emp).then(Mono.just(emp)))
                        .flatMap(employee->
                                ServerResponse.created(serverRequest.uri())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(Mono.just(employee), EmployeeEntity.class)
                        );
            })).switchIfEmpty(Mono.error(new Exception("Request Body not found")));

        //    return ServerResponse.ok().body(mono.flatMap(emp-> employeeService.saveEmployee(emp)), EmployeeEntity.class);
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
