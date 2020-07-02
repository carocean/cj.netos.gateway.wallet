package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.P2pRecord;
import cj.netos.gateway.wallet.model.P2pRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface P2pRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(P2pRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(P2pRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(P2pRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(P2pRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<P2pRecord> selectByExample(P2pRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    P2pRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") P2pRecord record, @Param("example") P2pRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") P2pRecord record, @Param("example") P2pRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(P2pRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(P2pRecord record);

    void done(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

}