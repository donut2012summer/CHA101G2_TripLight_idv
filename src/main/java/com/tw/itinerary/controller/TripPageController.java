package com.tw.itinerary.controller;

import com.google.gson.Gson;
import com.tw.trip.dao.TourGroupDao;
import com.tw.trip.dao.TripDao;
import com.tw.itinerary.model.TourGroup;
import com.tw.itinerary.model.Trip;
import com.tw.itinerary.model.TripComment;
import com.tw.itinerary.service.TripPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tripPage")
public class TripPageController {

    @Autowired
    TripPageService tripPageService;

    @Autowired
    TourGroupDao tourGroupDao;

    @Autowired
    TripDao tripDao;



    @GetMapping("/getTourGroup")
    public String getTourGroup(@RequestParam Integer tripId){

        List<TourGroup> tourGroupList = tripPageService.getTourGroupWithDates(tripId);

            String json = new Gson().toJson(tourGroupList);
            return json;



    }




    @GetMapping ("/getTripPics")
    public String getTripPics(@RequestParam Integer tripId){

        List<Integer> tripImageList = tripPageService.getTripPicsById(tripId);

        Gson gson = new Gson();
        String json = gson.toJson(tripImageList);

        return json;
    }

    @GetMapping("/getTrip")
    public String getTrip(@RequestParam Integer tripId){

        Trip trip = tripPageService.getTripByTripId(tripId);

        String json = new Gson().toJson(trip);

        return json;
    }

    @GetMapping("/getTripComments")
    public String getTripComments(@RequestParam Integer tripId){

        List<TripComment> tripCommentList = tripPageService.getTripCommentsByTripId(tripId);

        Gson gson = new Gson();
        String json = gson.toJson(tripCommentList);

        return json;
    }

    /*
    * 評論需要的資訊
    *
    *
    * */



}
