package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IProfitBillService;
import cj.netos.gateway.wallet.ports.IFreezenBillPorts;
import cj.netos.gateway.wallet.ports.IProfitBillPorts;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.ISecuritySession;
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

@CjService(name = "profitBillService")
public class ProfitBillService implements IProfitBillService {
    @CjServiceRef
    IServiceSite site;

    @Override
    public List<Object> pageBill(String person, String wenyBankID, int limit, long offset) throws CircuitException {
        return call_pageBill(person, wenyBankID, limit, offset);
    }

    private List<Object> call_pageBill(String person, String wenyBankID, int limit, long offset) throws CircuitException {
        OkHttpClient client = (OkHttpClient) site.getService("@.http");

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String portsUrl = site.getProperty("rhub.ports.oc.wallet");
        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));

        Map<String, Object> args = new HashMap<>();
        args.put("person", person);
        RequestBody body = RequestBody.create(new Gson().toJson(args).getBytes());
        final Request request = new Request.Builder()
                .url(String.format("%s?person=%s&limit=%s&offset=%s&wenyBankID=%s", portsUrl, person, limit, offset, wenyBankID))
                .addHeader("Rest-Command", "pageBill")
                .addHeader("app-id", appid)
                .addHeader("app-key", appKey)
                .addHeader("app-nonce", nonce)
                .addHeader("app-sign", sign)
                .post(body)
                .build();
        final Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        if (response.code() >= 400) {
            throw new CircuitException("1002", String.format("远程访问失败:%s", response.message()));
        }
        String json = null;
        try {
            json = response.body().string();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        Map<String, Object> map = new Gson().fromJson(json, HashMap.class);
        if (Double.parseDouble(map.get("status") + "") >= 400) {
            throw new CircuitException(map.get("status") + "", map.get("message") + "");
        }
        json = (String) map.get("dataText");
        List<Object> list = new Gson().fromJson(json, ArrayList.class);
        return list;
    }

    @Override
    public List<Object> monthBill(String person, String wenyBankID, int year, int month, int limit, long offset) throws CircuitException {
        return call_monthBill(person, wenyBankID, year, month, limit, offset);
    }

    private List<Object> call_monthBill(String person, String wenyBankID, int year, int month, int limit, long offset) throws CircuitException {
        OkHttpClient client = (OkHttpClient) site.getService("@.http");

        String appid = site.getProperty("appid");
        String appKey = site.getProperty("appKey");
        String appSecret = site.getProperty("appSecret");
        String portsUrl = site.getProperty("rhub.ports.oc.wallet");
        String nonce = Encript.md5(String.format("%s%s", UUID.randomUUID().toString(), System.currentTimeMillis()));
        String sign = Encript.md5(String.format("%s%s%s", appKey, nonce, appSecret));

        Map<String, Object> args = new HashMap<>();
        args.put("person", person);
        RequestBody body = RequestBody.create(new Gson().toJson(args).getBytes());
        final Request request = new Request.Builder()
                .url(String.format("%s?person=%s&limit=%s&offset=%s&year=%s&month=%s&wenyBankID=%s", portsUrl, person, limit, offset, year, month, wenyBankID))
                .addHeader("Rest-Command", "monthBill")
                .addHeader("app-id", appid)
                .addHeader("app-key", appKey)
                .addHeader("app-nonce", nonce)
                .addHeader("app-sign", sign)
                .post(body)
                .build();
        final Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        if (response.code() >= 400) {
            throw new CircuitException("1002", String.format("远程访问失败:%s", response.message()));
        }
        String json = null;
        try {
            json = response.body().string();
        } catch (IOException e) {
            throw new CircuitException("1002", e);
        }
        Map<String, Object> map = new Gson().fromJson(json, HashMap.class);
        if (Double.parseDouble(map.get("status") + "") >= 400) {
            throw new CircuitException(map.get("status") + "", map.get("message") + "");
        }
        json = (String) map.get("dataText");
        List<Object> list = new Gson().fromJson(json, ArrayList.class);
        return list;
    }

}
