package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

public class TaskSurveyOptionForm {
    private int id;
    
    private int taskId;

    @NotBlank(message = "选项不能为空")
    private String label;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}