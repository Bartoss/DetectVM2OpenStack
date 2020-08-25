package com.ibm.dao;

import com.ibm.model.Property;

import java.util.List;

public interface PropertyDao {

    List<Property> getPropertyList();

    void addProperty (Property property);

    void deleteProperty(Property property);

    void update(Property property);

}
