package org.cuong.intro.controller;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(BusinessFacadeUsingOtherLocalBean.class)
@Stateless
public class BusinessFacadeUsingOtherLocalBeanImpl implements BusinessFacadeUsingOtherLocalBean {
    @EJB
    private TestNotBusinessFacade notBusinessFacade;

    @Override
    public String createString() {
        return notBusinessFacade.getHello();
    }
}
