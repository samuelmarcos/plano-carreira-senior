package org.example.process_payment.domain.entities;


import jakarta.persistence.*;

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

}
