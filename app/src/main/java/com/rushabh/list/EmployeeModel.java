package com.rushabh.list;

import android.text.TextUtils;
import android.util.Log;

import java.util.Comparator;

/**
 * Created by rushabh on 22/12/16.
 */

public class EmployeeModel implements Comparable<EmployeeModel> {

    public String nameOfEmployee;
    public String department;

    private String salary;

    public EmployeeModel(String nameOfEmployee, String department, String salary) {
        this.nameOfEmployee = nameOfEmployee;
        this.department = department;
        this.salary = salary;
    }

    public String getSalary() {
        return "Rs. " + salary;
    }

    public void setSalary(String salary) {
        if (TextUtils.isDigitsOnly(salary)) {
            this.salary = salary;
        }
    }

    public int getNumberedSalary() {
        if (salary == null) {
            return 0;
        }
        return Integer.parseInt(salary);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
        /*if (obj == null) {
            return false;
        }
        if (obj instanceof EmployeeModel) {
            EmployeeModel e2 = (EmployeeModel) obj;
            return (this.getNumberedSalary() == e2.getNumberedSalary());
        }
        return false;*/
    }


    @Override
    public int compareTo(EmployeeModel e2) {
        if (this == e2) {
            return 0;
        }

        Log.d("beta", "" + this.getNumberedSalary() + " " + e2.getNumberedSalary());
        if (this.getNumberedSalary() < e2.getNumberedSalary()) {
            return 1;
        }
        return -1;

    }
}

class AscendingComprator implements Comparator<EmployeeModel> {

    @Override
    public int compare(EmployeeModel e1, EmployeeModel e2) {
        if (e1 == null || e2 == null) {
            return 0;
        }
        if (e1 == e2) {
            return 0;
        }
        if (e1.getNumberedSalary() > e2.getNumberedSalary()) {
            return 1;
        }
        return -1;
    }
}
