package com.joonho.portfolio.board.model.dto;

public class SearchDTO {
	
	private String Condition;
	private String Value;
	
	public SearchDTO() {
	}
	
	public SearchDTO(String condition, String value) {
		Condition = condition;
		Value = value;
	}
	public String getCondition() {
		return Condition;
	}
	public void setCondition(String condition) {
		Condition = condition;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	@Override
	public String toString() {
		return "SearchDTO [Condition=" + Condition + ", Value=" + Value + "]";
	}
	
}
