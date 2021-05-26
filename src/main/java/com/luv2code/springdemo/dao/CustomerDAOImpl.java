package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {

		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> customers = session.createQuery("from Customer order by lastName",
				Customer.class);
		
		return customers.getResultList();
	}

	@Override
	public void saveCustomer(Customer newCustomer) {

		Session session = sessionFactory.getCurrentSession();
		session.save(newCustomer);
	}

	@Override
	public Customer getCustomer(int customerId) {

		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, customerId);
		return customer;
	}

}
