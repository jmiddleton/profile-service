package au.com.qantas.profile.model;

public class ErrorCodeConstants {

  public static final String MEMBER_ID_MISSING = "program.member.memberid.missing";
  public static final String INVALID_PARAMS = "program.member.invalidparams";
  public static final String MEMBER_UNAVAILABLE = "program.member.unavailable";
  public static final String PROFILE_SYSTEM_ERROR = "program.profile.error";
  public static final String ACCOUNT_INACTIVE = "program.member.AccountNotActive";
  public static final String MEMBER_INACTIVE = "program.member.memberNotActive";
  public static final String ACCEPT_PAYMENT_ERROR = "program.member.acceptPaymentError";
  public static final String INVALID_PAYLOAD = "program.member.invalid.payload";
  public static final String EMPTY_ADDRESSES = "program.member.AddressDataRequired";
  public static final String BAD_REQUEST = "program.member.badrequest";
  public static final String PRODUCT_CODE = "program.member.product.code";
  public static final String MEMBER_NOT_FOUND = "program.member.noMatchingMemberFound";
  public static final String MEMBER_ALREADY_IN_QCASH = "member.qcash.alreadyMember";

  public static final String MEMBER_EXCEPTION = "program.member.exception";
  public static final String MEMBER_WEBSERVICE_EXCEPTION = "MemberWebServiceException";
  public static final String MEMBER_SERVICE_CURRENTLY_UNAVAILABLE =
      "Member Service Currently Unavailable";
  public static final String DOES_NOT_EXIST = "DoesNotExist";

  public static final String SCHEME_EXCEPTION = "program.scheme.exception";
  public static final String QUERY_WEBSERVICE_EXCEPTION = "CRMCoreWebServiceException";

  private ErrorCodeConstants() {}
}
