package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WenyPurchRecord;
import cj.netos.gateway.wallet.model.WenyPurchRecordExample;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;

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

    void done(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void ackPurchasing(@Param(value = "sn") String sn, @Param(value = "purchAmount") Long purchAmount, @Param(value = "feeRatio") BigDecimal feeRatio, @Param(value = "serviceFee") Long serviceFee, @Param(value = "principalAmount") Long principalAmount, @Param(value = "principalRatio") BigDecimal principalRatio, @Param(value = "ttm") BigDecimal ttm, @Param(value = "bankPurchSn") String bankPurchSn, @Param(value = "status") Integer status, @Param(value = "message") String message);

    void ackPurchased(@Param(value = "sn") String sn, @Param(value = "stock") BigDecimal stock, @Param(value = "price") BigDecimal price, @Param(value = "freeAmount") long freeAmount, @Param(value = "freeRatio") BigDecimal freeRatio, @Param(value = "reserveAmount") long reserveAmount, @Param(value = "reserveRatio") BigDecimal reserveRatio, @Param(value = "status") Integer status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    List<WenyPurchRecord> page(@Param(value = "person") String person, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    void updateStatus(@Param(value = "sn") String sn, @Param(value = "status") Integer status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void exchanging(@Param(value = "sn") String sn, @Param(value = "lutime") String lutime);

    void exchanged(@Param(value = "sn") String sn, @Param(value = "lutime") String lutime);

    List<WenyPurchRecord> pageUnexchanged(@Param(value = "person") String person, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

}