package main.java.com.springmvc.empmanagement01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.com.springmvc.empmanagement01.entity.Dept;
import main.java.com.springmvc.empmanagement01.entity.Emp;
import main.java.com.springmvc.empmanagement01.service.EmpService;

@Controller
public class EmpController
{
	private int addResult;
	
	@Resource
	private EmpService empService;

	@RequestMapping("index.jsp")
	public String index()
	{
		return "redirect:/listEmp";
	}

	@RequestMapping("/listEmp")
	public String getAllEmpList(HttpSession session)
	{
		return "index";
	}

	@RequestMapping(value = "/getAllEmpJson", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAllEmpJson()
	{
		List<Emp> allEmp = empService.getAllEmp();

		Map<String, Object> map = new HashMap();
		map.put("result", allEmp);
		map.put("total", allEmp.size());

		return map;
	}

	@RequestMapping(value = "/getAllDeptJson", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getAllDeptJson()
	{
		List<Dept> allDept = empService.getAllDept();

		Vector vector = new Vector();

		for (int i = 0; i < allDept.size(); i++)
		{
			Vector sub = new Vector();
			Dept dept = allDept.get(i);
			sub.add(dept.getDid());
			sub.add(dept.getDname());
			vector.add(sub);
		}

		return vector;
	}

	@RequestMapping(value = "/getSingleEmp", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Emp getEmpById(@RequestParam long id)
	{
		Emp emp = empService.queryEmpById(id);
		return emp;
	}

	@RequestMapping("/addEmp")
	public String addEmp(Emp emp, HttpSession session)
	{
		List<Dept> allDept = empService.getAllDept();
		int addEmpResult = empService.addEmp(emp);
		
		this.setAddResult(addEmpResult);
		
		return "redirect:/listEmp";
	}
	
	@RequestMapping("/getAddEmpResult")
	@ResponseBody
	public Object getAddEmpResult(Emp emp, HttpSession session)
	{
		boolean success = false;
		
		List<Dept> allDept = empService.getAllDept();
		int addEmpResult = empService.addEmp(emp);
		
		System.out.println("addEmpResult = " + addEmpResult);
		
		if(addEmpResult == 1)
		{
			success = true;
		} 
		
		return success;
	}
	
	@RequestMapping("/delEmp")
	public String deleteEmp(Emp emp, HttpSession session)
	{
		int delEmp = empService.delEmp(emp);
		session.setAttribute("delEmp", delEmp);
		return "redirect:/listEmp";
	}

	@RequestMapping("/updateEmp")
	public String updateEmp(Emp emp, HttpSession session)
	{
		int updateEmp = empService.updateEmp(emp);
		session.setAttribute("updateEmp", updateEmp);
		return "updateEmp";
	}

	public int getAddResult()
	{
		return addResult;
	}

	public void setAddResult(int addResult)
	{
		this.addResult = addResult;
	}


}
