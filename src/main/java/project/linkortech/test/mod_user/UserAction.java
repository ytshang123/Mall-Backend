package project.linkortech.test.mod_user;

import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.linkortech.test.mod_user.bean.LoadAddress;
import project.linkortech.test.mod_user.bean.User;
import project.linkortech.test.mod_user.dao.UserMapper;

@RestController
@RequestMapping("/user")
@SessionAttributes({"user"})
public class UserAction {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value="/login",method= {RequestMethod.POST})
    @ApiOperation("login")
    public BasicMapDataRet login(
            Model model,
            @RequestParam String username,
            @RequestParam String passwd
    ) throws RestMainException {
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            User user = userMapper.login(username,passwd);
            if(user==null){
                ret.setResult(BasicRet.ERR).setMessage("用户名密码错误");
                return ret;
            }
            ret.getData().put("user",user);
            model.addAttribute("user",user);
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/reg",method= RequestMethod.POST)
    @ApiOperation(value = "注册")
    public BasicRet reg(
            Model model,
            @RequestParam String username,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String passwd
    ) throws RestMainException{
        try{
            User user = userMapper.findByName(username);
            if(user!=null){
                return new BasicRet(BasicRet.ERR,"用户名被占用");
            }
            user = new User().setEmail(email).setPasswd(passwd).setUsername(username).setContact(phone);
            userMapper.save(user);
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/addAddress",method= RequestMethod.POST)
    @ApiOperation(value = "添加收货地址")
    public BasicRet addAddress(
            Model model,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String area,
            @RequestParam String address,
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String postalcode
    ) throws RestMainException{
        try{
            User user = (User)model.asMap().get("user");
            LoadAddress loadAddress = new LoadAddress().setUserid(user.getUserid())
                    .setName(name).setCity(city).setArea(area).setAddress(address)
                    .setPhone(phone).setPostalcode(postalcode).setProvince(province);
            userMapper.saveAddress(loadAddress);
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/updateAddress",method= RequestMethod.POST)
    @ApiOperation(value = "添加收货地址")
    public BasicRet updateAddress(
            Model model,
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String area,
            @RequestParam String address,
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String postalcode
    ) throws RestMainException{
        try{
//            User user = (User)model.asMap().get("user");
            LoadAddress loadAddress = userMapper.findAddress(id);
            if(loadAddress==null) return new BasicRet(BasicRet.ERR,"address null");
            loadAddress.setName(name).setCity(city).setArea(area).setAddress(address)
                    .setPhone(phone).setPostalcode(postalcode).setProvince(province);
            userMapper.updateAddress(loadAddress);
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/delAddress",method= RequestMethod.POST)
    @ApiOperation(value = "删除收货地址")
    public BasicRet delAddress(
            Model model,
            @RequestParam int id
    ) throws RestMainException{
        try{
            userMapper.delAddress(new LoadAddress().setAddressid(id));
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/listAddress",method= RequestMethod.POST)
    @ApiOperation(value = "我的收货地址")
    public BasicMapDataRet listAddress(Model model) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            User user = (User)model.asMap().get("user");
            ret.getData().put("list",userMapper.listByUser(user.getUserid()));
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
}
