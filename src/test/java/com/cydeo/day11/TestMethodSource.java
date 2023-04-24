package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class TestMethodSource {

    //bu methodun içine bir tane string parametre giriliyor.
    //bu parametrede STATIK olan aynı sınıftaki diğer bir method yazılır

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void testGetExcelData(Map<String, String> parameters) {
        System.out.println(parameters);
    }
    //methodsource içine yazılacak bu method bir RETURN TYPE almalıdır!!! ve STATIK olmalıdır!!!
    public static List<Map<String, String>> getExcelData() {
        ExcelUtil excel = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");
        List<Map<String, String>> dataList = excel.getDataList();
        return dataList;
    }


}
