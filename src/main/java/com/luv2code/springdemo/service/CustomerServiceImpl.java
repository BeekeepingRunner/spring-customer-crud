package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers(int sortField) {

		return customerDAO.getCustomers(sortField);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer newCustomer) {
		
		customerDAO.saveCustomer(newCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int customerId) {

		return customerDAO.getCustomer(customerId);
	}

	@Override
	@Transactional
	public void delete(int customerId) {

		customerDAO.delete(customerId);
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String searchName) {
		
		return customerDAO.searchCustomers(searchName);
	}
}
