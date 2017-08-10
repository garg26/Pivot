package simplifii.framework.utility;

import java.util.LinkedHashMap;

public interface AppConstants {

    public static final String DEF_REGULAR_FONT = "Roboto-Regular.ttf";
    String APP_LINK = "https://drive.google.com/file/d/0B8wKJnD6sONHeXlUbm5pOTk4dGM/view?usp=sharing";
    LinkedHashMap<Integer, String> storeCategory = new LinkedHashMap<Integer, String>();
    int TASKCODE_REGISTER = 10;
    int TASKCODE_LOGIN = 11;
    int TASKCODE_BRICKS_REGISTER = 12;
    int REQUESTCODE_GOOGLE_SIGHN_IN = 13;
    int TASKCODE_WHEELS_REGISTER = 14;
    int TASKCODE_SEARCH_BRICKS = 15;
    int TASKCODE_SEARCH_WHEELS = 16;
    int TASK_CODE_IMAGE_SLIDER = 17;

    interface REQUEST_CODE {

        int GOOGLE_SIGN_IN = 4;
        int ADD_DOC_ACTIVITY = 17;
        int LOGIN = 19;
        int SERVICE_REQ = 20;
        int CART_IEMS = 21;
        int ORDER_PLACED = 22;
        int LAUNDRY_CART_ITEM = 23;
        int SHOW_FOOD_DETAIL = 24;
        int ORDER_UPDATE = 25;
        int EDIT_ORDER = 26;
        int LOGIN_RETRIVE_STAY = 27;
        int LOGIN_STAY_LINE = 28;
        int LOGIN_MY_PROFILE = 29;
        int PERSONAL_INFO_ACTIVITY = 30;
        int IDENTITY_ACTIVITY = 31;
        int PREF_ACTIVITY = 32;
        int LOYALTY_ACTIVITY = 33;
        int SPECIAL_REQ_ACTIVITY = 34;
        int COMPANY_ACTIVITY = 35;
        int EARLY_CHECKIN_ACTIVITY = 36;
        int PROFILE = 37;
        int FROM_EDIT_PROFILE = 38;
        int INTERNET_ORDER = 39;
        int CHECK_IN = 40;
        int RESULT_NULL = 41;
        int GET_DIRECTION = 42;
    }


    public static interface PARAMS {
        String LAT = "latitude";
        String LNG = "longitude";
    }

    public static interface VALIDATIONS {
        String EMPTY = "empty";
        String EMAIL = "email";
        String MOBILE = "mobile";
    }

    public static interface ERROR_CODES {

        public static final int UNKNOWN_ERROR = 0;
        public static final int NO_INTERNET_ERROR = 1;
        public static final int NETWORK_SLOW_ERROR = 2;
        public static final int URL_INVALID = 3;
        public static final int DEVELOPMENT_ERROR = 4;

    }

    public static interface PAGE_URL {
        String TEST_URL = "http://52.26.27.78:8080";
        String BASE_URL = "http://52.26.27.78:8080";
//        String BASE_URL = "http://35.154.99.43:8080";
        String APP_BASE_URL = BASE_URL + "/aboutstays";

