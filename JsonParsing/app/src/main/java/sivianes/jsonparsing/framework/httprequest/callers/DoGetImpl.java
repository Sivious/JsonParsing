package sivianes.jsonparsing.framework.httprequest.callers;

import sivianes.jsonparsing.framework.httprequest.request.HTTPRequestClient;
import sivianes.jsonparsing.framework.httprequest.request.HTTPRequestClientImpl;

/**
 * Created by Javier on 21/05/2016.
 */
public class DoGetImpl implements DoGet {
    private final HTTPRequestClient httpRequestClient;

    public DoGetImpl() {
        httpRequestClient = new HTTPRequestClientImpl();
    }


    @Override
    public void execute(final String url, final HTTPRequestClient.Callback callback) {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                httpRequestClient.get(url, callback);
            }
        };

        thread.start();
    }
}
