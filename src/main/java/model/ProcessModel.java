package model;

public class ProcessModel {

    private int Pid;
    private String name;
    private String user;
    private String description;
    private char priority;
    private String memory;
    private String cpu;
    public ProcessModel() {
    }

    public void setPid(int Pid) {
        this.Pid = Pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(char priority) {
        this.priority = priority;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getPid() {
        return Pid;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public char getPriority() {
        return priority;
    }

    public String getMemory() {
        return memory;
    }

    public String getCpu() {
        return cpu;
    }
}
