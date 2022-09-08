package com.cybertek.tests.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class PojoTests {
  //private List<Spartan> spartans;
    private  String countryId;
    private  String countryName;
    private int regionId;

    private List<Link> links;


/**
 *"region_id": 1,
 *             "region_name": "Europe",
 *             "links": [
 */
}
