package com.example.Farmer.s.Market.models;

import com.montealegreluis.yelpv3.businesses.*;
import com.montealegreluis.yelpv3.businesses.distance.Distance;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Entity
public class TrackerBusiness extends Business {

    @Id
    private String id;
    public String name;
    public Double rating;
    public URL url;


    public TrackerBusiness(){}

    public TrackerBusiness(Business business) {
        super();
        this.rating = business.rating;
        this.pricingLevel = business.pricingLevel;
        this.phone = business.phone;
        this.id = business.id;
        this.isClosedPermanently = business.isClosedPermanently;
        this.categories = business.categories;
        this.reviewCount = business.reviewCount;
        this.name = business.name;
        this.url = business.url;
        this.coordinates = business.coordinates;
        this.image = business.image;
        this.location = business.location;
        this.distance = business.distance;      }


}

