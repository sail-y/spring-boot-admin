package com.dmc.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by sail on 2016/12/25.
 */
@Data
public class DataTable<T> {
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<T> data;
}
