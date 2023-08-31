package com.tw.itinerary.controller;

import java.util.List;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.tw.itinerary.model.Trip;
import com.tw.itinerary.service.TripSearchService;

@RestController
@RequestMapping("/tripSearch")
public class TripSearchController {

    @Autowired
    TripSearchService tripSearchService;

    /*
    * Default Trips in the Search Page
    * @param page index
    * */
    @GetMapping("/default")
    public List<DescDTO> searchDefault(){
        return tripSearchService.getTrips();
    }

    /*
    * Search by keyword, trip type and cities
    * @Param ReqDto
    * */
    @PostMapping("/search")
    public List<DescDTO> searchTrips(@RequestBody final ReqDto reqDto){
        return tripSearchService.getTripsByKeywords(reqDto);
    }

    /*
    * Order Trips By Prices
    * */
    @GetMapping("/orderByPrice")
    public List<DescDTO> getTripListOrderByPrice() {
        return tripSearchService.getTripsOrderByPrice();
    }

    /*
    * Request DTO
    * */
    @Data
    public static class ReqDto {
        private String keyword;
        private String[] tripDayTypes;
        private String[] cities;
    }

    /*
     * Response DTO
     */
    @Data
    public static class DescDTO{
        private int tripId;
        private String tripName;
        private int tripDay;
        private String city;
        private String tripContent;
        private String imageUrl;

        // Constructor: without imageUrl (url handled in service
        public DescDTO (final Trip trip) {
            this.tripId = trip.getTripId();
            this.tripName = trip.getTripName();
            this.tripDay = trip.getTripDay();
            this.city = trip.getCity();
            this.tripContent = trip.getTripContent();
        }

    }

    /* end */
}
