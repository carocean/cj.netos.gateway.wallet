package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.DepositAbsorbActivity;
import cj.netos.gateway.wallet.model.DepositAbsorbActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositAbsorbActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DepositAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DepositAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DepositAbsorbActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DepositAbsorbActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DepositAbsorbActivity> selectByExample(DepositAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DepositAbsorbActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DepositAbsorbActivity record, @Param("example") DepositAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DepositAbsorbActivity record, @Param("example") DepositAbsorbActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DepositAbsorbActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DepositAbsorbActivity record);

    List<DepositAbsorbActivity> getAllActivities(@Param(value = "record_sn") String record_sn);
}