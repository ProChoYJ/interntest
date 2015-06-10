package com.woowahan.intern.internproject.imformation;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2015. 6. 9..
 */
public class BusinessInfomation {

    @SerializedName("Address")
    private String Address;

    @SerializedName("Business_Hour")
    private String BusinessHour;

    @SerializedName("Open_Date")
    private String OpenDate;

    @SerializedName("Phone")
    private String Phone;

    @SerializedName("Order_Count")
    private int OrderCount;

    @SerializedName("Comment")
    private String comment;

    public BusinessInfomation(String address, String businessHour, String openDate, String Phone, int orderCount, String comment) {
        Address = address;
        BusinessHour = businessHour;
        OpenDate = openDate;
        this.Phone = Phone;
        OrderCount = orderCount;
        this.comment = comment;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBusinessHour() {
        return BusinessHour;
    }

    public void setBusinessHour(String businessHour) {
        BusinessHour = businessHour;
    }

    public String getOpenDate() {
        return OpenDate;
    }

    public void setOpenDate(String openDate) {
        OpenDate = openDate;
    }

    public int getOrderCount() {
        return OrderCount;
    }

    public void setOrderCount(int orderCount) {
        OrderCount = orderCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
