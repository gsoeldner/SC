package de.soeldnerconsult.samples.web.users;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.hsqldb.lib.Collection;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.soeldnerconsult.samples.purchasing.model.User;
import de.soeldnerconsult.samples.purchasing.model.UserStatus;
import de.soeldnerconsult.samples.purchasing.service.SecurityService;
import de.soeldnerconsult.samples.web.JqgridResponse;
import de.soeldnerconsult.samples.web.StatusResponse;

@Controller
@RequestMapping("/users")
public class UsersController {
	

	SecurityService sec;

	@Autowired
	public UsersController (SecurityService sec)
	{
		this.sec= sec;
	}
	
	@ModelAttribute(value="users")
	protected java.util.Collection<User> findUsers() {
		return sec.findUserByLastName("");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "users/list";
	}
	
	  @RequestMapping(value = "/new", method = RequestMethod.GET)
	    public String newUser(Model model) {
		  User user = new User();
	        model.addAttribute("user", user);
	        model.addAttribute("isnew", true);
	        return "users/edit";
	    }

	    @RequestMapping(value = "/new", method = RequestMethod.POST)
	    public String newUser(Model model,@Valid User user, BindingResult result) {
	        if (result.hasErrors()) {
	        	  model.addAttribute("isnew", true);
	            return "users/edit";
	        } else {
	        		        	
	    		user.setStatus(UserStatus.ENABLED);
	    		user.setDateCreated(DateTime.now());
	    		user.setDateModified(DateTime.now());
	        	
	            this.sec.saveUser(user);
	            return "redirect:/users/" + user.getId();
	        }
	    }
	
		@RequestMapping(value = "/ajax",method = RequestMethod.GET)
		public String ajax() {
			return "users/users";
		}
	    
	    @RequestMapping(value="/records", produces="application/json")
		public @ResponseBody JqgridResponse<User> records(
//	    		@RequestParam("_search") Boolean search,
//	    		@RequestParam(value="filters", required=false) String filters,
//	    		@RequestParam(value="page", required=false) Integer page,
//	    		@RequestParam(value="rows", required=false) Integer rows,
//	    		@RequestParam(value="sidx", required=false) String sidx,
//	    		@RequestParam(value="sord", required=false) String sord
	    		) {
	 
//			Pageable pageRequest = new PageRequest(page-1, rows);
//			
//			if (search == true) {
//				return getFilteredRecords(filters, pageRequest);
//				
//			} 
		
			java.util.Collection<User> userColl = sec.findUserByLastName("");
			java.util.List<User> userList = new ArrayList<User>(userColl);
			
			JqgridResponse<User> response = new JqgridResponse<User>();
			response.setRows(userList);
			response.setRecords(Long.valueOf( userColl.size()).toString());
			response.setTotal("1");//Integer.valueOf(userColl.getTotalPages()).toString());
			response.setPage("0");//Integer.valueOf(userColl.getNumber()+1).toString());
			
			return response;
		}
	    
	    @RequestMapping(value="/records", method=RequestMethod.POST, produces="application/json")
			public @ResponseBody StatusResponse add(
					@RequestParam String username,
					@RequestParam String password,
					@RequestParam String firstName,
					@RequestParam String lastName
		    		) {
	    	User user = new User();
	    	user.setUsername(username);
	    	user.setFirstName(firstName);
	    	user.setPassword(password);
	    	user.setLastName(lastName);	    	
	    	user.setStatus(UserStatus.ENABLED);
    		user.setDateCreated(DateTime.now());
    		user.setDateModified(DateTime.now());
        	
            this.sec.saveUser(user);
            
            return new StatusResponse(true);
		}
	    
}
