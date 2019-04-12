package com.lx.xml;

public class Dto {
	private String name = "haha";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Dto(String name) {
		super();
		this.name = name;
	}

	public Dto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Dto [name=" + name + "]";
	}
	
}
