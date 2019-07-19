package test.com.springmvc.empmanagement01.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import main.java.com.springmvc.empmanagement01.dao.EmpDao;
import main.java.com.springmvc.empmanagement01.entity.Emp;
import test.com.springmvc.empmanagement01.BaseTest;

public class EmpDaoTest extends BaseTest
{
	@Autowired
	private EmpDao empDao;
	
	@Test
    public void queryEmpByIdTest()
    {
    	long id = 1000;
    	Emp emp = empDao.queryEmpById(id);
    	System.out.println("Emp: " + emp.getEid() + ", " + emp.getEname() + ", " + emp.getDid() + ", " + emp.getDeptName());
    }
}
