package com.ui.dataproviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class LoginDataProvider {

    @DataProvider(name = "LoginJSONDataProvider")
    public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir")+"\\testData\\logindata.json");
        FileReader fileReader = new FileReader(testDataFile);
        TestData testdata = gson.fromJson(fileReader, TestData.class);  //// Deserialization!! converting JSON object to Java object

        List<Object[]> dataToReturn = new ArrayList<Object[]>();
        for(User user: testdata.getData()){
            dataToReturn.add(new Object[] {user});
        }
        return dataToReturn.iterator();
    }

    @DataProvider(name = "LoginCSVTestDataProvider")
    public Iterator<User> loginCSVDataProvider(){
        return CSVReaderUtility.readCSVFile("loginData.csv");
    }

    @DataProvider(name = "LoginExcelTestDataProvider")
    public Iterator<User> loginExcelDataProvider(){
        return ExcelReaderUtility.readExcelFile("loginData.xlsx");
    }

}
