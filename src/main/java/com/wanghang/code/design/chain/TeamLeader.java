package com.wanghang.code.design.chain;


/**
 *具体的处理对象TeamLeader,10天以内直接领导审批
 *
 *
 */
public class TeamLeader implements ApproveHandler {

    private ApproveHandler nextHandler;

    public final static int MAX_LEAVES_CAN_APPROVE = 10;

    @Override
    public void setNextHandler(ApproveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 请假天数小于10天的直接由TeamLeader审批完了后结束流程，否则到下个节点审批;
     * @param leave
     */
    @Override
    public void approve(Leave leave) {
        if (leave.getNumberOfDays() < MAX_LEAVES_CAN_APPROVE) {
            String output = String.format("LeaveId: %d, Days: %d, Approver: %s", leave.getLeaveId(), leave.getNumberOfDays(), "TeamLeader");
            System.out.println(output);
        } else {
            if (nextHandler != null) {
                nextHandler.approve(leave);
            }
        }
    }
}
