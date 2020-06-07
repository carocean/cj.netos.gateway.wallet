package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.TransAbsorbActivity;
import cj.netos.gateway.wallet.model.TransAbsorbActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransAbsorbActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TransAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TransAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TransAbsorbActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TransAbsorbActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TransAbsorbActivity> selectByExample(TransAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TransAbsorbActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TransAbsorbActivity record, @Param("example") TransAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TransAbsorbActivity record, @Param("example") TransAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TransAbsorbActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TransAbsorbActivity record);

    List<TransAbsorbActivity> getAllActivities(@Param(value = "record_sn") String record_sn);
}