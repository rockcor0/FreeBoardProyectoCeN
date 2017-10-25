package com.project.freeboard.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Transactional;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.mysql.jdbc.Blob;
import com.project.freeboard.dao.AuctionsDAO;
import com.project.freeboard.dao.CompaniesDAO;
import com.project.freeboard.dao.OffersDAO;
import com.project.freeboard.dao.StudentsDAO;
import com.project.freeboard.entity.Auctions;
import com.project.freeboard.entity.Companies;
import com.project.freeboard.entity.Offers;
import com.project.freeboard.entity.Students;

@Api(name = "freeboard", version = "v1", namespace = @ApiNamespace(ownerDomain = "service.freeboard.project.com", ownerName = "service.freeboard.project.com", packagePath = ""))
// [END echo_api_annotation]
public class Freboard {

	private CompaniesDAO cDAO;

	private StudentsDAO sDAO;

	private AuctionsDAO aDAO;

	private OffersDAO oDAO;

	/**
	 * API Company Entity
	 */
	@ApiMethod(name = "addCompany", path = "addCompany", httpMethod = ApiMethod.HttpMethod.POST)
	public Companies addCompany(@Named("nit") String nit, @Named("name") String name, @Named("phone") String phone,
			@Named("address") String address, @Named("mail") String mail, @Named("password") String password,
			@Named("contactPerson") String contactPerson) throws NotFoundException {

		cDAO = new CompaniesDAO();
		Companies company = new Companies(nit, name, phone, address, mail, password, contactPerson, null);
		if (cDAO.addCompany(company)) {
			return company;
		} else {
			throw new NotFoundException("Company not added.");
		}
	}

	@ApiMethod(name = "updateCompany", path = "updateCompany", httpMethod = ApiMethod.HttpMethod.PUT)
	public Companies updateCompany(Companies c) throws NotFoundException {
		cDAO = new CompaniesDAO();
		if (cDAO.updateCompanie(c)) {
			return c;
		} else {
			throw new NotFoundException("Company doesn't exist.");
		}
	}

	@Transactional
	@ApiMethod(name = "removeCompany", path = "removeCompany/{nit}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removeCompany(@Named("nit") String nit) throws NotFoundException {
		cDAO = new CompaniesDAO();
		if (!cDAO.removeCompanie(nit)) {
			throw new NotFoundException("Company doesn't exist.");
		}
	}

