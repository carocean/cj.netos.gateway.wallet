package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.RechargeActivity;
import cj.netos.gateway.wallet.model.RechargeActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RechargeActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(RechargeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(RechargeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(RechargeActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(RechargeActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<RechargeActivity> selectByExample(RechargeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    RechargeActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") RechargeActivity record, @Param("example") RechargeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") RechargeActivity record, @Param("example") RechargeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(RechargeActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(RechargeActivity record);

    List<RechargeActivity> getAllActivities(@Param(value = "record_sn") String record_sn);
}