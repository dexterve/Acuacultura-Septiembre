package itvo.acuacultura.View.Alimentacion.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import itvo.acuacultura.View.Alimentacion.AlimentacionActivity;


public class DialogoListaAlimentacion extends DialogFragment {
    String re="";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String m []= new  String[5];

        for (int i=1;i<=5;i++){
            m [i-1]= String.valueOf(i);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Ingresé el Numero \nde Mezclas")
                .setItems(m, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), "Seleccionaste:" + items[which], Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), AlimentacionActivity.class);
                        i.putExtra("re", re);
                        i.putExtra("num", m[which]);
                        startActivity(i);
                    }
                });

        return builder.create();
    }

    public void Re(String re) {
        this.re=re;
    }
}