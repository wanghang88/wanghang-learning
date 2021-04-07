package com.wanghang.code.design.decorator.one.ConcreteComponent;

import com.wanghang.code.design.decorator.one.component.Component;


/**
 *
 * 具体构件角色,也就是Component的具体实现，大哥比方对应的是狗:(对应狗)，也是Component的实现类
 */
public class ConcreteComponent implements Component {
    @Override
    public void function() {
        System.out.println("基本功能：呼吸+觅食+睡觉");
    }
}
