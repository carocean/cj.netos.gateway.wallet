package cj.netos.gateway.wallet;

import cj.netos.gateway.wallet.model.PersonCard;

import java.util.List;

public interface IPersonCardService {
    boolean existsCardBySn(String principal, String cardSn);

    void addPersonCard(PersonCard card);

    PersonCard getPersonCard(String principal, String id);

    List<PersonCard> pagePersonCard(String principal, int limit, long offset);

    void removePersonCard(String principal, String id);

    long totalPersonCard(String principal);
}
