package com.jarmison.consulta.credito.core.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PageResponse<T> {
    private List<T> content;
    private int number;
    private int size;
    private long totalElements;
    private int totalPages;

}
