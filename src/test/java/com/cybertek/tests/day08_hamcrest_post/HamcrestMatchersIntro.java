package com.cybertek.tests.day08_hamcrest_post;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersIntro {
    @Test
    public void hamcrestTest(){ // the purpose of hamcrest is readabilliy
        //numbers
        assertThat(4 + 3, equalTo(7));
        assertThat(11 + 3 , is(14));
        assertThat(5 + 2 , is(equalTo(7)));
        assertThat(5 + 2 ,not(8));
        assertThat(10 + 3 , is(greaterThan(11)));
        assertThat(9 - 5 , is(equalTo(4)));
        //String assertions
        String str = "B24 - wooden spoon";
        String st = "b24 - heater not working!";
        assertThat(str,is(equalTo("B24 - wooden spoon")));
        assertThat(str,not(st));
        assertThat(str,startsWith("B24"));
        assertThat(str,endsWith("spoon"));
        assertThat(str,containsStringIgnoringCase("WOODEN"));
        str = "";
        assertThat(str,emptyString());
        //list
        List<Integer> numbs = Arrays.asList(3,1,2,9,10,66);
        assertThat(numbs, hasSize(6));//check size
        assertThat(numbs,hasItem(10)); //check if contains 10
        assertThat(numbs,hasItems(1,3,66));
        assertThat(numbs,everyItem(lessThan(70))); // it checks items less than 70

        List<String>names = Arrays.asList("Mike","Jame","Ahmad","Sam","AMI");
        assertThat(names,hasItem("Mike"));
        assertThat(names,hasItems("Jame","Sam"));
    }

}
