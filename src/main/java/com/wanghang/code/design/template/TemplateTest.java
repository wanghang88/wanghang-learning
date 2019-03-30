package com.wanghang.code.design.template;


/**
 * 模板方法模式:对于一组共性的操作,共性的操作可以在父类中进行, 可变的逻辑在子类中进行
 * 
 * 1：定义一个抽象的基类
 * 2：将共性的操作的逻辑给实现;
 * 3：将可变的逻辑,申明为抽象方法(并且方法由protect修饰),这部分可变的逻辑由子类去实现;
 * 4：对于子类中可变逻辑的实现,可能由条件的限制(钩子方法,这个钩子方法是一个空实现或者默认实现),
  *       根据条件是否使用条件,由子类自行决定(子类中重写这个钩子函数);
 * 5:在基类中定义一个模板方法, 将这组操作的方法组合在一起, 并且用final修饰, 就完成了模板方法模式了
 * 
 * @author wangh
 *
 */

public class TemplateTest {
	
	public static void main(String[] args) {
		Business business=new Person1();
		System.out.println("用户Person1去办理业务===========");
		business.businessTemplate();
		System.out.println("用户Person1去办理业务结束===========");
	}

}
