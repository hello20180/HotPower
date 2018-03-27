package com.hnzy.hot.kfgl.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hnzy.hot.kfgl.pojo.Notice;
import com.hnzy.hot.kfgl.service.NoticeService;
import com.hnzy.hot.xxgl.pojo.Rz;
import com.hnzy.hot.xxgl.service.RzService;

@Controller
@RequestMapping("/Notice")
public class NoticeController
{
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private RzService rzService;
	
	private List<Rz> rzs;

	private List<Notice> notices;

	// 显示公告标题
	@RequestMapping("/Noticelist")
	public String list(HttpServletRequest request)
	{
		notices = noticeService.findNotice();
		request.setAttribute("list", notices);
		return "kfgl/gsgg/noticelist";
	}
	@ResponseBody
	@RequestMapping("/noticelist")
	public JSONObject noticelist(HttpServletRequest request)
	{
		notices = noticeService.findNoticega();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("list", notices);
		return jsonObject;
	}
	

	// 查看公告内容
	@RequestMapping("/findId")
	public String findId(HttpServletRequest request, Notice notice, int id)
	{
		notice = noticeService.findNoticeById(id);
		request.setAttribute("noti", notice);
		return "kfgl/gsgg/main";
	}

	// 发布公告信息
	@RequestMapping(value = "InsertNotice", method = RequestMethod.POST)
	public String InsertNotice(HttpSession session ,Notice notice,Rz rz)
	{	//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("发布公告标题为:"+notice.getTitle());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//发布公告信息
		notice.setSpokesMan((String)session.getAttribute("userName"));
		notice.setCreatTime(new Date());
		noticeService.InsertNotice(notice);
		return "redirect:Noticelist.action";
	}



	//更新信息
	@RequestMapping("updateNotice")
	public String updateNotice(HttpSession session,Notice notice, Rz rz){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("修改公告标题为:"+notice.getTitle());//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//修改公告信息
		notice.setSpokesMan((String)session.getAttribute("userName"));
		notice.setCreatTime(new Date());
		noticeService.updateNotice(notice);
		return "redirect:Noticelist.action";
		
	}
	//删除信息
	@RequestMapping("delete")
	@ResponseBody
	public void delete(HttpSession session,Notice notice,Rz rz,@RequestParam("id")String id){
		//向日志表添加操做
		rz.setCzr((String)session.getAttribute("userName"));//获取操作人
		rz.setCz("删除公告标题");//获取操作内容
		rz.setCzsj(new Date());//获取操作时间
		rzService.insert(rz);
		//删除公告
		noticeService.deleteNotice(id);
		
	}
	
	public List<Notice> getNotices()
	{
		return notices;
	}

	public void setNotices(List<Notice> notices)
	{
		this.notices = notices;
	}

	public List<Rz> getRzs()
	{
		return rzs;
	}

	public void setRzs(List<Rz> rzs)
	{
		this.rzs = rzs;
	}

}
