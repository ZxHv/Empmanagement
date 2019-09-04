package main.java.com.springmvc.empmanagement01.entity;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * 员工信息
 * 
 * @author Administrator
 * 
 */
@Component
public class Emp
{
	/*
	 * 员工编码
	 */
	private long eid;

	/*
	 * 员工姓名
	 */
	private String ename;

	/*
	 * 部门ID
	 */
	private long did;
	
	/*
	 * 部门名称
	 */
	private String deptName;

	/*
	 * 年龄
	 */
	private int age;

	/*
	 * 性别
	 */
	private String gender;

	/*
	 * 入职日期
	 */
	private Date workDate;

	public long getEid()
	{
		return eid;
	}

	public void setEid(long eid)
	{
		this.eid = eid;
	}

	public String getEname()
	{
		return ename;
	}

	public void setEname(String ename)
	{
		this.ename = ename;
	}

	public long getDid()
	{
		return did;
	}

	public void setDid(long did)
	{
		this.did = did;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Date getWorkDate()
	{
		return workDate;
	}

	public void setWorkDate(Date workDate)
	{
		this.workDate = workDate;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

}
