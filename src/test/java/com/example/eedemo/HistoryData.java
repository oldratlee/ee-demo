package com.example.eedemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryData {
    private static final long serialVersionUID = 1L;

    // 时间
    private String time;
    // 温度
    private Double temp;
    // 压力
    private Double press;
    // 瞬时流量
    private Double insFlow;
    // 累计流量
    private Double accFlow;
    // 瞬时热量
    private Double insHeat;
    // 累计热量
    private Double accHeat;
}
