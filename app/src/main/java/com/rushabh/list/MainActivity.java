package com.rushabh.list;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recylcerview)
    RecyclerView recyclerView;


    ArrayList<EmployeeModel> employeeModelArrayList = new ArrayList<>();

    String department[] = new String[]{"Tech", "Marketing", "Operations", "HR"};

    EmployeeAdapter employeeAdapter;

    int selectedFilterItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        generateData();
        findViewById(R.id.tv_sort).setTag(1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        employeeAdapter = new EmployeeAdapter(employeeModelArrayList, this);
        recyclerView.setAdapter(employeeAdapter);
    }

    @OnClick(R.id.tv_sort)
    void sort(View v) {


        TextView view = (TextView) v;
        int tag = (int) view.getTag();
        int drawble;
        if (tag == 1) {
            Collections.sort(employeeAdapter.getDataSet());
            view.setTag(-1);
            drawble = R.drawable.ic_ascending;
        } else {
            Collections.sort(employeeAdapter.getDataSet(), new AscendingComprator());
            view.setTag(1);
            drawble = R.drawable.ic_descending;
        }

        Drawable d = getResources().getDrawable(drawble);
        d.setBounds(0, 0, 60, 60);
        view.setCompoundDrawables(d, null, null, null);
        employeeAdapter.notifyDataSetChanged();

    }

    void filterArrayList() {

        if (selectedFilterItem != -1) {

            String filterDepartment = department[selectedFilterItem];

            ArrayList<EmployeeModel> filteredList = new ArrayList<>();
            for (EmployeeModel employee :
                    employeeModelArrayList) {

                if (employee.department.equals(filterDepartment)) {
                    filteredList.add(employee);
                }
            }

            employeeAdapter.changeArrayList(filteredList);
        }
    }

    @OnClick(R.id.tv_filter)
    void showFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Department")


                .setSingleChoiceItems(department, selectedFilterItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedFilterItem = which;
                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        filterArrayList();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).setNeutralButton("Show All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removeFilters();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void removeFilters() {
        selectedFilterItem = -1;
        employeeAdapter.changeArrayList(employeeModelArrayList);
    }

    void generateData() {
        employeeModelArrayList.clear();
        int numberOfDepartment = department.length;
        for (int i = 0; i < 1000; i++) {
            int salary = (i + 1) * 1000;
            EmployeeModel employeeModel = new EmployeeModel("Employee" + i,
                    department[i % numberOfDepartment], salary + "");
            employeeModelArrayList.add(employeeModel);
        }
    }
}


