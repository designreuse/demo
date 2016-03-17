
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
@Table(name = "sys_user_role_relationship" )
public class SysUserRoleRelationship extends BaseEntity<Long>{

	public static final String SYS_USER_ID = "sysUserId";
	public static final String SYS_ROLE_ID = "sysRoleId";

    /** SYS_USER_ID - sysUserId */
    @Column(name = "SYS_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Integer sysUserId;
    /** SYS_ROLE_ID - sysRoleId */
    @Column(name = "SYS_ROLE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Integer sysRoleId;

    public void setSysUserId(Integer value) {
		this.sysUserId = value;
	}

    public Integer getSysUserId() {
		return this.sysUserId;
	}

    public void setSysRoleId(Integer value) {
		this.sysRoleId = value;
	}

    public Integer getSysRoleId() {
		return this.sysRoleId;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SYS_USER_ID",getSysUserId())
			.append("SYS_ROLE_ID",getSysRoleId())
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
		if(!(obj instanceof SysUserRoleRelationship)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysUserRoleRelationship other = (SysUserRoleRelationship)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

