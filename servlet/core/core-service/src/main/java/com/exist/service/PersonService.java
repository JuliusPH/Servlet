package com.exist.service;

import com.exist.dto.PersonDto;
import com.exist.model.Person;
import com.exist.model.enums.Sort;
import java.util.List;

public interface PersonService extends GenericService<PersonDto, Person, Long>{
    List<PersonDto> getAllBy(Sort sort, boolean isAscending);
}
