package org.cuong.intro.controller;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless(mappedName = "testFacadeWithoutInf", name = "testFacadeWithoutInf")
public class TestFacadeImplWithoutBusinessInterface implements TestNotBusinessFacade {
    public static final String HELLO = "Hello World";

    public String getHello() {
        return HELLO;
    }
}
