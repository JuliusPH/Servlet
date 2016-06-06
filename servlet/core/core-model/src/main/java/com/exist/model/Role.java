package com.exist.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "Role")
@Immutable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="role")
public class Role extends BaseEntity{
    private String value;
    private Set<Person> persons;
    
    public Role(){}
    
    public Role(String value){
        this.value = value;
    }
    
    @Column(name = "value")
    public String getValue(){
        return value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(value);
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Role)) {
            return false;
        }
        Role otherRole = (Role) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(value, otherRole.getValue());
        return eb.isEquals();
    }
}
