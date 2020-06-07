package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.TransAbsorbRecord;
import cj.netos.gateway.wallet.model.TransAbsorbRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransAbsorbRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TransAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TransAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TransAbsorbRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TransAbsorbRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TransAbsorbRecord> selectByExample(TransAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TransAbsorbRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TransAbsorbRecord record, @Param("example") TransAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TransAbsorbRecord record, @Param("example") TransAbsorbRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TransAbsorbRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TransAbsorbRecord record);

    void done(@Param(value = "sn") String sn, @Param(value = "realAmount") long realAmount, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);
}