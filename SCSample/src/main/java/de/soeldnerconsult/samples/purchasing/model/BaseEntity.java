package de.soeldnerconsult.samples.purchasing.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import java.io.Serializable;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects needing this property.
 * See: http://www.onjava.com/pub/a/onjava/2006/09/13/dont-let-hibernate-steal-your-identity.html
 * @author Guido Söldner

 */

@MappedSuperclass
public class BaseEntity implements  Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -579025866051121788L;

	@Id
	private String id = java.util.UUID.randomUUID().toString();
	
	@Version
    private Integer version;
		 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ||
            !(o instanceof PersistentObject)) {

            return false;
        }

        PersistentObject other
            = (PersistentObject)o;

        // if the id is missing, return false
        if (id == null) return false;

        // equivalence by id
        return id.equals(other.getId());
    }

    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    public String toString() {
        return this.getClass().getName()
            + "[id=" + id + "]";
    }
	

}
