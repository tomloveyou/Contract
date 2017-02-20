/*
 * Copyright (C) 2016 CaMnter yuanyu.camnter@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package personcom.yl.contract.activity.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Description：Contacts
 * Created by：CaMnter
 * Time：2016-04-10 21:05
 */
public class Contacts implements Serializable{

    public int resId;
    public String name;
    public String pinyin;
    public String header;
    public boolean top = false;
    private String avatot;
    private String phone;
    private String job;
    private String address;
    private String company;
    private String department;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public String getAvatot() {
        return avatot;
    }

    public void setAvatot(String avatot) {
        this.avatot = avatot;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getHeader() {
        if (this.header != null) return this.header;
        if (TextUtils.isEmpty(this.pinyin) || Character.isDigit(this.pinyin.charAt(0))) {
            this.header = null;
        } else {
            this.header = this.pinyin.substring(0, 1).toUpperCase();
            char temp = this.header.charAt(0);
            if (temp < 'A' || temp > 'Z') {
                this.header = "#";
            }
        }
        return this.header;
    }
}
