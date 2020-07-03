package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.DepositHubTailsActivity;
import cj.netos.gateway.wallet.model.DepositHubTailsActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositHubTailsActivityMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(DepositHubTailsActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(DepositHubTailsActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(DepositHubTailsActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(DepositHubTailsActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<DepositHubTailsActivity> selectByExample(DepositHubTailsActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    DepositHubTailsActivity selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") DepositHubTailsActivity record, @Param("example") DepositHubTailsActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") DepositHubTailsActivity record, @Param("example") DepositHubTailsActivityExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(DepositHubTailsActivity record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(DepositHubTailsActivity record);
}