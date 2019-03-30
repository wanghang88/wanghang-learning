package com.wanghang.code.design.template;



/**
 * 用户1来办理业务
 * 
 * @author wangh
 *
 */
public class Person1 extends Business{
	@Override
	protected void writeBusinessType() {
		System.out.println("我填好业务类型了哦");
	}
	@Override
	protected void handleBusiness() {
		System.out.println("我办理好业务了");
	}
	
	
	protected boolean isPersonType() {
		System.out.println("因为这个用户是Vip客户,所以不需要排队");
		return false;
	}
	
	
}
