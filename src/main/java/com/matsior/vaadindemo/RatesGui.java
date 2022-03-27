package com.matsior.vaadindemo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route("")
public class RatesGui extends VerticalLayout {

    CurrencyManager currencyManager = new CurrencyManager();

    public RatesGui() {

        Label label = new Label("Tabela walut");

//        Grid<Currency> grid = new Grid<>(Currency.class, false);
//        grid.addColumn(Currency::getName).setHeader("Waluta");
//        grid.addColumn(Currency::getCode).setHeader("Kod");
//        grid.addColumn(currency -> currency.getMid()).setHeader("Kurs średni [PLN]");
//
//        List<Currency> rates = currencyManager.getCurrencyList();

//        grid.setAllRowsVisible(true);
//        grid.setWidth("600px");
        List<Currency> rates = currencyManager.getCurrencyList();

        Grid<Currency> grid = new Grid<>(Currency.class, false);
        grid.addColumn(c -> c.getName()).setHeader("Waluta");
        grid.addColumn(c -> c.getCode()).setHeader("Kod");
        grid.addColumn(currency -> currency.getMid()).setHeader("Kurs średni [PLN]");
        grid.setItems(rates);
        grid.setWidth("600px");
        grid.setAllRowsVisible(true);



        add(label, grid);

    }
}
