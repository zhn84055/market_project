package com.market.dao;

import com.market.domain.Traveller;

import java.util.List;

public interface TravellerDao {
    /**
     * 根据TravellerId查找Travellers
     * @return
     */
    List<Traveller> findTravellersById();
}
