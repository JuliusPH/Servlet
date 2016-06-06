package com.exist.dto;

import java.io.Serializable;

public abstract class BaseEntityDto implements Serializable{
    private Long id;
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
}
