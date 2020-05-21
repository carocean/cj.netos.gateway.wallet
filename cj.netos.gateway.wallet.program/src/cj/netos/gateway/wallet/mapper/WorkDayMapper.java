package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.WorkDay;
import cj.netos.gateway.wallet.model.WorkDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkDayMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(WorkDayExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(WorkDayExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(WorkDay record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(WorkDay record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<WorkDay> selectByExample(WorkDayExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    WorkDay selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") WorkDay record, @Param("example") WorkDayExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") WorkDay record, @Param("example") WorkDayExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(WorkDay record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(WorkDay record);
}