package com.project.freeboard.service;

import java.util.List;

import javax.jdo.annotations.Transactional;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.project.freeboard.dao.OffersDAO;
import com.project.freeboard.entity.Offers;

@Api(name = "freeboard", version = "v1", namespace = @ApiNamespace(ownerDomain = "service.freeboard.project.com", ownerName = "service.freeboard.project.com", packagePath = ""))
public class EpOffer {
	
	private OffersDAO oDAO;
	
	/**
	 * API Offer Entity
	 */
	@ApiMethod(name = "updateOffers", path = "offers", httpMethod = ApiMethod.HttpMethod.PUT)
	public Offers updateOffers(Offers e) throws NotFoundException {
		oDAO = new OffersDAO();
		if (oDAO.updateOffers(e)) {
			return e;
		} else {
			throw new NotFoundException("Offer no existe.");
		}
	}

	@Transactional
	@ApiMethod(name = "removeOffers", path = "offers/{id}", httpMethod = ApiMethod.HttpMethod.DELETE)
	public void removeOffers(@Named("id") String id) throws NotFoundException {
		oDAO = new OffersDAO();
		if (!oDAO.removeOffers(id)) {
			throw new NotFoundException("Offer no existe.");
		}
	}

	@ApiMethod(name = "getAllOffers", path = "offers", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Offers> getOffers() {

		oDAO = new OffersDAO();
		List<Offers> Offers = oDAO.getOffers();

		return Offers;
	}

	@ApiMethod(name = "getOffersById", path = "offers/{id}", httpMethod = ApiMethod.HttpMethod.GET)
	public Offers getOffersById(@Named("id") String id) throws NotFoundException {
		oDAO = new OffersDAO();
		Offers Offers = oDAO.getOffersById(id);
		if (Offers != null) {
			return Offers;
		} else {
			throw new NotFoundException("Offer no existe.");
		}
	}
}
