package com.xdl.act;

public enum Week {
  MON("1111"),WDD("2222");
  private int value;
  
  private String v;
  
  Week(int v){
  	this.value=v;
  }
  
  Week(String v){
  	this.v=v;
  }
  
  public String getV() {
		return v;
	}
  public int getValue() {
		return value;
	}
}
