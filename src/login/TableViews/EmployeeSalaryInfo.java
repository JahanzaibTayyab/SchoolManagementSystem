/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.TableViews;

/**
 *
 * @author Jahanzaib Tayyab
 */
public class EmployeeSalaryInfo {
    private String id;
    private String name;
    private String post;
    private String joiningdate;
    private String hour;
    private String salary;
    private String service;
    private int sr;

    public EmployeeSalaryInfo(String id, String name, String post, String joiningdate, String hour, String salary, String service, int sr) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.joiningdate = joiningdate;
        this.hour = hour;
        this.salary = salary;
        this.service = service;
        this.sr = sr;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
        
}
