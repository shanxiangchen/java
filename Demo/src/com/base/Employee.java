package com.base;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public abstract class Employee implements Externalizable {

	private static final long serialVersionUID = 1371481020413353308L;
	private int empId;
	private String empName;

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("ReadExternal");
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("WriteExternal");
	}
	
	abstract String getName();

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
