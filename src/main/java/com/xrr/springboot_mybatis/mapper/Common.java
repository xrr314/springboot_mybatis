package com.xrr.springboot_mybatis.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import com.xrr.springboot_mybatis.entity.Stu;

import java.util.List;

/**
 * Created by MWL on 2017/11/25.
 */
@Component
public interface Common {

    //登陆成功后查询用户的学号
    @Select("select sno from stu where sno=#{sno} and password=#{password}")
    public String getsno(@Param("sno") String sno, @Param("password") String password);

    //登陆成功后查询用户的姓名
    @Select("select sname from stu where sno=#{sno} and password=#{password}")
    public String login(@Param("sno") String sno, @Param("password") String password);

    //登陆成功后查询用户的所有信息
    @Select("select id,sno,sname from stu where sno=#{sno}")
    public List<Stu> userinfor(@Param("sno") String sno);

    //用户表中数据记录数
    @Select("select count(*) from stu")
    public int gettstunumber();

    //分页展示
    @Select("select * from stu limit #{startRecord},#{pageSize}")
    public List<Stu> stuinfo(@Param("startRecord") int startRecord, @Param("pageSize") int pageSize);

    @Insert("insert into stu (sno,sname,password,tno,tname,tgrade) values(#{sno},#{sname},#{password},#{tno},#{tname},#{tgrade})")
    public void addusers(@Param("sno") String sno, @Param("sname") String sname, @Param("password") String password, @Param("tno") String tno, @Param("tname") String tname, @Param("tgrade") String tgrade);

    @Update("update stu set sno=#{sno},sname=#{sname},password=#{password},tno=#{tno},tname=#{tname},tgrade=#{tgrade} where id=#{id}")
    public void updateusers(@Param("id") Integer id, @Param("sno") String sno, @Param("sname") String sname, @Param("password") String password, @Param("tno") String tno, @Param("tname") String tname, @Param("tgrade") String tgrade);

    @Delete("DELETE FROM stu WHERE id = #{id}")
    public void removeUsers(@Param("id") Integer id);

}