        String USER = APP_BASE_URL + "/user";
        String HOTEL = APP_BASE_URL + "/hotel";
        String BOOKING = APP_BASE_URL + "/booking";
        String STAYS = APP_BASE_URL + "/stays";
        String ROOM = APP_BASE_URL + "/room";
        String USER_OPTED_SERVICES = APP_BASE_URL + "/userOptedService";
        String AIRPORT_SERVICE = APP_BASE_URL + "/airportService";
        String AIRPORT_PICKUP = APP_BASE_URL + "/airportPickup";
        String ROOM_SERVICE = APP_BASE_URL + "/roomService";
        String FOOD_ITEMS = APP_BASE_URL + "/foodItem";
        String LAUNDRY = APP_BASE_URL + "/laundry";
        String LAUNDRY_ITEM = APP_BASE_URL + "/laundryItem";
        String CART = APP_BASE_URL + "/cart";
        String ADD_REMOVER_MULTIPLE_CART = CART + "/addOrRemoveMultiple";
        String HOUSEKEEPING_SERVICE = APP_BASE_URL + "/housekeepingService";
        String INTERNET_SERVICE = APP_BASE_URL + "/internetService";
        String EXPENSE_DASHBOARD = APP_BASE_URL + "/expenseDashboard";
        String CITY_GUIDE = APP_BASE_URL + "/cityGuide";
        String CITY_GUIDE_ITEM = APP_BASE_URL + "/cgItem";
        String HOUSE_RULES = APP_BASE_URL + "/houseRules";
        String REQUEST_DASHBOARD = APP_BASE_URL + "/requestDashboard";
        String COMMUTE_SERVICE = APP_BASE_URL + "/commuteService";
        String BELLBOY_ORDER = APP_BASE_URL + "/bellBoyOrder";
        String MAINTENANCE = APP_BASE_URL + "/maintenance";
        String MAINTENANCE_ITEM = APP_BASE_URL + "/maintenanceItem";
        String COMMUTE_ORDER = APP_BASE_URL + "/commuteOrder";
        String MAINTENANCE_REQUEST = APP_BASE_URL + "/maintenanceRequest";
        String RESERVATION_SERVICE = APP_BASE_URL + "/reservationService";
        String RESERVATION_ITEM = APP_BASE_URL + "/reservationItem";
        String SERVICE_FEEDBACK = APP_BASE_URL + "/serviceFeedback";
        String LATE_CHECKOUT =  APP_BASE_URL + "/lateCheckoutRequest";
        String STAYS_FEEDBACK = APP_BASE_URL + "/staysFeedback";
        String CHECKIN = APP_BASE_URL + "/checkin";
        String ENTERTAINMENT_SERVICE = APP_BASE_URL + "/entertaimentService";
        String ENTERTAINMENT_ITEM = APP_BASE_URL + "/entItem";
        String SUGGESTED_HOTELS = APP_BASE_URL + "/suggestedHotels";
        String COMPLEMENTARY_SERVICE = APP_BASE_URL + "/complementaryServices";
        String CURRENT_STAYS = APP_BASE_URL + "/currentStays";
        String NOTIFICATION = APP_BASE_URL + "/notification";
        String BASE_COMMON = APP_BASE_URL + "/common";

//        String ROOM_SERVICE = TEST_URL + "/aboutstays/roomService";
        String SIGNUP = USER + "/signup";
        String GET_OTP = USER + "/generateOtp";
        String VALIDATE_OTP = USER + "/otpValidated";
        String LOGIN = USER + "/login";
        String GET_USER = USER + "/get";
        String UPDATE_PASS = USER + "/updatePassword";
        String FORGOT_PASS = USER + "/forgotPassword";
        String VALIDATE_SIGNUP = USER + "/validateSignup";
        String UPDATE_CONTACT = USER +"/updateContactDetails";
        String UPDATE_PERSONAL_DETAILS = USER + "/updatePersonalDetails";
        String UPDATE_PREFERENCE = USER + "/updateStayPrefs";
        String UPDATE_IDENTITY = USER + "/updateIdentityDocs";
        String UPDATE_PROFILE_PIC = USER + "/updateProfilePic";

        String GET_ALL_HOTELS = HOTEL + "/getHotels";
        String GET_ROOM_BY_ID = HOTEL + "/getRooms?hotelId=";
        String GET_HOTELS_BY_NAME_CITY = HOTEL + "/cityAndName";
        String GET_HOTEL_BY_ID = HOTEL + "/get";
        String GET_HOTEL_GENERAL_INFO = HOTEL + "/generalInfo";
        String GET_SERVICE = HOTEL + "/getGS";
        String GET_BOOKING_DATA = BOOKING + "/get";
        String ADD_TO_STAYLINE = STAYS + "/add";
        String GET_USER_STAYLINE = STAYS + "/getAllWithHotelInfo";
        String GET_USER_ALL_STAYS = STAYS + "/getUsersAllStays";

