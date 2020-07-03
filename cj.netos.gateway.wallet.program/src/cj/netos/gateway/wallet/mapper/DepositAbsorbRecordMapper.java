package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.DepositAbsorbRecord;
import cj.netos.gateway.wallet.model.DepositAbsorbRecordExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositAbsorbRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DepositAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DepositAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DepositAbsorbRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DepositAbsorbRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DepositAbsorbRecord> selectByExample(DepositAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DepositAbsorbRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DepositAbsorbRecord record, @Param("example") DepositAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DepositAbsorbRecord record, @Param("example") DepositAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DepositAbsorbRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DepositAbsorbRecord record);

    void done(@Param(value = "sn") String sn, @Param(value = "realAmount") BigDecimal realAmount, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);
}