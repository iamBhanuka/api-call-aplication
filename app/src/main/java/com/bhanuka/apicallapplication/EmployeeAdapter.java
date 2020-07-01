package com.bhanuka.apicallapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhanuka.apicallapplication.models.APIResponse;
import com.squareup.picasso.Picasso;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private APIResponse apiResponse;

    EmployeeAdapter(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.tvName.setText(apiResponse.getEmployees().get(position).getName());
        holder.tvId.setText(apiResponse.getEmployees().get(position).getId());
        holder.tvSalary.setText(apiResponse.getEmployees().get(position).getId());
        holder.tvAge.setText(apiResponse.getEmployees().get(position).getId());



    }

    @Override
    public int getItemCount() {
        return apiResponse.getEmployees().size();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvName;
        TextView tvSalary;
        TextView tvAge;
        ImageView imProfile;


        EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSalary =itemView.findViewById(R.id.tv_salary);
            tvAge = itemView.findViewById(R.id.tv_age);
            imProfile = itemView.findViewById(R.id.iv_profile);
            String imageUrl = "https://via.placeholder.com/500";
            Picasso.get().load(imageUrl).into(imProfile);
        }
    }
}
