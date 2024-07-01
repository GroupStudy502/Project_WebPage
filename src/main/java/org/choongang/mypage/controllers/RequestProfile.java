package org.choongang.mypage.controllers;

import lombok.Data;

@Data
public class RequestProfile {
    private String password;
    private String confirmPassword;
    private String userName;
}
