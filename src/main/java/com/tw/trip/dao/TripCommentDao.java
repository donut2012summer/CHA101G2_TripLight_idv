package com.tw.trip.dao;

import com.tw.itinerary.model.TripComment;

import java.util.List;

public interface TripCommentDao {
    public void insert(TripComment tripComment);
    public void update(TripComment tripComment);
    public void deleteById(Integer id);
    public TripComment findByPrimaryKey(Integer id);
    public List<TripComment> getAll();

}
