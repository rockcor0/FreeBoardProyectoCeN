package com.project.freeboard.service;

import java.util.List;

import javax.jdo.annotations.Transactional;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.NotFoundException;
import com.project.freeboard.dao.AuctionsDAO;
import com.project.freeboard.entity.Auctions;

@Api(name = "freeboard", version = "v1", namespace = @ApiNamespace(ownerDomain = "service.freeboard.project.com", ownerName = "service.freeboard.project.com", packagePath = ""))
public class EpAuction {

	private AuctionsDAO aDAO;
	
	/**
	 * API Auction Entity
	 */
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

	@ApiMethod(name = "getAuctionById", path = "auctionsById/{id}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionById(@Named("id") String id) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(id);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@ApiMethod(name = "getAuctionByType", path = "auctionsByType/{type}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionByType(@Named("type") String type) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(type);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@ApiMethod(name = "getAuctionByTime", path = "auctionsByTime/{time}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionByTime(@Named("id") String time) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(time);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}

	@ApiMethod(name = "getAuctionByPrice", path = "auctionsByPrice/{price}", httpMethod = ApiMethod.HttpMethod.GET)
	public Auctions getAuctionByPrice(@Named("price") String price) throws NotFoundException {
		aDAO = new AuctionsDAO();
		Auctions auction = aDAO.getAuctionsById(price);
		if (auction != null) {
			return auction;
		} else {
			throw new NotFoundException("Auction doesn't exist.");
		}
	}
}
