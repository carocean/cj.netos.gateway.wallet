package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.model.PersonCardExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PersonCardMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(PersonCardExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(PersonCardExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(PersonCard record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(PersonCard record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<PersonCard> selectByExample(PersonCardExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    PersonCard selectByPrimaryKey(String id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") PersonCard record, @Param("example") PersonCardExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") PersonCard record, @Param("example") PersonCardExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(PersonCard record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(PersonCard record);

    List<PersonCard> pagePersonCard(@Param(value = "person") String person, @Param(value = "limit") int limit, @Param(value = "offset") long offset);

}