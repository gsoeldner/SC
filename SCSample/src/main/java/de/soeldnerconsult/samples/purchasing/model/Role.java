package de.soeldnerconsult.samples.purchasing.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

@Entity
@Table(name="role")
public class Role extends BaseEntity{
	
	@NotEmpty
	@Column(name="name")
	@Size(max=40)
	String name;
	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_permissions", joinColumns=@JoinColumn(name="role_id"), 
		inverseJoinColumns=@JoinColumn(name="permission_id"))
	Set<Permission> permissions;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	protected void setPermissionsInternal(Set<Permission> perms)
	{
		this.permissions = perms;
	}
	
	protected Set<Permission> getPermissionsInternal()
	{
		if(this.permissions == null)
		{
			this.permissions = new HashSet<Permission>();
		}
		return this.permissions;
		
	}
	
	public List<Permission> getPermissions() {
		List<Permission> perms = new ArrayList<Permission>(this.getPermissionsInternal());
		PropertyComparator.sort(perms, new MutableSortDefinition("name", true, true));
		return perms;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString()
	{
		return new ToStringCreator(this).append("id", this.getId()).				
				append("name", this.getName()).toString();
	}

}
