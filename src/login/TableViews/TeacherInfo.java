/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.TableViews;

import javafx.beans.property.*;

/**
 *
 * @author Jahanzaib Tayyab
 */
public class TeacherInfo {
    private String staffid;
    private String name;
    private String lastname;
    private String cnic;
    private String cellno;
    private String marriedstatus;
    private String address;
    private String post;
    private String dateofleaving;
    private String gender;
    private String Currentstatus;
    private String dateofjoining;
    private int id;
    public TeacherInfo() {
    }

    public TeacherInfo(String staffid, String name, String lastname, String cnic, String cellno, String marriedstatus, String address, String post, String dateofleaving, String gender, String Currentstatus, String dateofjoining, int id) {
        this.staffid = staffid;
        this.name = name;
        this.lastname = lastname;
        this.cnic = cnic;
        this.cellno = cellno;
        this.marriedstatus = marriedstatus;
        this.address = address;
        this.post = post;
        this.dateofleaving = dateofleaving;
        this.gender = gender;
        this.Currentstatus = Currentstatus;
        this.dateofjoining = dateofjoining;
        this.id = id;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getCellno() {
        return cellno;
    }

    public void setCellno(String cellno) {
        this.cellno = cellno;
    }

    public String getMarriedstatus() {
        return marriedstatus;
    }

    public void setMarriedstatus(String marriedstatus) {
        this.marriedstatus = marriedstatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDateofleaving() {
        return dateofleaving;
    }

    public void setDateofleaving(String dateofleaving) {
        this.dateofleaving = dateofleaving;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCurrentstatus() {
        return Currentstatus;
    }

    public void setCurrentstatus(String Currentstatus) {
        this.Currentstatus = Currentstatus;
    }

    public String getDateofjoining() {
        return dateofjoining;
    }

    public void setDateofjoining(String dateofjoining) {
        this.dateofjoining = dateofjoining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    
    
    
}
