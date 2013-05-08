package de.soeldnerconsult.samples.purchasing.repository;

import static org.junit.Assert.*;

import org.hsqldb.lib.HashSet;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
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
import de.soeldnerconsult.samples.web.users.UsersController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test-config.xml"})
@ActiveProfiles("jpa")
public class UsersControllerTest {

	  @Autowired
	  private org.springframework.validation.Validator validator;
	 
	  private UsersController usersController;
	  private SecurityService sec = new StubSecurityService();

	  @Before
		public void setUp() throws Exception {
		  usersController = new UsersController(sec);
		}
	  
	  @Test
	  public void testListGet() {

			String view = usersController.list();
			assertEquals("users/list", view);
	  }
	  
	  @Test
	  public void testNewUserGet() {
		  
		  ExtendedModelMap model = new ExtendedModelMap();
			String view = usersController.newUser(model);
			assertEquals("users/edit", view);
			assertEquals(true, model.get("isnew"));
			assertThat(model.get("user"), instanceOf(User.class));
	  }
	  
	  @Test
	  public void testNewUserPost() {

			User user = new User();
			user.setFirstName("xMatthias");
			user.setLastName("xElbert");
			user.setUsername("xmelbert");
			user.setPassword("x123");
			user.setStatus(UserStatus.ENABLED);
			user.setDateCreated(DateTime.now());
			user.setDateModified(DateTime.now());
			
			BeanPropertyBindingResult result = new BeanPropertyBindingResult(user, "user");
			ExtendedModelMap model = new ExtendedModelMap();
			String view = usersController.newUser(model, user, result);
			assertEquals("redirect:/users/" + user.getId(), view);
			assertEquals(sec.findUserById(user.getId()), user);
	  }
	  
	  @Test
	  public void testAjaxGet() {

			String view = usersController.ajax();
			assertEquals("users/users", view);
	  }
	  
	  
	  
	}
