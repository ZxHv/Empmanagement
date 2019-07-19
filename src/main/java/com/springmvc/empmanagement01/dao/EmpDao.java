package main.java.com.springmvc.empmanagement01.dao;

import main.java.com.springmvc.empmanagement01.entity.Emp;


public interface EmpDao
{
	/**
	 * 通过编号查找员工
	 * @param id
	 * @return
	 */
	Emp queryEmpById(long id);
}
