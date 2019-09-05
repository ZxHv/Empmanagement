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

		if (addEmpResult == 1)
		{
			success = true;
		}

		return success;
	}

	@RequestMapping("/delEmp")
	@ResponseBody
	public Object deleteEmp(int eid, HttpSession session)
	{
		boolean delResult = false;
		
		Emp emp = new Emp();

		emp.setEid(eid);

		int delEmp = empService.delEmp(emp);
		
		System.out.println("eid=" + eid + " , delEmp=" + delEmp);
		
		if(delEmp==1)//删除成功
		{
			delResult = true;
		}

		return delResult;
	}

	/**
	 * 批量删除
	 * @param eids
	 * @param session
	 * @return
	 */
	@RequestMapping("/delBatchEmp")
	@ResponseBody
	public Object deleteBatchEmp(String eids, HttpSession session)
	{
		boolean delResult = true;
		
		String[] split = null;
		
		if(eids==null || eids.length()==0)
		{
			System.out.println("eids is null!!!!");
			return false;
		} else 
		{
			split = eids.split(",");
			for (int i = 0; i < split.length; i++)
            {
				System.out.println("split[" + i + "]= " + "'" + split[i] + "'");
				Emp emp = new Emp();
				emp.setEid(Long.parseLong(split[i]));
				int delEmp = empService.delEmp(emp);
				
				if(delEmp!=1)//删除失败
				{
					System.out.println("eid: " + split[i] + " delete failed.. ");
					delResult = false;
				}
					
			}
		}

		return delResult;
	}
	
	@RequestMapping("/updateEmp")
	public String updateEmp(int eid, HttpSession session)
	{
		Emp emp = new Emp();

		emp.setEid(eid);
		
		int updateEmp = empService.updateEmp(emp);

		return "updateEmp";
	}

}
