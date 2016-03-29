package com.github.alexwolfgoncharov.termdata.interfaces;



public enum UserRoleEnum{

	ADMIN("ADMIN"), DISPATCHER("DISPATCHER"), USER("USER"), ANONYMOUS("ANONYMOUS");
	
	String userProfileType;

	UserRoleEnum() {
	}
	UserRoleEnum(String userProfileType) {
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	

	 
}