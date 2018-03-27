package com.hnzy.hot.kfgl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hnzy.hot.kfgl.dao.NoticeDao;
import com.hnzy.hot.kfgl.pojo.Notice;
import com.hnzy.hot.kfgl.service.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService
{
	@Autowired
	private NoticeDao noticeDao;
	@Override
	public List<Notice> findNotice()
	{
		return noticeDao.find();
	}
	
	@Override
	public Notice findNoticeById(int id)
	{
		return noticeDao.findById(id);
	}

	@Override
	public void InsertNotice(Notice notice)
	{
		noticeDao.Insert(notice);
	}

	@Override
	public void deleteNotice(String id)
	{
		noticeDao.delete(Integer.parseInt(id));
	}


	@Override
	public void updateNotice(Notice notice)
	{
		noticeDao.update(notice);
	}

	@Override
	public List<Notice> findNoticega() {
		return noticeDao.findNoticega();
	}

}
