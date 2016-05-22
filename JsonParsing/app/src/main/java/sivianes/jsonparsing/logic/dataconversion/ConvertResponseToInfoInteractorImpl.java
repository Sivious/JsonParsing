package sivianes.jsonparsing.logic.dataconversion;

import com.google.gson.Gson;

import android.util.Log;

import sivianes.jsonparsing.model.JSONSchema;

/**
 * Created by Javier on 22/05/2016.
 */
public class ConvertResponseToInfoInteractorImpl implements ConvertResponseToInfoInteractor {

    private static final String TAG = "ConvertResponseToInfo";

    @Override
    public void execute(String response, Callback callback) {
        Gson gson = new Gson();

        try{
            JSONSchema animals = gson.fromJson(response, JSONSchema.class);
            callback.OnSuccess(animals);
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
            callback.OnFailure();
        }
    }
}
