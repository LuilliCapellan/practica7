package com.example.practica7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EstudianteAdapter adapter;
    private EstudianteService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = RetrofitClientInstance.getRetrofitInstance().create(EstudianteService.class);
        Call<List<Estudiante>> call = service.getData();
        call.enqueue(new Callback<List<Estudiante>>() {
            @Override
            public void onResponse(Call<List<Estudiante>> call, Response<List<Estudiante>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Estudiante>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Eploto", Toast.LENGTH_SHORT).show();
            }
        });

        final EditText matricula = findViewById(R.id.matriculaAdd),
                nombre = findViewById(R.id.nombreAdd),
                correo = findViewById(R.id.correoAdd),
                carrera = findViewById(R.id.carreraAdd);
        Button btnAdd = findViewById(R.id.addstudent), buscarbtn = findViewById(R.id.buscar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estudiante nuevo = new Estudiante(Integer.parseInt(matricula.getText().toString()), nombre.getText().toString(), correo.getText().toString(), Integer.parseInt(carrera.getText().toString()));
                createEstudiante(nuevo, new Callback<Estudiante>() {
                    @Override
                    public void onResponse(Call<Estudiante> call, Response<Estudiante> response) {
                        Estudiante responseEstudiante = response.body();
                        if (response.isSuccessful() && responseEstudiante != null) {
                            finish();
                            startActivity(getIntent());
                        } else {
                            Toast.makeText(MainActivity.this,
                                    String.format("Response is %s", String.valueOf(response.code()))
                                    , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Estudiante> call, Throwable t) {

                    }
                });
            }
        });

        buscarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(Integer.parseInt(matricula.getText().toString()), new Callback<Estudiante>() {
                    @Override
                    public void onResponse(Call<Estudiante> call, Response<Estudiante> response) {
                        Estudiante responseEsudiante = response.body();
                        if (response.isSuccessful() && responseEsudiante != null) {
                            Toast.makeText(MainActivity.this, "Matricula:" + responseEsudiante.getMatricula() +
                                    "\nNombre: " + responseEsudiante.getNombre() +
                                    "\nCorreo: "+responseEsudiante.getCorreo() +
                                    "\nCarrera: " + responseEsudiante.getCarrera(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Estudiante> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void createEstudiante(Estudiante estudiante, Callback<Estudiante> callback) {
        Call<Estudiante> userCall = service.setData(estudiante);
        userCall.enqueue(callback);
    }

    public void search(int matricula, Callback<Estudiante> callback) {
        Call<Estudiante> call = service.getStudent(matricula);
        call.enqueue(callback);
    }

    private void generateDataList(List<Estudiante> estudianteList) {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new EstudianteAdapter(this, estudianteList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
