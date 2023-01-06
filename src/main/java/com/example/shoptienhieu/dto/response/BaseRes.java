package com.example.shoptienhieu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BaseRes<T> {
    private int status;
    private String statusText;
    private T data;
    private List<T> listData;

    public BaseRes(int status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

    public BaseRes(int status, String statusText, T data) {
        this.status = status;
        this.statusText = statusText;
        this.data = data;
    }

    public BaseRes(int status, String statusText, List<T> listData) {
        this.status = status;
        this.statusText = statusText;
        this.listData = listData;
    }
}
