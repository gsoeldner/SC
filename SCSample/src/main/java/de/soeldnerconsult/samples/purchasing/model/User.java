package de.soeldnerconsult.samples.purchasing.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;


import org.jadira.usertype.dateandtime.joda.PersistentDateTime;

@Entity
@Table(name="user")
public class User extends BaseEntity{
	
	//test
	
	@Column(name = "username")
	@NotEmpty
	@Size(max=40)
	String username;
	
	@Column(name="password")
	@NotEmpty
	@Size(max=40)
	String password;
	
	@Column(name="first_name")
	@NotEmpty
	@Size(max=40)
	String firstName;
	
	@Column(name="last_name")
	@NotEmpty
	@Size(max=40)
	String lastName;
	
	@Enumerated
	UserStatus status;
	
	@Column(name = "date_created")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	DateTime dateCreated;
	
	@Column(name = "date_modified")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	DateTime dateModified;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public DateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public DateTime getDateModified() {
		return dateModified;
	}

	public void setDateModified(DateTime dateModified) {
		this.dateModified = dateModified;
	}
	
	protected void setRolesInternal(Set<Role> roles)
	{
		this.roles = roles;
	}
	
	protected Set<Role> getRolesInternal()
	{
		return this.roles;
	}
	
	public List<Role> getRoles(){
		List<Role> roles = new ArrayList<Role>(this.getRolesInternal());
		PropertyComparator.sort(roles, new MutableSortDefinition("name", true, true));
		return roles;
	}
	
	public void addRole(Role role)
	{
		this.roles.add(role);
	}
	
	public Role getRole(String name)
	{
		name = name.toLowerCase();
		for(Role role : this.getRolesInternal())
		{
			String compName = role.getName();
			if(compName.equals(name))
				return role;
		}
		return null;
	}
	
	@Override
	public String toString(){
		return new ToStringCreator(this)
				.append("username", username)
				.append("password", password)
				.append("firstName", firstName)
				.append("lastName", lastName)
				.append("status", status)
				.append("dateCreated", dateCreated)
				.append("dateModified", dateModified)
				.toString();
	}

}
