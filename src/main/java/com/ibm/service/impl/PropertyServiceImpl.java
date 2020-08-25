package com.ibm.service.impl;

import com.ibm.dao.PropertyDao;
import com.ibm.model.Property;
import com.ibm.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyDao propertyDao;

    @Override
    public List<Property> getPropertyList() {
        return propertyDao.getPropertyList();
    }

    @Override
    public void addProperty(Property property) {
        propertyDao.addProperty(property);
    }

    @Override
    public void deleteProperty(Property property) {
        propertyDao.deleteProperty(property);
    }

    public void update(Property property){propertyDao.update(property);}


}
