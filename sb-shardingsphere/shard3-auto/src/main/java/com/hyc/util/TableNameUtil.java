package com.hyc.util;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TableNameUtil {

    public static List<String> getMonthTable(String tablePrefix, String startDateStr, String endDateStr) {
        List<String> tableNames = new ArrayList<>();
        String monthPattern = "yyyyMM";
        Date now = new Date();

        if(StringUtils.isBlank(startDateStr)) {
            startDateStr = DateUtil.format(now, monthPattern);
        }
        if(StringUtils.isBlank(endDateStr)) {
            endDateStr = DateUtil.format(now, monthPattern);
        }

        Date dateStart = DateUtil.parse(startDateStr, monthPattern);
        Date dateEnd = DateUtil.parse(endDateStr, monthPattern);
        Calendar cal = Calendar.getInstance();
        while (dateStart.compareTo(dateEnd) <= 0) {
            cal.setTime(dateStart);
            tableNames.add(tablePrefix + DateUtil.format(dateStart, monthPattern));
            cal.add(Calendar.MONTH,1);
            dateStart = cal.getTime();
        }

        return tableNames;
    }

    public static void main(String[] args) {
        List<String> list = getMonthTable("aaa_","201906","201908");
        list.stream().forEach(System.out::println);
    }
}
