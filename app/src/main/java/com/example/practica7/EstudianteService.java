package com.example.practica7;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EstudianteService {
    @GET("/rest/estudiantes/")
    Call<List<Estudiante>> getData();

    @GET("/rest/estudiantes/{matricula}")
    Call<Estudiante> getStudent(@Path("matricula") int matricula);

    @POST("/rest/estudiantes/")
    Call<Estudiante> setData(@Body Estudiante estudiante);
}
