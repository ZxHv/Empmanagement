package test.com.springmvc.empmanagement01.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import main.java.com.springmvc.empmanagement01.dao.EmpDao;
import main.java.com.springmvc.empmanagement01.entity.Dept;
import main.java.com.springmvc.empmanagement01.entity.Emp;
import test.com.springmvc.empmanagement01.BaseTest;

public class EmpDaoTest extends BaseTest
{
	@Autowired
	private EmpDao empDao;
	
	@Test
	@Ignore
    public void queryEmpByIdTest()
    {
    	long id = 1000;
    	Emp emp = empDao.queryEmpById(id);
    	System.out.println("Emp: " + emp.getEid() + ", " + emp.getEname() + ", " + emp.getDid() + ", " + emp.getDeptName());
    }
	
	@Test
	@Ignore
	public void insertEmpTest()
	{
		Emp emp = new Emp();
		emp.setEname("小FFFFFFF");
		emp.setEid(1004);
		emp.setAge(234);
		emp.setDid(10);
		emp.setGender("女");
		emp.setWorkDate(new Date());
		
		int addEmp = empDao.addEmp(emp);
		
		System.out.println("addEmpFlag: " + addEmp);
	}
	
	@Test
	@Ignore
	public void deleteEmpTest()
	{
		long eid = 6668;
		
		Emp emp = empDao.queryEmpById(eid);
		
		int delEmp = empDao.delEmp(emp);
		
		System.out.println("delEmp: " + delEmp);
	}
	
	@Test
	@Ignore
    public void getAllEmpTest()
    {
    	List<Emp> allEmp = empDao.getAllEmp();
    	
    	for (int i = 0; i < allEmp.size(); i++)
		{
    		Emp emp = allEmp.get(i);
        	System.out.println("Emp[" + i + "]: " +  emp.getEid() + ", " + emp.getEname() + 
        			", " + emp.getDid() + ", " + emp.getDeptName() + ", " + emp.getWorkDate());
		}
    }
	
	@Test
	@Ignore
	public void updateEmpTest()
	{
		long eid = 6667;
		Emp emp = empDao.queryEmpById(eid);
		
		emp.setEname("小EEE");
		
		int updateEmp = empDao.updateEmp(emp);
		
		System.out.println("updateEmp: " + updateEmp);
	}
	
	@Test
	public void getAllDept()
	{
		List<Dept> allDept = empDao.getAllDept();
		
		Vector vector = new Vector();
		
		Map map = new HashMap();
		
		for (int i = 0; i < allDept.size(); i++)
		{
			Vector sub = new Vector();
    		Dept dept = allDept.get(i);
    		sub.add(dept.getDid());
    		sub.add(dept.getDname());
    		vector.add(sub);
		}
		System.out.println(vector);
	}
	
}
