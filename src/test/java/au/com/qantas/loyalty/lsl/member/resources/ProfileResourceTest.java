package au.com.qantas.loyalty.lsl.member.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.qantas.profile.clients.CRMClient;
import au.com.qantas.profile.clients.impl.DummyInMemoryCRMClientImpl;
import au.com.qantas.profile.exceptions.ProfileException;
import au.com.qantas.profile.model.Profile;
import au.com.qantas.profile.model.ProfileInfo;
import au.com.qantas.profile.resources.ProfileResource;
import au.com.qantas.profile.services.ProfileService;

@RunWith(SpringRunner.class)
public class ProfileResourceTest {

  ProfileResource resource;

  private String xRequestId = "6a0e9dab-0b99-35d4-bf0f-c3b5a58bb73f";

  @Before
  public void setup() {
    CRMClient crmClient = new DummyInMemoryCRMClientImpl();
    resource = new ProfileResource(new ProfileService(crmClient));
  }

  @Test
  public void create_profile_should_return_the_identifier() {

    Profile profile =
        new Profile().firstName("Juan").lastName("Perez").dateOfBirth(LocalDate.of(1978, 3, 15));

    ResponseEntity<ProfileInfo> response = resource.createProfile(profile, xRequestId);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody().getProfileId());
  }

  @Test
  public void update_profile_should_change_the_attributes() {
    Profile profile =
        new Profile().firstName("Juan").lastName("Perez").dateOfBirth(LocalDate.of(1978, 3, 15));

    ResponseEntity<ProfileInfo> response = resource.createProfile(profile, xRequestId);

    profile.countryOfResidency("AU");
    String profileId = response.getBody().getProfileId();
    resource.updateProfile(profile, profileId, xRequestId);
    assertEquals(HttpStatus.OK, response.getStatusCode());

    // check if update successed
    assertNotNull(
        resource.getProfile(profileId, xRequestId, null).getBody().getCountryOfResidency());
  }

  @Test(expected = ProfileException.class)
  public void update_invalid_profile_should_return_not_found() {

    Profile profile =
        new Profile().firstName("Juan").lastName("Perez").dateOfBirth(LocalDate.of(1978, 3, 15));

    resource.updateProfile(profile, "12345", xRequestId);
  }

  @Test
  public void get_profile_should_return_a_valid_profile() {
    Profile profile =
        new Profile().firstName("Juan").lastName("Perez").dateOfBirth(LocalDate.of(1978, 3, 15));

    ResponseEntity<ProfileInfo> response = resource.createProfile(profile, xRequestId);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    String profileId = response.getBody().getProfileId();

    Profile newProfile = resource.getProfile(profileId, xRequestId, null).getBody();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Juan", newProfile.getFirstName());
  }

  @Test(expected = ProfileException.class)
  public void get_profile_should_return_not_found_when_profile_does_not_exists() {
    resource.getProfile("abcd", xRequestId, null).getBody();
  }
}
