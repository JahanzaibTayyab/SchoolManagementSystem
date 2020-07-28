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
public class SubjectInfo {
    private String teacherid;
    private String name;
    private String subjectcode;
    private String bookname;
    private String marks;
    private int id;

    public SubjectInfo(String teacherid, String name, String subjectcode, String bookname, String marks, int id) {
        this.teacherid = teacherid;
        this.name = name;
        this.subjectcode = subjectcode;
        this.bookname = bookname;
        this.marks = marks;
        this.id = id;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectcode() {
        return subjectcode;
    }

    public void setSubjectcode(String subjectcode) {
        this.subjectcode = subjectcode;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

}