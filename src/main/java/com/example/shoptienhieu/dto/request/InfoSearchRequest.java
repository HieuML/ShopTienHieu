package com.example.shoptienhieu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoSearchRequest {
    private String productName;
    private int price;
    private String slug;
}
