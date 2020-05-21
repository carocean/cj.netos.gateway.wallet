package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WenyExchangeRecord;
import cj.netos.gateway.wallet.model.WenyExchangeRecordExample;
import java.util.List;
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
}