package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WenyPurchRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WenyPurchRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WenyPurchRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WenyPurchRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WenyPurchRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WenyPurchRecord> selectByExample(WenyPurchRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WenyPurchRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WenyPurchRecord record, @Param("example") WenyPurchRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WenyPurchRecord record, @Param("example") WenyPurchRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WenyPurchRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WenyPurchRecord record);

    void updateState(@Param(value = "sn") String sn, @Param(value = "state") int state, @Param(value = "code") String code, @Param(value = "message") String message, @Param(value = "lutime") String lutime);
}