package edm.kassimentz.exemplorealm;

import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import android.text.format.DateFormat;
/**
 * Created by 630910144 on 25/06/16.
 */
public class Task extends RealmObject{
    @PrimaryKey
    public String nome;
    public String descricao;
    public long termino;
    public String local;
    public boolean iniciada;

    @Override
    public String toString() {
        /*Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(termino);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);
        int ano = cal.get(Calendar.YEAR);


        return "Nome: " + nome + "\nDescrição: " + descricao + "\nTérmino: " +
                (dia < 10 ? "0" + dia : "" + dia) + "/" +
                (mes < 10 ? "0" + mes : "" + mes) + "/" +
                ano;
        */

        String dateString = DateFormat.format("dd/MM/yyyy", new Date(termino)).toString();
        return "Nome: " + nome + "\nDescrição: " + descricao + "\nTérmino: " + dateString;
    }
}
