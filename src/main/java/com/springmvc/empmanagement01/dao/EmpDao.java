package main.java.com.springmvc.empmanagement01.dao;

import main.java.com.springmvc.empmanagement01.entity.Emp;


public interface EmpDao
{
	/**
	 * ͨ����Ų���Ա��
	 * @param id
	 * @return
	 */
	Emp queryEmpById(long id);
}
