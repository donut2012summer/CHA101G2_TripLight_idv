package com.tw.itinerary.model;

import com.tw.member.model.Member;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Base64;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripComment implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer tripId;

    @Transient
    private Integer memberId;

    private Integer rating;

    private String comment;

    private Integer status;

    @Transient
    private Timestamp postTime;

    private Integer editCount;

    @Transient
    private Timestamp lastEditTime;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @Transient
    private String memberPicBase64;

    @Transient
    private String memberName;

    private Integer tripOrderId;

    public TripComment(String memberName, byte[] memberPic, String comment, Timestamp postTime, int rating ){
        this.memberName = memberName;
        memberPicBase64 = Base64.getEncoder().encodeToString(memberPic);
        this.comment = comment;
        this.postTime = postTime;
        this.rating = rating;
    }


}
