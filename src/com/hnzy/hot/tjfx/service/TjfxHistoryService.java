package com.hnzy.hot.tjfx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hnzy.hot.tjfx.pojo.TjfxHistory;

public interface TjfxHistoryService
{
	 public void InTjfx(TjfxHistory tjfxHistory);
	 public List<TjfxHistory>findTjfx();
	 public List<TjfxHistory>searchInfo( @Param("xqName")String xqName,@Param("recordTime1") String recordTime1,@Param("recordTime2") String recordTime2);
	 //查出金领的室温历史数据
	 public List<TjfxHistory> findSwJl();
}
