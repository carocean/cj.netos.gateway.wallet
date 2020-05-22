package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WithdrawRecord;
import cj.netos.gateway.wallet.model.WithdrawRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WithdrawRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WithdrawRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WithdrawRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WithdrawRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WithdrawRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WithdrawRecord> selectByExample(WithdrawRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WithdrawRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WithdrawRecord record, @Param("example") WithdrawRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WithdrawRecord record, @Param("example") WithdrawRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WithdrawRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WithdrawRecord record);

    void settle(@Param(value = "sn") String sn, @Param(value = "realAmount") long realAmount, @Param(value = "settleCode") String settleCode, @Param(value = "settleMsg") String settleMsg, @Param(value = "lutime") String lutime);

    void done(@Param(value = "sn") String sn, @Param(value = "state") int state, @Param(value = "ocCode") String ocCode, @Param(value = "ocMsg") String ocMsg, @Param(value = "lutime") String lutime);
}