package com.tw.itinerary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripImage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int tripId;

    private byte[] image;

    private Timestamp uploadTime;

    @Transient
    private String imageBase64;

}
