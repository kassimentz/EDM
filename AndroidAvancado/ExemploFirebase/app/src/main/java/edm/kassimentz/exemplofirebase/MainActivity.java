package edm.kassimentz.exemplofirebase;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    public List<Task> tasks = new ArrayList<Task>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase refCurrent = FirebaseDatabase.getInstance();
        DatabaseReference myRef = refCurrent.getReference("tasks");

        Query query = myRef.orderByChild("nome");
        query.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    Log.e("teste", "key: "+ snap.getKey());
                    Log.e("teste", "value: "+ snap.getValue());
//                    if (Integer.parseInt(snap.getKey()) > key){
//                        key = Integer.parseInt(snap.getKey());
//                    }
                    Task task = snap.getValue(Task.class);
                    tasks.add(task);
                }

                setListAdapter(
                        new ArrayAdapter<Task>(
                                MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                tasks
                        )
                );
            }
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
