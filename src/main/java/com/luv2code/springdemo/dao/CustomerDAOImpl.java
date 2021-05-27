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
		session.saveOrUpdate(newCustomer);
	}

	@Override
	public Customer getCustomer(int customerId) {

		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, customerId);
		return customer;
	}

	@Override
	public void delete(int customerId) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", customerId);
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {

		Session session = sessionFactory.getCurrentSession();
		Query query = null;
		
		if (searchName != null && searchName.trim().length() > 0) {
			
			query = session.createQuery("from Customer where lower(firstName) like :name or lower(lastName) like :name",
					Customer.class);
			query.setParameter("name", "%" + searchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer", Customer.class);
		}
		
		return query.getResultList();
	}

}
