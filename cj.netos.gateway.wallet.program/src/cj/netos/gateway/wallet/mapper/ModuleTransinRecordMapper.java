package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.ModuleTransinRecord;
import cj.netos.gateway.wallet.model.ModuleTransinRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleTransinRecordMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(ModuleTransinRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(ModuleTransinRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(ModuleTransinRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(ModuleTransinRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<ModuleTransinRecord> selectByExample(ModuleTransinRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    ModuleTransinRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") ModuleTransinRecord record, @Param("example") ModuleTransinRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") ModuleTransinRecord record, @Param("example") ModuleTransinRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(ModuleTransinRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(ModuleTransinRecord record);

    void settle(@Param(value = "sn") String sn, @Param(value = "amount") long amount, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

    void done(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message, @Param(value = "lutime") String lutime);

}