        //String GET_USER_OPTED_SERVICES = USER_OPTED_SERVICES + "/getByUserAndStay";
        String GET_USER_OPTED_SERVICES = USER_OPTED_SERVICES + "/getByUserStaysHotel";

        String GET_AIRPORT_SERVICE = AIRPORT_SERVICE + "/get";
        String INITIATE_PICKUP = AIRPORT_PICKUP + "/add";

        String GET_AIRPORT_BOOKING = AIRPORT_PICKUP + "/get";
        String UPDATE_PICKUP = AIRPORT_PICKUP + "/update";
        String GET_ROOM_SERVICE = ROOM_SERVICE + "/get";
        String GET_FOOD_ITEM = FOOD_ITEMS + "/fetchByType";
        String SEARCH_FOOD_ITEM = ROOM_SERVICE + "/searchFoodItem";
        String GET_FOOD_BYID = FOOD_ITEMS + "/get";
        String GET_LAUNDRY_BYID = LAUNDRY + "/get";
        String GET_LAUNDRY_ITEM_BYID = LAUNDRY_ITEM + "/getByLaundryType";
        String GET_LAUNDRY_ITEM_BY_SEARCH = LAUNDRY_ITEM + "/search";
        String ADD_DELETE_TO_CART = ADD_REMOVER_MULTIPLE_CART + "/foodItem";
        String GET_CART_DETAILS = CART + "/get";
        String GET_HOUSEKEEPING_SERVICE = HOUSEKEEPING_SERVICE + "/get";
        String GET_HOUSEKEEPING_ITEM = APP_BASE_URL + "/housekeepingItem/fetchByType";
        String SEARCH_HOUSEKEEPING_ITEM = HOUSEKEEPING_SERVICE + "/searchHKItem";
        String GET_INTERNET_SERVICE = INTERNET_SERVICE + "/get";
        String PLACE_CART_ORDER = CART + "/placeOrder";
        String ADD_DELETE_TO_LAUNDRY_CART = ADD_REMOVER_MULTIPLE_CART + "/laundryItem";

        String GET_CATEGORY_BASED_EXPENSE = EXPENSE_DASHBOARD + "/generateCategorized";
        String GET_DATE_WISE_EXPENSE = EXPENSE_DASHBOARD + "/generateTimeBased";
        String ADD_DELETE_TO_HOUSEKEEPING_CART = ADD_REMOVER_MULTIPLE_CART + "/hkItem";
        String GET_HOTELS_BY_ID = HOTEL + "/get";

        String GET_CITY_GUIDE = CITY_GUIDE + "/get";
        String FETCH_CITY_GUIDE_ITEM = CITY_GUIDE_ITEM + "/fetchByType";

        String GET_HOUSE_RULES = HOUSE_RULES + "/get";
        String GET_REQUEST_DASHBOARD = REQUEST_DASHBOARD + "/create";
        String GET_REQUEST_DASHBOARD_DETAIL = REQUEST_DASHBOARD + "/getDetails";
        String CANCEL_ORDER = REQUEST_DASHBOARD + "/cancel";
        String CANCEL_AIRPORT_PICKUP = AIRPORT_PICKUP + "/cancel";
        String EDIT_ORDER_DETAILS = REQUEST_DASHBOARD + "/edit";
        String GET_COMMUTE = COMMUTE_SERVICE + "/get";
        String REQUEST_BELLBOY = BELLBOY_ORDER + "/add";
        String GET_MAINTENANCE = MAINTENANCE + "/get";
        String ORDER_COMMUTE = COMMUTE_ORDER + "/add";
        String GET_MAINTENANCE_ITEM = MAINTENANCE_ITEM +  "/fetchByType";
        String REQUEST_MAINTENANCE = MAINTENANCE_REQUEST + "/add";
        String GET_RESERVATION = RESERVATION_ITEM + "/fetchAllItemsForImageListingFlow";
        String GET_RESERVATION_ITEM_BY_TYPE = RESERVATION_ITEM + "/fetchByType";
        String GET_RESERVATION_SUB_ITEMS = RESERVATION_ITEM + "/getAllSubItems";
        String REQUEST_HEALTH_BEAUTY_RESERVE = APP_BASE_URL + "/reservationOrder/add";
        String GET_SERVICE_FEEDBACK = SERVICE_FEEDBACK + "/getByUosId";
        String SUBMIT_FEEDBACK = SERVICE_FEEDBACK + "/add";
        String UPDATE_FEEDBACK = SERVICE_FEEDBACK + "/update";

