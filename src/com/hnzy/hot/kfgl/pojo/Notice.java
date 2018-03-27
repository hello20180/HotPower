package com.hnzy.hot.kfgl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
	private static final long serialVersionUID = -3546956450124418191L;
	private int id;
	private String title;
	private String article;
	private String spokesMan;
	private Date creatTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getSpokesMan() {
		return spokesMan;
	}

	public void setSpokesMan(String spokesMan) {
		this.spokesMan = spokesMan;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", article=" + article + ", spokesMan=" + spokesMan
				+ ", creatTime=" + creatTime + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
