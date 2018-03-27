package com.hnzy.hot.xxgl.service;

import java.util.List;

import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.xtgl.pojo.User;

public interface YZglService {
	//查询所有
	public List<YhInfo> findYhInfo();
	//更新
	public void update(YhInfo yhInfo);
	//插入
	public void Insert(YhInfo yhInfo);
	//删除
	public void delete(String id);
	//根据Id查找
	//public YhInfo findById(int id);
	//根据用户分类搜索
	public List<YhInfo> yhflSear(String yhfl);
}