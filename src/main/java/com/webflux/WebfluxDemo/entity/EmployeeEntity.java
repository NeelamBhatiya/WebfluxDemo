package com.webflux.WebfluxDemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("emp")
public class EmployeeEntity {
    @Id
    private Integer id;
    @NonNull
    private String name;

}
