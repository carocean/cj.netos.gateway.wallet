package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.ITrialFundsConfig;
import cj.netos.gateway.wallet.mapper.TrialFundsConfigMapper;
import cj.netos.gateway.wallet.model.TrialFundsConfig;
import cj.netos.gateway.wallet.model.TrialFundsConfigExample;
import cj.netos.gateway.wallet.util.IdWorker;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "trialFundsConfig")
public class TrialFundsConfigService implements ITrialFundsConfig {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.TrialFundsConfigMapper")
    TrialFundsConfigMapper trialFundsConfigMapper;

    @CjTransaction
    @Override
    public cj.netos.gateway.wallet.model.TrialFundsConfig getConfig() {
        TrialFundsConfigExample example = new TrialFundsConfigExample();
        example.createCriteria();
        List<TrialFundsConfig> list = trialFundsConfigMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public void configTrial(String remitAccount, String remitName, long trialAmount) {
        TrialFundsConfigExample example = new TrialFundsConfigExample();
        example.createCriteria();
        this.trialFundsConfigMapper.deleteByExample(example);
        TrialFundsConfig config = new TrialFundsConfig();
        config.setTrialAmount(trialAmount);
        config.setState(1);
        config.setRemitName(remitName);
        config.setRemitAccount(remitAccount);
        config.setId(new IdWorker().nextId());
        this.trialFundsConfigMapper.insert(config);
    }

    @CjTransaction
    @Override
    public void updateState(int i) {
        TrialFundsConfig config = getConfig();
        if (config == null) {
            return;
        }
        trialFundsConfigMapper.updateState(config.getId(), i);
    }

    @CjTransaction
    @Override
    public void updateRemitAccount(String remitAccount, String remitName) {
        TrialFundsConfig config = getConfig();
        if (config == null) {
            return;
        }
        trialFundsConfigMapper.updateRemitAccount(config.getId(), remitAccount, remitName);
    }

    @CjTransaction
    @Override
    public void updateTrialAmount(long trialAmount) {
        TrialFundsConfig config = getConfig();
        if (config == null) {
            return;
        }
        trialFundsConfigMapper.updateTrialAmount(config.getId(), trialAmount);
    }
}
