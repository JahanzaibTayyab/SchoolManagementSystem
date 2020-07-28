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
public class FeeDetailsInfo {
    private String studenid;
    private String name;
    private String month;
    private String year;
    private String classes;
    private String section;
    private String classfee;
    private String generator;
    private String functionfee;
    private String examsfee;
    private String partyfee;
    private String sportsfee;
    private String tripfee;
    private String booksfee;
    private String stationaryfee;
    private String others;
    private String total;
    private String paid;
    private String balance;
    private int id;
    private String feecode;
    private String Lastmonth;
    public FeeDetailsInfo() {
    }

    public FeeDetailsInfo(String studenid, String name, String month, String year, String classes, String section, String classfee, String generator, String functionfee, String examsfee, String partyfee, String sportsfee, String tripfee, String booksfee, String stationaryfee, String others, String total, String paid, String balance, int id, String Lastmonth) {
        this.studenid = studenid;
        this.name = name;
        this.month = month;
        this.year = year;
        this.classes = classes;
        this.section = section;
        this.classfee = classfee;
        this.generator = generator;
        this.functionfee = functionfee;
        this.examsfee = examsfee;
        this.partyfee = partyfee;
        this.sportsfee = sportsfee;
        this.tripfee = tripfee;
        this.booksfee = booksfee;
        this.stationaryfee = stationaryfee;
        this.others = others;
        this.total = total;
        this.paid = paid;
        this.balance = balance;
        this.id = id;
        this.Lastmonth = Lastmonth;
    }
    public FeeDetailsInfo(String studenid, String name, String month, String year, String classes, String section, String classfee, String generator, String functionfee, String examsfee, String partyfee, String sportsfee, String tripfee, String booksfee, String stationaryfee, String others, String total, String paid, String balance, String feecode, String Lastmonth) {
        this.studenid = studenid;
        this.name = name;
        this.month = month;
        this.year = year;
        this.classes = classes;
        this.section = section;
        this.classfee = classfee;
        this.generator = generator;
        this.functionfee = functionfee;
        this.examsfee = examsfee;
        this.partyfee = partyfee;
        this.sportsfee = sportsfee;
        this.tripfee = tripfee;
        this.booksfee = booksfee;
        this.stationaryfee = stationaryfee;
        this.others = others;
        this.total = total;
        this.paid = paid;
        this.balance = balance;
        this.feecode = feecode;
        this.Lastmonth = Lastmonth;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String getLastmonth() {
        return Lastmonth;
    }

    public void setLastmonth(String Lastmonth) {
        this.Lastmonth = Lastmonth;
    } 
    public String getFeecode() {
        return feecode;
    }

    public void setFeecode(String feecode) {
        this.feecode = feecode;
    }
    public String getStudenid() {
        return studenid;
    }

    public void setStudenid(String studenid) {
        this.studenid = studenid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getClassfee() {
        return classfee;
    }

    public void setClassfee(String classfee) {
        this.classfee = classfee;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getFunctionfee() {
        return functionfee;
    }

    public void setFunctionfee(String functionfee) {
        this.functionfee = functionfee;
    }

    public String getExamsfee() {
        return examsfee;
    }

    public void setExamsfee(String examsfee) {
        this.examsfee = examsfee;
    }

    public String getPartyfee() {
        return partyfee;
    }

    public void setPartyfee(String partyfee) {
        this.partyfee = partyfee;
    }

    public String getSportsfee() {
        return sportsfee;
    }

    public void setSportsfee(String sportsfee) {
        this.sportsfee = sportsfee;
    }

    public String getTripfee() {
        return tripfee;
    }

    public void setTripfee(String tripfee) {
        this.tripfee = tripfee;
    }

    public String getBooksfee() {
        return booksfee;
    }

    public void setBooksfee(String booksfee) {
        this.booksfee = booksfee;
    }

    public String getStationaryfee() {
        return stationaryfee;
    }

    public void setStationaryfee(String stationaryfee) {
        this.stationaryfee = stationaryfee;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

   

    

    
    
    
}
