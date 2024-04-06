package com.prueba.backend_prueba.dto;

import com.prueba.backend_prueba.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private String nit;
    private String company_name;
    private String address;
    private String phone;

    public static CompanyDTO fromEntity(Company company) {
        return new CompanyDTO(
                company.getNit(),
                company.getCompanyName(),
                company.getAddress(),
                company.getPhone()
        );
    }

    public static Company toEntity(CompanyDTO companyDTO) {
        return new Company(
                companyDTO.getNit(),
                companyDTO.getCompany_name(),
                companyDTO.getAddress(),
                companyDTO.getPhone()
        );
    }
}
