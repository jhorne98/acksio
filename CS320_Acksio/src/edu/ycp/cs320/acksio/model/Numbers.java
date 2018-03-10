package edu.ycp.cs320.acksio.model;

public class Numbers {
	private Double first;
	private Double second;
	private Double third;
	private Double result;
	
	public Numbers() {
		
	}
	
	public Numbers(Double first, Double second, Double third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public Double getFirst() {
		return first;
	}
	public void setFirst(Double first) {
		this.first = first;
	}
	public Double getSecond() {
		return second;
	}
	public void setSecond(Double second) {
		this.second = second;
	}
	public Double getThird() {
		return third;
	}
	public void setThird(Double third) {
		this.third = third;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
}
