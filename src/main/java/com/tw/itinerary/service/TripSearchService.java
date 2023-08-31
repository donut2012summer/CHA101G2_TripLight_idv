package com.tw.itinerary.service;

import com.tw.itinerary.controller.TripSearchController;
import com.tw.itinerary.controller.TripSearchController.DescDTO;
import com.tw.itinerary.model.repository.TripRepository;
import com.tw.itinerary.model.Trip;

import com.tw.trip.service.TripImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripSearchService {

        @Autowired
        TripImageService tripImageService;

        @Autowired
        TripRepository tripRepository;

        /*
        * Trips for default page
        * imgUrl should be dealt with
        * */
        public List<DescDTO> getTrips(){
            // findAll in DB and return page obj for data sourced from DB
            List<Trip> trips = tripRepository.findAll();

            // List for response DescDTO
            List<DescDTO> descDTO = new ArrayList<>();

            // iterate trips and store in the DTO
            for ( Trip trip : trips){

                // initialize DTO with trip arg
                DescDTO dto = new DescDTO(trip);

                // set the imageUrl by trip ID
                // imgUrl deal by TripImageService
                dto.setImageUrl(tripImageService.findImgUrl(trip.getTripId()));

                // add in the arraylist descDTO for return
                descDTO.add(dto);

            }

            return descDTO;
        }

        /*
        * Search Trips by keyword, tripDayType and cities
        * imgUrl should be dealt with
        * @param ReqDTO
        * */
        public List<DescDTO> getTripsByKeywords(final TripSearchController.ReqDto reqDto){
            // List for return obj
            List<DescDTO> descDTOS = new ArrayList<>();

            // List for data sourced from DB
            List<Trip> trips = tripRepository.searchTripsByKeywords(
                    reqDto.getKeyword(),
                    reqDto.getTripDayTypes(),
                    reqDto.getCities()
            );

            // iterate the data and store in DTO
            for(Trip trip : trips){

                // DTO to store the trip
                DescDTO dto = new DescDTO(trip);

                // deal with the img
                dto.setImageUrl(tripImageService.findImgUrl(trip.getTripId()));

                // add DTO to the return DTOs
                descDTOS.add(dto);
            }

            return descDTOS;

        }

        /*
        * Get Trips Order By Price
        * imgUrl should be dealt with
         * */
        public List<DescDTO> getTripsOrderByPrice(){
            // List DTOs to store result
            List<DescDTO> descDTOS = new ArrayList<>();

            // List for retrieving data from DB
            List<Trip> trips = tripRepository.findAllByOrderByPriceAdultDesc();

            // iterate data from DB and store in DTO
            for (Trip trip : trips){

                DescDTO dto = new DescDTO(trip);

                dto.setImageUrl(tripImageService.findImgUrl(trip.getTripId()));

                descDTOS.add(dto);

            }
            return descDTOS;
        }

        /* end */
}
