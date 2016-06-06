package com.exist.dto;

import com.exist.model.enums.ContactType;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

public class ContactDto extends BaseEntityDto{
    private ContactType contactType;
    private String value;
    private PersonDto person;
    
    public ContactDto(){}
    
    public ContactDto(ContactType contactType, String value){
        this.contactType = contactType;
        this.value = value;
    }
    
    public ContactType getContactType(){
        return contactType;
    }
    
    public void setContactType(ContactType contactType){
        this.contactType = contactType;
    }
    
    public String getValue(){
        return value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public PersonDto getPerson(){
        return person;
    }
    
    public void setPerson(PersonDto person){
        this.person = person;
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
        if (!(obj instanceof ContactDto)) {
            return false;
        }
        ContactDto otherContact = (ContactDto) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(value, otherContact.getValue());
        return eb.isEquals();
    }
}
