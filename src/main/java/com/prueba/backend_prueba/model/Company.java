package com.prueba.backend_prueba.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Company {
    @Id
    @Column(length = 20)
    private String nit;

    @Column(length = 100, nullable = false)
    private String companyName;

    @Column(length = 200, nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String phone;

    public Company(String nit, String companyName, String address, String phone) {
        this.nit = nit;
        this.companyName = companyName;
        this.address = address;
        this.phone = phone;
    }

    public Company() {
    }
}

