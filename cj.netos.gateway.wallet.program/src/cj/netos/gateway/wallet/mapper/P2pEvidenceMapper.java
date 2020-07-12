package cj.netos.gateway.wallet.mapper;

import cj.netos.gateway.wallet.model.P2pEvidence;
import cj.netos.gateway.wallet.model.P2pEvidenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface P2pEvidenceMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(P2pEvidenceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(P2pEvidenceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(P2pEvidence record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(P2pEvidence record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<P2pEvidence> selectByExample(P2pEvidenceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    P2pEvidence selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") P2pEvidence record, @Param("example") P2pEvidenceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") P2pEvidence record, @Param("example") P2pEvidenceExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(P2pEvidence record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(P2pEvidence record);
}