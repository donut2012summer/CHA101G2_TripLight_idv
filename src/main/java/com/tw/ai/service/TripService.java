package com.tw.ai.service;


import com.google.gson.Gson;
import com.tw.ai.common.GetMethod;
import com.tw.ai.dao.TripDAO;
import com.tw.ai.dao.TripImgDAO;
import com.tw.ai.entity.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements GetMethod {


    private final TripDAO tripDAO;
    private final TripImgDAO tripImgDAO;

    @Autowired
    public TripService(TripDAO tripDAO,TripImgDAO tripImgDAO) {
        this.tripDAO = tripDAO;
        this.tripImgDAO = tripImgDAO;
    }

    public String getTrip(String destination){
        List<Trip> result = tripDAO.findByCityContaining(destination);
        for(var trip:result){
            var tripImg = tripImgDAO.findByTripId(1);  // TODO:這邊要改
            byte[] image = tripImg.get(0).getImage();  // 獲得圖片byte[]
            var url = convertBlobToUrl(image);
            // 將圖片byte轉成url
            trip.setImgUrl(url);
        }
        var gson = new Gson();
        return gson.toJson(result);
    }


}