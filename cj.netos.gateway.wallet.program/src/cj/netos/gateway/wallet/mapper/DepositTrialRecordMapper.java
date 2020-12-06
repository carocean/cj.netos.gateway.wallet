package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.DepositTrialRecord;
import cj.netos.gateway.wallet.model.DepositTrialRecordExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositTrialRecordMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DepositTrialRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DepositTrialRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DepositTrialRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DepositTrialRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DepositTrialRecord> selectByExample(DepositTrialRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DepositTrialRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DepositTrialRecord record, @Param("example") DepositTrialRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DepositTrialRecord record, @Param("example") DepositTrialRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DepositTrialRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DepositTrialRecord record);

    void done(@Param(value = "sn") String sn, @Param(value = "amount") long amount, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);
}