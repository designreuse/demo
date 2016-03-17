package com.ozh.common.Enum;

import com.ozh.common.entity.ICodeEnum;

/**
 * Created by ozh on 2016/3/2.
 /**
 * 用户性别代码.
 * @author wj
 *
 */
public enum RoleTypeCodeEnum  implements ICodeEnum {

    /**
     * 后台用户
     */
    BACKGROUND,
    /**
     * 前台用户
     */
    FRONT;

    public static RoleTypeCodeEnum fromCode(String code){
        try{
            return values()[Integer.parseInt(code)];
        }catch(Exception e){
            return null;
        }
    }

    public String toCode(){
        return Integer.toString(this.ordinal());
    }
}
