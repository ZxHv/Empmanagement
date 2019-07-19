package main.java.com.springmvc.empmanagement01.entity;

import org.springframework.stereotype.Component;

/**
 * 部门信息
 * @author Administrator
 *
 */
@Component
public class Dept
{
	/*
	 * 部门编号
	 */
	private long did;

	/*
	 * 部门名称
	 */
	private String dname;

	public long getDid()
	{
		return did;
	}

	public void setDid(long did)
	{
		this.did = did;
	}

	public String getDname()
	{
		return dname;
	}

	public void setDname(String dname)
	{
		this.dname = dname;
	}

}
