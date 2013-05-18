package org.cuong.intro.controller;

import javax.ejb.Stateless;

@Stateless
public class TestFacade {
    public static final String HELLO = "Hello World";

    public String getHello() {
        return HELLO;
    }
}
