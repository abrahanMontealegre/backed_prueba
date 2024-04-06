package com.prueba.backend_prueba.service;

import com.prueba.backend_prueba.exeption.company.CompanyNotFoundException;
import com.prueba.backend_prueba.exeption.company.DuplicateNitException;
import com.prueba.backend_prueba.model.Company;
import com.prueba.backend_prueba.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanys() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with ID: " + id));
    }

    public Company getCompanyByNit(String nit) {
        return companyRepository.findByNit(nit)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with NIT: " + nit));
    }

    public Company createCompany(Company company) {
        Optional<Company> existingCompany = companyRepository.findByNit(company.getNit());
        if (existingCompany.isPresent()) {
            throw new DuplicateNitException("NIT already exists: " + company.getNit());
        }
        return companyRepository.save(company);
    }

    public Company updateEmpresa(String nit, Company companyUpdates) {
        Company company = getCompanyByNit(nit);
        company.setCompanyName(companyUpdates.getCompanyName());
        company.setAddress(companyUpdates.getAddress());
        company.setPhone(companyUpdates.getPhone());
        return companyRepository.save(company);
    }

    public void deleteEmpresa(UUID id) {
        Company empresa = getCompanyById(id);
        companyRepository.delete(empresa);
    }
}
