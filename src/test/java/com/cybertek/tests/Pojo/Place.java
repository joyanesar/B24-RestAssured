package com.cybertek.tests.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.naming.ldap.PagedResultsControl;
import java.security.PrivateKey;
import java.util.List;

@Data
public class Place {
    @JsonProperty("place name")
    private String placeName;

    private String longitude;

    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    private String latitude;

}
