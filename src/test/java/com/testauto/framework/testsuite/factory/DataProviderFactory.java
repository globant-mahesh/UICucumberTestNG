package com.testauto.framework.testsuite.factory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by maheshb on 7/4/17.
 */
public class DataProviderFactory {

    @DataProvider(name = "DataProviderFactory")
    public Object[][] factoryDataProvider() throws IOException {
//        Set adds/retrieves data maintaining unique data discarding duplicate elements
//        LinkedHashSet maintains the insertion order unlike HashSet that randomly places/retrieves data
//        LinkedHashSet gets performance advantage since accessing any element in the HashTable takes the same time
//        LinkedHashSet is preferable over Treeset because Treeset sorts the data which is not testing requirement
//        Other alternative to the LinkedHashSet here should be ArrayList or LinkedList
//        LinkedHashSet stores data in the hastable that uses hashing based on the keydata to store data
//        If each row has atleast one element different from all other rows then it is possible to retrieve all the file
//        records in LinkedHashSet of LinkedHashSet which is equivalent of two dimensional array
        LinkedHashSet<LinkedHashSet<String>> tableSet = new LinkedHashSet();
        File f = new File("/home/maheshb/phptravels/src/test/java/com/testauto/framework/testsuite/factory/data.xlsx");
        FileInputStream fis = new FileInputStream(f);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(0);
        int rowNum = sh.getPhysicalNumberOfRows();
        int cellNum = 0;
        for (int i = 0; i<rowNum; i++) {
            XSSFRow rw = sh.getRow(i);
            cellNum = rw.getLastCellNum();
            LinkedHashSet<String> rowSet = new LinkedHashSet();
            for (int j = 0; j<cellNum; j++) {
                XSSFCell cl = rw.getCell(j);
//                cell value is return in type Object. Convert it to the string.
                rowSet.add(String.valueOf(cl));
            }
            tableSet.add(rowSet);
        }
        Object o[][]  = new Object[tableSet.size()][cellNum];
        Iterator<LinkedHashSet<String>> itr = tableSet.iterator();
        for (int i = 0; itr.hasNext(); i++) {
            LinkedHashSet<String> lhs = itr.next();
//            convert set to Array
            o[i] = lhs.toArray();
        }
//        System.out.println(tableSet);
        tableSet.clear();
        return o;
    }

//    public static void main(String[] args) throws IOException {
//        DataProviderFactory dpf = new DataProviderFactory();
//        dpf.factoryDataProvider();
//    }

}
