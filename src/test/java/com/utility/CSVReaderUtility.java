package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {
    public static Iterator<User> readCSVFile(String fileName) {
        FileReader fileReader= null;
        CSVReader csvReader;
        String[] line;
        List<User> userList = new ArrayList<User>();
        User user;

        File csvFile = new File(System.getProperty("user.dir")+"//testData//"+fileName);
        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            line = csvReader.readNext();       //Reading the column  Names... Row 1

          /*  data = csvReader.readNext(); //Reading the Row 2
            System.out.println(Arrays.toString(data));

            data = csvReader.readNext();   // Reading the Row 3
            System.out.println(Arrays.toString(data));*/


            //We can also check null till the end of csv file by using while loop
            while ((line=csvReader.readNext())!=null){
                user = new User(line[0],line[1]);
                userList.add(user);
            }

            for (User userData:userList){
                System.out.println(userData);
            }


            /*data = csvReader.readNext();   // Reading the Row 4 ..... No data in that row so returning null
            System.out.println(Arrays.toString(data));*/

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return userList.iterator();

    }
}
