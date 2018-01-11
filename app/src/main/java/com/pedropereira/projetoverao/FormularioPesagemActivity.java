package com.pedropereira.projetoverao;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pedropereira.projetoverao.dao.PesagemDao;
import com.pedropereira.projetoverao.modelo.Pesagem;
import com.pedropereira.projetoverao.modelo.PesagemHelper;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class FormularioPesagemActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private PesagemHelper helper;
    private PesagemDao pesagemDao;

    private Button btnDatePicker;
    private EditText edtData;

    private Button btnTimePicker;
    private EditText edtHorario;

    private Button botaoCapturarLocalizacao;
    private TextView txtLatitude;
    private TextView txtLongitude;

    private Spinner spinnerFiliais;
    private Spinner spinnerMomentos;

    private Button botaoInserirPesagem;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private Double latitude;
    private Double longitude;

    private String momento;
    private String filial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pesagem);
        helper = new PesagemHelper(this);

        btnDatePicker = (Button) findViewById(R.id.btn_data);
        edtData = (EditText) findViewById(R.id.edt_data);
        btnTimePicker = (Button) findViewById(R.id.btn_horario);
        edtHorario = (EditText) findViewById(R.id.edt_horario);
        txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        botaoCapturarLocalizacao = (Button) findViewById(R.id.btn_capturar_localizacao);
        spinnerFiliais = (Spinner) findViewById(R.id.spn_filial);
        spinnerMomentos = (Spinner) findViewById(R.id.spn_momento);
        botaoInserirPesagem = (Button) findViewById(R.id.btn_inserir_pesagem);

        // Data
        btnDatePicker.setOnClickListener(this);

        // Horario
        btnTimePicker.setOnClickListener(this);

        // Localizacao
        botaoCapturarLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedirPermissoes();
            }
        });

        ArrayAdapter<CharSequence> adapter;

        // Filial
        adapter = ArrayAdapter.createFromResource(this,
                R.array.filiais, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFiliais.setAdapter(adapter);
        spinnerFiliais.setOnItemSelectedListener(this);

        // Momento
        adapter = ArrayAdapter.createFromResource(this,
                R.array.momentos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMomentos.setAdapter(adapter);
        spinnerMomentos.setOnItemSelectedListener(this);

        // Recuperando parametro em caso de edicao
        final Pesagem pesagemSelecionada = (Pesagem) getIntent().getSerializableExtra("pesagemSelecionada");

        if(pesagemSelecionada != null) {
            helper.carregaPesagemNoFormulario(pesagemSelecionada, this);
            botaoInserirPesagem.setText("Alterar");
        }

        // Botão "Salvar"
        botaoInserirPesagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pesagem pesagemDoFormulario = helper.getPesagemDoFormulario();
                pesagemDao = new PesagemDao(FormularioPesagemActivity.this);

                if(pesagemSelecionada != null) {
                    pesagemDoFormulario.setChave(pesagemSelecionada.getChave());
                    pesagemDao.alterar(pesagemDoFormulario);
                } else {
                    pesagemDao.inserir(pesagemDoFormulario);
                }

                pesagemDao.close();
                finish();

                Intent intencao = new Intent(FormularioPesagemActivity.this, ListaPesagemActivity.class);
                startActivity(intencao);
            }
        });
    }

    private void pedirPermissoes() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else
            configurarServico();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configurarServico();
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void configurarServico() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {

                public void onLocationChanged(Location location) {
                    atualizar(location);
                }

                public void onStatusChanged(String provider, int status, Bundle extras) { }

                public void onProviderEnabled(String provider) { }

                public void onProviderDisabled(String provider) { }
            }
            ;

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        } catch (SecurityException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void atualizar(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        txtLatitude.setText(String.valueOf(latitude));
        txtLongitude.setText(String.valueOf(longitude));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition = parent.getItemAtPosition(position);
        if(view == spinnerMomentos) {
            momento = itemAtPosition.toString();
            Toast.makeText(this, "Momento: " + momento, Toast.LENGTH_LONG).show();
        }

        if(view == spinnerFiliais) {
            filial = itemAtPosition.toString();
            Toast.makeText(this, "Filial: " + filial, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {
            // Get Current Date
            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"), new Locale("pt", "BR"));
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                                                                             new DatePickerDialog.OnDateSetListener() {
                                                                                @Override
                                                                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                                                    edtData.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                                                                }
                                                                             },
                                                                             mYear,
                                                                             mMonth,
                                                                             mDay
                                                                    );
            datePickerDialog.show();
        }

        if (v == btnTimePicker) {
            // Get Current Time
            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-3"), new Locale("pt", "BR"));
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                                                                             new TimePickerDialog.OnTimeSetListener() {
                                                                                 @Override
                                                                                 public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                                                    edtHorario.setText(hourOfDay + ":" + minute);
                                                                                 }
                                                                             },
                                                                             mHour,
                                                                             mMinute,
                                                                false
                                                                    );
            timePickerDialog.show();
        }
    }
}