package com.cybertek.tests.Pojo.ZipCode;

import com.cybertek.tests.Pojo.Place;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class ZipInfo {
    @JsonProperty("post code")
    private String postCode;

    private String country;

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;


    List<Place> places;

}
