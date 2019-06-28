package com.hyc.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum GenderEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer code;
    private String desc;

    private GenderEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static GenderEnum codeOf(Integer code) {
        GenderEnum item = CODE_MAP.get(code);
        if (item != null) {
            return item;
        }
        return MALE;
    }

    private static final Map<Integer, GenderEnum> CODE_MAP = Collections.unmodifiableMap(initializeCodeMapping());

    private static Map<Integer, GenderEnum> initializeCodeMapping() {
        final Map<Integer, GenderEnum> codeMap = new HashMap<Integer, GenderEnum>();
        for (GenderEnum item : GenderEnum.values()) {
            codeMap.put(item.code, item);
        }
        return codeMap;
    }
}
