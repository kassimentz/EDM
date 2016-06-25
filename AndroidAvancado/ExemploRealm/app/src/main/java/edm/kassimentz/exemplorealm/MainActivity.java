package edm.kassimentz.exemplorealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    private ListView listTasks;

    public static RealmResults<Task> results;

    private RealmChangeListener callback = new RealmChangeListener() {
        public void onChange(Object element) {
            results = (RealmResults<Task>) element;
            results = results.sort("termino", Sort.ASCENDING);
            listTasks.setAdapter(new ArrayAdapter<Task>(
                            MainActivity.this, android.R.layout.simple_list_item_1, results
                    )
            );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listTasks = (ListView) findViewById(R.id.listTasks);

        RealmResults<Task> result = ((CoreApplication)getApplication()).realm.where(Task.class).findAllAsync();
        result.addChangeListener(callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.miAddTask:
                //chamar tela de insercao de task
        }
        return true;
    }
}
