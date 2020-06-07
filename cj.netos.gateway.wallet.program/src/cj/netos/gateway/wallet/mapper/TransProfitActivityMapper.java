package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.TransProfitActivity;
import cj.netos.gateway.wallet.model.TransProfitActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransProfitActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(TransProfitActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(TransProfitActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(TransProfitActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(TransProfitActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<TransProfitActivity> selectByExample(TransProfitActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    TransProfitActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") TransProfitActivity record, @Param("example") TransProfitActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") TransProfitActivity record, @Param("example") TransProfitActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(TransProfitActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(TransProfitActivity record);

    List<TransProfitActivity> getAllActivities(@Param(value = "record_sn") String record_sn);
}