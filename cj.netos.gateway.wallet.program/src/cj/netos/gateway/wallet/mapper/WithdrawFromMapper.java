package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WithdrawFrom;
import cj.netos.gateway.wallet.model.WithdrawFromExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WithdrawFromMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WithdrawFromExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WithdrawFromExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WithdrawFrom record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WithdrawFrom record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WithdrawFrom> selectByExample(WithdrawFromExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WithdrawFrom selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WithdrawFrom record, @Param("example") WithdrawFromExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WithdrawFrom record, @Param("example") WithdrawFromExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WithdrawFrom record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WithdrawFrom record);
}