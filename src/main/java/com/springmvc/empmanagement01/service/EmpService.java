package main.java.com.springmvc.empmanagement01.service;

import java.util.List;

import main.java.com.springmvc.empmanagement01.entity.Dept;
import main.java.com.springmvc.empmanagement01.entity.Emp;

/**
 * 员工操作业务接口
 * @author Administrator
 *
 */
public interface EmpService
{
	Emp queryEmpById(long id);
	
	int addEmp(Emp emp);
	
	int delEmp(Emp emp);
	
	List<Emp> getAllEmp();
	
	int updateEmp(Emp emp);
	
	List<Dept> getAllDept();

}
