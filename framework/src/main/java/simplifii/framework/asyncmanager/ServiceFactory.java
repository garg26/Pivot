package simplifii.framework.asyncmanager;

import android.content.Context;

import simplifii.framework.utility.AppConstants;


public class ServiceFactory {

    public static Service getInstance(Context context, int taskCode) {
        Service service = null;
        switch (taskCode) {
            case AppConstants.TASK_CODES.UPLOAD_FILE:
                service = new FileUploadService();
                break;
            case AppConstants.TASK_CODES.READ_INSTRUMENT:
                service = new MockService();
                break;
            default:
                service = new OKHttpService();
                break;
        }
        return service;
    }

}
