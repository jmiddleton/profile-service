package au.com.qantas.profile.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import au.com.qantas.profile.exceptions.ProfileException;
import au.com.qantas.profile.model.Profile;
import au.com.qantas.profile.model.ProfileInfo;
import au.com.qantas.profile.services.ProfileService;

/**
 * Main API to manage {@link Profile} information.
 * 
 * @author Jorge Middleton
 *
 */
@RestController
@RequestMapping(value = "/api/profile")
public class ProfileResource {

  private ProfileService profileService;

  @Autowired
  public ProfileResource(ProfileService profileService) {
    this.profileService = profileService;
  }

  /**
   * 
   * Endpoint to create a customer Profile.
   * 
   * @param profile Profile created including profileId. (required)
   * @param profileId
   * @param xRequestId Transaction identifier (optional)
   * @throws ApiException if fails to make API call
   */
  @PostMapping
  public ResponseEntity<ProfileInfo> createProfile(@RequestBody @Valid Profile profile,
      @RequestHeader(value = "X-Request-Id", required = false) String xRequestId) {

    if (profile == null) {
      throw new ProfileException(
          "Missing the required parameter 'profile' when calling createProfile");
    }

    String profileId = profileService.create(profile, xRequestId);

    return new ResponseEntity<>(new ProfileInfo().profileId(profileId), HttpStatus.OK);
  }

  /**
   * 
   * Endpoint to delete a customer Profile.
   * 
   * @param profileId
   * @param xRequestId Transaction identifier (optional)
   * @throws ApiException if fails to make API call
   */
  @DeleteMapping("/{profileId}")
  @CacheEvict(value = "profiles", key = "#profileId")
  public ResponseEntity<Void> deleteProfile(@PathVariable("profileId") String profileId,
      @RequestHeader(value = "X-Request-Id", required = false) String xRequestId) {

    profileService.delete(profileId, xRequestId);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  /**
   * 
   * Get detailed profile information about the current user.
   * 
   * @param contentType Required. Valid value is &#x60;application/json&#x60; (optional)
   * @param profileId
   * @param xRequestId The unique string identifying the request. (optional)
   * @param cacheControl This header allows the client to specify which version of member details is
   *        happy to receive. If max-age&#x3D;0 is set, the server will reply with a fresh version
   *        of the Profile. (optional)
   * @return Profile
   * @throws ApiException if fails to make API call
   */
  @GetMapping("/{profileId}")
  @Cacheable(value = "profiles", key = "#profileId")
  public ResponseEntity<Profile> getProfile(@PathVariable("profileId") String profileId,
      @RequestHeader(value = "X-Request-Id", required = false) String xRequestId,
      @RequestHeader(value = "Cache-Control", required = false) String cacheControl) {

    Profile profile = profileService.get(profileId, xRequestId);
    return new ResponseEntity<>(profile, HttpStatus.OK);
  }

  /**
   * 
   * Endpoint to update profile details. The information provided will be merged with the existing
   * details.
   * 
   * @param profile Updated profile details. (required)
   * @param profileId
   * @param xRequestId Transaction identifier (optional)
   * @throws ApiException if fails to make API call
   */
  @PutMapping("/{profileId}")
  @CacheEvict(value = "profiles", key = "#profileId")
  public ResponseEntity<Void> updateProfile(@RequestBody @Valid Profile profile,
      @PathVariable("profileId") String profileId,
      @RequestHeader(value = "X-Request-Id", required = false) String xRequestId) {

    if (profile == null) {
      throw new ProfileException(
          "Missing the required parameter 'profile' when calling createProfile");
    }

    profile.setProfileId(profileId);
    profileService.update(profile, xRequestId);

    return new ResponseEntity<>(null, HttpStatus.OK);
  }

}
