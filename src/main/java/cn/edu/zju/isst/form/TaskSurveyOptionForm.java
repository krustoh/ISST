package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.TaskSurveyOption;

public class TaskSurveyOptionForm {
    private int id;
    
    private int taskId;

    @NotBlank(message = "选项不能为空")
    private String label;

    public TaskSurveyOptionForm() {
        
    }
    
    public TaskSurveyOptionForm(TaskSurveyOption option) {
        id = option.getId();
        taskId= option.getTaskId();
        label = option.getLabel();
    }
    
    public TaskSurveyOption build() {
        TaskSurveyOption option = new TaskSurveyOption();
        return bind(option);
    }
    
    public TaskSurveyOption bind(TaskSurveyOption option) {
        option.setId(id);
        option.setTaskId(taskId);
        option.setLabel(label);
        
        return option;
    }
    
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