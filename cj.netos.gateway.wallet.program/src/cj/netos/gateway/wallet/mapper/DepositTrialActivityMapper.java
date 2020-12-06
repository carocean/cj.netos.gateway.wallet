package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.DepositTrialActivity;
import cj.netos.gateway.wallet.model.DepositTrialActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositTrialActivityMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DepositTrialActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DepositTrialActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DepositTrialActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DepositTrialActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DepositTrialActivity> selectByExample(DepositTrialActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DepositTrialActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DepositTrialActivity record, @Param("example") DepositTrialActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DepositTrialActivity record, @Param("example") DepositTrialActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DepositTrialActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DepositTrialActivity record);

    List<DepositTrialActivity> getAllActivities(@Param(value = "record_sn")String record_sn);

}