	@ApiMethod(name = "getAllCompanies", path = "companies", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Companies> getCompanies() {

		cDAO = new CompaniesDAO();
		List<Companies> companies = cDAO.getCompanies();

		return companies;
	}

	@ApiMethod(name = "getCompanyByNIT", path = "companies/{nit}", httpMethod = ApiMethod.HttpMethod.GET)
	public Companies getCompanyByNIT(@Named("nit") String nit) throws NotFoundException {
		cDAO = new CompaniesDAO();
		Companies companies = cDAO.getCompanieById(nit);
		if (companies != null) {
			return companies;
		} else {
			throw new NotFoundException("Company doesn't exist.");
		}
	}

	@ApiMethod(name = "getCompanyByName", path = "companies/{nit}", httpMethod = ApiMethod.HttpMethod.GET)
	public Companies getCompanyByName(@Named("name") String name) throws NotFoundException {
		cDAO = new CompaniesDAO();
		Companies companies = cDAO.getCompanieByName(name);
		if (companies != null) {
			return companies;
		} else {
			throw new NotFoundException("Company doesn't exist.");
		}
	}

	/**
	 * API Student Entity
	 */

	@ApiMethod(name = "addStudent", path = "addStudent", httpMethod = ApiMethod.HttpMethod.POST)
	public Students addStudent(@Named("cc") String cc, @Named("name") String name, @Named("email") String email,
			@Named("phone") String phone, @Named("bankWire") String bankWire, @Named("bank") String bank,
			@Named("accountType") String accountType, @Named("university") String university,
			@Named("career") String career, @Named("accountOwner") String titular,
			@Named("experience") String experiencia, @Named("skills") String skills, @Named("password") String password)
			throws NotFoundException {

		sDAO = new StudentsDAO();
//		Students s = new Students(cc, name, email, phone, bankWire, bank, accountType, university, career, titular,
//				experiencia, skills, password);
//		if (sDAO.addStudent(s)) {
//			return s;
//		} else {
			throw new NotFoundException("Student not added.");
//		}
	}

	@ApiMethod(name = "updateStudent", path = "updateStudent", httpMethod = ApiMethod.HttpMethod.PUT)
	public Students updateStudent(Students s) throws NotFoundException {
		sDAO = new StudentsDAO();
		if (sDAO.updateStudent(s)) {
			return s;
		} else {
			throw new NotFoundException("Student doesn't exist.");
		}
	}

	@Transactional
	@ApiMethod(name = "removeStudent", path = "removeStudent/{cc}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removeStudent(@Named("cc") String cc) throws NotFoundException {
		sDAO = new StudentsDAO();
		if (!sDAO.removeStudent(cc)) {
			throw new NotFoundException("Student doesn't exist.");
		}
	}

	@ApiMethod(name = "getAllStudents", path = "students", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Students> getStudents() {

		sDAO = new StudentsDAO();
		List<Students> students = sDAO.getStudents();

		return students;
	}

	@ApiMethod(name = "getStudentByCC", path = "students/{cc}", httpMethod = ApiMethod.HttpMethod.GET)
	public Students getStudentByCC(@Named("cc") String cc) throws NotFoundException {
		sDAO = new StudentsDAO();
		Students students = sDAO.getStudentByCC(cc);
		if (students != null) {
			return students;
		} else {
			throw new NotFoundException("Student doesn't exist.");
		}
	}

	/**
	 * API Auction Entity
	 */

	@ApiMethod(name = "addAuction", path = "addAuction", httpMethod = ApiMethod.HttpMethod.POST)
	public Auctions addAuction(@Named("type") String type, @Named("size") String size,
			@Named("mainColor") String mainColor, @Named("secundaryColor") String secundaryColor,
			@Named("description") String description, @Named("date") Date time, @Named("price") String price,
			@Named("sketch") Blob sketch) throws NotFoundException {

		aDAO = new AuctionsDAO();
		int idauctions = -1;
		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			idauctions = new Integer(prng.nextInt());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

//		Auctions auction = new Auctions(idauctions, type, size, mainColor, secundaryColor, description, time, price,
//				sketch);
//		if (aDAO.addAuctions(auction)) {
//			return auction;
//		} else {
//			throw new NotFoundException("Auction not added.");
//		}
	}

	@ApiMethod(name = "updateAuction", path = "updateAuction", httpMethod = ApiMethod.HttpMethod.PUT)
	public Auctions updateAuction(Auctions a) throws NotFoundException {
		aDAO = new AuctionsDAO();
		if (aDAO.updateAuctions(a)) {
			return a;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@Transactional
	@ApiMethod(name = "removeAuction", path = "removeAuction/{id}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removeAuction(@Named("id") String id) throws NotFoundException {
		aDAO = new AuctionsDAO();
		if (!aDAO.removeAuctions(id)) {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@ApiMethod(name = "getAllAuctions", path = "auctions", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Auctions> getAuctions() {

		aDAO = new AuctionsDAO();
		List<Auctions> auctions = aDAO.getAuctions();

		return auctions;
	}

	@ApiMethod(name = "getAuctionById", path = "auctions/{id}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionById(@Named("id") String id) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(id);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@ApiMethod(name = "getAuctionByType", path = "auctions/{type}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionByType(@Named("type") String type) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(type);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@ApiMethod(name = "getAuctionByTime", path = "auctions/{time}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionByTime(@Named("id") String time) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(time);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@ApiMethod(name = "getAuctionByPrice", path = "auctions/{price}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionByPrice(@Named("price") String price) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(price);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	/**
	 * API Offer Entity
	 */

	@ApiMethod(name = "addoffers", path = "offers", httpMethod = ApiMethod.HttpMethod.POST)
	public Offers addOffers(@Named("price") String price, @Named("students_cc") String students_cc,
			@Named("auctions_idauctions") int auctions_idauctions) throws NotFoundException {

		oDAO = new OffersDAO();
		int idoffers = -1;
		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			idoffers = new Integer(prng.nextInt());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Offers offer = new Offers(idoffers, price, students_cc, auctions_idauctions);
//		if (oDAO.addOffers(offer)) {
//			return offer;
//		} else {
			throw new NotFoundException("Offers no se pudo agregar.");
//		}
	}

	@ApiMethod(name = "updateOffers", path = "offers", httpMethod = ApiMethod.HttpMethod.PUT)
	public Offers updateOffers(Offers e) throws NotFoundException {
		oDAO = new OffersDAO();
		if (oDAO.updateOffers(e)) {
			return e;
		} else {
			throw new NotFoundException("Offers no existe.");
		}
	}

	@Transactional
	@ApiMethod(name = "removeOffers", path = "Offers/{id}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removeOffers(@Named("id") String id) throws NotFoundException {
		oDAO = new OffersDAO();
		if (!oDAO.removeOffers(id)) {
			throw new NotFoundException("Offers no existe.");
		}
	}

	@ApiMethod(name = "getAllOffers", path = "Offerss", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Offers> getOffers() {

		oDAO = new OffersDAO();
		List<Offers> Offers = oDAO.getOffers();

		return Offers;
	}

	@ApiMethod(name = "getOffersById", path = "Offerss/{id}", httpMethod = ApiMethod.HttpMethod.GET)
	public Offers getOffers(@Named("id") String id) throws NotFoundException {
		oDAO = new OffersDAO();
		Offers Offers = oDAO.getOffersById(id);
		if (Offers != null) {
			return Offers;
		} else {
			throw new NotFoundException("Offers no existe.");
		}
	}
}
