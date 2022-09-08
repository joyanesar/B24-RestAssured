package com.cybertek.tests.day13_bookit;
import com.cybertek.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
public class ParameterizedDemoTest {
    public static List<String> getFriendsNames(){
        List<String>friendsNames = Arrays.asList("NesarAhmad","Fahima","Mir","Tach","Nisso","ShahmuradHalikov");
        return friendsNames;
    }
    @ParameterizedTest
    @MethodSource("getFriendsNames")
    public  void friendsNamesTest(String name){
        System.out.println("name = " + name);
    }

    /**
     * read allcredentails from bookitq3.xls file
     * and return as list maps
     * Map will have 2 keys, email and password
     * @return
     */
    public static List<Map<String,String>>getAllUserCredentials(){
        //open excel file worksheet
        ExcelUtil excelUtil = new ExcelUtil("BookItQa3.xlsx","QA3");
        //returns excel data as list of maps
        return excelUtil.getDataList();
    }
    @ParameterizedTest
    @MethodSource("getAllUserCredentials")
    public void bookItUsersTest(Map<String,String> userInf){
        System.out.println("userInf = " + userInf);
    }
    @ParameterizedTest
    @CsvSource({"1,4","10,3","5,9","44,33"})
    public void addNumbers(int num1, int num2){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("(num1 + num2 = " + (num1 + num2));


    }

}
