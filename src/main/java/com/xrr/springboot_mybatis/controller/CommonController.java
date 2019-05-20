package com.xrr.springboot_mybatis.controller;

import com.xrr.springboot_mybatis.entity.Stu;
import com.xrr.springboot_mybatis.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by MWL on 2017/11/25.
 */
@Controller
public class CommonController extends SuffixController {
    @Autowired
    public CommonService commonservice;

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {
        return "/login";
    }

    //用户登陆
    @RequestMapping(value = "/loginPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request, HttpSession session) {
        String tno = request.getParameter("tno");
        String password = request.getParameter("password");
        String tname = commonservice.login(tno, password);
        String  no=commonservice.getsno(tno, password);
        List<Stu> stuinfo=commonservice.userinfor(tno);
        int id=stuinfo.get(0).getId();
        session.setAttribute("id",id);
        session.setAttribute("no",no);
        session.setAttribute("tname", tname);

        if (tname == null) {
            return "redirect:/login.do";
        } else {
            return "redirect:/index.do";
        }
    }

    //用户登陆成功主页面
    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginindex(HttpSession session) {
        return "/login/index";
    }

    //用户信息管理
    @RequestMapping(value = "/stuinfomation", method = {RequestMethod.POST, RequestMethod.GET})
    public String stuinfo(HttpSession session) {

        return "/common/information";
    }

    /*
       用户信息列表
    */
    @PostMapping(value = "/stuinforlist")
    @ResponseBody
    public Map getStuinforList(HttpServletRequest request){
        int page=Integer.parseInt(request.getParameter("page"));
        int pageSzie=Integer.parseInt(request.getParameter("rows"));//pageSzie
        int startRecord=(page-1)*pageSzie+1;
        int total=commonservice.gettstunumber();
        List<Stu>  stuinforlist=commonservice.stuinfo(startRecord,pageSzie);
        Map resultMap=new HashMap();
        resultMap.put("total",total-1);
        resultMap.put("rows",stuinforlist);
        return resultMap;
    }

    /*
    进入用户信息界面
    */
    @GetMapping(value="/info")
    public String stuinfor(){
        return "/common/information";
    }

    @PostMapping(value="/save_users")//保存新增用户和修改的用户信息
    @ResponseBody
    public Map<String,String> saveUsers(@RequestParam(value="id",required = false, defaultValue = "0") Integer id,
                                        @RequestParam("sno") String sno,
                                        @RequestParam("sname") String sname,
                                        @RequestParam("password") String password,
                                        @RequestParam("tno") String tno,
                                        @RequestParam("tname") String tname,
                                        @RequestParam("tgrade") String tgrade,HttpSession session){
        Map<String,String> map=new HashMap<>();
//        if(session.getAttribute("id").equals(id)){
//            map.put("msg","违法操作！不能修改自己的信息！");
//            return map;
//        }
        try{
            if(id==0){
                commonservice.addusers(sno,sname,password,tno,tname,tgrade);
            }else{
                commonservice.updateusers(id,sno,sname,password,tno,tname,tgrade);
            }
            map.put("success","true");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","请核对信息确保登录名唯一");
        }
        return map;
    }


    @PostMapping(value = "/remove_users")//删除用户
    @ResponseBody
    public Map<String,String> removeUsers(@RequestParam("id") Integer id,HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(session.getAttribute("id").equals(id)){
            result.put("msg","违法操作！不能删除自己！");
            return result;
        }
        try{
            commonservice.removeUsers(id);
            result.put("success","true");
            System.out.println("删除Id: "+id);
        }catch(Exception e) {
            e.printStackTrace();
            result.put("msg","Some errors occured.");
        }
        return result;
    }

}
