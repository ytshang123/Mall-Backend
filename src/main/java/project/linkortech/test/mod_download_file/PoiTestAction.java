package project.linkortech.test.mod_download_file;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

//@RestController
@RequestMapping("/rest/poi")
@SessionAttributes({"user"})
@Transactional(rollbackFor = Exception.class)
@Api(tags = "文件下载")
public class PoiTestAction {
    @Autowired
    private PoiCommonService poiCommonService;
    private ZipService zipService;

    @RequestMapping(value="/download",method= RequestMethod.GET)
    @ApiOperation(value = "")
    public ResponseEntity<InputStreamResource> download(Model model) throws RestMainException {
        String uuId = GetUUID.getUUID();
        File file = new File("E:\\111AAA\\Test\\Excel\\"+uuId);
        file.mkdir();
        try{
            // todo 生成文件
            List<Map<String,Object>> data = new ArrayList<>();
            Map<String,Object> map1 = new HashMap<>();
            map1.put("key1",123);
            map1.put("key2","ssss");
            data.add(map1);
            for(int i=0;i<5000;i++) {
                Map<String, Object> map2 = new HashMap<>();
                map2.put("key1", new Timestamp(System.currentTimeMillis()));
                map2.put("key2", "ddd");
                data.add(map2);
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            String filename=uuId+".zip";
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            int i = data.size()%5000;
            List<File> fileList = new ArrayList<>();
            for(int m = 0 ; m<5;m++){
                //poiCommonService.genExcel(data,"测试用报表/key1:表头1:20/key2:表头2").write(outputStream);
                long start = System.currentTimeMillis();
                XSSFWorkbook workbook = new PoiCommonService().genExcel(data,"测试用报表/key1:表头1:20/key2:表头2");
                String numExcel = m+"";
                FileOutputStream outFile = new FileOutputStream("E:\\111AAA\\Test\\Excel\\testExcel\\tempsxssf"+numExcel+".xlsx");
                workbook.write(outFile);
                fileList.add(new File("E:\\111AAA\\Test\\Excel\\testExcel\\tempsxssf"+numExcel+".xlsx"));
                long end = System.currentTimeMillis();
                //System.out.println(System.currentTimeMillis());
                out.println("生成第Excel文件耗时：" + (end - start) +" ms");
            }
//            poiCommonService.genExcel(data,"测试用报表/key1:表头1:20/key2:表头2").write(outputStream);
//            System.out.println(System.currentTimeMillis());

            File zipFile = new File("E:\\111AAA\\Test\\Excel\\"+uuId+".zip");
            //压缩文件
            FileOutputStream fos2 = new FileOutputStream(zipFile);
            zipService.toZip(fileList, fos2);
            //删除文件夹
            file.delete();
            InputStream inputStream = new FileInputStream(zipFile);
            return ResponseEntity.ok().headers(headers)
                   .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream));
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
}
