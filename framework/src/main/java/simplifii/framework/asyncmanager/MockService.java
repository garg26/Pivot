package simplifii.framework.asyncmanager;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import simplifii.framework.exceptionhandler.RestException;

/**
 * Created by kartikeya on 2/8/17.
 */

public class MockService extends OKHttpService{
    @Override
    public Object getData(Object... params) throws JSONException, SQLException, NullPointerException, RestException, ClassCastException, IOException {

        if (params != null && params.length > 0) {
            HttpParamObject param = (HttpParamObject) params[0];
            Object o = parseJson(param.getUrl(), param);
            return o;

        }

        return null;
    }



}
