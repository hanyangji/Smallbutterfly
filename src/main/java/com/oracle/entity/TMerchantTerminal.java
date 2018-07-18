package com.oracle.entity;

public class TMerchantTerminal {
    private String customerid;

    private String branchid;

    private String branchname;

    private String branchaddress;

    private String branchcontact;

    private String branchtel;

    private String creatdate;

    private String modifydate;

    private String terminalid;

    private String terminaltype;

    private String terminalstatus;

    private String property;

    private String terminalserial;

    private String terminalbrand;

    private String terminalmodel;

    private String remark;

    private String logoutdate;

    private String deposit;

    private String maintainfee;

    private String communicationfee;

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid == null ? null : customerid.trim();
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid == null ? null : branchid.trim();
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname == null ? null : branchname.trim();
    }

    public String getBranchaddress() {
        return branchaddress;
    }

    public void setBranchaddress(String branchaddress) {
        this.branchaddress = branchaddress == null ? null : branchaddress.trim();
    }

    public String getBranchcontact() {
        return branchcontact;
    }

    public void setBranchcontact(String branchcontact) {
        this.branchcontact = branchcontact == null ? null : branchcontact.trim();
    }

    public String getBranchtel() {
        return branchtel;
    }

    public void setBranchtel(String branchtel) {
        this.branchtel = branchtel == null ? null : branchtel.trim();
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate == null ? null : creatdate.trim();
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate == null ? null : modifydate.trim();
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid == null ? null : terminalid.trim();
    }

    public String getTerminaltype() {
        return terminaltype;
    }

    public void setTerminaltype(String terminaltype) {
        this.terminaltype = terminaltype == null ? null : terminaltype.trim();
    }

    public String getTerminalstatus() {
        return terminalstatus;
    }

    public void setTerminalstatus(String terminalstatus) {
        this.terminalstatus = terminalstatus == null ? null : terminalstatus.trim();
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public String getTerminalserial() {
        return terminalserial;
    }

    public void setTerminalserial(String terminalserial) {
        this.terminalserial = terminalserial == null ? null : terminalserial.trim();
    }

    public String getTerminalbrand() {
        return terminalbrand;
    }

    public void setTerminalbrand(String terminalbrand) {
        this.terminalbrand = terminalbrand == null ? null : terminalbrand.trim();
    }

    public String getTerminalmodel() {
        return terminalmodel;
    }

    public void setTerminalmodel(String terminalmodel) {
        this.terminalmodel = terminalmodel == null ? null : terminalmodel.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getLogoutdate() {
        return logoutdate;
    }

    public void setLogoutdate(String logoutdate) {
        this.logoutdate = logoutdate == null ? null : logoutdate.trim();
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit == null ? null : deposit.trim();
    }

    public String getMaintainfee() {
        return maintainfee;
    }

    public void setMaintainfee(String maintainfee) {
        this.maintainfee = maintainfee == null ? null : maintainfee.trim();
    }

    public String getCommunicationfee() {
        return communicationfee;
    }

    public void setCommunicationfee(String communicationfee) {
        this.communicationfee = communicationfee == null ? null : communicationfee.trim();
    }
}