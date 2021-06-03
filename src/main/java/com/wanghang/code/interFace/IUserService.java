package com.wanghang.code.interFace;


/**
 *IUserService接口继承IBaseService,其实现就会实现IBaseService定义的方法
 */
public interface IUserService extends IBaseService{

    void save();

    String getStr(String name);

}
