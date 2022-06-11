package model;

public class ProcessModel {

    private String processName;
    private String pid;
    private String sessionName;
    private String sessionNumber;
    private String memoryUse;
    private String state;
    private String cpuTime;
    private String userName;
    private String windowTitle;
    private String arrivalTime;

    public ProcessModel() {
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public void setMemoryUse(String memoryUse) {
        this.memoryUse = memoryUse;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getProcessName() {
        return processName;
    }

    public String getPid() {
        return pid;
    }

    public String getSessionName() {
        return sessionName;
    }

    public String getMemoryUse() {
        return memoryUse;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public String getState() {
        return state;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

}
