package com.hnzy.hot.nhfx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.nhfx.pojo.Znh;

public interface ZnhDao
{
  public void InsertRh(@Param("rhLx")String rhLx,@Param("rh")Double rh,@Param("readTime") String readTime);
  public List<Znh> find(@Param("rhLx")String rhLx,@Param("readTime1") String readTime1,@Param("readTime2") String readTime2);
}
