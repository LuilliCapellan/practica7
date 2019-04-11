package com.example.practica7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteHolder> {
    private Context context;
    private List<Estudiante> estudiantes;

    public EstudianteAdapter(Context context, List<Estudiante> estudiantes) {
        this.context = context;
        this.estudiantes = estudiantes;
    }

    @NonNull
    @Override
    public EstudianteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.estudiante_row, viewGroup, false);
        return new EstudianteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudianteHolder estudianteHolder, int i) {
        Estudiante estudiante = estudiantes.get(i);
        estudianteHolder.setDetails(estudiante);
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

}

class EstudianteHolder extends RecyclerView.ViewHolder {
    private TextView txtMatricula, txtNombre, txtCorreo, txtCarrera;

    public EstudianteHolder(View itemView) {
        super(itemView);
        txtMatricula = itemView.findViewById(R.id.matricula);
        txtNombre = itemView.findViewById(R.id.nombre);
        txtCorreo = itemView.findViewById(R.id.correo);
        txtCarrera = itemView.findViewById(R.id.carrera);
    }

    public void setDetails(Estudiante estudiante) {
        txtMatricula.setText(String.valueOf(estudiante.getMatricula()));
        txtNombre.setText(estudiante.getNombre());
        txtCorreo.setText(( estudiante.getCorreo()));
        txtCarrera.setText(estudiante.getCarrera()+"");
    }
}
