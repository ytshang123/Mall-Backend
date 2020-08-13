package project.linkortech.test.mod_product;

import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.linkortech.test.mod_product.bean.Product;
import project.linkortech.test.mod_product.dao.ProductMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/admin/product")
@SessionAttributes({"user"})
public class ProductAdminAction {
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(value="/list",method= RequestMethod.POST)
    public BasicMapDataRet list(
            Model model
    ) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            List<Product> list;
            list = productMapper.listAllProduct();
            ret.setResult(BasicRet.SUCCESS);
            ret.getData().put("list",list);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public BasicRet add(
            Model model,
            @RequestParam int amount,
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String tag,
            @RequestParam BigDecimal price,
            @RequestParam MultipartFile file
    ) throws RestMainException{
        try{
            String filename = System.currentTimeMillis()+".jpg";
            File file1 = new File(path+"/img/"+filename);
            FileOutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(file.getBytes());
            outputStream.flush();
            Product product = new Product().setImg(filename)
                    .setAmount(amount).setName(name).setOff(0)
                    .setPrice(price).setTag(tag).setType(type);
            productMapper.save(product);
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @Value("${project.projectPath}")
    private String path;

    @RequestMapping(value="/update",method= RequestMethod.POST)
    public BasicRet update(
            Model model,
            @RequestParam int id,
            @RequestParam int amount,
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String tag,
            @RequestParam BigDecimal price,
            @RequestParam(required = false)MultipartFile file
    ) throws RestMainException{
        try{
            String filename=null;
            if(file!=null){
                filename = System.currentTimeMillis()+".jpg";
                File file1 = new File(path+"/img/"+filename);
                FileOutputStream outputStream = new FileOutputStream(file1);
                outputStream.write(file.getBytes());
                outputStream.flush();
            }
            Product product = productMapper.findById(id);
            if(product==null) return new BasicRet(BasicRet.ERR,"product is null");
            product.setAmount(amount).setName(name).setOff(0).setPrice(price).setTag(tag).setType(type);
            if(filename!=null) product.setImg(filename);
            productMapper.update(product);
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/manage",method= RequestMethod.POST)
    @ApiOperation(value = "")
    public BasicRet manage(
            Model model,
            @RequestParam int id,
            @RequestParam int type
    ) throws RestMainException{
        try{
            if(type==0){
                productMapper.active(id);
            }else{
                productMapper.ban(id);
            }
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }



}
