package ru.acviewer.av.client.bean;

public class PlayerControl {

	private Long id;
	
	private double percentMouse;
	
	public PlayerControl(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public double getPercentMouse() {
		return percentMouse;
	}

	public void setPercentMouse(double percentMouse) {
		this.percentMouse = percentMouse;
	}

}
