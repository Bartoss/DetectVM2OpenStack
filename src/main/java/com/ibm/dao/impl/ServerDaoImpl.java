package com.ibm.dao.impl;

import com.ibm.dao.ServerDao;
import com.ibm.model.Server;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ServerDaoImpl implements ServerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addServer(Server server) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(server);
        session.flush();
    }


    public void deleteServer(Server server) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(server);
        session.flush();
    }

    public List<Server> getServerList() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Server");
        List<Server> serverList = query.list();
        session.flush();
        return serverList;
    }


}