        String GET_LATE_CHECKOUT_INFO = ROOM + "/getLateCheckoutInfo";
        String REQUEST_LATE_CHECKOUT = LATE_CHECKOUT + "/modify";
        String RETREIVE_LATE_CHECKOUT = LATE_CHECKOUT + "/retreive";
        String GET_ENTERTAINMENT_SERVICE = ENTERTAINMENT_SERVICE + "/getById";
        String GET_ALL_ENTERTAINMENT_SERVICE = ENTERTAINMENT_ITEM + "/getAll";
        String GET_ALL_ENTERTAINMENT_SERVICE_CATEGORY = ENTERTAINMENT_ITEM + "/getAllbyCategory";
//        String GET_FEEDBACK_AND_SUGGESTIONS = STAYS_FEEDBACK + "/getAllByUserIdAndHotelId";
        String GET_INICIATE_CHECKOUT_DATA = APP_BASE_URL + "/checkout/retreive";
        String POST_INICIATE_DATA = APP_BASE_URL + "/checkout/modify";
        String GET_EARLY_CHECKIN_INFO = ROOM + "/getCheckinInfo";
        String GET_FEEDBACK_AND_SUGGESTIONS = STAYS_FEEDBACK + "/getByUserHotelStays";
        String SUBMIT_STAYS_FEEDBACK = STAYS_FEEDBACK + "/updateNew";

        String INITIATE_CHECKIN_RESPONSE = CHECKIN + "/modify";
        String GET_INITIATE_CHECKIN = CHECKIN + "/retreive";
        String GET_PREF = BASE_COMMON + "/getAllPreferences";
        String REQUEST_INTERNET_ORDER = APP_BASE_URL + "/internetOrder/add";
        String SUGGEST_HOTEL = SUGGESTED_HOTELS + "/add";

        String GET_COMP_SERVICE = COMPLEMENTARY_SERVICE + "/getById";
        String GET_ALL_SERVICES = CURRENT_STAYS + "/getAllServices";
        String GET_NOTIFICATION_BY_ID = NOTIFICATION + "/getStayNotifications";
        String GET_MESSAGE = APP_BASE_URL + "/message/getStayNotifications";
        String ADD_MESSAGE = APP_BASE_URL + "/message/add";
        String FETCH_PROFILE_TITLES = BASE_COMMON + "/getProfileTitles";
        String FETCH_COMMON_DATA = BASE_COMMON + "/getCommonData";
        String FETCH_IDENTITY_DOCS = BASE_COMMON + "/getIdentityDocs";
        String DELETE_DOC = USER + "/deleteIdentityDoc";
        String UPDATE_IDENTITY_IMAGE = APP_BASE_URL + "/file/image/upload";
        String DELETE_STAY = STAYS + "/delete";
        String GENERATE_OTP = USER + "/generateOtp";
    }
    interface PREF_KEYS {

