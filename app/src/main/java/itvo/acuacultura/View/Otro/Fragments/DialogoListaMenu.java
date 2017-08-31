package itvo.acuacultura.View.Otro.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import itvo.acuacultura.View.Alimentacion.Fragments.DialogoListaAlimentacion;
import itvo.acuacultura.View.CalidadDelAgua.CalidadDelAguaActivity;
import itvo.acuacultura.View.Crecimiento.Fragments.DialogoListaCrecimiento;
import itvo.acuacultura.View.Enfermedades.EnfermedadesActivity;


public class DialogoListaMenu extends DialogFragment {
    String re="";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String m []= new  String[4];
        m [0]= "Calidad del Agua";
        m [1]= "Alimentación";
        m [2]= "Crecimiento";
        m [3]= "Enfermedades";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(m, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), "Seleccionaste:" + items[which], Toast.LENGTH_SHORT).show();
                        Intent i;
                        switch (m[which]){
                            case "Calidad del Agua":
                                i = new Intent(getActivity(), CalidadDelAguaActivity.class);
                                i.putExtra("re", re);
                                startActivity(i);
                                break;
                            case "Alimentación":
                                DialogoListaAlimentacion lista = new DialogoListaAlimentacion();
                                lista.Re(re);
                                lista.show(getActivity().getSupportFragmentManager(), "Lista");
                                break;
                            case "Crecimiento":
                                DialogoListaCrecimiento lista2 = new DialogoListaCrecimiento();
                                lista2.Re(re);
                                lista2.show(getActivity().getSupportFragmentManager(), "Lista");
                                break;
                            case "Enfermedades":
                                i = new Intent(getActivity(), EnfermedadesActivity.class);
                                i.putExtra("re", re);
                                startActivity(i);
                                break;
                        }

                    }
                });

        return builder.create();
    }

    public void Re(String re) {
        this.re=re;
    }
}