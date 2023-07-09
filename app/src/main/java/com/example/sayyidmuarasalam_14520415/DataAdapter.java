package com.example.sayyidmuarasalam_14520415;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<DataItem> dataList;
    private DatabaseHelper databaseHelper;
    private Context context;

    public DataAdapter(List<DataItem> dataList, DatabaseHelper databaseHelper, Context context) {
        this.dataList = dataList;
        this.databaseHelper = databaseHelper;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataItem dataItem = dataList.get(position);

        holder.namaTextView.setText(dataItem.getNama());
        holder.nimTextView.setText(dataItem.getNim());
        holder.jurusanTextView.setText(dataItem.getJurusan());
        holder.jenisKelaminTextView.setText(dataItem.getJenisKelamin());

        // Menghapus data
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    DataItem itemToRemove = dataList.get(itemPosition);
                    databaseHelper.deleteData(itemToRemove.getId());
                    Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    dataList.remove(itemToRemove);
                    notifyItemRemoved(itemPosition);
                }
            }
        });

        // Mengubah data
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = holder.getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    DataItem itemToEdit = dataList.get(itemPosition);
                    Intent intent = new Intent(context, CreateActivity.class);
                    intent.putExtra("id", itemToEdit.getId());
                    intent.putExtra("nama", itemToEdit.getNama());
                    intent.putExtra("nim", itemToEdit.getNim());
                    intent.putExtra("jurusan", itemToEdit.getJurusan());
                    intent.putExtra("jenis_kelamin", itemToEdit.getJenisKelamin());
                    context.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaTextView;
        TextView nimTextView;
        TextView jurusanTextView;
        TextView jenisKelaminTextView;
        Button deleteButton;
        Button editButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaTextView = itemView.findViewById(R.id.namaTextView);
            nimTextView = itemView.findViewById(R.id.nimTextView);
            jurusanTextView = itemView.findViewById(R.id.jurusanTextView);
            jenisKelaminTextView = itemView.findViewById(R.id.jenisKelaminTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton = itemView.findViewById(R.id.editButton);
        }
    }
}
