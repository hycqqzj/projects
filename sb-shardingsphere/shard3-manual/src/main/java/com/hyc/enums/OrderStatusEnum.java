package com.hyc.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum OrderStatusEnum {
    PROCESSING(1, "进行中"),
    FINISHED(2, "已完成"),
    CANCELLED(2, "已取消");

    private Integer code;
    private String desc;

    private OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatusEnum codeOf(Integer code) {
        OrderStatusEnum item = CODE_MAP.get(code);
        if (item != null) {
            return item;
        }
        return PROCESSING;
    }

    private static final Map<Integer, OrderStatusEnum> CODE_MAP = Collections.unmodifiableMap(initializeCodeMapping());

    private static Map<Integer, OrderStatusEnum> initializeCodeMapping() {
        final Map<Integer, OrderStatusEnum> codeMap = new HashMap<Integer, OrderStatusEnum>();
        for (OrderStatusEnum item : OrderStatusEnum.values()) {
            codeMap.put(item.code, item);
        }
        return codeMap;
    }
}
