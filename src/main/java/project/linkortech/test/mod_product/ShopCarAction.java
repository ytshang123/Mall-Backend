package project.linkortech.test.mod_product;

import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.linkortech.test.mod_product.bean.ShopCart;
import project.linkortech.test.mod_product.dao.ShopCartMapper;
import project.linkortech.test.mod_user.bean.User;

import java.sql.Timestamp;

@RestController
@RequestMapping("/shopCart")
@SessionAttributes({"user"})
public class ShopCarAction {
    @Autowired
    private ShopCartMapper shopCartMapper;

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public BasicRet add(
            Model model,
            @RequestParam int productid,
            @RequestParam(required = false, defaultValue = "1") int count
    ) throws RestMainException {
        try{
            User user = (User)model.asMap().get("user");
            ShopCart shopCart = shopCartMapper.find(user.getUserid(),productid);
            if(shopCart==null){
                shopCart = new ShopCart()
                        .setCreatedt(new Timestamp(System.currentTimeMillis()))
                        .setCount(count).setProductid(productid).setUserid(user.getUserid());
                shopCartMapper.save(shopCart);
            }else{
                shopCart.setCount(shopCart.getCount()+count);
                shopCartMapper.update(shopCart);
            }
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/del",method= RequestMethod.POST)
    public BasicRet del(
            Model model,
            @RequestParam int productid
    ) throws RestMainException{
        try{
            User user = (User)model.asMap().get("user");
            shopCartMapper.del(new ShopCart().setUserid(user.getUserid()).setProductid(productid));
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/updateCount",method= RequestMethod.POST)
    public BasicRet updateCount(
            Model model,
            @RequestParam int productid,
            @RequestParam int count
    ) throws RestMainException{
        try{
            User user = (User)model.asMap().get("user");
            ShopCart shopCart = shopCartMapper.find(user.getUserid(),productid);
            if(shopCart!=null){
                shopCart.setCount(count);
                shopCartMapper.update(shopCart);
            }
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/list",method= RequestMethod.POST)
    public BasicMapDataRet list(
            Model model
    ) throws RestMainException{
        try{
            User user = (User)model.asMap().get("user");
            BasicMapDataRet ret = new BasicMapDataRet();
            ret.getData().put("list",shopCartMapper.listByUser(user.getUserid()));
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
}
