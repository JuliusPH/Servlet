package com.exist.service;

import java.util.Collection;
import java.util.List;

public interface GenericService<DTO, E, K>{
    boolean add(DTO entityDto);
    
    boolean update(DTO entityDto);
    
    boolean delete(DTO entityDto);
    
    DTO get(K key);
    
    List<DTO> getAll();
    
    boolean deleteAll(Collection<DTO> entities);
    
    boolean updateAll(Collection<DTO> entities);
}
