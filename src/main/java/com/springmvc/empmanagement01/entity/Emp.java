package main.java.com.springmvc.empmanagement01.entity;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * ְԱ��Ϣ
 * 
 * @author Administrator
 * 
 */
@Component
public class Emp
{
	/*
	 * ���
	 */
	private long eid;

	/*
	 * ����
	 */
	private String ename;

	/*
	 * ��������ID
	 */
	private long did;
	
	/*
	 * ��������
	 */
	private String deptName;

	/*
	 * ����
	 */
	private int age;

	/*
	 * �Ա�
	 */
	private String gender;

	/*
	 * ��ְ����
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
