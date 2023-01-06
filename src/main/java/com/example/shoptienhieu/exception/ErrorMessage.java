package com.example.shoptienhieu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
public class ErrorMessage {
  private Integer statusCode;
  private String message;
}