package com.xrr.springboot_mybatis.entity;

/**
 * Created by MWL on 2017/11/25.
 */
public class Stu {
    Integer id;
    public String sno;
    public String sname;
    public String password;
    public String tno;
    public String tname;
    public String tgrade;

    public Stu() {
    }

    public Stu (String sno, String sname, String password, String tno, String tname, String tgrade){
        this.sname=sname;
        this.password=password;
        this.tno=tno;
        this.tname=tname;
        this.tgrade=tgrade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTgrade() {
        return tgrade;
    }

    public void setTgrade(String tgrade) {
        this.tgrade = tgrade;
    }

    @Override
    public String toString() {
        return  sno + " " + sname + " " +password + " " +tno + " " +tname + " " +tgrade;
    }
}
