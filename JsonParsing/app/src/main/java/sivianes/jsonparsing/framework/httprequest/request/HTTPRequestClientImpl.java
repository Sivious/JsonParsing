package sivianes.jsonparsing.framework.httprequest.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by Javier on 21/05/2016.
 */
public class HTTPRequestClientImpl implements HTTPRequestClient {
    private static final String TAG = "HTTPRequestClientImpl";
    private final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";

    @Override
    public void get(String url, Callback responseListener) {
        try {
            URL getUrl = new URL(url);

            HttpURLConnection urlConnection = (HttpURLConnection) getUrl.openConnection();

            urlConnection.setAllowUserInteraction(false);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestProperty(HEADER_ACCEPT_LANGUAGE, Locale.getDefault().getLanguage());
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            responseHandler(urlConnection, responseListener);

        } catch (MalformedURLException me) {
            me.printStackTrace();
            responseListener.onNullResponse();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            responseListener.onNullResponse();
        }
    }

    private void responseHandler(HttpURLConnection urlConnection, Callback responseListener) {
        int responseCode = 0;

        try {
            responseCode = urlConnection.getResponseCode();

            switch (responseCode) {
                case HttpURLConnection.HTTP_OK:
                    onSuccess(urlConnection, responseListener);
                    break;
                case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                    responseListener.onNullResponse();
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    responseListener.onNotFoundError();
                    break;
                default:
                    responseListener.onFailureServiceWithError();
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
            responseListener.onFailureServiceWithError();
        }
    }

    private void onSuccess(HttpURLConnection urlConnection, Callback responseListener) {
        StringBuffer sb = new StringBuffer();

        try {
            InputStream stream = urlConnection.getInputStream();
            InputStreamReader isReader = new InputStreamReader(stream);

            BufferedReader br = new BufferedReader(isReader);
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            responseListener.onSuccessWebService(sb.toString());

            br.close();
            urlConnection.disconnect();

        } catch (IOException e) {
            responseListener.onFailureServiceWithError();
        }
    }

}
