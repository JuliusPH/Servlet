package com.exist.dao.command.impl;

import com.exist.dao.command.DaoCommand;
import java.io.Serializable;
import org.hibernate.Session;

public class AddDaoCommand<E> implements DaoCommand{
    private Session session;
    private E entity;
    
    public AddDaoCommand(Session session, E entity){
        this.session = session;
        this.entity = entity;
    }
    
    @Override
    public void execute(){
        session.save(entity);
    }
}