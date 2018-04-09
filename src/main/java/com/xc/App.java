package com.xc;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        File file = new File("E:\\DOC\\sql_slow.xlsx");

        readNewExcel(file);

    }


    public static void readOldExcel(File xlsFile) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(xlsFile));
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        Set<String> set = new HashSet<String>();
        if (null != hssfSheet) {
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row row = hssfSheet.getRow(rowNum);

                if(null == row){
                    continue;
                }

                if(row.getCell(0)==null || row.getCell(0).toString().equals("") || row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK){
                    continue;
                }

                String str = StringUtils.trim(row.getCell(10).getStringCellValue());
                set.add(str.substring(str.lastIndexOf("*/") + 2));
            }
        }

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public static void readNewExcel(File xlsFile) throws IOException {
        XSSFWorkbook xssfWorkbook  = new XSSFWorkbook(new FileInputStream(xlsFile));
        XSSFSheet xssfSheet = xssfWorkbook .getSheetAt(0);
        Set<String> set = new HashSet<String>();
        if (null != xssfSheet) {
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow row = xssfSheet.getRow(rowNum);

                if(null == row){
                    continue;
                }

                if(row.getCell(0)==null || row.getCell(0).toString().equals("") || row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK){
                    continue;
                }

                String str = StringUtils.trim(row.getCell(10).getStringCellValue());
                set.add(str.substring(str.lastIndexOf("*/") + 2));
            }
        }

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
