package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.RechargeRecord;
import cj.netos.gateway.wallet.model.RechargeRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(RechargeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(RechargeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(RechargeRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(RechargeRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<RechargeRecord> selectByExample(RechargeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    RechargeRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") RechargeRecord record, @Param("example") RechargeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") RechargeRecord record, @Param("example") RechargeRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(RechargeRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(RechargeRecord record);

    void settle(@Param(value = "sn") String sn, @Param(value = "realAmount") long realAmount, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void done(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    List<RechargeRecord> page(@Param(value = "person") String person, @Param(value = "limit") int limit, @Param(value = "offset") long offset);
}