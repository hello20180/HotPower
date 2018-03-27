package com.hnzy.hot.tjfx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.tjfx.pojo.TjfxHistory;
import com.hnzy.hot.tjfx.pojo.TjfxKdHis;

public interface TjfxKdHisDao
{
  public void InKd(TjfxKdHis tjfxKdHis);
  public List<TjfxKdHis>findKd();
  public List<TjfxKdHis>searchInfo( @Param("xqName")String xqName,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
  //查出金领的开度历史数据
  public List<TjfxKdHis> findKdJl();
}
