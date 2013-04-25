package de.soeldnerconsult.samples.purchasing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name="permission")
public class Permission extends BaseEntity{
	
	@NotEmpty
	@Size(max=40)
	@Column(name="name")
	String name;

	@Override
	public String toString()
	{
		return new ToStringCreator(this).append("name", this.name).toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
