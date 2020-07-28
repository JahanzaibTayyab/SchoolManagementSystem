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
public class EmployeeAllownacesInfo {
    private String id;
    private String name;
    private String post;
    private String Overtime;
    private String medcial;
    private String bounce;
    private String homeAllownace;
    private String TransportAllownce;
    private String others;
    private String month;
    private String year;
    private String Total;
    private int sr;

    public EmployeeAllownacesInfo(String id, String name, String post, String Overtime, String medcial, String bounce, String homeAllownace, String TransportAllownce, String others, String month, String year, String Total, int sr) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.Overtime = Overtime;
        this.medcial = medcial;
        this.bounce = bounce;
        this.homeAllownace = homeAllownace;
        this.TransportAllownce = TransportAllownce;
        this.others = others;
        this.month = month;
        this.year = year;
        this.Total = Total;
        this.sr = sr;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
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

    public String getOvertime() {
        return Overtime;
    }

    public void setOvertime(String Overtime) {
        this.Overtime = Overtime;
    }

    public String getMedcial() {
        return medcial;
    }

    public void setMedcial(String medcial) {
        this.medcial = medcial;
    }

    public String getBounce() {
        return bounce;
    }

    public void setBounce(String bounce) {
        this.bounce = bounce;
    }

    public String getHomeAllownace() {
        return homeAllownace;
    }

    public void setHomeAllownace(String homeAllownace) {
        this.homeAllownace = homeAllownace;
    }

    public String getTransportAllownce() {
        return TransportAllownce;
    }

    public void setTransportAllownce(String TransportAllownce) {
        this.TransportAllownce = TransportAllownce;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

   
        
}
