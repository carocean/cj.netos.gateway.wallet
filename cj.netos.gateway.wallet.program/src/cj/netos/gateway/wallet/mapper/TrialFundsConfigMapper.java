package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.TrialFundsConfig;
import cj.netos.gateway.wallet.model.TrialFundsConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrialFundsConfigMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TrialFundsConfigExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TrialFundsConfigExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TrialFundsConfig record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TrialFundsConfig record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TrialFundsConfig> selectByExample(TrialFundsConfigExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TrialFundsConfig selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TrialFundsConfig record, @Param("example") TrialFundsConfigExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TrialFundsConfig record, @Param("example") TrialFundsConfigExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TrialFundsConfig record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TrialFundsConfig record);

    void updateState(@Param(value = "id")String id,@Param(value = "state") int state);

    void updateRemitAccount(@Param(value = "id")String id,@Param(value = "remitAccount")String remitAccount, @Param(value = "remitName")String remitName);

    void updateTrialAmount(@Param(value = "id")String id,@Param(value = "trialAmount")long trialAmount);

}