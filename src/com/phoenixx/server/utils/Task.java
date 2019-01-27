package com.phoenixx.server.utils;

import java.util.UUID;

public abstract class Task {

    private boolean isComplete = false;
    private String taskUUID;
    private String taskName;

    /** The number of times the task is executed */
    public int taskCycles = 0;

    public Task(String par1) {
        this.taskName = par1;
        this.taskUUID = UUID.randomUUID().toString();
    }

    public abstract void runTask();

    protected void setComplete(boolean par1) {
        this.isComplete = par1;
    }

    public int getTaskCyclesMax() {
        return 80;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskUUID() {
        return this.taskUUID;
    }

    public boolean isComplete() {
        return this.isComplete;
    }
}
