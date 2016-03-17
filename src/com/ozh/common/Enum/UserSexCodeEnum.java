package com.ozh.common.Enum;

import com.ozh.common.entity.ICodeEnum;

/**
 * 用户性别代码.
 * @author ozh
 *
 */
public enum UserSexCodeEnum  implements ICodeEnum {

    /**
     * 男
     */
    MALE,
    /**
     * 女
     */
    FEMALE,
    /**
     * 保密
     */
    SECRET;

    public static UserSexCodeEnum fromCode(String code){
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
