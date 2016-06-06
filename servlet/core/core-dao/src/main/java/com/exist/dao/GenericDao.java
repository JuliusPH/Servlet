package com.exist.dao;

import java.util.Collection;
import java.util.List;

public interface GenericDao<E, K>{
    boolean add(E entity);
    
    boolean update(E entity);
    
    boolean delete(E entity);
    
    E get(K key);
    
    List<E> getAll();
    
    boolean deleteAll(Collection<E> entities);
    
    boolean updateAll(Collection<E> entities);
}
