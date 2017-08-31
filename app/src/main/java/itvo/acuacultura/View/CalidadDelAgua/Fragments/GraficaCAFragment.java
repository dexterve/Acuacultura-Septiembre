package itvo.acuacultura.View.CalidadDelAgua.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import itvo.acuacultura.Database.AdminBD;
import itvo.acuacultura.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraficaCAFragment extends Fragment {

    RadioButton temperatura;
    RadioButton ph;
    RadioButton oxigeno;
    RadioButton turbidez;
    String re;


    public GraficaCAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_grafica_ca, container, false);
        LineChart lineChart = (LineChart) view.findViewById(R.id.chartCA);
        vistas(view);

        temperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LineChart lineChart = (LineChart) view.findViewById(R.id.chartCA);
                AdminBD bd = new AdminBD(getContext(), "AcuaCultura", null, 1);
                ArrayList<Entry> entries = new ArrayList();
                LineDataSet[] dataSet = new LineDataSet[1];
                ArrayList<String> labels = new ArrayList<>();
                LineData[] data = new LineData[1];


                ArrayList temp = bd.GraficaCA("Temperatura", re);
                for (int i = 0; i < temp.size(); i++) {
                    entries.add(new Entry(Float.parseFloat(temp.get(i).toString()), i));
                }

                dataSet[0] = new LineDataSet(entries, "Temperatura");
                dataSet[0].setColors(ColorTemplate.COLORFUL_COLORS);

                for (int i = 1; i < temp.size() + 1; i++) {
                    labels.add("Rev " + i);
                }

                data[0] = new LineData(labels, dataSet[0]);
                lineChart.setData(data[0]);
                lineChart.setDescription("Grafica Temperatura");
                lineChart.animateY(2500);
            }
        });

        oxigeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chartCA);
                AdminBD bd = new AdminBD(getContext(), "AcuaCultura", null, 1);
                ArrayList<Entry> entries = new ArrayList();
                LineDataSet[] dataSet = new LineDataSet[1];
                ArrayList<String> labels = new ArrayList<>();
                LineData[] data = new LineData[1];


                ArrayList temp = bd.GraficaCA("Oxigeno", re);
                for (int i = 0; i < temp.size(); i++) {
                    entries.add(new Entry(Float.parseFloat(temp.get(i).toString()), i));
                }

                dataSet[0] = new LineDataSet(entries, "Oxigeno");
                dataSet[0].setColors(ColorTemplate.COLORFUL_COLORS);

                for (int i = 1; i < temp.size() + 1; i++) {
                    labels.add("Rev " + i);
                }

                data[0] = new LineData(labels, dataSet[0]);
                lineChart.setData(data[0]);
                lineChart.setDescription("Grafica Oxigeno");
                lineChart.animateY(2500);
            }
        });

        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chartCA);
                AdminBD bd = new AdminBD(getContext(), "AcuaCultura", null, 1);
                ArrayList<Entry> entries = new ArrayList();
                LineDataSet[] dataSet = new LineDataSet[1];
                ArrayList<String> labels = new ArrayList<>();
                LineData[] data = new LineData[1];


                ArrayList temp = bd.GraficaCA("PH", re);
                for (int i = 0; i < temp.size(); i++) {
                    entries.add(new Entry(Float.parseFloat(temp.get(i).toString()), i));
                }

                dataSet[0] = new LineDataSet(entries, "PH");
                dataSet[0].setColors(ColorTemplate.COLORFUL_COLORS);

                for (int i = 1; i < temp.size() + 1; i++) {
                    labels.add("Rev " + i);
                }

                data[0] = new LineData(labels, dataSet[0]);
                lineChart.setData(data[0]);
                lineChart.setDescription("Grafica PH");
                lineChart.animateY(2500);
            }
        });

        turbidez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LineChart lineChart = (LineChart) view.findViewById(R.id.chartCA);
                AdminBD bd = new AdminBD(getContext(), "AcuaCultura", null, 1);
                ArrayList<Entry> entries = new ArrayList();
                LineDataSet[] dataSet = new LineDataSet[1];
                ArrayList<String> labels = new ArrayList<>();
                LineData[] data = new LineData[1];


                ArrayList reg = bd.GraficaCA("Turbidez", re);
                for (int i = 0; i < reg.size(); i++) {
                    entries.add(new Entry(Float.parseFloat(reg.get(i).toString()), i));
                }

                dataSet[0] = new LineDataSet(entries, "Turbidez");
                dataSet[0].setColors(ColorTemplate.COLORFUL_COLORS);

                for (int i = 1; i < reg.size() + 1; i++) {
                    labels.add("Rev " + i);
                }

                data[0] = new LineData(labels, dataSet[0]);
                lineChart.setData(data[0]);
                lineChart.setDescription("Grafica Turbidez");
                lineChart.animateY(2500);
            }
        });

        return view;
    }

    public void vistas(View view) {
        temperatura = (RadioButton) view.findViewById(R.id.rbTemperatura);
        ph = (RadioButton) view.findViewById(R.id.rbPH);
        oxigeno = (RadioButton) view.findViewById(R.id.rbOxigeno);
        turbidez = (RadioButton) view.findViewById(R.id.rbTurbidez);
    }

    public void Re(String re) {
        this.re = re;
    }

}
