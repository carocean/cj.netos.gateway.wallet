package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.P2pActivity;
import cj.netos.gateway.wallet.model.P2pActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface P2pActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(P2pActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(P2pActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(P2pActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(P2pActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<P2pActivity> selectByExample(P2pActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    P2pActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") P2pActivity record, @Param("example") P2pActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") P2pActivity record, @Param("example") P2pActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(P2pActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(P2pActivity record);
}