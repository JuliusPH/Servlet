package com.exist.dao;

import com.exist.model.Person;
import com.exist.model.enums.Sort;
import java.util.List;

public interface PersonDao extends GenericDao<Person, Long>{
    List<Person> getAllBy(Sort sort, boolean isAscending);
}
