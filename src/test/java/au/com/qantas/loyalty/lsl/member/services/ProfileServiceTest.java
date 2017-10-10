package au.com.qantas.loyalty.lsl.member.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.qantas.profile.clients.CRMClient;
import au.com.qantas.profile.clients.impl.DummyInMemoryCRMClientImpl;
import au.com.qantas.profile.model.Profile;
import au.com.qantas.profile.services.ProfileService;

@RunWith(SpringRunner.class)
public class ProfileServiceTest {

  private ProfileService profileService;

  private String xRequestId = "45245245-adfa";

  @Before
  public void setup() {
    CRMClient crmClient = new DummyInMemoryCRMClientImpl();
    profileService = new ProfileService(crmClient);
  }

  @Test
  public void test_initialisation() {
    Profile profile =
        new Profile().firstName("Juan").lastName("Perez").dateOfBirth(LocalDate.of(1978, 3, 15));

    String profileId = profileService.create(profile, xRequestId);

    Profile newProfile = profileService.get(profileId, xRequestId);
    assertEquals("Juan", newProfile.getFirstName());
  }
}
