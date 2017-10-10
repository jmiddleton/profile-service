
package au.com.qantas.profile.services;

import org.springframework.stereotype.Service;

import au.com.qantas.profile.clients.CRMClient;
import au.com.qantas.profile.model.Profile;
import au.com.qantas.profile.model.Profile.MembershipStatusEnum;

/**
 * This service manages profile information.
 * 
 * @author Jorge Middleton
 *
 */
@Service
public class ProfileService {

  private CRMClient crmClient;

  public ProfileService(CRMClient crmClient) {
    this.crmClient = crmClient;
  }

  public void delete(String profileId, String requestId) {
    this.crmClient.delete(profileId, requestId);
  }

  public String create(Profile profile, String requestId) {
    profile.membershipStatus(MembershipStatusEnum.ACTIVE);

    return this.crmClient.create(profile, requestId);
  }

  public void update(Profile profile, String requestId) {
    this.crmClient.update(profile, requestId);
  }

  public Profile get(String profileId, String requestId) {
    return this.crmClient.get(profileId, requestId);
  }

}
