package main.java.com.springmvc.empmanagement01.controller;

import main.java.com.springmvc.empmanagement01.dao.EmpDao;
import main.java.com.springmvc.empmanagement01.entity.Emp;

import org.apache.catalina.SessionListener;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GenericController
{
	@Autowired
	private EmpDao empDao;
	
	private static Logger log = Logger.getLogger(SessionListener.class);  
	  
	static
	{
		log.info("SessionListener contextInitialized!");
	}
	
	@RequestMapping("/hello")
    public String hello()
    {
		
		System.out.println("starting hello....");

    	return "hello";
    }
	
	@RequestMapping("/emp")
	public String showEmp(Model model, Long eid)
	{
		if(eid == null)
		{
		    eid = 1001l;
		}
		
		System.out.println("starting showEmp....");
		long id = 1000;
		Emp emp = empDao.queryEmpById(eid);
		
	    model.addAttribute("eid", emp.getEid());
	    model.addAttribute("ename", emp.getEname());
	    model.addAttribute("dept", emp.getDeptName());

		return "testEmpDao";
	}
	
}
