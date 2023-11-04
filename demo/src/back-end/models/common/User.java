package models.common;

import models.Contact;

import java.time.LocalDate;

public abstract class User extends Entity {
    private String name;
    private LocalDate birth;
    private String cpf;
    private Contact contact;

    public User(String name, String cpf, LocalDate birth, Contact contact){
//        if(!User.isValidCPF(cpf)){
//            throw new IllegalArgumentException("Cpf inválido.");
//        }

        this.name = name;
        this.birth = birth;
        this.cpf = cpf;
        this.contact = contact;
    }

    public abstract int getAge();

    public String getName() {
        return this.name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getContact() {
        return this.contact.toString();
    }

    public LocalDate getBirthDate() {
        return this.birth;
    }
    
    public static boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstDigit = (sum * 10) % 11;
        if (firstDigit == 10) {
            firstDigit = 0;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondDigit = (sum * 10) % 11;
        if (secondDigit == 10) {
            secondDigit = 0;
        }

        return (cpf.charAt(9) - '0' == firstDigit) && (cpf.charAt(10) - '0' == secondDigit);
    }
}
