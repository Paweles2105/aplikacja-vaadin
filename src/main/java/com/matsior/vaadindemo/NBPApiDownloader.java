package com.matsior.vaadindemo;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class NBPApiDownloader {

    public static void main(String[] args) throws Exception {
        String sURL = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";
        URL request = new URL(sURL);
        URLConnection yc = request.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String data = bufferedReader.readLine();
        bufferedReader.close();

//        JSONArray jsonarray = new JSONArray(data);
//        for (int i = 0; i < jsonarray.length(); i++) {
//            JSONObject jsonobject = jsonarray.getJSONObject(i);
//            JSONArray arr = jsonobject.getJSONArray("rates");
//            for (i = 0; i < arr.length(); i++) {
//                String currency = arr.getJSONObject(i).getString("currency");
//                String code = arr.getJSONObject(i).getString("code");
//                double mid = arr.getJSONObject(i).getDouble("mid");
//                System.out.println(currency + " ("  + code + ") = " + mid);
//            }
//        }


        /* kawałek odpowiedzi z api w formacie JSON:
[
  {
    "table": "A",
    "no": "054/A/NBP/2022",
    "effectiveDate": "2022-03-18",
    "rates": [
      {
        "currency": "bat (Tajlandia)",
        "code": "THB",
        "mid": 0.1281
      },
      {
        "currency": "dolar amerykański",
        "code": "USD",
        "mid": 4.2707
      },
      {
        "currency": "dolar australijski",
        "code": "AUD",
        "mid": 3.1497
      },
      {
        "currency": "dolar Hongkongu",
        "code": "HKD",
        "mid": 0.5462
      },
      .......
         */

        JSONArray jsonArray = new JSONArray(data);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        JSONArray jsonArray1 = jsonObject.getJSONArray("rates");
        for (int i = 0; i < jsonArray1.length(); i++) {
            String currency = jsonArray1.getJSONObject(i).getString("currency");
            String code = jsonArray1.getJSONObject(i).getString("code");
            double mid = jsonArray1.getJSONObject(i).getDouble("mid");
            System.out.println(currency + " (" + code + ") = " + mid);
        }


        // konwersja walut
        String request1 = "http://api.nbp.pl/api/exchangerates/rates/a/chf/";
        String request2 = "http://api.nbp.pl/api/exchangerates/rates/a/eur/";
        JSONObject jsonObject1 = new JSONObject(IOUtils.toString(new URL(request1)));
        JSONObject jsonObject2 = new JSONObject(IOUtils.toString(new URL(request2)));

        // currency 1
        JSONArray array1 = jsonObject1.getJSONArray("rates");
        System.out.println(array1);
        double mid1 = array1.getJSONObject(0).getDouble("mid");
        System.out.println(mid1);

        // currency 2
        JSONArray array2 = jsonObject2.getJSONArray("rates");
        System.out.println(array2);
        double mid2 = array2.getJSONObject(0).getDouble("mid");
        System.out.println(mid2);

        System.out.println(100 * mid1 / mid2);

        BigDecimal rate1 = getMidByCurrencyCode("CHF");
        BigDecimal rate2 = getMidByCurrencyCode("EUR");

        System.out.println(rate1 + ", " + rate2);
        BigDecimal result = rate1.multiply(BigDecimal.valueOf(100)).divide(rate2, RoundingMode.FLOOR);
        System.out.println(result);

    }

    public static BigDecimal getMidByCurrencyCode(String code) throws Exception {
        return getMidFromJSONObject(getJSONByCode(code));
    }

    public static JSONObject getJSONByCode(String code) throws Exception {
        String request = "http://api.nbp.pl/api/exchangerates/rates/a/" + code + "/";
        return new JSONObject(IOUtils.toString(new URL(request)));
    }

    public static BigDecimal getMidFromJSONObject(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("rates");
        return jsonArray.getJSONObject(0).getBigDecimal("mid");
    }

    public static List<Currency> createCurrencyList() {
        List<Currency> result = new ArrayList<>();
        try {
            String sURL = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";
            URL request = new URL(sURL);
            URLConnection yc = request.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String data = bufferedReader.readLine();
            bufferedReader.close();

            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            JSONArray jsonArray1 = jsonObject.getJSONArray("rates");
            for (int i = 0; i < jsonArray1.length(); i++) {
                String currency = jsonArray1.getJSONObject(i).getString("currency");
                String code = jsonArray1.getJSONObject(i).getString("code");
                double mid = jsonArray1.getJSONObject(i).getDouble("mid");
                System.out.println(currency + " (hhh" + code + ") = " + mid);
                result.add(new Currency(code, currency, mid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}





