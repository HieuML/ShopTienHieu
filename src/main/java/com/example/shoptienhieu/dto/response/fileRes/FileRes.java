package com.example.shoptienhieu.dto.response.fileRes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FileRes {
    private String name;
    private String url;
    private String type;
    private long size;
}
