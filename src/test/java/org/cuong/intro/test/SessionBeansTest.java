package org.cuong.intro.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.cuong.intro.controller.MemberRegistration;
import org.cuong.intro.controller.TestFacade;
import org.cuong.intro.model.Member;
import org.cuong.intro.util.Resources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SessionBeansTest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap
                .create(WebArchive.class, "test.war")
                .addClasses(Member.class, MemberRegistration.class, Resources.class,
                        TestFacade.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    MemberRegistration memberRegistration;

    @Inject
    TestFacade testFacade;

    @Inject
    Logger log;

    @Test
    public void testRegister() throws Exception {
        Member newMember = memberRegistration.getNewMember();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");
        memberRegistration.register();
        assertNotNull(newMember.getId());
        log.info(newMember.getName() + " was persisted with id " + newMember.getId());
    }

    @Test
    public void testGetHello() {
        assertEquals(TestFacade.HELLO, testFacade.getHello());
    }

    @Test
    public void testLookUpBean() {
        Context context = null;
        try {
            context = new InitialContext();
            TestFacade testFacade = (TestFacade) context.lookup("java:app/TestFacade");
            assertNotNull(testFacade);
            assertEquals(TestFacade.HELLO, testFacade.getHello());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
