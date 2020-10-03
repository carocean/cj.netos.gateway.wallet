package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.ChannelAccount;
import cj.netos.gateway.wallet.model.ChannelAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChannelAccountMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(ChannelAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(ChannelAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(ChannelAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(ChannelAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<ChannelAccount> selectByExample(ChannelAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    ChannelAccount selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") ChannelAccount record, @Param("example") ChannelAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") ChannelAccount record, @Param("example") ChannelAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(ChannelAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(ChannelAccount record);

    List<ChannelAccount> pageAccountOfChannel(@Param("channel") String channel, @Param("limit") int limit, @Param("offset") long offset);

    List<ChannelAccount> pageAccount(@Param("limit") int limit, @Param("offset") long offset);

    long totalBalanceByChannel(@Param("channel") String channel);

    long totalAllChannelBalance();
}