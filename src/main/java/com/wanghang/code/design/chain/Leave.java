package com.wanghang.code.design.chain;


/**
 *请求对象
 */
public class Leave {

    private int leaveId;
    //请假的天数
    private int numberOfDays;
    public Leave(int leaveId, int numberOfDays) {
        this.leaveId = leaveId;
        this.numberOfDays = numberOfDays;
    }



    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }
    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    public int getLeaveId() {
        return leaveId;
    }
    public int getNumberOfDays() {
        return numberOfDays;
    }
}
