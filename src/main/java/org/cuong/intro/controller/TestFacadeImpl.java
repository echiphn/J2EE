package org.cuong.intro.controller;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Local(TestBusinessFacadeLocal.class)
@Remote(TestBusinessFacade.class)
@Stateless(mappedName = "testFacade", name = "testFacade")
public class TestFacadeImpl implements TestBusinessFacade, TestBusinessFacadeLocal {
    public static final String HELLO = "Hello World";

    @Override
    public String getHello() {
        return HELLO;
    }
}
