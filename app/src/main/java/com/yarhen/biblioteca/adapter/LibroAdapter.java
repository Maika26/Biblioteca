package com.yarhen.biblioteca.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yarhen.biblioteca.ListaRegistrosActivity;
import com.yarhen.biblioteca.R;
import com.yarhen.biblioteca.VerLibroActivity;
import com.yarhen.biblioteca.models.Libro;

import java.util.ArrayList;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolder> {
    ArrayList<Libro> listaLibros;

    public LibroAdapter(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public LibroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registro, parent, false);
        ViewHolder vHolder = new ViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LibroAdapter.ViewHolder holder, int position) {
        holder.cargarLibro(listaLibros.get(position));
        Libro l = listaLibros.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ABRIR LA VENTANA QUE MOSTRARA TODOS LOS DATOS DEL LIBRO
                Intent intent = new Intent(holder.itemView.getContext(), VerLibroActivity.class);
                intent.putExtra("mostrar_libro", l);
                //ENVIAR EL LIBRO A LA VENTANA VER LIBRO ACTIVITY
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgRegistro;
        TextView txtTituloLibro, txtAutorLibro, txtEditorialLibro, txtEstanteLibro;
        Button btn_verLibro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRegistro = itemView.findViewById(R.id.imgRegistro);
            txtTituloLibro = itemView.findViewById(R.id.txtTituloLibro);
            txtAutorLibro = itemView.findViewById(R.id.txtAutorLibro);
            txtEditorialLibro = itemView.findViewById(R.id.txtEditorialLibro);
            txtEstanteLibro = itemView.findViewById(R.id.txtEstanteLibro);
            btn_verLibro = itemView.findViewById(R.id.btn_verLibro);

            btn_verLibro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), VerLibroActivity.class);
                    itemView.getContext().startActivity(intent);
                }
            });

        }
        //METODO PARA CARGAR LOS DATOS A MOSTRAR EN ITEM REGISTRO
        void cargarLibro(Libro l){
            txtTituloLibro.setText(l.getTitulo());
            txtAutorLibro.setText(l.getAutor().getNombres()+" "+l.getAutor().getApellidos());
            txtEditorialLibro.setText(l.getEditorial().getNombre());
            txtEstanteLibro.setText(l.getEstante().getLetra()+l.getEstante().getNumero()+l.getEstante().getColor());
        }


    }

}
