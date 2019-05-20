package com.xrr.springboot_mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xrr.springboot_mybatis.entity.Stu;
import com.xrr.springboot_mybatis.mapper.Common;

import java.util.List;

/**
 * Created by MWL on 2017/11/25.
 */
@Service
public class CommonService {
    @Autowired
    public Common commonmapper;

    public String getsno(String sno,String password){
        return commonmapper.getsno(sno, password);
    }

    public String login(String sno, String password){
        return commonmapper.login(sno, password);
    }

    public List<Stu> userinfor(String sno){
        return commonmapper.userinfor(sno);
    }


    public int gettstunumber( ){
        return commonmapper.gettstunumber();
    }
    public List<Stu> stuinfo(int startRecord,int pageSize){
        return commonmapper.stuinfo(startRecord, pageSize);
    }

    public void addusers(String sno,String sname,String password,String tno,String tname,String tgrade){
        commonmapper.addusers(sno, sname, password, tno, tname, tgrade);
    }

    public void updateusers(Integer id,String sno,String sname,String password,String tno,String tname,String tgrade){
        commonmapper.updateusers(id,sno, sname, password, tno, tname, tgrade);
    }

    public void removeUsers(Integer id){
        commonmapper.removeUsers(id);
    }
}
