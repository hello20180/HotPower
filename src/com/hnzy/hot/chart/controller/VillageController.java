package com.hnzy.hot.chart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnzy.hot.chart.pojo.Village;
import com.hnzy.hot.chart.service.VillageService;
import com.hnzy.hot.custompojo.VillageSelectNodes;
@Controller
@RequestMapping("/Village")
public class VillageController {

	@Autowired
	private VillageService villageService;


	@RequestMapping("/selectMenu")
	@ResponseBody
	public List<VillageSelectNodes> getVillageSelectMenu() {
		List<VillageSelectNodes> nodes = new ArrayList<VillageSelectNodes>();
		List<Village> xqList = this.villageService.findXQ();
		for (int i = 0; i < xqList.size(); i++) {
			String xqName = xqList.get(i).getXqName();
			
			VillageSelectNodes nodesXQ = new VillageSelectNodes();
			nodesXQ.setNodeId(i + 11);
			nodesXQ.setParentId(0);
			nodesXQ.setNodeName(xqName);
			nodes.add(nodesXQ);
			//获取小区
			Village villageXQ = new Village();
			villageXQ.setXqName(xqName);
			List<Village> boList = this.villageService.findBOByXQ(villageXQ);
			for (int j = 0; j < boList.size(); j++) {
				Integer buildNO = boList.get(j).getBuildNO();
				VillageSelectNodes nodesBO = new VillageSelectNodes();
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
				nodesBO.setNodeName(String.valueOf(buildNO));
				nodes.add(nodesBO);
				Village villageBO = new Village();
				villageBO.setXqName(xqName);
				villageBO.setBuildNO(buildNO);
				List<Village> coList = this.villageService.findCOByXQAndBO(villageBO);
				for (int k = 0; k < coList.size(); k++) {
					Integer cellNO = coList.get(k).getCellNO();
					VillageSelectNodes nodesCO = new VillageSelectNodes();
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
					nodesCO.setNodeName(String.valueOf(cellNO));
					nodes.add(nodesCO);
				}
			}
		}
		return nodes;
	}


}
