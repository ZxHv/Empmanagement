package main.java.com.springmvc.empmanagement01.entity;

import org.springframework.stereotype.Component;

/**
 * ������Ϣ
 * @author Administrator
 *
 */
@Component
public class Dept
{
	/*
	 * ���ű��
	 */
	private long did;

	/*
	 * ��������
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
