package com.tw.trip.service;

import com.tw.itinerary.model.*;
import com.tw.member.model.Member;
import com.tw.trip.dao.TripCommentDao;
import com.tw.trip.dao.TripDao;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class GroupOrderDetailService {

    @PersistenceContext
    Session session;

    @Autowired
    TripDao tripDao;

    @Autowired
    TripCommentDao tripCommentDao;

    public List<TripOrder> getOrderInfor(Integer tripOrderId){
        final String HQL = """
                SELECT new com.tw.trip.pojo.TripOrder(payDate, orderStatus, travelersAdult, travelersChildren, remarks,paymentStatus) FROM TripOrder
                WHERE tripOrderId = :tripOrderId
                """;
        List<TripOrder> resultList = session.createQuery(HQL,TripOrder.class)
                .setParameter("tripOrderId", tripOrderId)
                .getResultList();

        return resultList ;
    }

    public Member getMemberInfor(Integer memberId){
        final String HQL = """
                SELECT new com.tw.member.model.Member(memberNameLast, memberNameFirst, memberPhone) FROM Member
                WHERE memberId= :memberId
                """;
        Member member = session.createQuery(HQL,Member.class)
                .setParameter("memberId", memberId)
                .uniqueResult();

        return member;

    }

    public TourGroup getTourGroupDate(Integer tripId, Integer tourGroupId) {

        final String HQL = """
                SELECT new com.tw.trip.pojo.TourGroup(startDate) FROM TourGroup
                WHERE tourGroupId = :tourGroupId
                """;

        TourGroup tourGroup = session.createQuery(HQL, TourGroup.class)
                .setParameter("tourGroupId", tourGroupId)
                .uniqueResult();

        // get tripDay from trip
        Trip trip = tripDao.findByPrimaryKey(tripId);
        Integer tripDay = trip.getTripDay();
        String tripName = trip.getTripName();

        // get startDate from tour_group
        LocalDate startDate = tourGroup.getStartDate().toLocalDate();

        // calculate days
        LocalDate calculatedDate = startDate.plusDays(tripDay);

        // format days
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = calculatedDate.format(formatter);

        // set and return
        tourGroup.setFormattedStartDate(formattedStartDate);
        tourGroup.setFormattedEndDate(formattedEndDate);
        tourGroup.setTripName(tripName);
        return tourGroup;

       }

    public List<TourGroupDetail> getTravelerList(Integer memberId, Integer tourGroupId){
        final String HQL = """
                FROM TourGroupDetail
                WHERE memberId = :memberId AND tourGroupId = :tourGroupId
                """;

        List<TourGroupDetail> resultList = session.createQuery(HQL, TourGroupDetail.class)
                .setParameter("memberId", memberId)
                .setParameter("tourGroupId", tourGroupId)
                .getResultList();

        return resultList;

    }

    public Integer getTripCommentEditCount(Integer tripOrderId){
        final String HQL = """
                SELECT editCount FROM TripComment
                WHERE tripOrderId= :tripOrderId
                """;
       Integer editCount = session.createQuery(HQL, Integer.class)
                .setParameter("tripOrderId", tripOrderId)
                .uniqueResult();

       // 不等於null 裡面有評論
       if(editCount != null){
           return editCount;
       }else {
           // == null 表示裡面還沒評論過
           return 0;
       }

    }

    // ====== Group Order 新增評論 ======
    public void addTripComment(Integer tripId, Integer memberId, Integer rating,
                               String comment, Integer status, Integer editCount, Integer tripOrderId){

        TripComment tripComment = new TripComment();
        tripComment.setTripId(tripId);

        tripComment.setMemberId(memberId);
        tripComment.setRating(rating);
        tripComment.setComment(comment);
        tripComment.setStatus(status);
        tripComment.setEditCount(editCount);
        tripComment.setTripOrderId(tripOrderId);
        tripCommentDao.insert(tripComment);

        //System.out.println("trip comments successfully updated!");
    }





}
