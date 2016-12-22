package com.rushabh.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rushabh.list.databinding.CrowEmployeeViewBinding;

import java.util.ArrayList;

/**
 * Created by rushabh on 22/12/16.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {

    ArrayList<EmployeeModel> employeeModelArrayList;
    Context context;
    LayoutInflater inflater;

    EmployeeAdapter(ArrayList<EmployeeModel> employeeModels, Context context) {
        this.employeeModelArrayList = employeeModels;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CrowEmployeeViewBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.crow_employee_view, parent, false);
        EmployeeViewHolder viewHolder = new EmployeeViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        EmployeeModel employeeModel = employeeModelArrayList.get(position);
        holder.getBinding().setEmployee(employeeModel);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return employeeModelArrayList == null ? 0 : employeeModelArrayList.size();
    }
}

class EmployeeViewHolder extends RecyclerView.ViewHolder {

    private CrowEmployeeViewBinding binding;

    public EmployeeViewHolder(CrowEmployeeViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public CrowEmployeeViewBinding getBinding() {
        return binding;
    }

    public EmployeeViewHolder(View itemView) {
        super(itemView);
    }
}

