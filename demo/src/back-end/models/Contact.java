package models;

import models.common.Entity;

public class    Contact extends Entity {
    private String phoneNumber;

    public Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return phoneNumber;
    }

    @Override
    public void Validate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Validate'");
    }
}
