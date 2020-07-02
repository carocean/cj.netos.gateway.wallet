package cj.netos.gateway.wallet;

import cj.studio.openport.CheckAccessTokenException;

import java.util.Map;

public interface IPersonService {
    Map<String,Object> getPersonInfo(String accessToken) throws CheckAccessTokenException;

    Map<String, Object> findPerson(String person,String accessToken) throws CheckAccessTokenException;
}
