package com.lz.manage.model.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 通用状态 枚举
 */
@Getter
public enum CommonStatusEnum {
    COMMON_STATUS_0("0", "正常"),
    COMMON_STATUS_1("1", "关闭");

    private static final Map<String, CommonStatusEnum> VALUE_MAP = new HashMap<>();

    static {
        for (CommonStatusEnum item : values()) {
            VALUE_MAP.put(item.value, item);
        }
    }

    private final String value;
    private final String label;

    CommonStatusEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Optional<CommonStatusEnum> getEnumByValue(String value) {
        return Optional.ofNullable(VALUE_MAP.get(value));
    }
}
