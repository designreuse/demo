package com.ozh.common.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.ozh.common.utils.DateTimeUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by kosam on 2016/2/28.
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity<ID extends Serializable> implements Persistable<ID> {
    private static final long serialVersionUID = -4829990623102036715L;
    public static final String ID = "id";
    public static final String CREATE_BY = "createBy";
    public static final String CREATE_DATE = "createDate";
    public static final String LAST_MODIFIED_BY = "lastModifiedBy";
    public static final String LAST_MODIFIED_DATE = "lastModifiedDate";
//    public static final String VERSION = "version";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    @CreatedBy
    @Column(updatable = false, name = "create_by",nullable = true)
    private String createBy;

    @CreatedDate
    @Column(updatable = false, name = "create_date",nullable = true)
    private Date createDate;

    @LastModifiedBy
    @Column(name = "last_modified_by",nullable = true)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date",nullable = true)
    private Date lastModifiedDate;
//
//    @Version
//    @Column(name = "version",nullable = true)
//    private Long version;

    public void setId(ID id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getCreateDateString() {
        return DateTimeUtils.convertDateTimeToString(getCreateDate());
    }
    public void setCreateDateString(String value) throws ParseException {
        setCreateDate(DateTimeUtils.convertStringToDateTime(value));
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedDateString() {
        return DateTimeUtils.convertDateTimeToString(getLastModifiedDate());
    }
    public void setLastModifiedDateString(String value) throws ParseException {
        setLastModifiedDate(DateTimeUtils.convertStringToDateTime(value));
    }

//    public Long getVersion() {
//        return version;
//    }
//
//    public void setVersion(Long version) {
//        this.version = version;
//    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return (null == getId()) || ("".equals(getId().toString()));
    }

//    public String toString() {
//        return "BaseEntity [id=" + this.id + ", createBy=" + this.createBy + ", createDate=" + this.createDate + ", lastModifiedBy=" + this.lastModifiedBy + ", lastModifiedDate=" + this.lastModifiedDate + ", version=" + this.version + "]";
//    }

    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();
        result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        return true;
    }
}
