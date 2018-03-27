package com.hnzy.hot.nhfx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.nhfx.pojo.Nhfxtxx;

public interface NhfxtxxService
{
	 public List<Nhfxtxx> sumYlHeat(@Param("xqName")String xqName,@Param("readTime1") String readTime1,@Param("readTime2") String readTime2);
	 public void UpNhxx(@Param("xqName")String xqName,@Param("nhz")Double nhz,@Param("readTime1") String readTime1);
	 public List<Nhfxtxx> findList(@Param("xqName")String xqName);
	 public List<Nhfxtxx> findTimeListS (@Param("xqName")String xqName, @Param("readTime") String readTime);
	 public Nhfxtxx findNhz(@Param("xqName")String xqName,@Param("TimeListId") String TimeListId);
	 public Nhfxtxx findNhzS(@Param("xqName")String xqName,@Param("TimeListSId") String TimeListSId);
}
