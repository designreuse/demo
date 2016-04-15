
package com.ozh.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.ozh.common.entity.BaseEntity;
import com.ozh.common.utils.DateTimeUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "prd_product" )
public class Product extends BaseEntity<Long>{

	public static final String PRODUCT_NM = "productNm";
	public static final String SELLING_POINT = "sellingPoint";
	public static final String PRODUCT_CODING = "productCoding";
	public static final String PRODUCT_TAG = "productTag";
	public static final String IS_ON_SALE = "isOnSale";
	public static final String MARKET_PRICE = "marketPrice";
	public static final String PRODUCT_DESCR = "productDescr";
	public static final String IS_DELETE = "isDelete";
	public static final String POSITION = "position";
	public static final String SALES_VOLUME = "salesVolume";
	public static final String LAST_ON_SALE_DATE = "lastOnSaleDate";
	public static final String LAST_MOD_TIME = "lastModTime";
	public static final String IS_INSTALLMENT = "isInstallment";
	public static final String IS_SUPPORT_COD = "isSupportCod";

    /** PRODUCT_NM - productNm */
    @Column(name = "PRODUCT_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    private String productNm;
    /** SELLING_POINT - sellingPoint */
    @Column(name = "SELLING_POINT", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
    private String sellingPoint;
    /** PRODUCT_CODING - productCoding */
    @Column(name = "PRODUCT_CODING", unique = false, nullable = false, insertable = true, updatable = true, length = 128)
    private String productCoding;
    /** PRODUCT_TAG - productTag */
    @Column(name = "PRODUCT_TAG", unique = false, nullable = false, insertable = true, updatable = true, length = 255)
    private String productTag;
    /** IS_ON_SALE - isOnSale */
    @Column(name = "IS_ON_SALE", unique = false, nullable = false, insertable = true, updatable = true, length = 2)
    private String isOnSale;
    /** MARKET_PRICE - marketPrice */
    @Column(name = "MARKET_PRICE", unique = false, nullable = false, insertable = true, updatable = true, length = 22)
    private Double marketPrice;
    /** PRODUCT_DESCR - productDescr */
    @Column(name = "PRODUCT_DESCR", unique = false, nullable = false, insertable = true, updatable = true, length = 2147483647)
    private byte[] productDescr;
    /** IS_DELETE - isDelete */
    @Column(name = "IS_DELETE", unique = false, nullable = false, insertable = true, updatable = true, length = 2)
    private String isDelete;
    /** POSITION - position */
    @Column(name = "POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Integer position;
    /** SALES_VOLUME - salesVolume */
    @Column(name = "SALES_VOLUME", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Integer salesVolume;
    /** LAST_ON_SALE_DATE - lastOnSaleDate */
    @Column(name = "LAST_ON_SALE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date lastOnSaleDate;
    /** LAST_MOD_TIME - lastModTime */
    @Column(name = "LAST_MOD_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date lastModTime;
    /** IS_INSTALLMENT - isInstallment */
    @Column(name = "IS_INSTALLMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
    private String isInstallment;
    /** IS_SUPPORT_COD - isSupportCod */
    @Column(name = "IS_SUPPORT_COD", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
    private String isSupportCod;

	/** IMAGE_FILE - imageFile */
	@Column(name = "IMAGE_FILE", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	private String imageFile;

    public void setProductNm(String value) {
		this.productNm = value;
	}

    public String getProductNm() {
		return this.productNm;
	}

    public void setSellingPoint(String value) {
		this.sellingPoint = value;
	}

    public String getSellingPoint() {
		return this.sellingPoint;
	}

    public void setProductCoding(String value) {
		this.productCoding = value;
	}

    public String getProductCoding() {
		return this.productCoding;
	}

    public void setProductTag(String value) {
		this.productTag = value;
	}

    public String getProductTag() {
		return this.productTag;
	}

    public void setIsOnSale(String value) {
		this.isOnSale = value;
	}

    public String getIsOnSale() {
		return this.isOnSale;
	}

    public void setMarketPrice(Double value) {
		this.marketPrice = value;
	}

    public Double getMarketPrice() {
		return this.marketPrice;
	}

    public void setProductDescr(byte[] value) {
		this.productDescr = value;
	}

    public byte[] getProductDescr() {
		return this.productDescr;
	}

    public void setIsDelete(String value) {
		this.isDelete = value;
	}

    public String getIsDelete() {
		return this.isDelete;
	}

    public void setPosition(Integer value) {
		this.position = value;
	}

    public Integer getPosition() {
		return this.position;
	}

    public void setSalesVolume(Integer value) {
		this.salesVolume = value;
	}

    public Integer getSalesVolume() {
		return this.salesVolume;
	}

    public void setLastOnSaleDate(java.util.Date value) {
		this.lastOnSaleDate = value;
	}

    public java.util.Date getLastOnSaleDate() {
		return this.lastOnSaleDate;
	}

    public void setLastModTime(java.util.Date value) {
		this.lastModTime = value;
	}

    public java.util.Date getLastModTime() {
		return this.lastModTime;
	}

    public void setIsInstallment(String value) {
		this.isInstallment = value;
	}

    public String getIsInstallment() {
		return this.isInstallment;
	}

    public void setIsSupportCod(String value) {
		this.isSupportCod = value;
	}

    public String getIsSupportCod() {
		return this.isSupportCod;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("PRODUCT_NM",getProductNm())
			.append("SELLING_POINT",getSellingPoint())
			.append("PRODUCT_CODING",getProductCoding())
			.append("PRODUCT_TAG",getProductTag())
			.append("IS_ON_SALE",getIsOnSale())
			.append("MARKET_PRICE",getMarketPrice())
			.append("PRODUCT_DESCR",getProductDescr())
			.append("IS_DELETE",getIsDelete())
			.append("POSITION",getPosition())
			.append("SALES_VOLUME",getSalesVolume())
			.append("LAST_ON_SALE_DATE",getLastOnSaleDate())
			.append("LAST_MOD_TIME",getLastModTime())
			.append("IS_INSTALLMENT",getIsInstallment())
			.append("IS_SUPPORT_COD", getIsSupportCod())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(this.getId() == null){
			return false;
		}
		if(!(obj instanceof Product)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		Product other = (Product)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	public String getLastOnSaleDateString() {
		return DateTimeUtils.convertDateTimeToString(getLastOnSaleDate());
	}
	public String getLastModTimeString() {
		return DateTimeUtils.convertDateTimeToString(getLastModTime());
	}
}

