package com.hnzy.hot.sbgl.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnzy.hot.chart.pojo.Village;
import com.hnzy.hot.chart.service.VillageService;
import com.hnzy.hot.custompojo.VillageTreeNodes;

@Controller
@RequestMapping("/Sbgl")
public class ZtreeController {

	@Autowired
	private VillageService villageService;

	private List<Village> villages;
	@RequestMapping("ztree")
	public String getVillageTreeMenu(HttpServletRequest request) {
		List<VillageTreeNodes> nodes = new ArrayList<VillageTreeNodes>();
		List<Village> xqList = this.villageService.findXQ();
		
		for (int i = 0; i < xqList.size(); i++) {
			String xqName = xqList.get(i).getXqName();
			VillageTreeNodes nodesXQ = new VillageTreeNodes();
			nodesXQ.setNodeId(i + 11);
			nodesXQ.setParentId(0);
			nodesXQ.setNodeName(xqName);
			nodes.add(nodesXQ);
			Village villageXQ = new Village();
			villageXQ.setXqName(xqName);
			List<Village> boList = this.villageService.findBOByXQ(villageXQ);
			for (int j = 0; j < boList.size(); j++) {
				Integer buildnO = boList.get(j).getBuildNO();
				String buildNo=String.valueOf(buildnO);
				VillageTreeNodes nodesBO = new VillageTreeNodes();
				if (i > 0) {
					if (j > 0) {
						nodesBO.setNodeId((i + 11) * 10 + j);
					} else {
						nodesBO.setNodeId((i + 11) * 10);
					}
				} else if (j > 0) {
					nodesBO.setNodeId((i + 11) * 10 + j);
				} else {
					nodesBO.setNodeId((i + 11) * 10);
				}
				nodesBO.setParentId(i + 11);
				nodesBO.setNodeName(buildNo);
				nodes.add(nodesBO);
				Village villageBO = new Village();
				villageBO.setXqName(xqName);
				villageBO.setBuildNO(buildnO);
				List<Village> coList = this.villageService.findCOByXQAndBO(villageBO);
				for (int k = 0; k < coList.size(); k++) {
					Integer cellNO = coList.get(k).getCellNO();
					VillageTreeNodes nodesCO = new VillageTreeNodes();
					if (i > 0) {
						if (j > 0) {
							if (k > 0) {
								nodesCO.setNodeId(((i + 11) * 10 + j) * 10 + k);
							} else {
								nodesCO.setNodeId(((i + 11) * 10 + j) * 10);
							}
							nodesCO.setParentId((i + 11) * 10 + j);
						} else if (k > 0) {
							nodesCO.setNodeId((i + 11) * 100 + k);
							nodesCO.setParentId((i + 11) * 10);
						} else {
							nodesCO.setNodeId((i + 11) * 100);
							nodesCO.setParentId((i + 11) * 10);
						}
					} else if (j > 0) {
						if (k > 0) {
							nodesCO.setNodeId(((i + 11) * 10 + j) * 10 + k);
						} else {
							nodesCO.setNodeId(((i + 11) * 10 + j) * 10);
						}
						nodesCO.setParentId((i + 11) * 10 + j);
					} else if (k > 0) {
						nodesCO.setNodeId((i + 11) * 100 + k);
						nodesCO.setParentId((i + 11) * 10);
					} else {
						nodesCO.setNodeId((i + 11) * 100);
						nodesCO.setParentId((i + 11) * 10);
					}
					String cellnO=String.valueOf(cellNO);
					nodesCO.setNodeName(cellnO);
					nodes.add(nodesCO);
				}
			}
		}
		StringBuffer buf=new StringBuffer();
		for (int i = 0; i < nodes.size(); i++) {
			VillageTreeNodes villageNodes = nodes.get(i);
			buf.append("{id:" + villageNodes.getNodeId() + ",pId:"
					+ villageNodes.getParentId() + " ,name: \""+ villageNodes.getNodeName()  + "\"},");
		}
		buf.deleteCharAt(buf.length() - 1);
		request.setAttribute("buf", buf);

		return "sbgl/ztree";
		}

	
	public List<Village> getVillages() {
		return villages;
	}

	public void setVillages(List<Village> villages) {
		this.villages = villages;
	}

}
