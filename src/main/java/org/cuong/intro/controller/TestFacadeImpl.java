package org.cuong.intro.controller;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(TestBusinessFacade.class)
@Stateless(mappedName = "testFacade", name = "testFacade")
public class TestFacadeImpl implements TestBusinessFacade {
    public static final String HELLO = "Hello World";

    @Override
    public String getHello() {
        return HELLO;
    }
}
