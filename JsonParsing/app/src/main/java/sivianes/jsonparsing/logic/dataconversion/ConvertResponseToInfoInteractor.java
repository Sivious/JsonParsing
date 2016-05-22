package sivianes.jsonparsing.logic.dataconversion;

import sivianes.jsonparsing.model.JSONSchema;

/**
 * Created by Javier on 22/05/2016.
 */
public interface ConvertResponseToInfoInteractor {
    void execute(String response, Callback callback);

    interface Callback {
        void OnSuccess(JSONSchema info);

        void OnFailure();
    }
}
