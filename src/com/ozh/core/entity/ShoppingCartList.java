
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
@Table(name = "shopping_cart_list" )
public class ShoppingCartList extends BaseEntity<Long>{

	public static final String USER_ID = "userId";
	public static final String PRODUCT_ID = "productId";
	public static final String QUANTITY = "quantity";
	public static final String IS_DELETE = "isDelete";

    /** USER_ID - userId */
    @Column(name = "USER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    private Long userId;
    /** PRODUCT_ID - productId */
    @Column(name = "PRODUCT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    private Long productId;
    /** QUANTITY - quantity */
    @Column(name = "QUANTITY", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
    private Integer quantity;
    /** IS_DELETE - isDelete */
    @Column(name = "IS_DELETE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
    private String isDelete;

    public void setUserId(Long value) {
		this.userId = value;
	}

    public Long getUserId() {
		return this.userId;
	}

    public void setProductId(Long value) {
		this.productId = value;
	}

    public Long getProductId() {
		return this.productId;
	}

    public void setQuantity(Integer value) {
		this.quantity = value;
	}

    public Integer getQuantity() {
		return this.quantity;
	}

    public void setIsDelete(String value) {
		this.isDelete = value;
	}

    public String getIsDelete() {
		return this.isDelete;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ID",getId())
			.append("USER_ID",getUserId())
			.append("PRODUCT_ID",getProductId())
			.append("QUANTITY",getQuantity())
			.append("CREATE_DATE",getCreateDate())
			.append("LAST_MODIFIED_DATE",getLastModifiedDate())
			.append("LAST_MODIFIED_BY",getLastModifiedBy())
			.append("IS_DELETE",getIsDelete())
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
		if(!(obj instanceof ShoppingCartList)){
			return false;
		}
		if(this == obj) {
			return true;
		}
		ShoppingCartList other = (ShoppingCartList)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

