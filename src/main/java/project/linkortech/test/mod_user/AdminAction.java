package project.linkortech.test.mod_user;

import io.swagger.annotations.ApiOperation;
import mizuki.project.core.restserver.config.BasicMapDataRet;
import mizuki.project.core.restserver.config.BasicRet;
import mizuki.project.core.restserver.config.exception.RestMainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.linkortech.test.mod_user.bean.Admin;
import project.linkortech.test.mod_user.bean.LoadAddress;
import project.linkortech.test.mod_user.bean.User;
import project.linkortech.test.mod_user.dao.AdminMapper;
import project.linkortech.test.mod_user.dao.UserMapper;

@RestController
@RequestMapping("/admin")
@SessionAttributes({"admin"})
public class AdminAction {
    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping(value="/login",method= {RequestMethod.POST})
    @ApiOperation("login")
    public BasicMapDataRet login(
            Model model,
            @RequestParam String name,
            @RequestParam String passwd
    ) throws RestMainException {
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            Admin user = adminMapper.login(name,passwd);
            if(user==null){
                ret.setResult(BasicRet.ERR).setMessage("用户名密码错误");
                return ret;
            }
            if(user.getState()==1){
                ret.setResult(BasicRet.ERR).setMessage("账户被禁用");
                return ret;
            }
            ret.getData().put("admin",user);
            model.addAttribute("admin",user);
            ret.setResult(BasicRet.SUCCESS);
            return ret;
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/addAdmin",method= RequestMethod.POST)
    @ApiOperation(value = "添加普通管理员")
    public BasicRet addAdmin(
            Model model,
            @RequestParam String name,
            @RequestParam String passwd,
            @RequestParam(required = false,defaultValue = "3") int type
    ) throws RestMainException{
        try{
            Admin admin = adminMapper.findByName(name);
            if(admin!=null) return new BasicRet(BasicRet.ERR,"用户名已存在");
            admin = new Admin().setName(name).setPasswd(passwd).setType(type);
            adminMapper.save(admin);
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }

    @RequestMapping(value="/list",method= RequestMethod.POST)
    @ApiOperation(value = "")
    public BasicMapDataRet list(Model model) throws RestMainException{
        try{
            BasicMapDataRet ret = new BasicMapDataRet();
            ret.getData().put("list",adminMapper.list());
            ret.setResult(BasicRet.SUCCESS);
            return ret;
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
                adminMapper.live(id);
            }else{
                adminMapper.ban(id);
            }
            return new BasicRet(BasicRet.SUCCESS);
        }catch (Exception e){
            throw new RestMainException(e, model);
        }
    }
}
