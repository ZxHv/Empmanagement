package main.java.com.springmvc.empmanagement01.dao;

import java.util.List;

import main.java.com.springmvc.empmanagement01.entity.Dept;
import main.java.com.springmvc.empmanagement01.entity.Emp;


public interface EmpDao
{
	/**
	 * 通过ID查询员工信息
	 * @param id
	 * @return
	 */
	Emp queryEmpById(long id);
	
	/**
	 * 新增员工
	 * @param emp
	 * @return
	 */
	int addEmp(Emp emp);
	
	/**
	 * 删除员工
	 * @param emp
	 * @return
	 */
	int delEmp(Emp emp);

	/**
	 * 获取所有员工信息
	 * @return
	 */
	List<Emp> getAllEmp();
	
	/**
	 * 更新员工信息
	 * @param emp
	 * @return
	 */
	int updateEmp(Emp emp);
	
	/**
	 * 获取所有的部门
	 * @return
	 */
	List<Dept> getAllDept();
	
}
