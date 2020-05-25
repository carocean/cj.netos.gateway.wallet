package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WenyPurchActivity;
import cj.netos.gateway.wallet.model.WenyPurchActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WenyPurchActivityMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WenyPurchActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WenyPurchActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WenyPurchActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WenyPurchActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WenyPurchActivity> selectByExample(WenyPurchActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WenyPurchActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WenyPurchActivity record, @Param("example") WenyPurchActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WenyPurchActivity record, @Param("example") WenyPurchActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WenyPurchActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WenyPurchActivity record);
}