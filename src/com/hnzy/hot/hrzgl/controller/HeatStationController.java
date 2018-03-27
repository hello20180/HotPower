package com.hnzy.hot.hrzgl.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hnzy.hot.hrzgl.pojo.HeatStation;
import com.hnzy.hot.hrzgl.pojo.HeatWX;
import com.hnzy.hot.hrzgl.service.HeatStationService;
import com.hnzy.hot.hrzgl.service.HeatWXService;


@Controller
@RequestMapping("HeatStation")
public class HeatStationController
{
	
	@Autowired
	private HeatStationService heatStationService;
	@Autowired
	private HeatWXService heatWXService;
	private List<HeatStation> heatStationsList;
    //定时刷新
	private List<HeatWX> heatWXList;

	@RequestMapping("heatStation")
	@ResponseBody
	public Map<String, Object> heatStation(HttpServletResponse response, HttpServletRequest request,
			HeatStation heatStation) throws IOException
	{
		Integer integer = (Integer) request.getSession().getAttribute("heat");
		// 当数据库没有数据时，从1开始读
		if (integer == null)
		{
			integer = 1;
			heatStation = heatStationService.findById(integer);
		} else if (integer == 6)
		{
			integer = 1;
			heatStation = heatStationService.findById(integer);
		} else
		{
			heatStation = heatStationService.findById(integer);
		}

		request.getSession().setAttribute("heat", ++integer);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ygsStress", heatStation.getYgsStress());
		map.put("ygsTpt", heatStation.getYgsTpt());
		map.put("yhsStress", heatStation.getYhsStress());
		map.put("yhsTpt", heatStation.getYhsTpt());
		map.put("rbgsTpt", heatStation.getRbgsTpt());
		map.put("rbghsTpt", heatStation.getRbghsTpt());
		map.put("ysFlow", heatStation.getYsFlow());
		map.put("ysHeat", heatStation.getYsHeat());
		map.put("ylHeat", heatStation.getYlHeat());
		map.put("ylFlow", heatStation.getYlFlow());
		map.put("zsStress", heatStation.getZsStress());
		map.put("zsFlow", heatStation.getZsFlow());
		map.put("ydl", heatStation.getYdl());
		map.put("tjFmTickling", heatStation.getTjFmTickling());
		map.put("egsStress", heatStation.getEgsStress());
		map.put("egsTpt", heatStation.getEgsTpt());
		map.put("ehsStress", heatStation.getEhsStress());
		map.put("ehsTpt", heatStation.getEhsTpt());
		map.put("xpTickling", heatStation.getXpTickling());
		map.put("xyElectricity", heatStation.getXyElectricity());
		map.put("yxTpt", heatStation.getYxTpt());
		map.put("exTpt", heatStation.getExTpt());
		map.put("esFlow", heatStation.getEsFlow());
		map.put("elFlow", heatStation.getElFlow());
		map.put("swTpt", heatStation.getSwTpt());
		map.put("bspTickling", heatStation.getBspTickling());
		map.put("bsyElectricity", heatStation.getBsyElectricity());
		map.put("bsFlow", heatStation.getBsFlow());
		map.put("blFlow", heatStation.getBlFlow());
		return map;
	}
	// 查看维修详情
			@RequestMapping("/findById")
			public String findId(HttpServletRequest request, HeatWX heatWX, int id)
			{
				heatWX = heatWXService.findById(id);
				String image=heatWX.getImage();
				 String a[] = image.split(";"); 
				 List<Object> list=new ArrayList<Object>();
				 for (int i = 0; i < a.length; i++)
				{
					list.add(a[i]);
				}
				 request.setAttribute("imag", list);
				request.setAttribute("heat", heatWX);
				return "hrzgl/main";
			}
		
	// 金领时代
	@RequestMapping("HeatStationstate")
	public String HeatStationstate()
	{
		return "hrzgl/HeatStation";
	}
	// 金领时代维修
	@RequestMapping("HeatStationWX")
	public String HeatStationWX(HttpServletRequest request)
	{
		heatWXList=heatWXService.findJLSD();
		
		request.setAttribute("list", heatWXList);
		
		return "hrzgl/HeatStationWX";
	}
	
	//砥柱大厦换热站
	@RequestMapping("heatStationDZDS")
	public String heatStationDZDS()
	{
		return "hrzgl/HeatStationDZDS";
	}
	// 砥柱大厦维修
	@RequestMapping("HeatStationDZDSwx")
	public String HeatStationDZDSwx(HttpServletRequest request)
	{
		heatWXList=heatWXService.findDZDS();
		request.setAttribute("list", heatWXList);
		return "hrzgl/HeatStationDZDSwx";
	}

	//天发首府换热站
	@RequestMapping("heatStationTFSF")
	public String heatStationTFSF()
	{
		return "hrzgl/HeatStationTFSF";
	}
	// 天发首府维修
	@RequestMapping("HeatStationTFSFwx")
	public String HeatStationTFSFwx(HttpServletRequest request)
	{
		heatWXList=heatWXService.findTFSF();
		request.setAttribute("list", heatWXList);
		return "hrzgl/HeatStationTFSFwx";
	}
	
	//壹号城邦换热站
	@RequestMapping("heatStationYHCB")
	public String heatStationYHCB()
	{
		return "hrzgl/HeatStationYHCB";
	}
	// 壹号城邦维修
	@RequestMapping("HeatStationYHCBwx")
	public String HeatStationYHCBwx(HttpServletRequest request)
	{
		heatWXList=heatWXService.findYHCB();
		request.setAttribute("list", heatWXList);
		return "hrzgl/HeatStationYHCBwx";
	}
	
	 //德馨园换热站
	@RequestMapping("heatStationDXY")
	public String heatStationDXY()
	{
		return "hrzgl/HeatStationDXY";
	}
	// 德馨园维修
	@RequestMapping("HeatStationDXYwx")
		public String HeatStationDXYwx(HttpServletRequest request)
		{
			heatWXList=heatWXService.findDXY();
			request.setAttribute("list", heatWXList);
			return "hrzgl/HeatStationDXYwx";
		}

	public List<HeatStation> getHeatStationsList()
	{
		return heatStationsList;
	}

	public void setHeatStationsList(List<HeatStation> heatStationsList)
	{
		this.heatStationsList = heatStationsList;
	}

	public List<HeatWX> getHeatWXList()
	{
		return heatWXList;
	}

	public void setHeatWXList(List<HeatWX> heatWXList)
	{
		this.heatWXList = heatWXList;
	}
	

}
