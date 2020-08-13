package project.linkortech.test.mod_download_file;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiBuilder {
    private XSSFWorkbook workbook;
    private CellStyle style;

    public PoiBuilder(XSSFWorkbook workbook){
        style = workbook.createCellStyle();
        this.workbook = workbook;
    }

    public PoiBuilder genCenter(){
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return this;
    }

    public PoiBuilder genBorder(){
        style.setBorderBottom(BorderStyle.THIN); //下边框
        style.setBorderLeft(BorderStyle.THIN);//左边框
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderRight(BorderStyle.THIN);//右边框
        return this;
    }

    public PoiBuilder genDateFormat(){
        DataFormat format= workbook.createDataFormat();
        style.setDataFormat(format.getFormat("yyyy/mm/dd hh:MM:ss.SSS"));
        return this;
    }

    public CellStyle build(){
        return style;
    }

}
