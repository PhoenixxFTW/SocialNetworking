package com.phoenixx.server.utils;

import java.util.ArrayList;

public class TaskHandler {

    /** List if tasks running on the cloud */
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public void runTasks() {
        for(int i = 0; i < taskList.size(); i++) {
            Task task = this.taskList.get(i);

            if(task.isComplete() == false) {
                try {
                    task.runTask();
                } catch (Exception e) {
                    System.out.println("Failed to Handle Task: " + task.getTaskName());
                    e.printStackTrace();
                    task.taskCycles = 999999999;
                }

                task.taskCycles++;
            }

            if(task.isComplete() || task.taskCycles > task.getTaskCyclesMax()) {
                this.taskList.remove(i);
            }
        }
    }

    /** Add a task to this specific handlers */
    public void addTask(Task par1) {
        if(this.taskList.contains(par1) == false) {
            taskList.add(par1);
        }
    }
}
