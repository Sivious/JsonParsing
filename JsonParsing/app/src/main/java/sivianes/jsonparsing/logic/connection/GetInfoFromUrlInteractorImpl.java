package sivianes.jsonparsing.logic.connection;

import sivianes.jsonparsing.framework.httprequest.callers.DoGet;
import sivianes.jsonparsing.framework.httprequest.callers.DoGetImpl;
import sivianes.jsonparsing.framework.httprequest.request.HTTPRequestClient;
import sivianes.jsonparsing.logic.dataconversion.ConvertResponseToInfoInteractor;
import sivianes.jsonparsing.logic.dataconversion.ConvertResponseToInfoInteractorImpl;
import sivianes.jsonparsing.model.JSONSchema;

/**
 * Created by Javier on 22/05/2016.
 */
public class GetInfoFromUrlInteractorImpl implements GetInfoFromUrlInteractor {
    DoGet doGet;
    ConvertResponseToInfoInteractorImpl convertResponseToInfoInteractor;

    @Override
    public void execute(final Callback callback) {
        doGet = new DoGetImpl();

        doGet.execute(INFO_URL, new HTTPRequestClient.Callback() {
            @Override
            public void onFailureServiceWithError() {
                callback.OnFailure();
            }

            @Override
            public void onSuccessWebService(String response) {
                convertResponseIntoInfo(response, callback);
            }

            @Override
            public void onNullResponse() {
                callback.OnFailure();
            }

            @Override
            public void onNotFoundError() {
                callback.OnFailure();
            }

        });
    }

    private void convertResponseIntoInfo(String response, final Callback callback) {
        convertResponseToInfoInteractor = new ConvertResponseToInfoInteractorImpl();

        convertResponseToInfoInteractor.execute(response, new ConvertResponseToInfoInteractor.Callback() {
            @Override
            public void OnSuccess(JSONSchema info) {
                callback.OnSuccess(info);
            }

            @Override
            public void OnFailure() {
                callback.OnFailure();
            }
        });
    }
}
