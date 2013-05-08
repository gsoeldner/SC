package de.soeldnerconsult.samples.purchasing.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test-config.xml"})
@ActiveProfiles("jpa")
public class SecurityServiceJPATests extends AbstractSecurityServiceTests {

}
