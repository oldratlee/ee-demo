package com.example.eedemo;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see <a href="https://blog.csdn.net/qq_42761569/article/details/119025171">Java根据模板生成excel文件</a>
 */
public class Main {
    public static void main(String[] args) {
        // 模板文件
        final String templateFile = "data/template/history-data-template.xlsx";
        // 结果文件，省去了根据模板文件生成的步骤
        final String resultFile = "data/result/history-data-" + System.currentTimeMillis() + ".xlsx";

        // 根据模板文件生成目标文件
        final ExcelWriter excelWriter = EasyExcel
                .write(resultFile)
                .withTemplate(templateFile)
                // 单独设置单元格格式
                //.registerWriteHandler(new CellStyleHandler())
                .build();

        final WriteSheet writeSheet = EasyExcel.writerSheet().build();

        // 第一种占位符替换
        final Map<String, Object> map = new HashMap<>();
        map.put("theUser", "牛顿的");
        map.put("reportDate", DateUtil.date().toString("yyyy年MM月dd日"));
        excelWriter.fill(map, writeSheet);

        // 第二种占位符替换，这里定义了 hisData
        // 每次都会重新生成新的一行，而不是使用下面的空行
        final FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
        excelWriter.fill(new FillWrapper("hisData", hisData()), fillConfig, writeSheet);

        excelWriter.finish();
    }

    private static List<HistoryData> hisData() {
        List<HistoryData> resList = new ArrayList<>();
        String today = DateUtil.now();
        String yesterday = DateUtil.yesterday().toString();
        HistoryData yesData = HistoryData.builder()
                .time(today)
                .temp(34.211)
                .press(1.222)
                .insFlow(34.211)
                .accFlow(233.125)
                .insHeat(20.532)
                .accHeat(112.562)
                .build();
        HistoryData nowData = HistoryData.builder()
                .time(yesterday)
                .temp(34.211)
                .press(1.222)
                .insFlow(34.211)
                .accFlow(233.125)
                .insHeat(20.532)
                .accHeat(112.562)
                .build();
        resList.add(yesData);
        resList.add(nowData);
        return resList;
    }

}
