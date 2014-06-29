package cn.edu.zju.isst.form;

import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.TaskSurvey;

public class TaskSurveyForm {
    private int id;

    private int userId;

    private int taskId;

    private int optionId;

    private String optionOther;

    private String remarks;

    public TaskSurveyForm() {
    }

    public TaskSurveyForm(TaskSurvey taskSurvey) {
        id = taskSurvey.getId();
        taskId = taskSurvey.getTaskId();
        userId = taskSurvey.getUserId();
        optionId = taskSurvey.getOptionId();
        optionOther = taskSurvey.getOptionOther();
        remarks = taskSurvey.getRemarks();
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
        taskSurvey.setOptionOther(optionOther);
        taskSurvey.setRemarks(remarks);
    }

    public Result validate() {
        if (optionId == 0 && (null == optionOther || "".equals(optionOther) || optionOther.trim().equals(""))) {
            return new Result(42, "请输入其他选项");
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
    
    public String getOptionOther() {
        return optionOther;
    }

    public void setOptionOther(String optionOther) {
        this.optionOther = optionOther;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }
}