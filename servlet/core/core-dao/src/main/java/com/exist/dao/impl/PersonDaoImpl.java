package com.exist.dao.impl;

import com.exist.dao.PersonDao;
import com.exist.model.Person;
import com.exist.model.enums.Sort;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import java.util.List;

public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao{
    @Override
    public List<Person> getAllBy(Sort sort, boolean isAscending){
        Session session = getSession();
        Criteria criteria = session.createCriteria(Person.class);
        switch(sort){
            case GWA:
                if(isAscending){
                    criteria.addOrder(Order.asc("gwa"));
                }
                else{
                    criteria.addOrder(Order.desc("gwa"));
                }
                break;
            case DateHired:
                if(isAscending){
                    criteria.addOrder(Order.asc("dateHired"));
                }
                else{
                    criteria.addOrder(Order.desc("dateHired"));
                }
                break;
            case LastName:
                if(isAscending){
                    criteria.addOrder(Order.asc("name.lastName"));
                }
                else{
                    criteria.addOrder(Order.desc("name.lastName"));
                }
                break;
            default:
                if(isAscending){
                    criteria.addOrder(Order.asc("id"));
                }
                else{
                    criteria.addOrder(Order.desc("id"));
                }
                break;
        }
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
