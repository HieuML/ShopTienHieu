package com.example.shoptienhieu.service;



import com.example.shoptienhieu.exception.DayException;
import com.example.shoptienhieu.exception.EmailException;
import com.example.shoptienhieu.exception.FullNameException;
import com.example.shoptienhieu.exception.PhoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorService {

    public static void dateCheck(String date) throws DayException {
        DateValidatorUsingDateFormat validator = new DateValidatorUsingDateFormat("dd/MM/yyyy");
        if(!validator.isValid(date)) throw new DayException("Nhập sai định dạng ngày, vui lòng nhập lại");

    }
    public static void phoneCheck(String phone) throws PhoneException {
        String regex = "^0\\d{9,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.find()) throw new PhoneException("Nhập sai định dạng số điện thoại, vui lòng nhập lại");
    }

    public static void emailCheck(String email) throws EmailException {
        String regex = "^[a-zA-Z]+[a-zA-Z0-9]*@[a-zA-z]+mail.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find())  throw new EmailException("Nhập sai định dạng email, vui lòng nhập lại");

    }

    public static void nameCheck(String name) throws FullNameException {
        String regex = "^[a-zA-Z]+\\s[a-zA-Z]+\\s[a-zA-Z]+(\\s[a-zA-Z]+)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        if(!matcher.find()) throw new FullNameException("Nhập sai định dạng họ và tên, vui lòng nhập lại");
    }
}