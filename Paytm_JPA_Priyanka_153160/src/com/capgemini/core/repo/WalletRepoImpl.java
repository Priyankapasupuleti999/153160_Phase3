package com.capgemini.core.repo;

import javax.persistence.EntityManager;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.exception.InvalidInputException;
import com.capgemini.core.util.JPAUtil;

public class WalletRepoImpl implements WalletRepo{

	private EntityManager entityManager;
	
	public WalletRepoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public boolean save(Customer customer) {
		try {
			entityManager.persist(customer);
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) throws InvalidInputException {
		Customer customer = entityManager.find(Customer.class, mobileNo);
		return customer;
	}

	@Override
	public void startTransaction() {
		
		entityManager.getTransaction().begin();
		
	}

	@Override
	public void commitTransaction() {
		
		entityManager.getTransaction().commit();
		
	}

	@Override
	public void update(Customer customer) {
		
		entityManager.merge(customer);
		
	}

}
