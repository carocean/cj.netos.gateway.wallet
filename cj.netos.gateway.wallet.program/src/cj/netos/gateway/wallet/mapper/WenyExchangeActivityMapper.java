package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WenyExchangeActivity;
import cj.netos.gateway.wallet.model.WenyExchangeActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WenyExchangeActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WenyExchangeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WenyExchangeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WenyExchangeActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WenyExchangeActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WenyExchangeActivity> selectByExample(WenyExchangeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WenyExchangeActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WenyExchangeActivity record, @Param("example") WenyExchangeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WenyExchangeActivity record, @Param("example") WenyExchangeActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WenyExchangeActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WenyExchangeActivity record);

    List<WenyExchangeActivity> getAllActivities(@Param(value = "record_sn") String record_sn);

}