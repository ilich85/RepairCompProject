package com.level.controller;

import javax.servlet.http.Cookie;

public class WorkWithCookies {
    public WorkWithCookies() {
    }

    public static String[] userCookieArr(Cookie[] cookies) {
        String[] username = new String[1];
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username[0] = cookie.getValue();
            }
        }
        return username;
    }

    public static String[] adminCookieArr(Cookie[] cookies) {
        String[] adminName = new String[1];
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("admin_name")) {
                adminName[0] = cookie.getValue();
            }
        }
        return adminName;
    }

    public static String userCookie(Cookie[] cookies) {
        String username = null;
        for (Cookie cookie : cookies) {
            if ((cookie.getName().equals("username"))) {
                username = cookie.getValue();
            }
        }
        return username;
    }

    public static String adminCookie(Cookie[] cookies) {
        String adminName = null;
        for (Cookie cookie : cookies) {
            if ((cookie.getName().equals("admin_name"))) {
                adminName = cookie.getValue();
            }
        }
        return adminName;
    }
}