package com.ozh.common.entity;

/**
 * Created by lxd on 2014/11/27.
 */
public enum BoolCode implements ICodeEnum {
    NO,
    YES
    ;
    public static BoolCode fromCode(String code) {
        try {
            return values()[Integer.parseInt(code)];
        } catch (Exception e) {
            return null;
        }
    }

    public static BoolCode fromCode(Integer code) {
        try {
            return values()[code];
        } catch (Exception e) {
            return null;
        }
    }

    public boolean boolValue() {
        return this.ordinal() == 1;
    }

    @Override
    public String toCode() {
        return Integer.toString(this.ordinal());
    }
}
