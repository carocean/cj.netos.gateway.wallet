package cj.netos.gateway.wallet.service;

import cj.netos.gateway.wallet.IPersonCardService;
import cj.netos.gateway.wallet.mapper.PersonCardMapper;
import cj.netos.gateway.wallet.model.PersonCard;
import cj.netos.gateway.wallet.model.PersonCardExample;
import cj.studio.ecm.annotation.CjBridge;
import cj.studio.ecm.annotation.CjService;
import cj.studio.ecm.annotation.CjServiceRef;
import cj.studio.orm.mybatis.annotation.CjTransaction;

import java.util.List;

@CjBridge(aspects = "@transaction")
@CjService(name = "personCardService")
public class PersonCardService implements IPersonCardService {
    @CjServiceRef(refByName = "mybatis.cj.netos.gateway.wallet.mapper.PersonCardMapper")
    PersonCardMapper personCardMapper;

    @CjTransaction
    @Override
    public boolean existsCardBySn(String principal, String cardSn) {
        PersonCardExample example = new PersonCardExample();
        example.createCriteria().andPersonEqualTo(principal).andCardSnEqualTo(cardSn);
        return personCardMapper.countByExample(example) > 0;
    }

    @CjTransaction
    @Override
    public void addPersonCard(PersonCard card) {
        personCardMapper.insert(card);
    }

    @CjTransaction
    @Override
    public PersonCard getPersonCard(String principal, String id) {
        PersonCardExample example = new PersonCardExample();
        example.createCriteria().andPersonEqualTo(principal).andIdEqualTo(id);
        List<PersonCard> list = personCardMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @CjTransaction
    @Override
    public List<PersonCard> pagePersonCard(String principal, int limit, long offset) {
        return personCardMapper.pagePersonCard(principal, limit, offset);
    }

    @CjTransaction
    @Override
    public long totalPersonCard(String principal) {
        PersonCardExample example = new PersonCardExample();
        example.createCriteria().andPersonEqualTo(principal);
        return personCardMapper.countByExample(example);
    }

    @CjTransaction
    @Override
    public void removePersonCard(String principal, String id) {
        PersonCardExample example = new PersonCardExample();
        example.createCriteria().andPersonEqualTo(principal).andIdEqualTo(id);
        personCardMapper.deleteByExample(example);
    }
}
