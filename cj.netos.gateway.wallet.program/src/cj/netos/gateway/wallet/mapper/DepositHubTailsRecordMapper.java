package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.DepositHubTailsRecord;
import cj.netos.gateway.wallet.model.DepositHubTailsRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositHubTailsRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DepositHubTailsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DepositHubTailsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DepositHubTailsRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DepositHubTailsRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DepositHubTailsRecord> selectByExample(DepositHubTailsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DepositHubTailsRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DepositHubTailsRecord record, @Param("example") DepositHubTailsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DepositHubTailsRecord record, @Param("example") DepositHubTailsRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DepositHubTailsRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DepositHubTailsRecord record);

    void done(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);
}