package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WithdrawActivity;
import cj.netos.gateway.wallet.model.WithdrawActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WithdrawActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WithdrawActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WithdrawActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WithdrawActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WithdrawActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WithdrawActivity> selectByExample(WithdrawActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WithdrawActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WithdrawActivity record, @Param("example") WithdrawActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WithdrawActivity record, @Param("example") WithdrawActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WithdrawActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WithdrawActivity record);

    WithdrawActivity getLastWithdrawActivity(@Param(value = "record_sn") String record_sn);

    List<WithdrawActivity> getAllActivities(@Param(value = "record_sn") String record_sn);
}