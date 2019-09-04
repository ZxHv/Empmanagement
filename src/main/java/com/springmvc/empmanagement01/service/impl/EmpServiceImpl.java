package main.java.com.springmvc.empmanagement01.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.springmvc.empmanagement01.dao.EmpDao;
import main.java.com.springmvc.empmanagement01.entity.Dept;
import main.java.com.springmvc.empmanagement01.entity.Emp;
import main.java.com.springmvc.empmanagement01.service.EmpService;

/**
 * 员工操作业务具体实现类
 * @author Administrator
 *
 */
@Service
public class EmpServiceImpl implements EmpService
{
    @Autowired
	private EmpDao empDao;

	@Override
	public Emp queryEmpById(long id)
	{
        Emp emp = empDao.queryEmpById(id);
		
		return emp;
	}

	@Override
	public int addEmp(Emp emp)
	{
		int addEmp = empDao.addEmp(emp);
		return addEmp;
	}

	@Override
	public int delEmp(Emp emp)
	{
		int delEmp = empDao.delEmp(emp);
		return delEmp;
	}

	@Override
	public List<Emp> getAllEmp()
	{
		List<Emp> allEmp = empDao.getAllEmp();
		return allEmp;
	}

	@Override
	public int updateEmp(Emp emp)
	{
		int updateEmp = empDao.updateEmp(emp);
		return updateEmp;
	}

	@Override
	public List<Dept> getAllDept()
	{
		List<Dept> allDept = empDao.getAllDept();
		return allDept;
	}

}
