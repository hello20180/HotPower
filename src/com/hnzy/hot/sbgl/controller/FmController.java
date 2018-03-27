package com.hnzy.hot.sbgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hnzy.hot.sbgl.pojo.Fm;
import com.hnzy.hot.sbgl.service.FmService;

@Controller
@RequestMapping("/Sbgl")
public class FmController {
	@Autowired
	private FmService fmService;

	private List<Fm> fmlist;

	// 数据展示
	@RequestMapping("Fm")
	public String fmlist(HttpServletRequest request) throws UnsupportedEncodingException {
		fmlist = fmService.find();
		request.setAttribute("Fm", fmlist);
		return "/sbgl/fm";
	}

	// 树形图数据查询
	@RequestMapping("findz")
	public String findz(HttpServletRequest request, @Param("xqName") String xqName, @Param("buildNO") Integer buildNO,
			@Param("cellNO") Integer cellNO) throws UnsupportedEncodingException {
		xqName = new String(xqName.getBytes("ISO-8859-1"), "utf-8") + "";
		fmlist = fmService.findZ(xqName, buildNO, cellNO);
		request.setAttribute("Fm", fmlist);
		return "/sbgl/fm";
	}

	public List<Fm> getFmlist() {
		return fmlist;
	}

	public void setFmlist(List<Fm> fmlist) {
		this.fmlist = fmlist;
	}

}
