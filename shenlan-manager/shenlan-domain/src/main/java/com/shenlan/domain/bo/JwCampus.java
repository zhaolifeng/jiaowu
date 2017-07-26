package com.shenlan.domain.bo;

import com.shenlan.domain.BaseEntity;

public class JwCampus extends BaseEntity {

    private  String name;
    private  String address;
    private  Integer rooms;
    private  String tel;
    private  String manager;
    private  String campusType;
    private  int campusStauts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCampusType() {
        return campusType;
    }

    public void setCampusType(String campusType) {
        this.campusType = campusType;
    }

    public int getCampusStauts() {
        return campusStauts;
    }

    public void setCampusStauts(int campusStauts) {
        this.campusStauts = campusStauts;
    }
}