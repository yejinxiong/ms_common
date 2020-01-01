package com.hero.ms.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExcelWriter extends AbstractParser {

    private static final Log log = LogFactory.getLog(AbstractParser.class);

    public ExcelWriter() {
        super("ExcelWriter");
        this.setEncoding("gbk");
    }

    @Override
    public void processRows(File var1) {
    }

    @Override
    public List<String[]> parseRows(File input) {
        try {
            InputStream is = new FileInputStream(input);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
            List<String[]> list = new ArrayList(10000);

            for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); ++numSheet) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet != null) {
                    int celNum = hssfSheet.getRow(0).getPhysicalNumberOfCells();

                    for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); ++rowNum) {
                        HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                        if (hssfRow != null) {
                            String[] arr = new String[celNum];

                            for(int i = 0; i < celNum; ++i) {
                                arr[i] = this.getValue(hssfRow.getCell(i));
                            }

                            list.add(arr);
                        }
                    }
                }
            }

            return list;
        } catch (Exception var12) {
            var12.printStackTrace();
            return null;
        }
    }

    @Override
    public File writeRows(List<Object[]> rows) {
        try {
            File tmpFile = File.createTempFile(UUID.randomUUID().toString(), ".xls");
            this.writeRows(rows, tmpFile.getAbsolutePath());
            return tmpFile;
        } catch (IOException var4) {
            log.error(var4.getMessage());
            return null;
        }
    }

    @Override
    public File writeRows(List<Object[]> rows, String filepath) {
        File file = new File(filepath);
        try {
            this.writeRows(rows, file.toString());
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        return file;
    }

    /**
     * 获取单元格的值
     * @param hssfCell
     * @return
     */
    private String getValue(HSSFCell hssfCell) {
        String cellValue = null;
        DecimalFormat df = new DecimalFormat("#");
        if (StringUtils.isNotEmpty(hssfCell.toString())) {
            switch(hssfCell.getCellType()) {
                case 0:
                    if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                        Date d = hssfCell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = formater.format(d).toString();
                    } else {
                        cellValue = df.format(hssfCell.getNumericCellValue()).toString();
                    }
                    break;
                case 1:
                    cellValue = hssfCell.getRichStringCellValue().getString().trim();
                    break;
                case 2:
                    cellValue = hssfCell.getCellFormula();
                    break;
                case 3:
                default:
                    cellValue = "";
                    break;
                case 4:
                    cellValue = String.valueOf(hssfCell.getBooleanCellValue()).trim();
            }
        }
        return cellValue;
    }
}
