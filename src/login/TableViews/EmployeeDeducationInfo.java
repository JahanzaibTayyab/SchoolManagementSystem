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
public class EmployeeDeducationInfo {
    private String id;
    private String name;
    private String post;
    private String deducationamount;
    private String totalsalary;
    private String reason;
    private String month;
    private String year;
    private int sr;

    public EmployeeDeducationInfo(String id, String name, String post, String deducationamount, String totalsalary, String reason, String month, String year, int sr) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.deducationamount = deducationamount;
        this.totalsalary = totalsalary;
        this.reason = reason;
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

    public String getDeducationamount() {
        return deducationamount;
    }

    public void setDeducationamount(String deducationamount) {
        this.deducationamount = deducationamount;
    }

    public String getTotalsalary() {
        return totalsalary;
    }

    public void setTotalsalary(String totalsalary) {
        this.totalsalary = totalsalary;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
