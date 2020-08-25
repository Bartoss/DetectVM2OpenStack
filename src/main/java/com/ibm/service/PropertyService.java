package com.ibm.service;

import com.ibm.model.Property;

import java.util.List;

public interface PropertyService {

    List<Property> getPropertyList();

    void addProperty (Property property);

    void deleteProperty(Property property);

    void update(Property property);
}
