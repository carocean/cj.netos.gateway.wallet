package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.PayActivity;
import cj.netos.gateway.wallet.model.PayActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(PayActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(PayActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(PayActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(PayActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<PayActivity> selectByExample(PayActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    PayActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") PayActivity record, @Param("example") PayActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") PayActivity record, @Param("example") PayActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(PayActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(PayActivity record);

    List<PayActivity> getAllActivities(@Param(value = "record_sn") String record_sn);
}