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
public class StudentInfo {
    private String studentid;
    private String name;
    private String lastname;
    private String bfrom;
    private String cellno;
    private String address;
    private String studentclass;
    private String section;
    private String gender;
    private String bloodgroup;
    private String dob;
    private String dateofadmission;
    private int id;
    public StudentInfo() {
    }

    public StudentInfo(int id ,String studentid, String name, String lastname, String bfrom, String cellno, String address, String studentclass, String section, String gender, String dob) {
        this.id=id;
        this.studentid = studentid;
        this.name = name;
        this.lastname = lastname;
        this.bfrom = bfrom;
        this.cellno = cellno;
        this.address = address;
        this.studentclass = studentclass;
        this.section = section;
        this.gender = gender;
        this.dob = dob;
    }

    public StudentInfo(String studentid, String name, String lastname, String studentclass, String section) {
        this.studentid = studentid;
        this.name = name;
        this.lastname = lastname;
        this.studentclass = studentclass;
        this.section = section;
    }
    
   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
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

    public String getBfrom() {
        return bfrom;
    }

    public void setBfrom(String bfrom) {
        this.bfrom = bfrom;
    }

    public String getCellno() {
        return cellno;
    }

    public void setCellno(String cellno) {
        this.cellno = cellno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentclass() {
        return studentclass;
    }

    public void setStudentclass(String studentclass) {
        this.studentclass = studentclass;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDateofadmission() {
        return dateofadmission;
    }

    public void setDateofadmission(String dateofadmission) {
        this.dateofadmission = dateofadmission;
    }
    
    
}
