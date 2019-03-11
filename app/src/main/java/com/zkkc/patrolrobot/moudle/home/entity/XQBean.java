package com.zkkc.patrolrobot.moudle.home.entity;

/**
 * Created by ShiJunRan on 2019/3/11
 * 详情展示popup列表
 */
public class XQBean {
    private int jdNum;
    private String thNum;
    private String jj;
    private String leftJd;
    private String rightJd;

    public XQBean(int jdNum, String thNum, String jj, String leftJd, String rightJd) {
        this.jdNum = jdNum;
        this.thNum = thNum;
        this.jj = jj;
        this.leftJd = leftJd;
        this.rightJd = rightJd;
    }

    public XQBean() {
    }

    public int getJdNum() {
        return jdNum;
    }

    public void setJdNum(int jdNum) {
        this.jdNum = jdNum;
    }

    public String getThNum() {
        return thNum;
    }

    public void setThNum(String thNum) {
        this.thNum = thNum;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj;
    }

    public String getLeftJd() {
        return leftJd;
    }

    public void setLeftJd(String leftJd) {
        this.leftJd = leftJd;
    }

    public String getRightJd() {
        return rightJd;
    }

    public void setRightJd(String rightJd) {
        this.rightJd = rightJd;
    }
}
