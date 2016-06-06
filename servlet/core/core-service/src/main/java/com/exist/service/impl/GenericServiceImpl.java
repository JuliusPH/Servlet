package com.exist.service.impl;

import com.exist.dao.GenericDao;
import com.exist.dao.impl.GenericDaoImpl;
import com.exist.service.GenericService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;

public class GenericServiceImpl<DTO, E, K> implements GenericService<DTO, E, K> {
    private GenericDao<E, K> genericDao;
    protected Class<? extends DTO> dtoType;
    protected Class<? extends E> daoType;
    protected ModelMapper mapper = new ModelMapper();
 
    public GenericServiceImpl(GenericDao<E, K> genericDao){
        this.genericDao = genericDao;
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        dtoType = (Class) parameterizedType.getActualTypeArguments()[0];
        daoType = (Class) parameterizedType.getActualTypeArguments()[1];
    }
    
    @Override
    public boolean add(DTO entityDto) {
        return genericDao.add(mapper.map(entityDto, daoType));
    }
 
    @Override
    public boolean update(DTO entityDto) {
        return genericDao.update(mapper.map(entityDto, daoType));
    }
 
    @Override
    public boolean delete(DTO entityDto) {
        return genericDao.delete(mapper.map(entityDto, daoType));
    }
    
    @Override
    public DTO get(K key){
        if(genericDao.get(key) != null){
            return (DTO) mapper.map(genericDao.get(key), dtoType);
        }
        return null;
    }
    
    @Override
    public List<DTO> getAll(){
        return (List<DTO>) genericDao.getAll().stream().map(e -> mapper.map(e, dtoType)).collect(Collectors.toList());
    }
    
    @Override
    public boolean deleteAll(Collection<DTO> entities){
        return genericDao.deleteAll(entities.stream().map(e -> mapper.map(e, daoType)).collect(Collectors.toSet()));
    }
    
    @Override
    public boolean updateAll(Collection<DTO> entities){
        return genericDao.updateAll(entities.stream().map(e -> mapper.map(e, daoType)).collect(Collectors.toSet()));
    }
}
