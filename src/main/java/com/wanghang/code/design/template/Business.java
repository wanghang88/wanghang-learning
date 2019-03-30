package com.wanghang.code.design.template;



/**
 * 处理业务的模板类,基类, 定义了处理这个业务的基本方法
 * 
 * 
 * 
 * @author wangh
 *
 */
public abstract class Business {
	//模板方法
     public final void businessTemplate() {
    	 //1:获取排队号
    	 getNusinessNo();
    	 
    	 //2填写用户资料
    	 writeBusinessType();
    	 
    	 //3:等待业务办理
    	 if(isPersonType()) {
    		 waiteBusiness(); 
    	 }
    	 //4:办理业务
    	 handleBusiness();
     }
   

	//1:获取排队号的具体实现
	 private void getNusinessNo() {
		System.out.println("先获取排队号");
	}
	
	//2:填写用户资料的业务(业务类型)由子类自己去实现
	 protected abstract void writeBusinessType();
	
	 //3:取到排队号之后, 填完业务类型之后, 等待办理业务
	 private void waiteBusiness() {
		 System.out.println("我是69号, 正在等待办理业务");
	 }
	 
	 //4:办理的业务在子类中实现
	 protected abstract void handleBusiness();
	 
	 //等待办理业务的时候, 如果是vip用户则不需要排队,这个判断条件也是在子类中做具体的实现
	 protected  boolean isPersonType() {
		 return true;
	 };
	
}
