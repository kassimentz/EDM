package edm.kassimentz.usosensores;

import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    //uma listActivity ja tem uma view interna listview, nao precisa dar um set content view.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //retorna todos os sensores que esse possivel hardware tem. retorna uma lista de instancia da classe sensor

        List<Sensor> sensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> nomesSensores = new ArrayList<String>();
        for(Sensor sensor : sensores){
            nomesSensores.add(sensor.getName());
        }

        setListAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomesSensores)
        );
    }
}
