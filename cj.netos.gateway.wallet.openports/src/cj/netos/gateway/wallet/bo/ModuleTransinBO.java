package cj.netos.gateway.wallet.bo;

import cj.netos.gateway.wallet.model.ModuleTransinRecord;
import cj.ultimate.gson2.com.google.gson.Gson;

public class ModuleTransinBO extends ModuleTransinRecord {

    public static ModuleTransinBO create(ModuleTransinRecord record) {
        return new Gson().fromJson(new Gson().toJson(record), ModuleTransinBO.class);
    }
}
