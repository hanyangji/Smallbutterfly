package com.oracle.entity;

public class TradeDetails {
    @Override
	public String toString() {
		return "TradeDetails [分公司代码=" + 分公司代码 + ", 收单机构代码=" + 收单机构代码 + ", 三十二域机构代码=" + 三十二域机构代码 + ", 发送机构代码=" + 发送机构代码
				+ ", 商户编码=" + 商户编码 + ", 商户名称=" + 商户名称 + ", 商户营业名称=" + 商户营业名称 + ", mcc十八域=" + mcc十八域 + ", mcc四十二域="
				+ mcc四十二域 + ", mcc大类=" + mcc大类 + ", 交易日期及时间=" + 交易日期及时间 + ", 清算日期=" + 清算日期 + ", 交易分类=" + 交易分类
				+ ", 交易类型=" + 交易类型 + ", 交易状态=" + 交易状态 + ", 清算标志=" + 清算标志 + ", 交易金额=" + 交易金额 + ", 交易卡号=" + 交易卡号
				+ ", 卡类别=" + 卡类别 + ", 发卡机构代码=" + 发卡机构代码 + ", 发卡机构代名称=" + 发卡机构代名称 + ", 分店代码=" + 分店代码 + ", 分店名称=" + 分店名称
				+ ", 终端编码=" + 终端编码 + ", 商户手续费=" + 商户手续费 + ", 商户产品手续费=" + 商户产品手续费 + ", 商户结算金额=" + 商户结算金额 + ", 集团商户编码="
				+ 集团商户编码 + ", 银联卡境内外标志=" + 银联卡境内外标志 + ", 特殊计费类型=" + 特殊计费类型 + ", 特殊计费档次=" + 特殊计费档次 + ", 输入点方式=" + 输入点方式
				+ ", 系统跟踪号=" + 系统跟踪号 + ", 通联收益=" + 通联收益 + ", 通联应付品牌服务费=" + 通联应付品牌服务费 + ", 支付方式=" + 支付方式 + ", 检索参考号="
				+ 检索参考号 + ", wechatOpenid=" + wechatOpenid + ", 近场扫码支付场景方式=" + 近场扫码支付场景方式 + ", 商户订单号=" + 商户订单号
				+ ", 银联双免标识=" + 银联双免标识 + ", 银联系交易成本优惠标识=" + 银联系交易成本优惠标识 + ", insertDate=" + insertDate + "]";
	}

	private String 分公司代码;

    private String 收单机构代码;

    private String 三十二域机构代码;

    private String 发送机构代码;

    private String 商户编码;

    private String 商户名称;

    private String 商户营业名称;

    private String mcc十八域;

    private String mcc四十二域;

    private String mcc大类;

    private String 交易日期及时间;

    private String 清算日期;

    private String 交易分类;

    private String 交易类型;

    private String 交易状态;

    private String 清算标志;

    private Double 交易金额;

    private String 交易卡号;

    private String 卡类别;

    private String 发卡机构代码;

    private String 发卡机构代名称;

    private String 分店代码;

    private String 分店名称;

    private String 终端编码;

    private Double 商户手续费;

    private Double 商户产品手续费;

    private Double 商户结算金额;

    private String 集团商户编码;

    private String 银联卡境内外标志;

    private String 特殊计费类型;

    private String 特殊计费档次;

    private String 输入点方式;

    private String 系统跟踪号;

    private Double 通联收益;

    private Double 通联应付品牌服务费;

    private String 支付方式;

    private String 检索参考号;

    private String wechatOpenid;

    private String 近场扫码支付场景方式;

    private String 商户订单号;

    private String 银联双免标识;

    private String 银联系交易成本优惠标识;

    private String insertDate;

    public String get分公司代码() {
        return 分公司代码;
    }

    public void set分公司代码(String 分公司代码) {
        this.分公司代码 = 分公司代码 == null ? null : 分公司代码.trim();
    }

    public String get收单机构代码() {
        return 收单机构代码;
    }

    public void set收单机构代码(String 收单机构代码) {
        this.收单机构代码 = 收单机构代码 == null ? null : 收单机构代码.trim();
    }



