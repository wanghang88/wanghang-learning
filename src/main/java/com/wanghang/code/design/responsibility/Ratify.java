package com.wanghang.code.design.responsibility;



/**
 * 责任链模式之接口用来处理请假请求和请假结果的返回
 * @author wangh
 * 
 *
 */

public interface Ratify {
	
	/***
	 * 
	 * 在接口Ratify中为什么又定义一个Chain接口呢？
	 * 其实这个接口是单独定义还是内部接口没有太大关系，但是考虑到Chain接口与Ratify接口的关系为提高内聚性就定义为内部接口了定义Ratify接口是为了处理Request那为什么还要定义Chain接口呢？
	 * 
	 *    这正是责任链接口的精髓之处：转发功能及可动态扩展“责任人”,这个接口中定义了两个方法一个是request（）就是为了获取request，如果当前Ratify的实现类获取到request之后发现自己不能处理或者说自己只能处理部分请求，那么他将自己的那部分能处理的就处理掉，然后重新构建一个或者直接转发Request给下一个责任人
	 *    在Android与后台交互中如果使用了Http协议，当然我们可能使用各种Http框架如HttpClient、OKHttp等，我们只需要发送要请求的参数就直接等待结果了，
	 *    这个过程中你可能并没有构建请求头，那么框架帮你把这部分工作给做了，它做的工程中如果使用了责任链模式的话，它肯定会将Request进行包装（也就是添加请求头）成新的Request，我们姑且加他为Request1，
	 *    如果你又希望Http做本地缓存，那么Request1又会被转发到并且重新进一步包装为Request2。
	 *    总之Chain这个接口就是起到对Request进行重新包装的并将包装后的Request进行下一步转发的作用
	 * 
    
     * 接口描述：对request和Result封装，用来转发
     */
   
    
    
	 // 处理请求
    public Result deal(Chain chain);
    
    
    // 接口描述：对request和Result封装，用来转发
    interface Chain {
        // 获取当前request
        Request request();

        // 转发request
        Result proceed(Request request);
   }
}
