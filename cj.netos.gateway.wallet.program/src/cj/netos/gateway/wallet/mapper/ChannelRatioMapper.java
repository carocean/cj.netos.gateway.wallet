package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.ChannelRatio;
import cj.netos.gateway.wallet.model.ChannelRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelRatioMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(ChannelRatioExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(ChannelRatioExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(ChannelRatio record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(ChannelRatio record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<ChannelRatio> selectByExample(ChannelRatioExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    ChannelRatio selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") ChannelRatio record, @Param("example") ChannelRatioExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") ChannelRatio record, @Param("example") ChannelRatioExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(ChannelRatio record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(ChannelRatio record);

    List<ChannelRatio> listFeeRatio(@Param(value = "channel") String channel);

    List<ChannelRatio> getFeeRatio(@Param(value = "channel") String channel, @Param(value = "amount") long amount);
}