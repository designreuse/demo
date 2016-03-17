package com.ozh.core.demo.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ozh.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by ksoam on 2016/2/27.
 */
@Entity
@Table(name="t_user_info")
public class UserInfo extends BaseEntity<Long>{

    //alias
    public static final String TABLE_ALIAS = "TuserInfo";
    public static final String ID = "id";
    public static final String USER_ACCOUNT = "userAccount";
    public static final String USER_PASSWORD = "userPassword";
    public static final String USE_NAME = "useName";

    //date formats

    /**
     * userAccount	   db_column: USER_ACCOUNT
     */
    @Column(name = "USER_ACCOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    private java.lang.String userAccount;
    /**
     * userPassword	   db_column: USER_PASSWORD
     */
    @Column(name = "USER_PASSWORD", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    private java.lang.String userPassword;
    /**
     * useName	   db_column: USE_NAME
     */
    @Column(name = "USE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
    private java.lang.String useName;
    //columns END

    public void setUserAccount(java.lang.String value) {
        this.userAccount = value;
    }

    public java.lang.String getUserAccount() {
        return this.userAccount;
    }
    public void setUserPassword(java.lang.String value) {
        this.userPassword = value;
    }

    public java.lang.String getUserPassword() {
        return this.userPassword;
    }
    public void setUseName(java.lang.String value) {
        this.useName = value;
    }

    public java.lang.String getUseName() {
        return this.useName;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ID",getId())
                .append("USER_ACCOUNT",getUserAccount())
                .append("USER_PASSWORD",getUserPassword())
                .append("USE_NAME",getUseName())
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
        if(!(obj instanceof UserInfo)){
            return false;
        }
        if(this == obj) {
            return true;
        }
        UserInfo other = (UserInfo)obj;
        return new EqualsBuilder()
                .append(getId(),other.getId())
                .isEquals();
    }
}
