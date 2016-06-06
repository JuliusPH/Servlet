package com.exist.service.impl;

import com.exist.dao.PersonDao;
import com.exist.dao.impl.PersonDaoImpl;
import com.exist.dao.impl.GenericDaoImpl;
import com.exist.dto.PersonDto;
import com.exist.model.Person;
import com.exist.model.enums.Sort;
import com.exist.service.PersonService;
import com.exist.service.impl.GenericServiceImpl;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl extends GenericServiceImpl<PersonDto, Person, Long> implements PersonService{
    private static final PersonDao personDao = new PersonDaoImpl();
    
    public PersonServiceImpl(){
        super(personDao);
    }
    
    public List<PersonDto> getAllBy(Sort sort, boolean isAscending){
        return (List<PersonDto>) personDao.getAllBy(sort, isAscending).stream().map(p -> mapper.map(p, PersonDto.class)).collect(Collectors.toList());
    }
}
