package personcom.yl.contract.mvp.model;

import java.io.Serializable;

/**
 * Created by changlianjiuzhou on 17/2/15.
 */

public class Contract implements Serializable {
    protected String initialLetter;
    private String name;
    private String avatot;
    private String phone;
    private String job;
    private String address;
    private String company;
    private String department;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatot() {
        return avatot;
    }

    public void setAvatot(String avatot) {
        this.avatot = avatot;
    }

    public String getInitialLetter() {
        return initialLetter;
    }

    public void setInitialLetter(String initialLetter) {
        this.initialLetter = initialLetter;
    }
}
