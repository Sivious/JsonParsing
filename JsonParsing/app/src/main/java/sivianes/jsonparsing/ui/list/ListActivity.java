package sivianes.jsonparsing.ui.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import sivianes.jsonparsing.R;
import sivianes.jsonparsing.model.Animal;
import sivianes.jsonparsing.ui.list.elements.AnimalAdapter;
import sivianes.jsonparsing.ui.list.presenter.ListPresenter;
import sivianes.jsonparsing.ui.list.presenter.ListPresenterImpl;

public class ListActivity extends AppCompatActivity implements ListView {

    private ListPresenter presenter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();

        presenter.getInformation();
    }

    private void init() {
        this.presenter = new ListPresenterImpl(getApplicationContext());
        presenter.setView(this);

        recyclerView = (RecyclerView) findViewById(R.id.list_recicle_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void showInfo(final List<Animal> JSONSchema) {

        runOnUiThread (new Thread(new Runnable() {
            public void run() {
                initAdapter(JSONSchema);
            }
        }));
    }

    @Override
    public void showMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    private void initAdapter(List<Animal> JSONSchema) {
        adapter = new AnimalAdapter(JSONSchema);
        recyclerView.setAdapter(adapter);
    }
}