        String KEY_LOGIN = "IsUserLoggedIn";
        String KEY_USERNAME = "username";
        String KEY_PASSWORD = "password";
        String ACCESS_CODE = "access";
        String APP_LINK = "appLink";
        String KEY_USER_DATA = "userData";
        String ROOM_SERVICE_CART_DATA = "room_service_cart_data";
        String IS_UPDATE_TOKEN = "is_toekn_update";
        String FCM_TOKEN = "fcm_token";
        String HOTEL_DATA = "hotel_data";
        String NOTIFICATION_ON = "notification_on";
        String CHECKOUT_DATE_TIME = "checkOutDateTime";
        String CHECKOUT_DATE = "checkOutDate";
        String CHECKOUT_TIME = "checkOutTime";
        String BLUETOOTH_DEVICE = "bluetooth_device";
    }

    public static interface BUNDLE_KEYS {
        String KEY_SERIALIZABLE_OBJECT = "KEY_SERIALIZABLE_OBJECT";
        String FRAGMENT_TYPE = "fragment_type";
        String EXTRA_BUNDLE = "bundle";
        String ADD_CARD_MODEL = "addCardModel";
        String BUTTON_TARGET = "buttonTarget";
        String TYPE = "type";
        String VALUE = "value";
        String FIRST_NAME = "firstName";
        String NUMBER = "number";
        String EMAIL = "email";
        String LAST_NAME = "lastName";
        String PASSWORD = "password";
        String REFERRAL_CODE = "referralCode";
        String SIGNUP_TYPE = "signupType";
        String HOTEL_DATA = "hotelData";
        String GENERAL_INFO = "generalInfo";
        String ROOMS_DATA = "roomsData";
        String SEARCH_HOTEL_RESPONSE = "searchHotelResponse";
        String CITY_NAME = "cityName";
        String HOTEL_NAME = "hotelName";
        String BOOKING_DATA = "bookingData";
        String REV_NUMBER = "revNum";
        String BOOKING_LIST = "bookingList";
        String FROM_ADD_STAY = "fromAddStayLine";
        String IDENTITY_DOC = "idntitydoc";
        String FROM_BOOKING = "loginBooking";
        String BOOKING_LOGIN_LIST = "bookingHotelList";
        String HOTEL_CHECK_IN = "hotelCheckIn";
        String IS_CHILD_ACTIVITY = "isChildActivity";
        java.lang.String IF_GUEST = "guest";
        String SERVICE_ID = "serviceId";
        String REF_ID = "refId";
        String ROOM_SERVICE_TITLE = "roomTitle";
        String ROOM_SERVICE_ID = "roomServiceId";
        String SEARCH_TEXT = "searchText";
        String FOOD_ITEM_DETAILS = "foodItemDetails";
        String FOOD_ITEM_ID = "foodItemId";
        String LAUNDRY_ITEMS = "laundryItem";
        String STAYS_ID = "staysId";
        String HOTEL_SERVICE_ID = "hotelServiceId";
        String HOUSEKEEPING_SERVICE_TITLE = "houseKeepingTitle";
        String CART_DATA = "cartData";
        String GS_ID = "gsId";
        String LAUNDRY_TYPE = "laundryType";
        String SUB_EXPENSE_LIST = "subExpenseList";
        String TIME_BASED_EXPENSE_ITEM = "timeBasedExpenseItem";
        String HOTEL_INFO = "hotelInffo";
        String CITY_GUIDE_ITEM_TYPE = "cgItemType";
        String CITY_GUIDE_ID = "cgId";
        String CITY_GUIDE_ITEM_OBJECT = "cgItemObj";
        String SUB_IMAGES_LIST = "subImagesList";
        String IMAGEURL = "imageUrl";
        String FROM_CHECKOUT = "fromCheckout";
        String CHILD_CATEGORY_ITEM = "cgItem";
        String GENERAL_DASHBOARD_ITEM = "dashBoardItem";
        String REQUEST_FROM = "openRequest";
        String UOSID = "uosID";
        String EDIT_ORDER_ITEM = "editOrderDetail";
        String MAINTENANCE_ITEM = "MaintenanceItem";
        String RESERVATION_SUB_TYPES = "reservationSubTypes";
        String TOOLBAR_TITLE = "toolbarTitle";
        String TITLE_NAME = "titleName";
        String RESERVATION_ITEM_ID = "parentId";
        String RESERVATION_TYPES = "reservationType";
        String IS_PREFERENCE = "is_preference";
        String INTERNET_PACK_LIST = "internetPackList";
        String IS_STAY_LINE_FRAGMENT = "stayLine";
        String HOTEL_ID = "hotelId";
        String FROM_CHECKIN = "checkIn";
        String FROM_HOTEL = "fromHotel";
        String RESERVATION_TYPE = "reservationType";
        String MAINTENANCE_ITEM_NAME = "maintenanceItemName";
        String SPECIAL_REQUEST = "specialRequest";
        String FROM_ORDER = "fromorder";
        String FROM_SAME_DAY = "fromSameDay";
        String ADD_STAY = "addStay";
        String FROM_PROFILE = "fromProfile";
        String SAME_DAY = "sameDay";
        String KEY_POSITION = "position";
        String BLUETOOTH_LIST = "bluetoothList";
    }

