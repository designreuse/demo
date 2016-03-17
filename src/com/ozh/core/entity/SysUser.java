
package com.ozh.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.ozh.common.entity.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.math.BigDecimal;
/**
 * 实体类
 * @author by imall core generator
 * @version 1.0.0
 */
@Entity
@Table(name = "sys_user" )
public class SysUser extends BaseEntity<Long>{

	public static final String LOGIN_ID = "loginId";
	public static final String IS_ADMIN = "isAdmin";
	public static final String USER_NAME = "userName";
	public static final String USER_PSW = "userPsw";
	public static final String USER_MOBILE = "userMobile";
	public static final String USER_TEL = "userTel";
	public static final String USER_SEX_CODE = "userSexCode";
	public static final String USER_EMAIL = "userEmail";
	public static final String REGISTER_DATE = "registerDate";
	public static final String REG_ORG_ID = "regOrgId";
	public static final String USER_ICON = "userIcon";
	public static final String IS_DELETE = "isDelete";
	public static final String LAST_PSW_MODIFY_TIME = "lastPswModifyTime";
	public static final String LAST_BUY_TIME = "lastBuyTime";

    /** LOGIN_ID - loginId */
    @Column(name = "LOGIN_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String loginId;
    /** IS_ADMIN - isAdmin */
    @Column(name = "IS_ADMIN", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private String isAdmin;
    /** USER_NAME - userName */
    @Column(name = "USER_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 48)
    private String userName;
    /** USER_PSW - userPsw */
    @Column(name = "USER_PSW", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String userPsw;
    /** USER_MOBILE - userMobile */
    @Column(name = "USER_MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 24)
    private String userMobile;
    /** USER_TEL - userTel */
    @Column(name = "USER_TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 24)
    private String userTel;
    /** USER_SEX_CODE - userSexCode */
    @Column(name = "USER_SEX_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String userSexCode;
    /** USER_EMAIL - userEmail */
    @Column(name = "USER_EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 32)
    private String userEmail;
    /** REGISTER_DATE - registerDate */
    @Column(name = "REGISTER_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date registerDate;
    /** REG_ORG_ID - regOrgId */
    @Column(name = "REG_ORG_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private Integer regOrgId;
    /** USER_ICON - userIcon */
    @Column(name = "USER_ICON", unique = false, nullable = true, insertable = true, updatable = true, length = 60)
    private String userIcon;
    /** IS_DELETE - isDelete */
    @Column(name = "IS_DELETE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String isDelete;
    /** LAST_PSW_MODIFY_TIME - lastPswModifyTime */
    @Column(name = "LAST_PSW_MODIFY_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date lastPswModifyTime;
    /** LAST_BUY_TIME - lastBuyTime */
    @Column(name = "LAST_BUY_TIME", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
    private java.util.Date lastBuyTime;

    public void setLoginId(String value) {
		this.loginId = value;
	}

    public String getLoginId() {
		return this.loginId;
	}

    public void setIsAdmin(String value) {
		this.isAdmin = value;
	}

    public String getIsAdmin() {
		return this.isAdmin;
	}

    public void setUserName(String value) {
		this.userName = value;
	}

    public String getUserName() {
		return this.userName;
	}

    public void setUserPsw(String value) {
		this.userPsw = value;
	}

    public String getUserPsw() {
		return this.userPsw;
	}

    public void setUserMobile(String value) {
		this.userMobile = value;
	}

    public String getUserMobile() {
		return this.userMobile;
	}

    public void setUserTel(String value) {
		this.userTel = value;
	}

    public String getUserTel() {
		return this.userTel;
	}

    public void setUserSexCode(String value) {
		this.userSexCode = value;
	}

    public String getUserSexCode() {
		return this.userSexCode;
	}

    public void setUserEmail(String value) {
		this.userEmail = value;
	}

    public String getUserEmail() {
		return this.userEmail;
	}

    public void setRegisterDate(java.util.Date value) {
		this.registerDate = value;
	}

    public java.util.Date getRegisterDate() {
		return this.registerDate;
	}

    public void setRegOrgId(Integer value) {
		this.regOrgId = value;
	}

    public Integer getRegOrgId() {
		return this.regOrgId;
	}

    public void setUserIcon(String value) {
		this.userIcon = value;
	}

    public String getUserIcon() {
		return this.userIcon;
	}

    public void setIsDelete(String value) {
		this.isDelete = value;
	}

    public String getIsDelete() {
		return this.isDelete;
	}

    public void setLastPswModifyTime(java.util.Date value) {
		this.lastPswModifyTime = value;
	}

    public java.util.Date getLastPswModifyTime() {
		return this.lastPswModifyTime;
	}

    public void setLastBuyTime(java.util.Date value) {
		this.lastBuyTime = value;
	}

    public java.util.Date getLastBuyTime() {
		return this.lastBuyTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("LOGIN_ID",getLoginId())
			.append("IS_ADMIN",getIsAdmin())
			.append("USER_NAME",getUserName())
			.append("USER_PSW",getUserPsw())
			.append("USER_MOBILE",getUserMobile())
			.append("USER_TEL",getUserTel())
			.append("USER_SEX_CODE",getUserSexCode())
			.append("USER_EMAIL",getUserEmail())
			.append("REGISTER_DATE",getRegisterDate())
			.append("REG_ORG_ID",getRegOrgId())
			.append("USER_ICON",getUserIcon())
			.append("IS_DELETE",getIsDelete())
			.append("LAST_PSW_MODIFY_TIME",getLastPswModifyTime())
			.append("LAST_BUY_TIME",getLastBuyTime())
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
		if(!(obj instanceof SysUser)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysUser other = (SysUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

