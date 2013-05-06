package de.soeldnerconsult.samples.web.users;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import de.soeldnerconsult.samples.purchasing.model.User;
import de.soeldnerconsult.samples.purchasing.service.SecurityService;

@Controller
@RequestMapping("/users/{id}")
public class UserController {
	

	SecurityService sec;

	@Autowired
	public UserController (SecurityService sec)
	{
		this.sec= sec;
	}
	
	@ModelAttribute
	protected User findAccount(@PathVariable("id") String id) {
		return sec.findUserById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String show() {
		return "users/show";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public String edit() {
		return "users/edit";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.PUT)
	public String edit(@Valid User user, BindingResult result) {				
		if (result.hasErrors()) {
			return "users/edit";
		}		
		sec.saveUser(user);			
		return "redirect:" ;
	}
}
