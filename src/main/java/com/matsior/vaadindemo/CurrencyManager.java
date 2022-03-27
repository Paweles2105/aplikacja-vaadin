package com.matsior.vaadindemo;
import java.util.List;

public class CurrencyManager {
    private List<Currency> currencyList = NBPApiDownloader.createCurrencyList();

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}