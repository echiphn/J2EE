package org.cuong.intro.test.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.cuong.intro.controller.TestBusinessFacade;
import org.cuong.intro.controller.TestFacadeImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SessionBeansTest {

    private static final String APPLICATION_NAME = "J2EESample";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testClientAccess() throws NamingException {
        // Properties clientProp = new Properties();
        //
        // clientProp.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED",
        // "false");
        //
        // clientProp.put("remote.connections", "default");
        //
        // clientProp.put("remote.connection.default.port", "4447");
        //
        // clientProp.put("remote.connection.default.host", "localhost");
        //
        // clientProp.put("remote.connection.default.username", "ejbUser");
        //
        // clientProp.put("remote.connection.default.password", "ejbPassword");
        //
        // clientProp
        // .put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS",
        // "false");
        //
        // EJBClientConfiguration cc = new
        // PropertiesBasedEJBClientConfiguration(clientProp);
        //
        // ContextSelector<EJBClientContext> selector = new
        // ConfigBasedEJBClientContextSelector(cc);
        //
        // EJBClientContext.setSelector(selector);
        //
        // Properties props = new Properties();
        //
        // props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        // props.put(Context.INITIAL_CONTEXT_FACTORY,
        // "org.jnp.interfaces.NamingContextFactory");
        final Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
        jndiProperties.put(InitialContext.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(InitialContext.PROVIDER_URL, "remote://localhost:4447");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        final Context context = new InitialContext(jndiProperties);
        // Context context = new InitialContext();
        // context.addToEnvironment("java.naming.factory.initial",
        // "org.jnp.interfaces.NamingContextFactory");
        // context.addToEnvironment("java.naming.factory.url.pkgs",
        // "org.jboss.naming:org.jnp.interfaces");
        // context.addToEnvironment(Context.PROVIDER_URL,
        // "jnp://localhost:1099");
        // TestBusinessFacade testFacade = (TestBusinessFacade)
        // context.lookup("java:global/"
        // + APPLICATION_NAME + "/TestBusinessFacade");
        TestBusinessFacade testFacade = (TestBusinessFacade) context
                .lookup("ear/jar/TestBusinessFacade");
        assertNotNull(testFacade);
        assertEquals(TestFacadeImpl.HELLO, testFacade.getHello());
    }

}
