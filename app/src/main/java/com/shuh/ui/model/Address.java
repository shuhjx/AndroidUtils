package com.shuh.ui.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
@Table(name = "addresses")
public class Address extends BaseModel {

    @Column(name = "tag")
    private String tag;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "addr")
    private String addr;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private boolean status;

    @Column(name = "user")
    private User user;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
