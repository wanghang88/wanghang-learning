package com.wanghang.code.design.strategy;


public class StrategyTest {
	
	public static void main(String[] args) {
		 StrategyFactory strategy = new StrategyFactory(new PersonServiceImpl());
		 strategy.eate("米饭");
		 strategy.work("工程师");
		 
		 StrategyFactory strategy2 = new StrategyFactory(new AnimalServiceImpl());
		 strategy2.eate("骨头");
		 strategy2.work("看门");
	}

}
