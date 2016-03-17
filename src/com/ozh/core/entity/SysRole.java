
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
@Table(name = "sys_role" )
public class SysRole extends BaseEntity<Long>{

	public static final String SYS_ROLE_NM = "sysRoleNm";
	public static final String ROLE_TYPE_CODE = "roleTypeCode";
	public static final String POSITION = "position";
	public static final String SCORE = "score";

    /** SYS_ROLE_NM - sysRoleNm */
    @Column(name = "SYS_ROLE_NM", unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    private String sysRoleNm;
    /** ROLE_TYPE_CODE - roleTypeCode */
    @Column(name = "ROLE_TYPE_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 1)
    private String roleTypeCode;
    /** POSITION - position */
    @Column(name = "POSITION", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Integer position;
    /** SCORE - score */
    @Column(name = "SCORE", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    private Integer score;

    public void setSysRoleNm(String value) {
		this.sysRoleNm = value;
	}

    public String getSysRoleNm() {
		return this.sysRoleNm;
	}

    public void setRoleTypeCode(String value) {
		this.roleTypeCode = value;
	}

    public String getRoleTypeCode() {
		return this.roleTypeCode;
	}

    public void setPosition(Integer value) {
		this.position = value;
	}

    public Integer getPosition() {
		return this.position;
	}

    public void setScore(Integer value) {
		this.score = value;
	}

    public Integer getScore() {
		return this.score;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("SYS_ROLE_NM",getSysRoleNm())
			.append("ROLE_TYPE_CODE",getRoleTypeCode())
			.append("POSITION",getPosition())
			.append("SCORE",getScore())
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
		if(!(obj instanceof SysRole)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		SysRole other = (SysRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

