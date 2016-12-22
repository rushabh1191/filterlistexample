package com.rushabh.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recylcerview)
    RecyclerView recyclerView;


    ArrayList<EmployeeModel> employeeModelArrayList = new ArrayList<>();

    EmployeeAdapter employeeAdapter;

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
    void sort(View view) {

        int tag = (int) view.getTag();
        if (tag == 1) {
            Collections.sort(employeeModelArrayList);
            view.setTag(-1);
        } else {
            Collections.sort(employeeModelArrayList, new AscendingComprator());
            view.setTag(1);
        }
        employeeAdapter.notifyDataSetChanged();

    }

    void generateData() {

        employeeModelArrayList.clear();
        String department[] = new String[]{"Tech", "Marketing", "Operations", "HR"};
        int numberOfDepartment = department.length;
        for (int i = 0; i < 1000; i++) {
            int salary = (i + 1) * 1000;
            EmployeeModel employeeModel = new EmployeeModel("Employee" + i,
                    department[i % numberOfDepartment], salary + "");
            employeeModelArrayList.add(employeeModel);
        }
    }
}