    public String get发送机构代码() {
        return 发送机构代码;
    }

    public void set发送机构代码(String 发送机构代码) {
        this.发送机构代码 = 发送机构代码 == null ? null : 发送机构代码.trim();
    }

    public String get商户编码() {
        return 商户编码;
    }

    public void set商户编码(String 商户编码) {
        this.商户编码 = 商户编码 == null ? null : 商户编码.trim();
    }

    public String get商户名称() {
        return 商户名称;
    }

    public void set商户名称(String 商户名称) {
        this.商户名称 = 商户名称 == null ? null : 商户名称.trim();
    }

    public String get商户营业名称() {
        return 商户营业名称;
    }

    public void set商户营业名称(String 商户营业名称) {
        this.商户营业名称 = 商户营业名称 == null ? null : 商户营业名称.trim();
    }



    public String get三十二域机构代码() {
		return 三十二域机构代码;
	}

	public void set三十二域机构代码(String 三十二域机构代码) {
		this.三十二域机构代码 = 三十二域机构代码;
	}

	public String getMcc十八域() {
		return mcc十八域;
	}

	public void setMcc十八域(String mcc十八域) {
		this.mcc十八域 = mcc十八域;
	}

	public String getMcc四十二域() {
		return mcc四十二域;
	}

	public void setMcc四十二域(String mcc四十二域) {
		this.mcc四十二域 = mcc四十二域;
	}

	public String getMcc大类() {
        return mcc大类;
    }

    public void setMcc大类(String mcc大类) {
        this.mcc大类 = mcc大类 == null ? null : mcc大类.trim();
    }

    public String get交易日期及时间() {
        return 交易日期及时间;
    }

    public void set交易日期及时间(String 交易日期及时间) {
        this.交易日期及时间 = 交易日期及时间 == null ? null : 交易日期及时间.trim();
    }

    public String get清算日期() {
        return 清算日期;
    }

    public void set清算日期(String 清算日期) {
        this.清算日期 = 清算日期 == null ? null : 清算日期.trim();
    }

    public String get交易分类() {
        return 交易分类;
    }

    public void set交易分类(String 交易分类) {
        this.交易分类 = 交易分类 == null ? null : 交易分类.trim();
    }

    public String get交易类型() {
        return 交易类型;
    }

    public void set交易类型(String 交易类型) {
        this.交易类型 = 交易类型 == null ? null : 交易类型.trim();
    }

    public String get交易状态() {
        return 交易状态;
    }

    public void set交易状态(String 交易状态) {
        this.交易状态 = 交易状态 == null ? null : 交易状态.trim();
    }

    public String get清算标志() {
        return 清算标志;
    }

    public void set清算标志(String 清算标志) {
        this.清算标志 = 清算标志 == null ? null : 清算标志.trim();
    }

    public Double get交易金额() {
        return 交易金额;
    }

    public void set交易金额(Double 交易金额) {
        this.交易金额 = 交易金额;
    }

    public String get交易卡号() {
        return 交易卡号;
    }

    public void set交易卡号(String 交易卡号) {
        this.交易卡号 = 交易卡号 == null ? null : 交易卡号.trim();
    }

    public String get卡类别() {
        return 卡类别;
    }

    public void set卡类别(String 卡类别) {
        this.卡类别 = 卡类别 == null ? null : 卡类别.trim();
    }

    public String get发卡机构代码() {
        return 发卡机构代码;
    }

    public void set发卡机构代码(String 发卡机构代码) {
        this.发卡机构代码 = 发卡机构代码 == null ? null : 发卡机构代码.trim();
    }

    public String get发卡机构代名称() {
        return 发卡机构代名称;
    }

    public void set发卡机构代名称(String 发卡机构代名称) {
        this.发卡机构代名称 = 发卡机构代名称 == null ? null : 发卡机构代名称.trim();
    }

    public String get分店代码() {
        return 分店代码;
    }

    public void set分店代码(String 分店代码) {
        this.分店代码 = 分店代码 == null ? null : 分店代码.trim();
    }

    public String get分店名称() {
        return 分店名称;
    }

    public void set分店名称(String 分店名称) {
        this.分店名称 = 分店名称 == null ? null : 分店名称.trim();
    }

