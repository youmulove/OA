package com.thinkgem.jeesite.modules.iim.entity;

import java.util.ArrayList;
import java.util.List;

public class Group {

	private String name;
	private int nums;
	private int id;

	private List<Friend> item = new ArrayList<Friend>();

	public void setItem(List<Friend> item) {
		this.item = item;
	}

	public List<Friend> getItem() {
		return item;
	}

	public void setNums(int nums) {
		this.nums = nums;
	}

	public int getNums() {
		return nums;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
