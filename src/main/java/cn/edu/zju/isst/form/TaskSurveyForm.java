package cn.edu.zju.isst.form;

import java.util.Date;

import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.TaskSurvey;

public class TaskSurveyForm {
    private int id;

    private int userId;

    private int taskId;

    private Date departTime;

    private Date returnTime;

    private int optionId;

    public TaskSurveyForm() {
    }

    public TaskSurveyForm(TaskSurvey taskSurvey) {
        id = taskSurvey.getId();
        taskId = taskSurvey.getTaskId();
        userId = taskSurvey.getUserId();
        departTime = taskSurvey.getDepartTime();
        returnTime = taskSurvey.getReturnTime();
        optionId = taskSurvey.getOptionId();
    }

    public TaskSurvey build() {
        TaskSurvey taskSurvey = new TaskSurvey();
        bind(taskSurvey);

        return taskSurvey;
    }

    public void bind(TaskSurvey taskSurvey) {
        taskSurvey.setUserId(userId);
        taskSurvey.setTaskId(taskId);
        taskSurvey.setOptionId(optionId);
        taskSurvey.setDepartTime(departTime);
        taskSurvey.setReturnTime(returnTime);
    }

    public Result validate() {
        if (null != returnTime && null != departTime && returnTime.getTime() < departTime.getTime()) {
            return new Result(42, "出发时间不能大于返回时间");
        }

        return new Result();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }
}