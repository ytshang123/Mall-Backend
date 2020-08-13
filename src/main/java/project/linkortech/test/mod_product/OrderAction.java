package project.linkortech.test.mod_product;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import mizuki.project.core.restserver.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.linkortech.test.mod_product.bean.OrderMain;
import project.linkortech.test.mod_product.bean.OrderProduct;
import project.linkortech.test.mod_product.bean.Product;
import project.linkortech.test.mod_product.bean.ShopCart;
import project.linkortech.test.mod_product.dao.OrderMapper;
import project.linkortech.test.mod_product.dao.ProductMapper;
import project.linkortech.test.mod_product.dao.ShopCartMapper;
import project.linkortech.test.mod_user.bean.LoadAddress;
import project.linkortech.test.mod_user.bean.User;
import project.linkortech.test.mod_user.dao.UserMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@SessionAttributes({"user"})
public class OrderAction {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ShopCartMapper shopCartMapper;

    @RequestMapping(value="/save",method= RequestMethod.POST)
    @ApiOperation(value = "下单")
    public BasicMapDataRet save(
            Model model,
            @ApiParam(value = "[ {pid:1, amount:1} ]")
            @RequestParam String json,
            @RequestParam int addressId
    ) throws RestMainException{
        try{
            User user = (User) model.asMap().get("user");
            BasicMapDataRet ret = new BasicMapDataRet();
            List<Map<String,Object>> list = JsonUtil.toObject(json,List.class);
            LoadAddress address = userMapper.findAddress(addressId);
            if(address==null) return new BasicMapDataRet(BasicRet.ERR,"address is null");
            OrderMain orderMain = new OrderMain().setCreatetime(new Timestamp(System.currentTimeMillis()))
                    .setDeliver(address.getProvince()+address.getCity()+address.getArea()+address.getAddress())
                    .setDeName(address.getName()).setDePhone(address.getPhone())
                    .setStatus(0).setUserid(user.getUserid());
            orderMapper.save(orderMain);
            BigDecimal price = BigDecimal.ZERO;
            for(Map<String,Object> map:list){
                Product product = productMapper.findById((int)map.get("pid"));
                if(product==null) return new BasicMapDataRet(BasicRet.ERR,"product is null");
                if(product.getAmount()<(int)map.get("amount"))return new BasicMapDataRet(BasicRet.ERR,product.getName()+" 商品数量不足");
                OrderProduct orderProduct = new OrderProduct()
                        .setAmount((int)map.get("amount")).setImg(product.getImg())
                        .setName(product.getName()).setOrderid(orderMain.getOrderid())
                        .setPrice(product.getPrice()).setProductid(product.getProductid());
                orderMapper.saveProduct(orderProduct);
                if(orderMain.getProducts()==null) orderMain.setProducts(new ArrayList<>());
                orderMain.getProducts().add(orderProduct);
                price = price.add(orderProduct.getPrice().multiply(BigDecimal.valueOf(orderProduct.getAmount())));
            }
            orderMain.setPrice(price);
            // todo 默认支付成功
            orderMain.setStatus(1);
            orderMapper.update(orderMain);
            // 删除购物车中的
            for(Map<String,Object> map:list){
                shopCartMapper.del(new ShopCart().setProductid((int)map.get("pid")).setUserid(user.getUserid()));
            }
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
    
    @RequestMapping(value="/detail",method= RequestMethod.POST)
    @ApiOperation(value = "查询订单")
    public BasicMapDataRet detail(
            Model model,
            @RequestParam int id
    ) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            ret.getData().put("order",orderMapper.find(id));
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
    
    @RequestMapping(value="/list",method= RequestMethod.POST)
    @ApiOperation(value = "")
    public BasicMapDataRet list(
            Model model
    ) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            User user = (User) model.asMap().get("user");
            ret.getData().put("list",orderMapper.listByUser(user.getUserid()));
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
    
    @RequestMapping(value="/pay",method= RequestMethod.POST)
    @ApiOperation(value = "")
    public BasicRet pay(
            Model model,
            @RequestParam int id
    ) throws RestMainException{
        try{
            orderMapper.updateStatus(id,1);
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
}
