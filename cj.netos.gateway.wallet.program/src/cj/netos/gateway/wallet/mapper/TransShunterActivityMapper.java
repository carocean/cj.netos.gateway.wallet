package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.TransShunterActivity;
import cj.netos.gateway.wallet.model.TransShunterActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransShunterActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TransShunterActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TransShunterActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TransShunterActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TransShunterActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TransShunterActivity> selectByExample(TransShunterActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TransShunterActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TransShunterActivity record, @Param("example") TransShunterActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TransShunterActivity record, @Param("example") TransShunterActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TransShunterActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TransShunterActivity record);

    List<TransShunterActivity> getAllActivities(@Param(value = "recordSn") String recordSn);
}