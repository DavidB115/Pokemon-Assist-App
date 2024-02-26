package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao auctionDao;

    public AuctionController() {
        this.auctionDao = new MemoryAuctionDao();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Auction> listOfAuctions(@RequestParam(value = "title_like", defaultValue = "") String title){
        if(!title.isEmpty()){
            return auctionDao.getAuctionsByTitle(title);
        }else {
            return auctionDao.getAuctions();
        }
    }

}