    interface FACEBOOK_BUNDLE_KEYS {
        String ID_FACEBOOK = "idFacebook";
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String EMAIL = "email";
        String NUMBER = "number";
        String PICTURE = "imageUrl";
    }

    interface STAY_STATUS{
        String CHECK_IN = "1";
        String STAYS = "2";
        String CHECK_OUT = "3";
    }
    interface STAYS_TYPE{
        String ROOM_SERVICE = "1";
        String HOUSE_KEEPING = "2";
        String LAUNDRY = "3";
    }

    interface FRAGMENT_TYPE {
        int CITY_GUIDE_FRAGMENT = 1;
        int CITY_GUIDE_ITEM_FRAGMENT = 2;
        int CITY_GUIDE_ITEM_DETAIL_FRAGMENT = 3;
        int SUB_IMAGE_FRAGMENT = 4;
        int IMAGE_FRAGMENT = 5;
        int HOUSE_RULES_DESCRIPTION = 6;
        int HOUSE_RULES = 7;
        int EDIT_ORDER_FRAGMENT = 8;
        int COMMUTE_FRAGMENT = 9;
        int BELL_BOY_FRAGMENT = 10;
        int MAINTENANCE_FRAGMENT = 11;
        int MAINTENANCE_ITEM_FRAGMENT = 12;
        int MAINTENANCE_DETAIL_FRAGMENT = 13;
        int RESERVATION_FRAGMENT = 14;
        int RESERVATION_SUBITEM_FRAGMENT = 15;
        int FRAGMENT_RESERVATION_PACKAGE = 16;
        int FRAGMENT_RESERVATION_PACKAGE_DETAIL = 17;
        int RESTAURANTS_LIST_FRAGMENT = 18;
        int FRAGMENT_HEALTH_AND_BEAUTY_RESERVE = 19;
        int LATE_CHECKOUT = 25;
        int RESERVATION_ORDER_FRAGMENT = 19;
        int TV_FRAGMENT = 20;
        int FRAGMENT_ALL_CHANNEL = 21;
        int FRAGMENT_ENTERTAINMENT_DETAILS = 22;
        int FEEDBACK_AND_SUGGESTIONS = 23;
        int COMPLEMENTARY_SERVICE = 24;
        int INTERNET_ORDER_DETAILS = 50;
        int MESSAGE = 51;
        int RETRIEVE_FRAGMENT = 52;
        int FRAGMENT_GET_DIRECTION = 53;
    }

