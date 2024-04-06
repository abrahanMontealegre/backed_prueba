package com.prueba.backend_prueba.controller;

import com.prueba.backend_prueba.dto.CompanyDTO;
import com.prueba.backend_prueba.model.Company;
import com.prueba.backend_prueba.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyDTO> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanys();
        return companies.stream()
                .map(CompanyDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{nit}")
    public CompanyDTO getCompanyByNit(@PathVariable String nit) {
        Company company = companyService.getCompanyByNit(nit);
        return CompanyDTO.fromEntity(company);
    }

    @PostMapping
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) {
        Company company = companyService.createCompany(CompanyDTO.toEntity(companyDTO));
        return companyDTO.fromEntity(company);
    }

    @PatchMapping
    public CompanyDTO updateCompany(@RequestBody CompanyDTO companyDTO,@PathVariable String nit) {
        Company company = companyService.updateEmpresa(nit,CompanyDTO.toEntity(companyDTO));
        return companyDTO.fromEntity(company);
    }
    // Agrega los métodos PUT, PATCH y DELETE según los requisitos
}