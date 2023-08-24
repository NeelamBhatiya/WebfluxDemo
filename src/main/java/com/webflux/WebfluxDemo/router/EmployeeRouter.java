package com.webflux.WebfluxDemo.router;


import com.webflux.WebfluxDemo.handler.EmployeeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class EmployeeRouter {
    @Autowired
    private EmployeeHandler employeeHandler;
@Bean
    public RouterFunction<ServerResponse> routerFunction (){
        return RouterFunctions.route()
                .GET("/emp",employeeHandler::getEmployees)
                .GET("/emp/{id}",employeeHandler::getEmployeeById)
                .POST("/emp",employeeHandler::saveEmployee)
                .DELETE("/emp/{id}",employeeHandler::deleteEmployeeById)
                .build();

}
}
