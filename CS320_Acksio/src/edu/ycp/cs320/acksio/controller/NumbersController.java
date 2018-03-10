package edu.ycp.cs320.acksio.controller;

import edu.ycp.cs320.acksio.model.Numbers;

public class NumbersController {
	private Numbers model;
	
	public NumbersController(Numbers model) {
		this.model = model;
	}
	
	public void add() {
		if(model.getFirst() == null)
			model.setResult(add(model.getSecond(), model.getThird()));
		else if(model.getSecond() == null)
			model.setResult(add(model.getFirst(), model.getThird()));
		else if(model.getThird() == null)
			model.setResult(add(model.getFirst(), model.getSecond()));
		else
			model.setResult(add(model.getFirst(), model.getSecond(), model.getThird()));
	}
	
	public Double add(Double first, Double second) {
		return first + second;
	}
	
	public Double add(Double first, Double second, Double third) {
		return add(first, second) + third;
	}
	
	public void multiply() {
		if(model.getFirst() == null)
			model.setResult(multiply(model.getSecond(), model.getThird()));
		else if(model.getSecond() == null)
			model.setResult(multiply(model.getFirst(), model.getThird()));
		else if(model.getThird() == null)
			model.setResult(multiply(model.getFirst(), model.getSecond()));
		else
			model.setResult(multiply(model.getFirst(), model.getSecond(), model.getThird()));
	}
	
	public Double multiply(Double first, Double second) {
		return first * second;
	}
	
	public Double multiply(Double first, Double second, Double third) {
		return multiply(first, second) * third;
	}
}
