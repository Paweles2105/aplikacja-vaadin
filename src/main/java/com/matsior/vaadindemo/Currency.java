package com.matsior.vaadindemo;

public class Currency {
    private String code;
    private String name;
    private double mid;

    public Currency(String code, String name, double mid) {
        this.code = code;
        this.name = name;
        this.mid = mid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return name + " (" + code + ") = " + mid;
    }
}
