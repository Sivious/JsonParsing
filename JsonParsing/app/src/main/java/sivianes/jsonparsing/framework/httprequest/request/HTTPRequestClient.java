package sivianes.jsonparsing.framework.httprequest.request;

import java.io.Serializable;

/**
 * Created by Javier on 21/05/2016.
 */
public interface HTTPRequestClient {
    void get(String url, Callback responseListener);

    interface Callback extends Serializable {
        void onFailureServiceWithError();

        void onSuccessWebService(String response);

        void onNullResponse();

        void onNotFoundError();
    }
}
