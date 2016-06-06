package com.exist.dao.impl;

import com.exist.dao.GenericDao;
import com.exist.dao.command.DaoCommandInvoker;
import com.exist.dao.command.impl.AddDaoCommand;
import com.exist.dao.command.impl.UpdateAllDaoCommand;
import com.exist.dao.command.impl.UpdateDaoCommand;
import com.exist.dao.command.impl.DeleteAllDaoCommand;
import com.exist.dao.command.impl.DeleteDaoCommand;
import com.exist.util.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.FetchMode;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K>{
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    protected Class<? extends E> daoType;
    protected DaoCommandInvoker invoker = new DaoCommandInvoker();
    
    public GenericDaoImpl(){   
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        daoType = (Class) parameterizedType.getActualTypeArguments()[0];
    }
    
    protected Session getSession(){
        return sessionFactory.openSession();
    }
    
    @Override
    public boolean add(E entity){
        Session session = getSession();
        return invoker.execute(new AddDaoCommand<E>(session, entity), session);
    }
    
    @Override
    public boolean update(E entity){
        Session session = getSession();
        return invoker.execute(new UpdateDaoCommand<E>(session, entity), session);
    }
    
    @Override
    public boolean delete(E entity){
        Session session = getSession();
        return invoker.execute(new DeleteDaoCommand<E>(session, entity), session);
    }
    
    @Override
    public E get(K key){
        return (E) getSession().get(daoType, key);
    }
    
    @Override
    public List<E> getAll(){
        return getSession().createCriteria(daoType)
                           .addOrder(Order.asc("id"))
                           .setCacheable(true)
                           .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                           .list();
    }
    
    @Override
    public boolean deleteAll(Collection<E> entities){
        Session session = getSession();
        return invoker.execute(new DeleteAllDaoCommand<E>(session, entities), session);
    }
    
    @Override
    public boolean updateAll(Collection<E> entities){
        Session session = getSession();
        return invoker.execute(new UpdateAllDaoCommand<E>(session, entities), session);
    }
}
