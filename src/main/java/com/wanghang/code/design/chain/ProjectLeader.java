package com.wanghang.code.design.chain;


/**
 *项目经理处理审批流程：
 * 请假天数大于等于10天小于20,请假则需要项目经理审批
 *
 *
 */
public class ProjectLeader implements ApproveHandler{

    private ApproveHandler nextHandler;

    public final static int MAX_LEAVES_CAN_APPROVE = 20;


    @Override
    public void setNextHandler(ApproveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void approve(Leave leave) {
        if (leave.getNumberOfDays()>=10 && leave.getNumberOfDays() < MAX_LEAVES_CAN_APPROVE) {
            String output = String.format("LeaveId: %d, Days: %d, Approver: %s", leave.getLeaveId(), leave.getNumberOfDays(), "ProjectLeader");
            System.out.println(output);
        } else {
            if (nextHandler != null) {
                nextHandler.approve(leave);
            }
        }

    }
}
