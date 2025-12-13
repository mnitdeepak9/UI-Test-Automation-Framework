package com.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {
    public static Iterator<User> readExcelFile(String fileName){
        File file = new File(System.getProperty("user.dir")+"//testData//"+fileName);

        XSSFWorkbook  xssfWorkbook = null;   //XSSFWorkbook class is used to read XLSX File
        List<User> userList = new ArrayList<>();
        Row row;
        Cell firstCellEmail;
        Cell secondCellPassword;
        XSSFSheet xssfSheet;
        User user;

        try {
            xssfWorkbook = new XSSFWorkbook(file);
            xssfSheet = xssfWorkbook.getSheet("LoginTestData");
            Iterator<Row> rowIterator = xssfSheet.iterator();
            rowIterator.next(); //Skipping coulumn name
            while(rowIterator.hasNext()){
                row = rowIterator.next();
                firstCellEmail = row.getCell(0);
                secondCellPassword = row.getCell(1);

                System.out.println(firstCellEmail.toString());
                System.out.println(secondCellPassword.toString());
                user = new User(firstCellEmail.toString(), secondCellPassword.toString());
                userList.add(user);
                xssfWorkbook.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

        return userList.iterator();

    }
}
