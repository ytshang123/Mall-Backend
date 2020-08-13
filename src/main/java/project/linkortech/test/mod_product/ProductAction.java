package project.linkortech.test.mod_product;

import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.linkortech.test.mod_product.bean.Product;
import project.linkortech.test.mod_product.dao.ProductMapper;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@SessionAttributes({"user"})
public class ProductAction {
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(value="/detail",method= RequestMethod.POST)
    public BasicMapDataRet detail(
            Model model,
            @RequestParam int productid
    ) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            Product product = productMapper.findById(productid);
            ret.getData().put("product",product);
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/list",method= RequestMethod.POST)
    public BasicMapDataRet list(
            Model model,
            @RequestParam(required = false) String type
    ) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            List<Product> list;
            if(type==null){
                list = productMapper.listProduct();
            }
            else
                list = productMapper.listProductByType(type);
            ret.setResult(BasicRet.SUCCESS);
            ret.getData().put("list",list);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/search",method=RequestMethod.POST)
    public BasicMapDataRet search(
            Model model,
            @RequestParam String key
    ) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            List<Product> list;
            list = productMapper.search(key);
            ret.setResult(BasicRet.SUCCESS);
            ret.getData().put("list",list);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @Value("${project.projectPath}")
    private String path;

    @RequestMapping(value="/img/{name}",method= RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(
            Model model,
            @PathVariable String name
    ) throws RestMainException {
        File file = new File(path+"/img/"+name);
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//            headers.add("Content-Disposition", String.format("attachment;"));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            return ResponseEntity.ok().headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(new FileInputStream(file)));
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
}
