package project.linkortech.test.mod_download_file;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoiCommonService {

    /**
     * config: title/key:name/key:name
     */
    public XSSFWorkbook genExcel(List<Map<String,Object>> data, String config) {
//        XSSFWorkbook workbook = new XSSFWorkbook(
//                context.getResource("classpath:xlsTemplates/code_print_template.xlsx").getInputStream());
        if(config==null) return null;
        List<Map<String,Object>> configs = new ArrayList<>();
        String[] temps = config.split("/");
        String title = temps[0];
        for(int i=1;i<temps.length;i++){
            String temp = temps[i];
            Map<String,Object> map = new HashMap<>();
            String[] temp2 = temp.split(":");
            map.put("key",temp2[0]);
            map.put("name",temp2[1]);
            if(temp2.length>2) map.put("width", Integer.parseInt(temp2[2])*256);
            configs.add(map);
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        CellStyle centerStyle = new PoiBuilder(workbook).genCenter().genBorder().build();
        CellStyle dateFormatStyle = new PoiBuilder(workbook).genCenter().genBorder().genDateFormat().build();
        XSSFSheet sheet = workbook.createSheet();
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,configs.size()-1));
        XSSFCell cell = sheet.createRow(0).createCell(0);
        cell.setCellStyle(centerStyle);
        cell.setCellValue(title);
        // titles
        XSSFRow row = sheet.createRow(1);
        for(int i=0;i<configs.size();i++){
            cell = row.createCell(i);
            cell.setCellStyle(centerStyle);
            cell.setCellValue((String) configs.get(i).get("name"));
            sheet.setColumnWidth(i,(int)configs.get(i).getOrDefault("width",20*256));
        }
        //content
        for (int i=0;i<data.size();i++){
            row = sheet.createRow(2+i);
            Map<String,Object> data0 = data.get(i);
            for(int j=0;j<configs.size();j++){
                Object val = data0.get(configs.get(j).get("key"));
                // todo
                if("time".equals(configs.get(j).get("key")) && val instanceof Long)
                    val = new Timestamp((long)val);
                if(val==null) val="";
                cell = row.createCell(j);
                cell.setCellStyle(centerStyle);
                String res = String.valueOf(val);
                if(val instanceof Timestamp){
                    cell.setCellStyle(dateFormatStyle);
                }
                cell.setCellValue(res);
            }
        }

        return workbook;
    }

    public static void main(String[] args) throws Exception {
        // todo 修改保存地址
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/ycj/Downloads/test.xlsx"));
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("key1",123);
        map1.put("key2","ssss");
        data.add(map1);
        for(int i=0;i<50000;i++) {
            Map<String, Object> map2 = new HashMap<>();
            map2.put("key1", new Timestamp(System.currentTimeMillis()));
            map2.put("key2", "ddd");
            data.add(map2);
        }
        long a = System.currentTimeMillis();
        XSSFWorkbook workbook = new PoiCommonService().genExcel(data,"测试用报表/key1:表头1:20/key2:表头2");
        System.out.println(System.currentTimeMillis()-a);
        workbook.write(outputStream);
        outputStream.close();
    }
}
