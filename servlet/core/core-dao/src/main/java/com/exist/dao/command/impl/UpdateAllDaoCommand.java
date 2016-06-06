package com.exist.dao.command.impl;

import com.exist.dao.command.DaoCommand;
import java.util.Collection;
import org.hibernate.Session;

public class UpdateAllDaoCommand<E> implements DaoCommand{
    private Session session;
    private Collection<E> entities;
    
    public UpdateAllDaoCommand(Session session, Collection<E> entities){
        this.session = session;
        this.entities = entities;
    }
    
    @Override
    public void execute(){
        entities.stream().forEach(entity -> session.saveOrUpdate(entity));
    }
}