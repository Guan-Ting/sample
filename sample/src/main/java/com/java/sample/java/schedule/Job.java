package com.java.sample.java.schedule;

public class Job implements Comparable<Job>{

    private Runnable task;
    //當前時間
    private long startTime;

    //任務下次執行時間
    private long delay;

    //是否執行循環
    private boolean repeat;

    //最大執行次數
    private int maxExecutionCount;

    //已執行次數
    private int executionCount;

    public int getMaxExecutionCount() {
        return maxExecutionCount;
    }

    public void setMaxExecutionCount(int maxExecutionCount) {
        this.maxExecutionCount = maxExecutionCount;
    }

    public int getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }


    @Override
    public int compareTo(Job o) {
        return Long.compare(this.startTime, o.startTime);
    }
}
