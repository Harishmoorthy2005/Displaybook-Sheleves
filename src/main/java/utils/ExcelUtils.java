package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;
    private static final String FILE_PATH = ConfigReader.getProperty("excel.output.path");

    public static void setExcelFile(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            LoggerManager.error("Error loading Excel file: " + e.getMessage());
        }
    }

    public static String getCellData(int rowNum, int colNum) {
        try {
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            if (cell == null) {
                return "";
            }
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getDateCellValue().toString();
                    }
                    return String.valueOf((long) cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case BLANK:
                    return "";
                default:
                    return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    public static int getColumnCount(int rowNum) {
        return sheet.getRow(rowNum).getLastCellNum();
    }

    public static void writeBookshelfData(List<String> names, List<String> prices) {
        try {
            XSSFWorkbook workbook = getWorkbook();
            Sheet sheet = workbook.getSheet("Bookshelves");
            int rowNum = sheet.getLastRowNum() + 1;
            for (int i = 0; i < names.size(); i++) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rowNum - 1);
                row.createCell(1).setCellValue(names.get(i));
                row.createCell(2).setCellValue(prices.get(i));
            }
            saveWorkbook(workbook);
            LoggerManager.info("Bookshelf data written successfully");
        } catch (Exception e) {
            LoggerManager.error("Excel writing failed: " + e.getMessage());
        }
    }

    public static void writeTerraData(List<String> items) {
        try {
            XSSFWorkbook workbook = getWorkbook();
            Sheet sheet = workbook.getSheet("Terra Collection");
            int rowNum = sheet.getLastRowNum() + 1;
            for (String item : items) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue("Terra Collection");
                row.createCell(1).setCellValue(item);
            }
            saveWorkbook(workbook);
            LoggerManager.info("Terra data written successfully");
        } catch (Exception e) {
            LoggerManager.error("Excel writing failed: " + e.getMessage());
        }
    }

    public static void writeDiscountData(List<String[]> productData) {
        try {
            XSSFWorkbook workbook = getWorkbook();
            Sheet sheet = workbook.getSheet("Bookshelves");
            int rowNum = sheet.getLastRowNum() + 3;
            Row titleRow = sheet.createRow(rowNum++);
            titleRow.createCell(0).setCellValue("TC_08 - Discount High To Low");
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("S.No");
            headerRow.createCell(1).setCellValue("Product Name");
            headerRow.createCell(2).setCellValue("Discount");
            int serialNo = 1;
            for (String[] product : productData) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(serialNo++);
                row.createCell(1).setCellValue(product[0]);
                row.createCell(2).setCellValue(product[1]);
            }
            saveWorkbook(workbook);
            LoggerManager.info("Discount data written successfully");
        } catch (Exception e) {
            LoggerManager.error("Excel writing failed : " + e.getMessage());
        }
    }

    private static XSSFWorkbook getWorkbook() throws Exception {
        FileInputStream fis = new FileInputStream(FILE_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        fis.close();
        return workbook;
    }

    private static void saveWorkbook(XSSFWorkbook workbook) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        }
        workbook.close();
    }

    public static void writeProductImageUrl(String imageUrl) {
        try {
            XSSFWorkbook workbook = getWorkbook();
            Sheet sheet = workbook.getSheet("Bookshelves");
            int rowNum = sheet.getLastRowNum() + 3;
            Row titleRow = sheet.createRow(rowNum++);
            titleRow.createCell(0).setCellValue("TC_10 - Product Image URL");
            Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(0).setCellValue("Image URL");
            Row dataRow = sheet.createRow(rowNum);
            dataRow.createCell(0).setCellValue(imageUrl);
            saveWorkbook(workbook);
            LoggerManager.info("Product Image URL written successfully");
        } catch (Exception e) {
            LoggerManager.error("Excel writing failed : " + e.getMessage());
        }
    }
    public static void writeTopThreeStudyChairs(List<String> products) {

        try {
            XSSFWorkbook workbook = getWorkbook();
            Sheet sheet = workbook.getSheet("Study Chairs");
            if (sheet == null) {
                sheet = workbook.createSheet("Study Chairs");
            }
            int rowNum = sheet.getLastRowNum();
            if (rowNum == 0 && sheet.getRow(0) == null) {
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("S.No");
                header.createCell(1).setCellValue("Product Name");
                rowNum = 1;
            }
            else {
                rowNum++;
            }
            for (int i = 0; i < products.size(); i++) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(products.get(i));
            }
            saveWorkbook(workbook);
            LoggerManager.info("Top 3 Study Chairs written successfully");
        } catch (Exception e) {
            LoggerManager.error("Excel writing failed : " + e.getMessage());
        }
    }
}