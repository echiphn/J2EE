package org.cuong.intro.test.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Hashtable;

import javax.ejb.NoSuchEJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.cuong.intro.controller.BusinessFacadeUsingOtherLocalBean;
import org.cuong.intro.controller.TestBusinessFacade;
import org.cuong.intro.controller.TestBusinessFacadeLocal;
import org.cuong.intro.controller.TestFacadeImpl;
import org.cuong.intro.controller.TestNotBusinessFacade;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class SessionBeansTest {

    private static final String APPLICATION_NAME = "J2EESample";

    private static Hashtable jndiProps;

    @BeforeClass
    public static void setUp() throws Exception {
        jndiProps = new Hashtable();
        jndiProps.put("java.naming.factory.initial",
                "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put(InitialContext.PROVIDER_URL, "remote://localhost:4447");
        jndiProps.put("jboss.naming.client.ejb.context", true);

        // needed for remote access - remember to run add-user.bat
        jndiProps.put(Context.SECURITY_PRINCIPAL, "test");
        jndiProps.put(Context.SECURITY_CREDENTIALS, "password");

        jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testClientAccess() throws NamingException {
        final Context context = new InitialContext(jndiProps);

        TestBusinessFacade testFacade = (TestBusinessFacade) context.lookup("ejb:/"
                + APPLICATION_NAME + "/testFacade!org.cuong.intro.controller.TestBusinessFacade");
        assertNotNull(testFacade);
        assertEquals(TestFacadeImpl.HELLO, testFacade.getHello());
    }

    @Test
    public void testClientAccess_Testcase2() throws NamingException {
        final Context context = new InitialContext(jndiProps);
        TestBusinessFacade testFacade = (TestBusinessFacade) context.lookup(APPLICATION_NAME
                + "/testFacade!org.cuong.intro.controller.TestBusinessFacade");
        assertNotNull(testFacade);
        assertEquals(TestFacadeImpl.HELLO, testFacade.getHello());
    }

    @Test(expected = NoSuchEJBException.class)
    public void testClientAccessSessionWithoutBusInterface() throws NamingException {
        final Context context = new InitialContext(jndiProps);

        TestNotBusinessFacade testFacade = (TestNotBusinessFacade) context.lookup("ejb:/"
                + APPLICATION_NAME
                + "/testFacadeWithoutInf!org.cuong.intro.controller.TestNotBusinessFacade");
        assertNotNull(testFacade);
        assertEquals(TestFacadeImpl.HELLO, testFacade.getHello());
    }

    @Test(expected = NoSuchEJBException.class)
    public void testClientAccessLocalBean() throws NamingException {
        final Context context = new InitialContext(jndiProps);

        TestBusinessFacadeLocal testFacade = (TestBusinessFacadeLocal) context.lookup("ejb:/"
                + APPLICATION_NAME
                + "/testFacadeWithoutInf!org.cuong.intro.controller.TestBusinessFacadeLocal");
        assertNotNull(testFacade);
        assertEquals(TestFacadeImpl.HELLO, testFacade.getHello());
    }

    @Test
    public void testClientAccess_Testcase3() throws NamingException {
        final Context context = new InitialContext(jndiProps);

        BusinessFacadeUsingOtherLocalBean testFacade = (BusinessFacadeUsingOtherLocalBean) context
                .lookup("ejb:/"
                        + APPLICATION_NAME
                        + "/BusinessFacadeUsingOtherLocalBeanImpl!org.cuong.intro.controller.BusinessFacadeUsingOtherLocalBean");
        assertNotNull(testFacade);
        assertEquals(TestFacadeImpl.HELLO, testFacade.createString());
    }

}
