package com.muhardin.endy.belajar.hibernate.dao;

import com.muhardin.endy.belajar.hibernate.entity.Customer;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository @Transactional
public class CustomerDao {
    @Autowired private SessionFactory sessionFactory;
    
    public void save(Customer c){
        sessionFactory.getCurrentSession().saveOrUpdate(c);
    }
    
    public Customer findById(Long id){
        if(id == null) {
            return null;
        }
        return (Customer) sessionFactory.getCurrentSession().get(Customer.class, id);
    }
}