    public static interface VIEW_TYPE {
        int CARD_MY_TEAM = 0;
        int CHECKIN_SERVICE_DATA = 1;
        int INFORMATION_DATA = 2;
        int STAY_SERVICE_DATA = 3;
        int STAYS = 4;
        int CHAT_OUTGOING_MSG = 5;
        int CHAT_INCOMMING_MSG = 6;
    }

    public interface TASK_CODES {

        int LOGIN = 10;
        int GET_OTP = 11;
        int VALIDATE_OTP = 12;
        int UPDATE_PROFILE = 14;
        int CHANGE_PASS = 15;
        int SIGNUP = 18;
        int FORGOT_PASSWORD = 19;
        int VALIDATE_SIGNUP = 9;
        int GET_HOTELS_DATA = 20;
        int GET_ROOM_BY_ID = 21;
        int UPDATE_USER_PERSONAL_DETAILS = 1;
        int GETBOOKHOTELDATA = 22;
        int GET_BOOKING_DATA = 23;
        int ADD_STAYLINE = 24;
        int GET_STAYLINE = 25;
        int UPDATE_CONTACTS = 26;
        int UPDATE_PREFERCENCE = 27;
        int GET_USER = 2;
        int UPDATE_IDENTITY = 28;
        int UPLOAD_FILE = 29;
        int GET_HOTEL_GENERAL_SERVICES = 30;
        int GET_USER_OPTED_SERVICES = 3;
        int HOTEL_GENERAL_INFO = 4;
        int GET_AIRPORT_SERVICE = 5;
        int INITIATE_AIRPORT_PICKUP = 6;
        int GET_AIRPORT_BOOKING = 31;
        int UPDATE_AIRPORT_PICKUP = 32;
        int GET_ROOM_SERVICE = 33;
        int GET_FOOD_ITEMS = 34;
        int SEARCH_FOOD_ITEMS = 35;
        int GET_FOOD_BY_ID = 36;
        int LAUNDRY_DATA = 37;
        int GET_LAUNDRY_ITEM = 38;
        int ADD_REMOVE_FOOD_TO_CART = 39;
        int GET_CART_DETAILS = 40;
        int GET_HOUSEKEEPING_SERVICE = 41;
        int GET_HOUSEKEEPING_ITEMS = 42;
        int SEARCH_HOUSEKEEPING = 43;
        int INTERNET_DATA = 44;
        int PLACE_ORDER = 45;
        int ADD_REMOVE_LAUNDRY_TO_CART = 46;
        int GET_LAUNDRY_CART_DETAILS = 47;
        int GENERATE_CATEGORY_BASED_EXPENSE = 48;
        int GENERATE_DATEWISE_EXPENSE = 49;
        int ADD_REMOVE_HOUSEKEEPING_TO_CART = 248;
        int GET_HOUSEKEEPING_CART_DETAILS = 249;
        int GET_HOTEL_GENERAL_INFORMATION = 50;
        int HOTEL_BY_ID = 51;
        int GET_CITY_GUIDE = 52;
        int FETCH_CITY_GUIDE_ITEM_BY_TYPE = 53;
        int GET_HOUSE_RULES = 54;
        int GET_REQUEST_DASHBOARD = 55;
        int DASHBOARD_ORDER_DETAILS = 56;
        int MY_PERMISSIONS_REQUEST_CALL = 57;
        int CANCEL_ORDER = 58;
        int CANCEL_AIRPORT_PICKUP = 59;
        int EDIT_ORDER_DETAIL = 60;
        int GET_COMMUTE = 61;
        int GET_BELL_BOY = 62;
        int COMMUTE_ORDER = 63;
        int MAINTENANCE_DATA = 64;
        int GET_MAINTENANCE_ITEM = 65;
        int REQUEST_MAINTENANCE = 66;
        int GET_RESERVATION = 67;
        int GET_RESERVATION_BY_TYPE = 68;
        int GET_RESERVATION_SUB_ITEMS = 69;
        int RESERVATION_REQUEST = 70;
        int GET_FEEDBACK = 71;
        int SUBMIT_FEEDBACK = 72;
        int GET_LATE_CHECKOUT_SERVICE_RESPONSE = 73;
        int REQUEST_LATE_CHECKOUT = 74;
        int ENTERTAINMENT_SERVICE = 273;
        int ALL_ENTERTAINMENT_SERVICE = 274;
        int ALL_ENTERTAINMENT_SERVICE_BY_CATEGORY = 75;
        int GET_STAYS_FEEDBACK = 76;
        int GET_EARLY_CHECKIN_SERVICE_RESPONSE = 77;
        int GET_INITIATE_CHECKIN_DATA = 78;
        int POST_INITIATE_CHECKIN_DATA = 79;
        int SUBMIT_STAYS_FEEDBACK = 278;
        int ALL_CART_ITEMS = 279;
        int INIITIATE_CHECKIN = 80;
        int GET_INITIATE_CHECKIN = 81;
        int GET_STAY_PREF = 82;
        int RETREIVE_LATE_CHECKOUT = 83;
        int SUGGEST_HOTLE = 84;
        int GET_COMPLEMENTARY_SERVICE = 85;
        int GET_ALL_SERVICES = 86;
        int INTERNET_ORDER = 87;
        int GET_NOTIFICATION = 88;
        int GET_MESSAGE = 89;
        int SEND_MESSAGE = 90;
        int FETCH_PROFILE_TITLES = 91;
        int FETCH_USER_COMMON_DATA = 92;
        int GET_IDENTITY_DOC_TYPES = 93;
        int DELETE_DOC = 94;
        int UPLOAD_IMAGE = 95;
        int DELETE_STAY = 96;
        int GENERATE_OTP = 97;
    }

