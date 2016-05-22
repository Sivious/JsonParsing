package sivianes.jsonparsing.ui.list.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import sivianes.jsonparsing.R;
import sivianes.jsonparsing.logic.connection.GetInfoFromUrlInteractor;
import sivianes.jsonparsing.logic.connection.GetInfoFromUrlInteractorImpl;
import sivianes.jsonparsing.model.Animal;
import sivianes.jsonparsing.model.JSONSchema;
import sivianes.jsonparsing.ui.list.ListView;

/**
 * Created by Javier on 21/05/2016.
 */
public class ListPresenterImpl implements ListPresenter {
    GetInfoFromUrlInteractor getInfoFromUrlInteractor;
    private ListView view;
    private Context context;

    public ListPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setView(ListView view) {
        this.view = view;
    }

    @Override
    public void getInformation() {
        getInfoFromUrlInteractor = new GetInfoFromUrlInteractorImpl();

        getInfoFromUrlInteractor.execute(new GetInfoFromUrlInteractor.Callback() {
            @Override
            public void OnSuccess(JSONSchema info) {
                System.out.println("Success getting info");
                view.showInfo(joinLists(info));
            }

            @Override
            public void OnFailure() {
                //TODO: Do something
                System.out.println("Error getting info");
                view.showMessage(context.getString(R.string.error_server));
            }
        });

    }

    private List<Animal> joinLists(JSONSchema info) {
        List<Animal> animalList = new ArrayList<>();

        animalList.addAll(info.Animals.animals);
        animalList.addAll(info.Animals.others);

        return animalList;
    }
}
