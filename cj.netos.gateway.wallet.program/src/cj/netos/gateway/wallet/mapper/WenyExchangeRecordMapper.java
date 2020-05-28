package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyExchangeRecordExample;
import java.util.List;

import cj.netos.gateway.wallet.model.WenyPurchRecord;
import org.apache.ibatis.annotations.Param;

public interface WenyExchangeRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WenyExchangeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WenyExchangeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WenyExchangeRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WenyExchangeRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WenyExchangeRecord> selectByExample(WenyExchangeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WenyExchangeRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WenyExchangeRecord record, @Param("example") WenyExchangeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WenyExchangeRecord record, @Param("example") WenyExchangeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WenyExchangeRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WenyExchangeRecord record);

    void updateStatus(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void done(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    List<WenyExchangeRecord> page(@Param(value = "person") String person, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

}