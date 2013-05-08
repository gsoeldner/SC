package de.soeldnerconsult.samples.purchasing.repository;

import static org.junit.Assert.*;

import org.hsqldb.lib.HashSet;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.WebDataBinder;

import de.soeldnerconsult.samples.purchasing.model.User;
import de.soeldnerconsult.samples.purchasing.model.UserStatus;
import de.soeldnerconsult.samples.purchasing.service.SecurityService;
import de.soeldnerconsult.samples.web.users.UserController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test-config.xml"})
@ActiveProfiles("jpa")
public class UserControllerTest {

	  @Autowired
	  private org.springframework.validation.Validator validator;
	 
	  private UserController userController;
	  private SecurityService sec = new StubSecurityService();

	  @Before
		public void setUp() throws Exception {
		  userController = new UserController(sec);
		}
	  
	  @Test
	  public void testShowGet() {

			String view = userController.show();
			assertEquals("users/show", view);
	  }
	  
	  @Test
	  public void testEditGet() {

			String view = userController.edit();
			assertEquals("users/edit", view);
	  }
	  
	  @Test
	  public void testEditPut() {

			User user = new User();
			user.setId("2");
			user.setFirstName("xMatthias");
			user.setLastName("xElbert");
			user.setUsername("xmelbert");
			user.setPassword("x123");
			user.setStatus(UserStatus.ENABLED);
			user.setDateCreated(DateTime.now());
			user.setDateModified(DateTime.now());
			
			BeanPropertyBindingResult result = new BeanPropertyBindingResult(user, "user");
			ExtendedModelMap model = new ExtendedModelMap();
			String view = userController.edit(user,result);
			assertEquals("redirect:", view);
			assertEquals(sec.findUserById("2"), user);
	  }
	  

	  
	}
