package com.tangtang.manager.controller.system;

import com.tangtang.manager.dto.LoginDTO;
import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.dto.UserSearchDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.AdminUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: UserController
 * @Description: 系统用户管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/09 15:17
 */
@Controller
@RequestMapping("user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminUserService adminUserService;


    /**
     *
     * 功能描述: 登入系统
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/10 15:47
     */
    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, LoginDTO loginDTO, HttpSession session){
        logger.info("进行登陆");
        Map<String,Object> data = new HashMap();
        // 使用 shiro 进行登录
        Subject subject = SecurityUtils.getSubject();

        String userName = loginDTO.getUsername().trim();
        String password = loginDTO.getPassword().trim();
        String rememberMe = loginDTO.getRememberMe();
        String host = request.getRemoteAddr();

        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password,host);

        // 设置 remenmberMe 的功能
        if (rememberMe != null && rememberMe.equals("on")) {
            token.setRememberMe(true);
        }

        try {
            subject.login(token);
            // 登录成功
            BaseAdminUser user = (BaseAdminUser) subject.getPrincipal();

            session.setAttribute("user", user.getSysUserName());
            data.put("code",1);
            data.put("url","/index");
            //data.put("message","登陆成功");
            logger.info(user.getSysUserName()+"登陆成功");
        } catch (UnknownAccountException e) {
            data.put("code",0);
            data.put("message",userName+"账号不存在");
            logger.error(userName+"账号不存在");
            return data;
        }catch (DisabledAccountException e){
            data.put("code",0);
            data.put("message",userName+"账号异常");
            logger.error(userName+"账号异常");
            return data;
        }
        catch (AuthenticationException e){
            data.put("code",0);
            data.put("message",userName+"密码错误");
            logger.error(userName+"密码错误");
            return data;
        }

        return data;
    }

    /**
     *
     * 功能描述: 修改密码
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/10 17:26
     */
    @RequestMapping("setPwd")
    @ResponseBody
    public Map<String,Object> setP(String pwd, String isPwd){
        logger.info("进行密码重置");
        Map<String,Object> data = new HashMap();
        if(!pwd.equals(isPwd)){
            data.put("code",0);
            data.put("message","两次输入的密码不一致!");
            logger.error("两次输入的密码不一致!");
            return data;
        }
        //获取当前登陆的用户信息
        BaseAdminUser user = (BaseAdminUser) SecurityUtils.getSubject().getPrincipal();
        int result = adminUserService.updatePwd(user.getSysUserName(),pwd);
        if(result == 0){
            data.put("code",0);
            data.put("msg","修改密码失败！");
            logger.error("用户修改密码失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","修改密码成功！");
        logger.info("用户修改密码成功！");
        return data;
    }

    /**
     *
     * 功能描述: 跳到系统用户列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/09 13:50
     */
    @RequestMapping("/userManage")
    public String userManage() {
        return "/user/userManage";
    }

    /**
     *
     * 功能描述: 分页查询用户列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/09 11:10
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ UserSearchDTO userSearch) {
        /*logger.info("分页查询用户列表！搜索条件：userSearch：" + userSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pdr = adminUserService.getUserList(userSearch, pageNum ,pageSize);
            logger.info("用户列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户列表查询异常！", e);
        }
        return pdr;
    }


    /**
     *
     * 功能描述: 新增和更新系统用户
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/10 10:14
     */
    @RequestMapping(value = "/setUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> setUser(BaseAdminUser user) {
        logger.info("设置用户[新增或更新]！user:" + user);
        Map<String,Object> data = new HashMap();
        System.out.println("data"+data);
        if(user.getId() == null){
            data = adminUserService.addUser(user);
        }else{
            data = adminUserService.updateUser(user);
        }
        return data;
    }


    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> regUser(StudentRegistrationDTO user) {
        logger.info("设置用户[注册用户]！user:" + user);
        Map<String,Object> data = new HashMap();
        System.out.println("data"+data);
        data = adminUserService.regUser(user);
        data.put("url","/login");
        return data;
    }


    /**
     * 院校申请使用
     * @param user
     * @return
     */
    @RequestMapping(value = "/regSchoolUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> regSchoolUser(SchoolRegistrationDTO user) {
        logger.info("院校申请使用 ------> user:" + user);
        Map<String,Object> data = new HashMap();
        data = adminUserService.regSchoolUser(user);
        data.put("url","/login");
        return data;
    }

    /**
     *
     * 功能描述: 删除/恢复 用户
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/10 11:59
     */
    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserStatus(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
        logger.info("删除/恢复用户！id:" + id+" status:"+status);
        Map<String, Object> data = new HashMap<>();
        if(status == 0){
            //删除用户
            data = adminUserService.delUser(id,status);
        }else{
            //恢复用户
            data = adminUserService.recoverUser(id,status);
        }
        return data;
    }

    @RequestMapping(value = "/userIsExist", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public Boolean userIsExist(@RequestBody BaseAdminUser user) {
        logger.info("注册用户[检测用户名是否存在]！username:" + user.getSysUserName());
        String username = user.getSysUserName();
        System.out.println(username);
        //根据用户名查询管理员(包括status为0的   以防恢复引起bug)
        BaseAdminUser admin1=adminUserService.findByUserName(username);
        if(admin1==null)
        {
            System.out.println(111);
            //返回true则为没有该用户名可以被注册
            return true;
        }else {
            System.out.println(222);
            return false;
        }
    }


}