    public String get终端编码() {
        return 终端编码;
    }

    public void set终端编码(String 终端编码) {
        this.终端编码 = 终端编码 == null ? null : 终端编码.trim();
    }

    public Double get商户手续费() {
        return 商户手续费;
    }

    public void set商户手续费(Double 商户手续费) {
        this.商户手续费 = 商户手续费;
    }

    public Double get商户产品手续费() {
        return 商户产品手续费;
    }

    public void set商户产品手续费(Double 商户产品手续费) {
        this.商户产品手续费 = 商户产品手续费;
    }

    public Double get商户结算金额() {
        return 商户结算金额;
    }

    public void set商户结算金额(Double 商户结算金额) {
        this.商户结算金额 = 商户结算金额;
    }

    public String get集团商户编码() {
        return 集团商户编码;
    }

    public void set集团商户编码(String 集团商户编码) {
        this.集团商户编码 = 集团商户编码 == null ? null : 集团商户编码.trim();
    }

    public String get银联卡境内外标志() {
        return 银联卡境内外标志;
    }

    public void set银联卡境内外标志(String 银联卡境内外标志) {
        this.银联卡境内外标志 = 银联卡境内外标志 == null ? null : 银联卡境内外标志.trim();
    }

    public String get特殊计费类型() {
        return 特殊计费类型;
    }

    public void set特殊计费类型(String 特殊计费类型) {
        this.特殊计费类型 = 特殊计费类型 == null ? null : 特殊计费类型.trim();
    }

    public String get特殊计费档次() {
        return 特殊计费档次;
    }

    public void set特殊计费档次(String 特殊计费档次) {
        this.特殊计费档次 = 特殊计费档次 == null ? null : 特殊计费档次.trim();
    }

    public String get输入点方式() {
        return 输入点方式;
    }

    public void set输入点方式(String 输入点方式) {
        this.输入点方式 = 输入点方式 == null ? null : 输入点方式.trim();
    }

    public String get系统跟踪号() {
        return 系统跟踪号;
    }

    public void set系统跟踪号(String 系统跟踪号) {
        this.系统跟踪号 = 系统跟踪号 == null ? null : 系统跟踪号.trim();
    }

    public Double get通联收益() {
        return 通联收益;
    }

    public void set通联收益(Double 通联收益) {
        this.通联收益 = 通联收益;
    }

    public Double get通联应付品牌服务费() {
        return 通联应付品牌服务费;
    }

    public void set通联应付品牌服务费(Double 通联应付品牌服务费) {
        this.通联应付品牌服务费 = 通联应付品牌服务费;
    }

    public String get支付方式() {
        return 支付方式;
    }

    public void set支付方式(String 支付方式) {
        this.支付方式 = 支付方式 == null ? null : 支付方式.trim();
    }

    public String get检索参考号() {
        return 检索参考号;
    }

    public void set检索参考号(String 检索参考号) {
        this.检索参考号 = 检索参考号 == null ? null : 检索参考号.trim();
    }

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid == null ? null : wechatOpenid.trim();
    }

    public String get近场扫码支付场景方式() {
        return 近场扫码支付场景方式;
    }

    public void set近场扫码支付场景方式(String 近场扫码支付场景方式) {
        this.近场扫码支付场景方式 = 近场扫码支付场景方式 == null ? null : 近场扫码支付场景方式.trim();
    }

    public String get商户订单号() {
        return 商户订单号;
    }

    public void set商户订单号(String 商户订单号) {
        this.商户订单号 = 商户订单号 == null ? null : 商户订单号.trim();
    }

    public String get银联双免标识() {
        return 银联双免标识;
    }

    public void set银联双免标识(String 银联双免标识) {
        this.银联双免标识 = 银联双免标识 == null ? null : 银联双免标识.trim();
    }

    public String get银联系交易成本优惠标识() {
        return 银联系交易成本优惠标识;
    }

    public void set银联系交易成本优惠标识(String 银联系交易成本优惠标识) {
        this.银联系交易成本优惠标识 = 银联系交易成本优惠标识 == null ? null : 银联系交易成本优惠标识.trim();
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate == null ? null : insertDate.trim();
    }
}