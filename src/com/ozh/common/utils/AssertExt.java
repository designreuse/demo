package com.ozh.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 对spring的Assert进行加强，增加更多丰富的判断检查和对BusinessExceptin的支持
 * User: lxd
 * Date: 2010-12-26
 * Time: 14:20:19
 */
public final class AssertExt extends Assert {
    private AssertExt() {
    }

    public static void equals(String expected, String actual) {
        StringBuffer messageBuf = new StringBuffer();
        messageBuf.append("Expected:").append(expected).append("\r\n");
        messageBuf.append("Actual:").append(actual).append("\r\n");
        messageBuf.append("equals failure");
        equals(expected, actual, messageBuf.toString());
    }

    public static void equals(String expected, String actual, String message) {
        if (!StringUtils.equalsIgnoreCase(expected, actual)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void equals(String expected, String actual, BusinessException exception) throws BusinessException {
        if (!StringUtils.equalsIgnoreCase(expected, actual)) {
            throw exception;
        }
    }

    public static void equals(int expected, int actual, BusinessException exception) throws BusinessException {
        if (expected != actual) {
            throw exception;
        }
    }

    public static void isTrue(boolean expression, BusinessException exception) throws BusinessException {
        if (!expression) {
            throw exception;
        }
    }

    public static void isNull(Object object, BusinessException exception) throws BusinessException {
        if (object != null) {
            throw exception;
        }
    }

    public static void notNull(Object object, BusinessException exception) throws BusinessException {
        if (object == null) {
            throw exception;
        }
    }

    public static void hasLength(String text, BusinessException exception) throws BusinessException {
        if (!org.springframework.util.StringUtils.hasLength(text)) {
            throw exception;
        }
    }

    public static void hasText(String text, BusinessException exception) throws BusinessException {
        if (!org.springframework.util.StringUtils.hasText(text)) {
            throw exception;
        }
    }

    public static void doesNotContain(String textToSearch, String substring, BusinessException exception) throws BusinessException {
        if (org.springframework.util.StringUtils.hasLength(textToSearch)
                && org.springframework.util.StringUtils.hasLength(substring) &&
                textToSearch.indexOf(substring) != -1) {
            throw exception;
        }
    }

    public static void notEmpty(Object[] array, BusinessException exception) throws BusinessException {
        if (ObjectUtils.isEmpty(array)) {
            throw exception;
        }
    }

    public static void noNullElements(Object[] array, BusinessException exception) throws BusinessException {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    throw exception;
                }
            }
        }
    }

    public static void notEmpty(Collection collection, BusinessException exception) throws BusinessException {
        if (CollectionUtils.isEmpty(collection)) {
            throw exception;
        }
    }

    public static void empty(Collection collection, BusinessException exception) throws BusinessException {
        if (!CollectionUtils.isEmpty(collection)) {
            throw exception;
        }
    }

    public static void notEmpty(Map map, BusinessException exception) throws BusinessException {
        if (CollectionUtils.isEmpty(map)) {
            throw exception;
        }
    }

    public static void isInstanceOf(Class type, Object obj, BusinessException exception) throws BusinessException {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw exception;
        }
    }

    public static void isAssignable(Class superType, Class subType, BusinessException exception) throws BusinessException {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw exception;
        }
    }

    public static void state(boolean expression, BusinessException exception) throws BusinessException {
        if (!expression) {
            throw exception;
        }
    }


}
