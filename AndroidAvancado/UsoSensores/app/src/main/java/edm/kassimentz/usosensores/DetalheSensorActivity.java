package edm.kassimentz.usosensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheSensorActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager sMgr;
    private TextView txt1, txt2, txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_sensor);

        int type = getIntent().getIntExtra("tipo", 0);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);

        sMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sMgr.registerListener(
                this,
                sMgr.getDefaultSensor(type),
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    //importante desregistrar para nao ficar lendo recurso a toa
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sMgr.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //qualquer sensor sempre retorna um sensor
        //com tres valores, o que muda é quais sao uteis ou nao
        float[] valores = event.values;
        txt1.setText("[0]: "+ valores[0]);
        txt2.setText("[1]: "+ valores[1]);
        txt3.setText("[2]: "+ valores[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
