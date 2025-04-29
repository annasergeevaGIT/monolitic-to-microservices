package com.mono_to_micro.app.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void createCompany(Company company);

    boolean updateCompany(Company company, Long id);

    boolean deleteCompanyById(Long id);
}
