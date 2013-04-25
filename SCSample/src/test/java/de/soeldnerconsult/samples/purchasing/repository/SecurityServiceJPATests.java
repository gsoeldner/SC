package de.soeldnerconsult.samples.purchasing.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {"classpath:spring/main-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpa")
public class SecurityServiceJPATests extends AbstractSecurityServiceTests {

}
