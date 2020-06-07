package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.PayDetails;
import cj.netos.gateway.wallet.model.PayDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayDetailsMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(PayDetailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(PayDetailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(PayDetails record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(PayDetails record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<PayDetails> selectByExample(PayDetailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    PayDetails selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") PayDetails record, @Param("example") PayDetailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") PayDetails record, @Param("example") PayDetailsExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(PayDetails record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(PayDetails record);
}