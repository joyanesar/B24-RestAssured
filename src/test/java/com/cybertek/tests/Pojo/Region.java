package com.cybertek.tests.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class Region {
    @JsonProperty("region_id")
    private int regionId;

    @JsonProperty("region_name")
    private String regionName;

    private List<Link> links;
}
