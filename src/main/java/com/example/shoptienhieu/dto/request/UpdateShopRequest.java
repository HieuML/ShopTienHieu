package com.example.shoptienhieu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class UpdateShopRequest {
    private int id;
    @Size(max = 45)
    private String name;
    private int ward_id;
}
