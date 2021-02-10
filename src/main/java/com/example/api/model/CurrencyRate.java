package com.example.api.model;

import javax.persistence.*;

@Entity
@Table(name = "CurrencyRate")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "code")
    private String r030;

    @Column(name = "rate")
    private double rate;

    @Column(name = "txt")
    private String txt;

    @Column(name = "cc")
    private String cc;

    public CurrencyRate() {
    }

    public CurrencyRate(String r030, double rate, String txt, String cc, int id) {
        this.r030 = r030;
        this.rate = rate;
        this.txt = txt;
        this.cc = cc;
        this.id = id;
    }

    public String getCode() {
        return r030;
    }

    public void setCode(String code) {
        this.r030 = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "id=" + id +
                ", code='" + r030 + '\'' +
                ", rate=" + rate +
                ", txt='" + txt + '\'' +
                ", cc='" + cc + '\'' +
                '}';
    }
}