    public static interface MEDIA_TYPES {
        String IMAGE = "img";
        String AUDIO = "audio";
        String VIDEO = "video";
    }

    public interface JSON_FILES {
        String folder = "json/";
        String PAST_CURRENT_ILLNESS = folder + "past_current_illness.json";
        String ALLERGIES = folder + "allergies.json";
        String FURTHER_ANALIST = folder + "further_anylist.json";
        String SYSTEM_REVIEW = folder + "system_review.json";
        String MEDICINE = folder + "medicine.json";
        String DIAGNOSIS = folder + "diagnosis.json";
    }

    public interface ACTION_TYPE {
        int NEXT_ACTIVITY = 1;
        int NEXT_ACTIVITY_INFORMATION = 2;
    }

    public interface SERVICE_TYPE {
        int AIRPORT_SERVICE = 1;
    }

    public interface ACTIVITY_CONSTANTS {
        int SUCCESS_DIALOG = 1;
        int RESEND_DIALOG = 2;
        int TARGET_SIGNUP_SUCCESS = 3;
        int TARGET_CHANGE_PASSWORD = 4;

    }

    public interface TYPE {
        int EMAIL = 1;
        int PHONE = 2;
        int USERID = 3;
    }
    public interface PAYMENT_METHODE {
        int ONLINE = 1;
        int FRONT_DESK = 2;
    }

    public interface SIGNUP_TYPE {
        Integer MANUAL = 1;
        Integer GMAIL = 2;
        Integer FACEBOOK = 3;
    }

    public interface LOGIN_TYPE extends SIGNUP_TYPE {

    }

    public interface REQUEST_CODES {
        int REQ_PICK_IMAGE = 1;
        int CHECKIN = 2;
    }

    interface STAYS_ORDERING {
        int CURRENT = 1;
        int FUTURE = 2;
        int PAST = 3;
    }

    interface CALL {
        int DRIVER = 1;
        int HOTEL = 2;
    }

    interface FROM_ORDERING {
        int ROOM_SERVICE = 1;
        int HOUSEKEEPING = 2;
        int LAUNDRY = 3;
    }

    public interface RESULT_CODE {
         int RESULT_DONE = 1;
        int RESULT_NULL = 2;
    }
}
