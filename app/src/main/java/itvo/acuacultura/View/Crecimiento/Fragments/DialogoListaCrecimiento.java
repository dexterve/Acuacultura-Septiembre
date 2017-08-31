package itvo.acuacultura.View.Crecimiento.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import itvo.acuacultura.View.Crecimiento.CrecimientoActivity;


public class DialogoListaCrecimiento extends DialogFragment {
    String re="";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String m []= new  String[3];



        m [0]="20";
        m [1]="25";
        m [2]="30";


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Ingrese el Numero de Ejemplares a Evaluar")
                .setItems(m, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), "Seleccionaste: "+re, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getActivity(), CrecimientoActivity.class);
                        i.putExtra("num", m[which]);
                        i.putExtra("re",re);
                        startActivity(i);
                        //String re = getIntent().getStringExtra("re");
                    }
                });


        return builder.create();
    }

    public void Re(String rre){
        re=rre;
    }


}