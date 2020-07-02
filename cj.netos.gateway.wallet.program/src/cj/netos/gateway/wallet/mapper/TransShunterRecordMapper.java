package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.TransShunterRecord;
import cj.netos.gateway.wallet.model.TransShunterRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransShunterRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TransShunterRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TransShunterRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TransShunterRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TransShunterRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TransShunterRecord> selectByExample(TransShunterRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TransShunterRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TransShunterRecord record, @Param("example") TransShunterRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TransShunterRecord record, @Param("example") TransShunterRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TransShunterRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TransShunterRecord record);

    void ackReceipt(@Param(value = "sn") String sn, @Param(value = "outTradeSn") String outTradeSn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void done(@Param(value = "sn") String sn, @Param(value = "outTradeSn") String outTradeSn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void updateStatus(@Param(value = "sn") String sn, @Param(value = "outTradeSn") String outTradeSn, @Param(value = "status") Integer status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void ackTransShuntFromBank(@Param(value = "sn") String sn, @Param(value = "realAmount") long realAmount, @Param(value = "status") Integer status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);
}