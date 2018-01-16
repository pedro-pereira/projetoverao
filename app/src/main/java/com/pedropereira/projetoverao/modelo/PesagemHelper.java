package com.pedropereira.projetoverao.modelo;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.pedropereira.projetoverao.FormularioPesagemActivity;
import com.pedropereira.projetoverao.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pedro.Pereira on 28/12/2017.
 */

public class PesagemHelper {

    private Pesagem pesagem;

    private EditText campoData;
    private EditText campoHorario;
    private TextView campoLatitude;
    private TextView campoLongitude;
    private Spinner campoFilial;
    private Spinner campoMomento;
    private EditText campoPeso;

    public PesagemHelper(FormularioPesagemActivity formulario) {
        campoData = (EditText) formulario.findViewById(R.id.edt_data);
        campoHorario = (EditText) formulario.findViewById(R.id.edt_horario);
        campoLatitude = (TextView) formulario.findViewById(R.id.txtLatitude);
        campoLongitude = (TextView) formulario.findViewById(R.id.txtLongitude);
        campoFilial = (Spinner) formulario.findViewById(R.id.spn_filial);
        campoMomento = (Spinner) formulario.findViewById(R.id.spn_momento);
        campoPeso = (EditText) formulario.findViewById(R.id.edt_peso);
    }

    public Pesagem getPesagemDoFormulario() {

        String data = campoData.getText().toString();
        String horario = campoHorario.getText().toString();

        Double latitude = 0.0;

        if(campoLatitude.getText() != null)
            latitude = Double.valueOf(campoLatitude.getText().toString());

        Double longitude = 0.0;
        if(campoLongitude.getText() != null)
            longitude = Double.valueOf(campoLongitude.getText().toString());

        String filial = campoFilial.getSelectedItem().toString();
        String momento = campoMomento.getSelectedItem().toString();

        Double peso = Double.valueOf(campoPeso.getText().toString());

        Date dataFormatada = null;
        try {
            String temp = data + ", " + horario;
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
            dataFormatada = formatter.parse(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pesagem pesagem = new Pesagem();

        pesagem.setDataHora(dataFormatada);
        pesagem.setLatitude(latitude);
        pesagem.setLongitude(longitude);
        pesagem.setFilial(filial);
        pesagem.setMomento(momento);
        pesagem.setPeso(peso);

        return pesagem;
    }

    public void carregaPesagemNoFormulario(Pesagem pesagem, android.content.Context contexto) {

        ArrayAdapter<CharSequence> adapter;
        int spinnerPosition = 0;

        adapter = ArrayAdapter.createFromResource(contexto, R.array.filiais, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoFilial.setAdapter(adapter);
        if (pesagem.getFilial() != null) {
            spinnerPosition = adapter.getPosition(pesagem.getFilial());
            campoFilial.setSelection(spinnerPosition);
        }

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatter.format(pesagem.getDataHora());
        campoData.setText(dataFormatada);

        formatter = new SimpleDateFormat("HH:mm");
        String horaFormatada = formatter.format(pesagem.getDataHora());
        campoHorario.setText(horaFormatada);

        campoLatitude.setText(String.valueOf(pesagem.getLatitude()));
        campoLongitude.setText(String.valueOf(pesagem.getLongitude()));

        spinnerPosition = 0;
        adapter = ArrayAdapter.createFromResource(contexto, R.array.momentos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoMomento.setAdapter(adapter);
        if (pesagem.getMomento() != null) {
            spinnerPosition = adapter.getPosition(pesagem.getMomento());
            campoMomento.setSelection(spinnerPosition);
        }

        campoPeso.setText(String.valueOf(pesagem.getPeso()));
    }
}
