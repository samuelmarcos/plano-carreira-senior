package org.example.process_payment.domain.entities;


import jakarta.persistence.*;
import org.example.process_payment.infra.Repositories.contracts.TransactionRepository;

import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Table(name="tb_transaction")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;
    private String description;
    private String method;
    private Integer number;
    private String carrier;
    private Timestamp date;
    private Integer verificationCode;

    public Transaction(){}

    public Transaction(Long id, Integer value, String description, String method, Integer number, String carrier, Timestamp date, Integer verificationCode) {
        super();
        this.id = id;
        this.value = value;
        this.description = description;
        this.method = method;
        this.number = parseCardNumber(number);
        this.carrier = carrier;
        this.date = date;
        this.verificationCode = verificationCode;
    }

    public Integer parseCardNumber(Integer number) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static Transaction builder() {
        return new Transaction();
    }

    public Transaction transactionId(Long value) {
        this.setId(value);
        return this;
    }

    public Transaction transactionValue(Integer value) {
        this.setValue(value);
        return this;
    }

    public Transaction transactionDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public Transaction transactionMethod(String method) {
        this.setMethod(method);
        return this;
    }

    public Transaction cardNumber(Integer number) {
        this.setNumber(number);
        return this;
    }

    public Transaction cardCarrier(String carrier) {
        this.setCarrier(carrier);
        return this;
    }

    public Transaction transactionDate(Timestamp date) {
        this.setDate(date);
        return this;
    }

    public Transaction cardVerificationCode(Integer verificationCode) {
        this.setVerificationCode(verificationCode);
        return this;
    }

    public Transaction build() {
        return this;
    }

}
