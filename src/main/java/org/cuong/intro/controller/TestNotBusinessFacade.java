package org.cuong.intro.controller;

import javax.ejb.Local;

@Local
public interface TestNotBusinessFacade {
    public String getHello();
}
