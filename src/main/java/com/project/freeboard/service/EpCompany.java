package com.project.freeboard.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jdo.annotations.Transactional;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.project.freeboard.dao.CompaniesDAO;
import com.project.freeboard.entity.Companies;

@Api(name = "freeboard", version = "v1", namespace = @ApiNamespace(ownerDomain = "service.freeboard.project.com", ownerName = "service.freeboard.project.com", packagePath = ""))
public class EpCompany {

	private CompaniesDAO cDAO;
	
	/**
	 * API Company Entity
	 */
	@ApiMethod(name = "signUpCompany", path = "signUpCompany", httpMethod = ApiMethod.HttpMethod.POST)
	public Companies signUpCompany(@Named("email") String email, @Named("name") String name, @Named("phone") String phone,
			@Named("address") String address, @Named("password") String password,
			@Named("contactPerson") String contactPerson) throws NotFoundException {

		cDAO = new CompaniesDAO();
		String hash = UUID.randomUUID().toString().replaceAll("-", "");
		Date created = Calendar.getInstance().getTime();
		Companies company = new Companies(email, name, phone, address, password, contactPerson, hash, created);
		if (cDAO.addCompany(company)) {
			return company;
		} else {
			throw new NotFoundException("Company not added.");
		}
	}

	@ApiMethod(name = "updateCompany", path = "updateCompany", httpMethod = ApiMethod.HttpMethod.PUT)
	public Companies updateCompany(Companies c) throws NotFoundException {
		cDAO = new CompaniesDAO();
		Date updated = Calendar.getInstance().getTime();
		c.setUpdated(updated);
		if (cDAO.updateCompanie(c)) {
			return c;
		} else {
			throw new NotFoundException("Company doesn't exist.");
		}
	}

	@Transactional
	@ApiMethod(name = "removeCompany", path = "removeCompany/{email}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removeCompany(@Named("email") String email) throws NotFoundException {
		cDAO = new CompaniesDAO();
		if (!cDAO.removeCompanie(email)) {
			throw new NotFoundException("Company doesn't exist.");
		}
	}

	@ApiMethod(name = "getAllCompanies", path = "companies", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Companies> getCompanies() {

		cDAO = new CompaniesDAO();
		List<Companies> companies = cDAO.getCompanies();

		return companies;
	}

	@ApiMethod(name = "getCompanyByNIT", path = "companiesByNIT/{nit}", httpMethod = ApiMethod.HttpMethod.GET)
	public Companies getCompanyByNIT(@Named("nit") String nit) throws NotFoundException {
		cDAO = new CompaniesDAO();
		Companies companies = cDAO.getCompanieById(nit);
		if (companies != null) {
			return companies;
		} else {
			throw new NotFoundException("Company doesn't exist.");
		}
	}

	@ApiMethod(name = "getCompanyByName", path = "companiesByName/{name}", httpMethod = ApiMethod.HttpMethod.GET)
	public Companies getCompanyByName(@Named("name") String name) throws NotFoundException {
		cDAO = new CompaniesDAO();
		Companies companies = cDAO.getCompanieByName(name);
		if (companies != null) {
			return companies;
		} else {
			throw new NotFoundException("Company doesn't exist.");
		}
	}
}
