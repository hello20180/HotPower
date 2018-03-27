package com.hnzy.hot.xxgl.service;

import java.util.List;

import com.hnzy.hot.xxgl.pojo.Rz;

public interface RzService {
	

	public List<Rz> rzList();
	public void insert(Rz rz);
	public List<Rz> Search(String cz);
}
