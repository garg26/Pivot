package simplifii.framework.utility;

import java.util.LinkedHashMap;

public interface AppConstants {

    public static final String DEF_REGULAR_FONT = "Roboto-Regular.ttf";
    String APP_LINK = "https://drive.google.com/file/d/0B8wKJnD6sONHeXlUbm5pOTk4dGM/view?usp=sharing";
    LinkedHashMap<Integer, String> storeCategory = new LinkedHashMap<Integer, String>();

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
        String BASE_URL = "http://pivotassets.ga/api/";
        String LOGIN = BASE_URL + "login";
        String IDENTIFY_TAGS = BASE_URL + "identify/tags";
        String REFRESH_SETS = BASE_URL + "search/set";
        String READ = "instruments.json";
    }

    public static interface PREF_KEYS {

        String APP_LINK = "appLink";
        String KEY_USER_DATA = "userData";
        String BLUETOOTH_DEVICE = "bluetooth_device";
    }

    public static interface BUNDLE_KEYS {
        String KEY_SERIALIZABLE_OBJECT = "KEY_SERIALIZABLE_OBJECT";
        String KEY_POSITION = "position";
        String SETSLIST = "sets_list";
        String SETS = "sets";
        String INSTRUMENT = "instruments";
        String TAGLIST = "tag_list";
    }



    public interface TASK_CODES {

        int LOGIN = 1;
        int IDENTIFY_TAGS = 2;
        int UPLOAD_FILE = 3;
        int READ_INSTRUMENT = 4;
        int REFRESH_SETS = 5;
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




}
