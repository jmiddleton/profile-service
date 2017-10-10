package au.com.qantas.profile.clients;

import au.com.qantas.profile.model.Profile;

/**
 * Example CRM api.
 * 
 * @author jmiddleton
 *
 */
public interface CRMClient {

  Profile get(String profileId, String requestId);

  String create(Profile profile, String requestId);

  void update(Profile profile, String requestId);

  void delete(String profileId, String requestId);
}
