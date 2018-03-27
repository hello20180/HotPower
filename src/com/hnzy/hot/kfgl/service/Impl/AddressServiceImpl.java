package com.hnzy.hot.kfgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.kfgl.dao.AddressDao;
import com.hnzy.hot.kfgl.pojo.Address;
import com.hnzy.hot.kfgl.service.AddressService;
@Service
public class AddressServiceImpl implements AddressService
{
@Autowired
private AddressDao addressDao;
	@Override
	public List<Address> find()
	{
		return addressDao.find();
	}

	@Override
	public void Insert(Address address)
	{
		addressDao.Insert(address);
	}

	@Override
	public void delete(String id)
	{
		addressDao.delete(Integer.parseInt(id));
	}

	@Override
	public Address findById(int id)
	{
		return addressDao.findById(id);
	}

	@Override
	public void update(Address address)
	{
		addressDao.update(address);
	}

}
