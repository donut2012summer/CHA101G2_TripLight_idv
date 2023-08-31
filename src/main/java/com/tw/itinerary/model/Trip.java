package com.tw.itinerary.model;

import static com.tw.trip.controller.TripImageController.IMG_URL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.tw.ticket.model.TicketType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip implements Serializable {

	public static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tripId;

	@OneToOne
	@JoinColumn(name = "tripTypeId")
	private TripType tripType;

	private int vendorId;

	private String tripName;

	private String tripDescription;

	private String tripNote;

	private int tripDay;

	private String city;

	private int totalSales;  // 總銷售量

	private int priceAdult;

	private int priceChild;

	private int minTravelersNo;

	private int maxTravelersNo;

	private double ratingSum;

	private int ratingCount;

	private Integer status;

	private String tripContent;

	@Transient
	private String imageBase64;

	@Transient
	private String imageUrl;

	@Transient
	private Integer tripOrderId;

	@Transient
	private Integer id;

	@Transient
	private Integer tourGroupId;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tripId")
	private List<TripComment> tripComments = new ArrayList<>();

	// cascade表示存檔時 也一起寫入AiLocations
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tripId")
	private List<TripImage> tripImage = new ArrayList<>();


	public Trip(final Integer tripId, final String tripName, final Integer tripDay, final String city, final String tripContent, final byte[] image) {

		this.tripId = tripId;
		this.tripName = tripName;
		this.tripDay = tripDay;
		this.city = city;
		this.tripContent = tripContent;
		imageBase64 = Base64.getEncoder().encodeToString(image);

	}

	public Trip(final Integer tripId, final String tripName, final Integer tripDay, final String city, final String tripContent, final Integer id) {

		this.tripId = tripId;
		this.tripName = tripName;
		this.tripDay = tripDay;
		this.city = city;
		this.tripContent = tripContent;
		imageUrl = "/img/trips/" + id;

	}

	public Trip(final String tripName, final Integer tripDay, final String city, final String tripContent, final String tripDescription,
				final String tripNote) {
		this.tripName = tripName;
		this.tripDay = tripDay;
		this.city = city;
		this.tripContent = tripContent;
		this.tripDescription = tripDescription;
		this.tripNote = tripNote;
	}

	public Trip(final Integer tripId, final String tripName, final String tripContent, final Integer tripOrderId, final Integer id,
				final Integer tourGroupId, final Integer tripDay) {

		this.tripId = tripId;
		this.tripName = tripName;
		this.tripContent = tripContent;
		this.tripOrderId = tripOrderId;
		this.id = id;
		this.tourGroupId = tourGroupId;
		this.tripDay = tripDay;
	}
}