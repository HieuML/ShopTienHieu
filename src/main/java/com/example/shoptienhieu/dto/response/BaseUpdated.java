package com.example.shoptienhieu.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseUpdated<T> {

    private T id;
    private Boolean updated;
}
