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
public class EmployeePaymentInfo {
    private String id;
    private String name;
    private String post;
    private String salary;
    private String totalsalary;
    private String paid;
    private String balance;
    private String status;
    private String month;
    private String year;
    private int sr;

    public EmployeePaymentInfo(String id, String name, String post, String salary, String totalsalary, String paid, String balance, String status, String month, String year, int sr) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.salary = salary;
        this.totalsalary = totalsalary;
        this.paid = paid;
        this.balance = balance;
        this.status = status;
        this.month = month;
        this.year = year;
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTotalsalary() {
        return totalsalary;
    }

    public void setTotalsalary(String totalsalary) {
        this.totalsalary = totalsalary;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    
   
        
}
