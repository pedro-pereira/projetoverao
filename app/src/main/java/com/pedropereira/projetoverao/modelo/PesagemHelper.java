package com.pedropereira.projetoverao.modelo;

import android.widget.EditText;

import com.pedropereira.projetoverao.FormularioPesagemActivity;
import com.pedropereira.projetoverao.R;

/**
 * Created by Pedro.Pereira on 28/12/2017.
 */

public class PesagemHelper {

    private Pesagem pesagem;

    private EditText campoChave;
    private EditText campoDataHora;
    private EditText campoMomento;
    private EditText campoLocal;
    private EditText campoPeso;

    public PesagemHelper(FormularioPesagemActivity formulario) {

        campoChave = (EditText) formulario.findViewById(R.id.edt_chave_pesagem);
        campoDataHora = (EditText) formulario.findViewById(R.id.edt_data_hora);
        campoMomento = (EditText) formulario.findViewById(R.id.edt_momento);
        campoLocal = (EditText) formulario.findViewById(R.id.edt_local);
        campoPeso = (EditText) formulario.findViewById(R.id.edt_peso);

    }

    public Pesagem getPesagemDoFormulario() {
        Long chave = new Long(campoChave.getText().toString());
        String dataHora = campoDataHora.getText().toString();
        String momento = campoMomento.getText().toString();
        String local = campoLocal.getText().toString();
        Double peso = new Double(campoPeso.getText().toString());

        Pesagem pesagem = new Pesagem();
        pesagem.setChave(chave);
        pesagem.setDataHora(dataHora);
        pesagem.setMomento(momento);
        pesagem.setLocal(local);
        pesagem.setPeso(peso);

        return pesagem;
    }
}
