package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.PlatformAccount;
import cj.netos.gateway.wallet.model.PlatformAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlatformAccountMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(PlatformAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(PlatformAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(PlatformAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(PlatformAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<PlatformAccount> selectByExample(PlatformAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    PlatformAccount selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") PlatformAccount record, @Param("example") PlatformAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") PlatformAccount record, @Param("example") PlatformAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(PlatformAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(PlatformAccount record);
}