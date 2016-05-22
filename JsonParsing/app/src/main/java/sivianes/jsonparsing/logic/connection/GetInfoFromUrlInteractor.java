package sivianes.jsonparsing.logic.connection;

import sivianes.jsonparsing.model.JSONSchema;

/**
 * Created by Javier on 22/05/2016.
 */
public interface GetInfoFromUrlInteractor {
    String INFO_URL = "http://files.ilicco.com/digitaslbi/recruitment/test.json";
    void execute(Callback callback);

    interface Callback{
        void OnSuccess(JSONSchema info);
        void OnFailure();
    }
}
