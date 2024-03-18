package org.example.process_payment.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Timestamp;
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer value;
    private String description;
    private String method;
    private Integer number;
    private String carrier;
    private Timestamp date;
    private Integer verificationCode;

    public Transaction() {}
    public Transaction(Integer value, String description, String method, Integer number, String carrier, Timestamp date, Integer verificationCode) {
        this.value = value;
        this.description = description;
        this.method = method;
        this.number = this.parseCarnNumber(number);
        this.carrier = carrier;
        this.date = date;
        this.verificationCode = verificationCode;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Integer parseCarnNumber(Integer number) {
        String parsedNumber = String.valueOf(number);
        String fourLastNumbers = parsedNumber.substring(parsedNumber.length() -4);
        return Integer.valueOf(fourLastNumbers);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "value=" + value +
                ", description='" + description + '\'' +
                ", method='" + method + '\'' +
                ", number=" + number +
                ", carrier='" + carrier + '\'' +
                ", date=" + date +
                ", verificationCode=" + verificationCode +
                '}';
    }
}
