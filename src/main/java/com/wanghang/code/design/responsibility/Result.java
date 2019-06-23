package com.wanghang.code.design.responsibility;





/**
 * 责任链模式的批准结果对象Result
 * 
 * @author wangh
 *
 */
public class Result {
	public boolean isRatify;
    public String info;

    public Result() {
    }
    public Result(boolean isRatify, String info) {
        super();
        this.isRatify = isRatify;
        this.info = info;
   }
    
   public boolean isRatify() {
        return isRatify;
   }

   public void setRatify(boolean isRatify) {
        this.isRatify = isRatify;
   }

   public String getReason() {
        return info;
   }

   public void setReason(String info) {
        this.info = info;
   }
   @Override
   public String toString() {
        return "Result [isRatify=" + isRatify + ", info=" + info + "]";
   }

}
