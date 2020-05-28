package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IBalanceService;
import cj.studio.ecm.IServiceSite;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceSite;
import cj.studio.ecm.net.CircuitException;
import cj.studio.openport.util.Encript;
import cj.ultimate.gson2.com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CjService(name = "balanceService")
public class BalanceService implements IBalanceService {
    @CjServiceSite
    IServiceSite site;

    @Override
    public Map<String, Object> getAllAccount(String principal) throws CircuitException {
        return callGetAllAccount(principal);
    }

    private Map<String, Object> callGetAllAccount(String person) throws CircuitException {
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
                .url(String.format("%s?person=%s", portsUrl, person))
                .addHeader("Rest-Command", "getAllAccount")
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
        map = new Gson().fromJson(json, HashMap.class);
        return map;
    }

    @Override
    public Map<String, Object> getRootAccount(String principal) {
        return null;
    }


    @Override
    public Map<String, Object> getBalanceAccount(String principal) throws CircuitException {
        return callGetBalanceAccount(principal);
    }

    private Map<String, Object> callGetBalanceAccount(String person) throws CircuitException {
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
                .url(String.format("%s?person=%s", portsUrl, person))
                .addHeader("Rest-Command", "getBalanceAccount")
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
        map = new Gson().fromJson(json, HashMap.class);
        return map;
    }

    @Override
    public Map<String, Object> getAbsorbAccount(String principal) {
        return null;
    }

    @Override
    public Map<String, Object> getFreezenAccount(String principal) {
        return null;
    }

    @Override
    public Map<String, Object> getProfitAccount(String principal) {
        return null;
    }

    @Override
    public Map<String, Object> listStockAccount(String principal) {
        return null;
    }

    @Override
    public Map<String, Object> getRelatedStockAccount(String principal) {
        return null;
    }

    @Override
    public Map<String, Object> getStockAccount(String principal, String wenyBankID) {
        return null;
    }
}
