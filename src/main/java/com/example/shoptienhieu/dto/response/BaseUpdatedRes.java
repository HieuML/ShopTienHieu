package com.example.shoptienhieu.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseUpdatedRes {
    private String id;
    private Boolean updated;
}
