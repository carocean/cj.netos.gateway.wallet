package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.ChannelBill;
import cj.netos.gateway.wallet.model.ChannelBillExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ChannelBillMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(ChannelBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(ChannelBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(ChannelBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(ChannelBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<ChannelBill> selectByExample(ChannelBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    ChannelBill selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") ChannelBill record, @Param("example") ChannelBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") ChannelBill record, @Param("example") ChannelBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(ChannelBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(ChannelBill record);

    List<ChannelBill> pageBill(@Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<ChannelBill> monthBill(@Param(value = "year") int year, @Param(value = "month") int month, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    long totalMonthBill(@Param(value = "order") int order, @Param(value = "year") int year, @Param(value = "month") int month);

    List<ChannelBill> pageBillByAccount(@Param(value = "channelAccount") String channelAccount, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<ChannelBill> monthBillByAccount(@Param(value = "channelAccount") String channelAccount, @Param(value = "year") int year, @Param(value = "month") int month, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

    long totalMonthBillByAccount(@Param(value = "channelAccount") String channelAccount, @Param(value = "order") int order, @Param(value = "year") int year, @Param(value = "month") int month);
}