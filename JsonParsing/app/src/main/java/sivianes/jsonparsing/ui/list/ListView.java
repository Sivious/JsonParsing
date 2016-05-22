package sivianes.jsonparsing.ui.list;

import java.util.List;

import sivianes.jsonparsing.model.Animal;

/**
 * Created by Javier on 22/05/2016.
 */
public interface ListView {
    void showInfo(List<Animal> JSONSchema);
    void showMessage(String message);
}
