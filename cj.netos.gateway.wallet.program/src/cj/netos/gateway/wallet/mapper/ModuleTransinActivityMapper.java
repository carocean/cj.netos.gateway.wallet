package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.ModuleTransinActivity;
import cj.netos.gateway.wallet.model.ModuleTransinActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleTransinActivityMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(ModuleTransinActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(ModuleTransinActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(ModuleTransinActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(ModuleTransinActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<ModuleTransinActivity> selectByExample(ModuleTransinActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    ModuleTransinActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") ModuleTransinActivity record, @Param("example") ModuleTransinActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") ModuleTransinActivity record, @Param("example") ModuleTransinActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(ModuleTransinActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(ModuleTransinActivity record);

    List<ModuleTransinActivity> getAllActivities(@Param(value = "record_sn") String record_sn);

}