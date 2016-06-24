package com.exist.result;

import com.exist.dto.PersonDto;
import java.util.List;

public class Result{
    private String title;
    private String message;
    private boolean isSuccess;
    private PersonDto person;
    private List<PersonDto> persons;
    
    public Result(){}
    
    public String getTitle(){
        return title;
    }
    
    public Result setTitle(String title){
        this.title = title;
        return this;
    }
    
    public String getMessage(){
        return message;
    }
    
    public Result setMessage(String message){
        this.message = message;
        return this;
    }
    
    public boolean isSuccess(){
        return isSuccess;
    }
    
    public Result setSuccess(boolean isSuccess){
        this.isSuccess = isSuccess;
        return this;
    }
    
    public PersonDto getPerson(){
        return person;
    }
    
    public Result setPerson(PersonDto person){
        this.person = person;
        return this;
    }
    
    public List<PersonDto> getPersons(){
        return persons;
    }
    
    public Result setPersons(List<PersonDto> persons){
        this.persons = persons;
        return this;
    }
}