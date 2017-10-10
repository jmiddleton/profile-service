package au.com.qantas.profile.clients.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import au.com.qantas.profile.clients.CRMClient;
import au.com.qantas.profile.exceptions.ProfileException;
import au.com.qantas.profile.model.Profile;

@Service
public class DummyInMemoryCRMClientImpl implements CRMClient {

  private Map<String, Profile> entries = new HashMap<>();

  public Profile get(String profileId, String requestId) {
    Profile profile = entries.get(profileId);

    if (profile == null) {
      throw new ProfileException("profile.notfound");
    }
    return profile;
  }

  public String create(Profile profile, String requestId) {

    String id = UUID.randomUUID().toString();
    profile.setProfileId(id.substring(0, 10));

    entries.put(profile.getProfileId(), profile);
    return profile.getProfileId();
  }

  public void update(Profile profile, String requestId) {
    if (!entries.containsKey(profile.getProfileId())) {
      throw new ProfileException("profile.notfound");
    }

    entries.put(profile.getProfileId(), profile);
  }

  public void delete(String profileId, String requestId) {
    if (!entries.containsKey(profileId)) {
      throw new ProfileException("profile.notfound");
    }

    entries.remove(profileId);
  }

}
