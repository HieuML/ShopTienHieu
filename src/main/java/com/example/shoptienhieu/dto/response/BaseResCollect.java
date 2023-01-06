package com.example.shoptienhieu.dto.response;

import java.util.Map;

public class BaseResCollect<T>{
    private int status;
    private String statusText;
    private Map<String,T> data;
}
