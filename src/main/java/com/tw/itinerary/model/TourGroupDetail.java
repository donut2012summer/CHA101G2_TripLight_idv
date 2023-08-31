package com.tw.itinerary.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourGroupDetail implements Serializable {

    public static final long serialVersionUID = 35L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer tourGroupId;

    private String travelerName;

    private String travelerPhone;

    private String travelerEmail;

    private Integer travelerGender;

    private Integer travelerAge;

    private Integer memberId;

}
