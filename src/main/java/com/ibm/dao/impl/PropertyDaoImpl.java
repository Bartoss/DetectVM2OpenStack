package com.ibm.dao.impl;

import com.ibm.dao.PropertyDao;
import com.ibm.model.Property;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PropertyDaoImpl implements PropertyDao{

    @Autowired
    SessionFactory sessionFactory;


    public List<Property> getPropertyList() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Property");
        List<Property> propertyList = query.list();
        session.flush();
        return propertyList;
    }


    public void addProperty(Property property) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(property);
        session.flush();
    }


    public void deleteProperty(Property property) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(property);
        session.flush();
    }

    @Override
    public void update(Property property) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(property);
        session.flush();
    }


}
