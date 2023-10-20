package models;
import java.util.regex.Pattern;

import models.common.Entity;

import java.util.regex.Matcher;

public class Contact extends Entity {
    private String phoneNumber;

    public Contact(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Número de telefone celular inválido.");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Número de telefone celular inválido.");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        String phonePattern = "\\d{10}";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return "Número de Telefone: " + phoneNumber;
    }

    @Override
    public void Validate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Validate'");
    }
}
