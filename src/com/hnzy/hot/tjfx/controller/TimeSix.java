package com.hnzy.hot.tjfx.controller;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.hnzy.hot.chart.service.CbtqService;
import com.hnzy.hot.sbgl.pojo.Yh;
import com.hnzy.hot.sbgls.service.FmsService;
import com.hnzy.hot.sbgls.service.RbsglService;
import com.hnzy.hot.sjbb.pojo.YhInfo;
import com.hnzy.hot.sjbb.service.YhInfoService;
import com.hnzy.hot.socket.Tqxb;
import com.hnzy.hot.tjfx.pojo.TjfxHistory;
import com.hnzy.hot.tjfx.pojo.TjfxKdHis;
import com.hnzy.hot.tjfx.pojo.XqAvg;
import com.hnzy.hot.tjfx.service.TjfxHistoryService;
import com.hnzy.hot.tjfx.service.TjfxKdHisService;
import com.hnzy.hot.tjfx.service.XqAvgService;
import com.hnzy.hot.xxgl.pojo.XqInfo;
import com.hnzy.hot.xxgl.service.XqInfoService;

@Controller
public class TimeSix
{
	@Autowired
	private YhInfoService yhInfoService;
	@Autowired
	private TjfxHistoryService tjfxHistoryService;
	@Autowired
	private TjfxKdHisService tjfxKdHisService;
	private List<YhInfo> yhInfoList;
	private List<YhInfo> yhList;
	@Autowired
	private XqInfoService xqInfoService;
	@Autowired
	private XqAvgService xqAvgService;
	private List<XqInfo> xqInfos;
	@Autowired
	private CbtqService cbtqService;
	@Autowired
	private RbsglService rbglService;
	@Autowired
	private FmsService fmsService;
	public void print(){
		Tqxb tqxb=new Tqxb();
		Map<String, String>  map =null;
		try
		{
			map = tqxb.getTodayWeather1("101181701");
		} catch (NullPointerException | IOException e1)
		{
			e1.printStackTrace();
		}
		//室外温度
		int wd=Integer.valueOf(map.get("wd"));
		TjfxHistory tjfxHistory=new TjfxHistory();

		yhInfoList=yhInfoService.findYhNameList();
		for(int i=0;i<yhInfoList.size();i++){
		//大于26
		int A=yhInfoService.chartSearchA(yhInfoList.get(i).getXqName(), 0,0,"是","--选择阀门状态--");
		//15-18
		int C=yhInfoService.chartSearchC(yhInfoList.get(i).getXqName(), 0,0,"是","--选择阀门状态--");
		//18-22
		int D=yhInfoService.chartSearchD(yhInfoList.get(i).getXqName(), 0,0,"是","--选择阀门状态--");
		//22-26
		int E=yhInfoService.chartSearchE(yhInfoList.get(i).getXqName(), 0,0,"是","--选择阀门状态--");
		//0
		int G=yhInfoService.chartSearchG(yhInfoList.get(i).getXqName(), 0,0,"是","--选择阀门状态--");
		//0-15
		int B=yhInfoService.chartSearchB(yhInfoList.get(i).getXqName(), 0,0,"是","--选择阀门状态--");
		int cout=A+C+D+E+G+B;
		if(cout!=0){
		float jlA=((float)A/cout)*100;
		float jlC=((float)C/cout)*100;
		float jlD=((float)D/cout)*100;
		float jlE=((float)E/cout)*100;
		float jlG=((float)G/cout)*100;
		float jlB=((float)B/cout)*100;
		String a = String .format("%.1f",jlA);
		String c = String .format("%.1f",jlC);
		String d = String .format("%.1f",jlD);
		String e = String .format("%.1f",jlE);
		String g = String .format("%.1f",jlG);
		String b = String .format("%.1f",jlB);
		tjfxHistory.setXqName(yhInfoList.get(i).getXqName());
		tjfxHistory.setZerot(g+"%");
		tjfxHistory.setzFift(b+"%");
		tjfxHistory.setFiftOct(c+"%");
		tjfxHistory.setOctTw(d+"%");
		tjfxHistory.setTwSix(e+"%");
		tjfxHistory.setTwentySix(a+"%");
		tjfxHistory.setDate(new Date());
		//插入室内温度
		tjfxHistory.setTqyb(wd);
		tjfxHistoryService.InTjfx(tjfxHistory);
		//开度3-15
		int kdc=yhInfoService.findFamKdC(yhInfoList.get(i).getXqName(), 0, 0, "是", "--选择阀门状态--");
		//0度
		int kdf=yhInfoService.findFamKdF(yhInfoList.get(i).getXqName(), 0, 0, "是", "--选择阀门状态--");
		//100度
		int kda=yhInfoService.findFamKdA(yhInfoList.get(i).getXqName(), 0, 0, "是", "--选择阀门状态--");
		int kdCout=kdc+kdf+kda;
		if(kdCout!=0){
		float kdC=((float)kdc/kdCout)*100;
		float kdF=((float)kdf/kdCout)*100;
		float kdA=((float)kda/kdCout)*100;
		
		String kC = String .format("%.1f",kdC);
		String kF = String .format("%.1f",kdF);
		String kA = String .format("%.1f",kdA);
		TjfxKdHis tjfxKdHis=new TjfxKdHis();
		tjfxKdHis.setXqName(yhInfoList.get(i).getXqName());
		tjfxKdHis.setKdLd(kF+"%");
		tjfxKdHis.setKdSW(kC+"%");
		tjfxKdHis.setKdYb(kA+"%");
		tjfxKdHis.setDate(new Date());
		tjfxKdHis.setDate(new Date());
		//插入室内温度
		tjfxKdHis.setTqyb(wd);
		tjfxKdHisService.InKd(tjfxKdHis);
		}
			}
		}	
		//小区平均温度
		xqInfos=xqInfoService.findAvgWdByXqName();
		for(int i=0;i<xqInfos.size();i++){
			XqAvg avg=new XqAvg();
			avg.setAvg(xqInfos.get(i).getAvgWd());
			avg.setXqName(xqInfos.get(i).getXqName());
			avg.setDate(new Date());
			try
			{
				map = tqxb.getTodayWeather1("101181701");
			} catch (NullPointerException | IOException e)
			{
				e.printStackTrace();
			}
			
			//更新时间
			String date=map.get("date");
			avg.setTqyb(wd);
			avg.setTqybDate(date);
			//插叙平均温度计
			xqAvgService.InTAvg(avg);
		}
	}
	int Sh=0;
	int Gw=0;
	public void Bjxx(){
		yhInfoList=yhInfoService.findVd();
		for(int i=0;i<yhInfoList.size();i++){  	//循环的是阀门地址为缴费的实时表中
			yhList=yhInfoService.findHist(yhInfoList.get(i).getValAd());
		    for(int j=0;j<yhList.size();j++){ //根据实时表查询历史表中的数据
		    	if(yhList.get(j).getFmHistory().getRoomTemp()<18 ){
		    		Sh++;
		    	}else if(yhList.get(j).getFmHistory().getRoomTemp()>22 ){
		    		Gw++;
		    	}
		    }
		    if(Sh==50){
		    	yhInfoService.upBjxx(new Date(),"低温",yhInfoList.get(i).getValAd());
		    	Sh=0;
		    	Gw=0;
		    }else if(Gw==50){
		    	yhInfoService.upBjxx(new Date(),"高温",yhInfoList.get(i).getValAd());
		    	Gw=0;
		    	Sh=0;
		    }else{
		    	yhInfoService.upBjxx(new Date(),"否",yhInfoList.get(i).getValAd());
		    	Gw=0;
		    	Sh=0;
		    }
		}
	}
	public void  tqyb(){
		Tqxb tqxb=new Tqxb();
		Map<String, String>  map =null;
		try
		{
			map = tqxb.getTodayWeather1("101181701");
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		} catch (NullPointerException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		int wd=Integer.valueOf(map.get("wd"));
		//更新金盾园小区的室内温度（室内温度=室外温度+1）
		fmsService.updateRooTemp(wd);
		
		String date=map.get("date");
		//插入室外温度
		cbtqService.InsertTq(wd,date);
	}
	//热表异常
	public void rbexp(){
		Exp();
		GHExp();
	}
	//温差在20度以上，累计热量不累加，用户名字蓝色
	public void GHExp(){
		List<Yh> rb=rbglService.SelRbExp(); 
		for(int i=0;i<rb.size();i++){
			List<Yh> rbs=rbglService.SelRbEp(rb.get(i).getRbAd());
			if(rb.get(i).getRb().getDiffTmp()>20){
				for(int j=0;j<rbs.size();j++){
					Double energy=rbs.get(j).getRb().getEnergy();
					j++;
					Double energys=rbs.get(j).getRb().getEnergy();
					if(energy.equals(energys)){
						rbglService.UpExp(rb.get(i).getRb().getRbAd(),2);
					}
				}
			}
		}
	}
	//供水温度40度以上，回水温度30度以上，累计热量不累加红色
	public void Exp(){
		List<Yh> rb=rbglService.SelRbExp();
		for(int i=0;i<rb.size();i++){
			List<Yh> rbs=rbglService.SelRbEp(rb.get(i).getRbAd());
			for(int j=0;j<rbs.size();j++){
				if(rbs.get(j).getRb().getInTmp()>40&& rbs.get(j).getRb().getOutTmp()>30){
					Double energy=rbs.get(j).getRb().getEnergy();
					j++;
					Double energys=rbs.get(j).getRb().getEnergy();
					if(energy.equals(energys)){
						rbglService.UpExp(rb.get(i).getRb().getRbAd(),1);
					}
				}
			}
		}
	}
	
	public List<YhInfo> getYhInfoList()
	{
		return yhInfoList;
	}
	public void setYhInfoList(List<YhInfo> yhInfoList)
	{
		this.yhInfoList = yhInfoList;
	}

	public List<YhInfo> getYhList()
	{
		return yhList;
	}

	public void setYhList(List<YhInfo> yhList)
	{
		this.yhList = yhList;
	}
}
