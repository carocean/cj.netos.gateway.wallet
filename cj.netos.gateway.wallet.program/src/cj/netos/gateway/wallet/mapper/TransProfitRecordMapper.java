package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.TransProfitRecord;
import cj.netos.gateway.wallet.model.TransProfitRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransProfitRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TransProfitRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TransProfitRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TransProfitRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TransProfitRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TransProfitRecord> selectByExample(TransProfitRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TransProfitRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TransProfitRecord record, @Param("example") TransProfitRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TransProfitRecord record, @Param("example") TransProfitRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TransProfitRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TransProfitRecord record);

    void done(@Param(value = "sn") String sn, @Param(value = "realAmount") long realAmount, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);
}