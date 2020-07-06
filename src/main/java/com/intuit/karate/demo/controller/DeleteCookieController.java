/*
 * The MIT License
 *
 * Copyright 2017 Intuit Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.intuit.karate.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgael
 */
@RestController
public class DeleteCookieController {

    private String displayCookies(Cookie[] cookies) {
        String res = "";
        if (cookies == null)
            return "EMPTY";
        for (Cookie c : cookies) {
            res += c.getName() + "\n";
            res += c.getValue() + "\n";
            res += "MaxAge : " + c.getMaxAge() + "\n";
        }
        return res;
    }

    @GetMapping("/set")
    public void set(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("mycookie", "valueset");
        response.addCookie(cookie);
    }

    @GetMapping("/list")
    public String list(HttpServletResponse response, HttpServletRequest request) {
        return displayCookies(request.getCookies());
    }

    @GetMapping("/delete")
    public void delete(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("mycookie", "valuedeleted");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
