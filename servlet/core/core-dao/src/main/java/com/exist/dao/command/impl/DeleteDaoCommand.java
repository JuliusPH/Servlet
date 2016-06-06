package com.exist.dao.command.impl;

import com.exist.dao.command.DaoCommand;
import org.hibernate.Session;

public class DeleteDaoCommand<E> implements DaoCommand{
    private Session session;
    private E entity;
    
    public DeleteDaoCommand(Session session, E entity){
        this.session = session;
        this.entity = entity;
    }
    
    @Override
    public void execute(){
        session.delete(entity);
    }
}