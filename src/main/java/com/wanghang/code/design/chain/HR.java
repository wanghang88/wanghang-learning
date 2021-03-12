package com.wanghang.code.design.chain;


/**
 *HR审批请假流程：
 *请假天数大于等于20天小于30,请假则需要HR审批
 *
 */
public class HR implements ApproveHandler {

    private ApproveHandler nextHandler;

    public final static int MAX_LEAVES_CAN_APPROVE = 30;

    @Override
    public void setNextHandler(ApproveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void approve(Leave leave) {
        if (leave.getNumberOfDays()>=20 && leave.getNumberOfDays() < MAX_LEAVES_CAN_APPROVE) {
            String output = String.format("LeaveId: %d, Days: %d, Approver: %s", leave.getLeaveId(), leave.getNumberOfDays(), "HR");
            System.out.println(output);
        } else {
            if (nextHandler != null) {
                nextHandler.approve(leave);
            }
        }
    }
}
