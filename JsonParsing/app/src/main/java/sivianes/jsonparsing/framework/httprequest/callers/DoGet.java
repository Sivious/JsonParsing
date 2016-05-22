package sivianes.jsonparsing.framework.httprequest.callers;

import sivianes.jsonparsing.framework.httprequest.request.HTTPRequestClient;

/**
 * Created by Javier on 21/05/2016.
 */
public interface DoGet {
    void execute(final String url, final HTTPRequestClient.Callback callback);
}
