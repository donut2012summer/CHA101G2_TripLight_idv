package com.tw.itinerary.model.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tw.itinerary.model.Trip;
@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    /*
    * Search from requestDTO in tripName, city and tripTypeName
    * @param keyword, tripDayTypes, cities
    * */
    @Query("""
    SELECT t FROM Trip t WHERE t.tripName LIKE %:keyword%
    AND t.city IN :cities AND t.tripType.tripTypeName IN :tripDayTypes ORDER BY t.tripId
    """)
    List<Trip> searchTripsByKeywords(
            @Param("keyword") String keyword,
            @Param("tripDayTypes") String[] types,
            @Param("cities") String[] cities
    );


    /*
    * Search and order by price
    * */
    List<Trip> findAllByOrderByPriceAdultDesc();

}
