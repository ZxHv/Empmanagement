<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>员工信息管理</title>
    
    <!-- 引入extjs样式文件  -->
    <link rel="stylesheet" type="text/css" href="extjs6.0/classic/theme-triton/resources/theme-triton-all.css">    <script src="https://cdn.bootcss.com/extjs/6.0.0/ext-all.js"></script>
    <!-- 引入extjs核心库  -->
    <script src="extjs6.0/ext-all.js"></script>
    
    <!-- 图标样式文件  -->
    <link rel="stylesheet" type="text/css" href="extjs6.0/css/icon.css">
    
    <!-- 国际化文件 -->
    <script src="extjs6.0/classic/locale/locale-zh_CN.js"></script>   
    
    <script>
        //alert("hello");
        Ext.onReady(function(){
        
        	var eid4Update = '';
        	
        	 //创建数据源store
        	Ext.define('User', {
                extend: 'Ext.data.Model',
                fields: [{name: 'ename'},
                    {name: 'deptName'},
                    {name: 'age'},
                    {name: 'gender'},
                    {name: 'workDate', type: 'date', mapping:'date.time', convert :function(v,record){
                        //将一个long型的time转换为标准的日期对象
                        //此时V为一个long型的时间毫秒数
                        return  Ext.util.Format.date(new Date(v),'Y/m/d');
                    }}]
            });
        	 
        	var myStore = Ext.create('Ext.data.Store', {
       	     model: 'User',
       	     pageSize: 30,
       	     proxy: {
       	         type: 'ajax',
       	         url: 'http://localhost:8080/EmpManagement01/getAllEmpJson',
       	         reader: {
       	        	 type: 'json',
       	        	 rootProperty: 'result'}
       	     },
       	     autoLoad: true
       	    });
        	
        	Ext.define("department",{
        		extend:'Ext.data.Model',
        		fields:[
        			{name:'did'},
        			{name:'dname'}
        		]
        	});
        	
        	var deptStore = Ext.create("Ext.data.Store",{
        		model:'department',
        		proxy: {
          	         type: 'ajax',
          	         url: 'http://localhost:8080/EmpManagement01/getAllDeptJson',
          	         reader: new Ext.data.JsonReader({}, 
          	        		 [
          	        		    {name : 'did'}, 
          	        		    {name : 'dname'}
          	        		 ])
          	    },
          	    autoLoad: true
        	});   
        	
        	
        	
        	//不用的情况下,发现默认就是不可多选的 
        	var sm = new Ext.selection.CheckboxModel({checkOnly:true});
        
        	Ext.create('Ext.grid.Panel', {
        		title:'员工信息管理',
        		height:500,
        		forceFit:true,
        		columnLines:true,
        		selModel:sm,  //复选框
        		store:myStore,
        		renderTo:'empFormMgmt',
        		tbar: [//顶部工具栏
        		       {xtype: 'button', text: '新增',iconCls:'add',border:false, handler:function(){addUser.show();}},
        		       {xtype: 'button', text: '编辑',iconCls:'edit',border:false, handler:function(){editEmp(this);}},
        		       {xtype: 'button', text: '删除',iconCls:'delete',border:false, handler:function(){del(this);}},
        		       {xtype: 'button', text: '刷新',iconCls:'reload',border:false, handler:function(){}}
        		],
        		bbar:{xtype: 'pagingtoolbar', displayInfo: true, store: myStore},//分页条
        		columns:[
                         {xtype: 'rownumberer', width:30, align:'center'},
        		         {header:'员工姓名', dataIndex:'ename'},
        		         {header:'部门', dataIndex:'deptName'},
        		         {header:'年龄', dataIndex:'age'},
        		         {header:'性别', dataIndex:'gender'},
        		         {header:'入职日期', dataIndex:'workDate'
        		        	 //, xtype:'datecolumn',dateFormat :'Y-m-d'
        		        	 }
        		]
        	});
        	
        	//创建formpanel
        	var addFormPanel = Ext.create('Ext.form.Panel', {
        		bodyPadding: 30,  //外边距
        		// The form will submit an AJAX request to this URL when submitted
        		url: 'http://localhost:8080/EmpManagement01/addEmp',
        		// Fields will be arranged vertically, stretched to full width
        		layout: 'anchor',
        		defaults: {
        			anchor: '100%'
        		},
        		// The fields
        		defaultType: 'textfield',
        		items: [
        		        {name:'id',hidden:true},
        		        {name:'ename', fieldLabel:'姓名', allowBlank: false},
        		        {
        		        	name:'did',
        		        	allowBlank: false,
        		        	hiddenName:'did',
        		        	xtype:"combobox",
        		        	fieldLabel:"部门",
        		        	queryParam:"did",
        		        	store:deptStore,
        		        	multiSelect:false,
        		        	valueField:"did",
        		        	displayField:"dname"
        		        },
        		        {name:'age', fieldLabel:'年龄',allowBlank: false},
        		        {
        		            name:'gender',
        		            xtype: 'radiogroup',
        		            fieldLabel: '性别',
        		            // Arrange radio buttons into two columns, distributed vertically
        		            columns: 2,
        		            vertical: true,
        		            items: [
        		                { boxLabel: '男', inputValue: '男', checked: true },
        		                { boxLabel: '女', inputValue: '女'}//inputValue为传入后台的值
        		            ]
        		        },
        		        {name:'workDate', fieldLabel:'入职日期', xtype:'datefield', format: 'Y-m-d', allowBlank: false} 
        		        ],
        		// Reset and Submit buttons
        		buttons: [{
        			       text: 'Reset', 
        			       handler: function() {
        			    	   this.up('form').getForm().reset();
        			       }}, 
        		          {
        			       text: 'Submit', 
        			       formBind: true, 
        			       disabled: true, 
        			       handler: function() {
        		        	  var form = this.up('form').getForm();
        		        	  if (form.isValid()) {
        		        		  form.submit({
        		        			  url:'http://localhost:8080/EmpManagement01/getAddEmpResult',
        		        			  waitTitle: '请稍等...',  
                                      waitMsg: '正在提交信息...', 
        		        			  success: function(form, action) {
                                               if (action.result == true) {  
        		        					       Ext.MessageBox.alert("信息提示","保存成功!"); 
        		        					       addFormPanel.getForm().reset();
        		        					       addUser.close(); //关闭窗口  
        		        					       myStore.reload();
        		        					   } else {
        		        						   msg('信息提示', '添加时出现异常！');
        		        					   }
        		        			  },
        		        			  failure: function(form, action) {
        		        				  Ext.MessageBox.alert("信息提示","添加失败!"); 
        		        				  addUser.close(); //关闭窗口  
        		        			  }
        		        		  });
        		              }
        		          }
        		          }],
        	});
        	
        	//创建formpanel
        	var editFormPanel = Ext.create('Ext.form.Panel', {
        		bodyPadding: 30,  //外边距
        		// The form will submit an AJAX request to this URL when submitted
        		//url: 'http://localhost:8080/EmpManagement01/updateEmp',
        		// Fields will be arranged vertically, stretched to full width
        		layout: 'anchor',
        		defaults: {
        			anchor: '100%'
        		},
        		// The fields
        		defaultType: 'textfield',
        		items: [
        		        {name:'id',hidden:true},
        		        {name:'ename', fieldLabel:'姓名', allowBlank: false},
        		        {
        		        	name:'did',
        		        	allowBlank: false,
        		        	hiddenName:'did',
        		        	xtype:"combobox",
        		        	fieldLabel:"部门",
        		        	queryParam:"did",
        		        	store:deptStore,
        		        	multiSelect:false,
        		        	valueField:"did",
        		        	displayField:"dname"
        		        },
        		        {name:'age', fieldLabel:'年龄',allowBlank: false},
        		        {
        		            name:'gender',
        		            xtype: 'radiogroup',
        		            fieldLabel: '性别',
        		            // Arrange radio buttons into two columns, distributed vertically
        		            columns: 2,
        		            vertical: true,
        		            items: [
        		                { boxLabel: '男', inputValue: '男', checked: true },
        		                { boxLabel: '女', inputValue: '女'}//inputValue为传入后台的值
        		            ]
        		        },
        		        {name:'workDate', fieldLabel:'入职日期', xtype:'datefield', format: 'Y-m-d', allowBlank: false} 
        		        ],
                		// Reset and Submit buttons
                		buttons: [{
                			       text: 'Reset', 
                			       handler: function() {
                			    	   this.up('form').getForm().reset();
                			       }}, 
                		          {
                			       text: 'Submit', 
                			       formBind: true, 
                			       disabled: true, 
                			       handler: function() {
                		        	  var form = this.up('form').getForm();
                		        	  if (form.isValid()) {
                		        		  form.submit({
                		        			  url:'http://localhost:8080/EmpManagement01/updateEmp',
                		        			  params:{'eid':eid4Update},
                		        			  waitTitle: '请稍等...',  
                                              waitMsg: '正在提交信息...', 
                		        			  success: function(form, action) {
                                                       if (action.result == true) {  
                		        					       Ext.MessageBox.alert("信息提示","修改成功!"); 
                		        					       editFormPanel.getForm().reset();
                		        					       editUser.close(); //关闭窗口  
                		        					       myStore.reload();
                		        					   } else {
                		        						   msg('信息提示', '修改时出现异常！');
                		        					   }
                		        			  },
                		        			  failure: function(form, action) {
                		        				  //Ext.MessageBox.alert('提示', eid4Update);
                		        				  Ext.MessageBox.alert("信息提示","修改失败!"); 
                		        				  editUser.close(); //关闭窗口  
                		        			  }
                		        		  });
                		              }
                		          }
                		          }],
        	});
        	
        	var addUser = Ext.create('Ext.window.Window', {
        	    title: '新增人员',
        	    height: 500,
        	    width: 350,
        	    layout: 'fit',
        	    closeAction: 'hide', //默认为destroy,若窗口关闭后就销毁了,所以再次点击就显示不出来了
        	    items: addFormPanel
        	});
        	
        	
        	var editUser = Ext.create('Ext.window.Window', {
        		title: '编辑人员',
        		height:500,
        		width:300,
        		layout: 'fit',
        		closeAction: 'hide',
        		items: editFormPanel
        	});
        	
            //编辑emp
            function editEmp(button)
            {
            	//首先获得关联编辑操作的grid  
                var grid = button.ownerCt.ownerCt;  
                //获得被选择的数据模型  
                var selmodels= grid.getSelectionModel();
                //返回模型中被记录的数组  
                var selections= grid.getSelectionModel().getSelection();
                
                
                //仅支持单个编辑
                if(selections.length != 1 )
                {
                	Ext.Msg.alert('提示','未选择或者选择了多行数据!')
                } else 
                {
                	//如果选中且确认编辑则执行操作
                    Ext.MessageBox.confirm("","确定要编辑所选信息吗？",function(choice){
                    	if(choice == 'yes')
                    	{
                    		//得到一个维持所有当前已选择的记录的混合集合
                    		var selecteds= grid.getSelectionModel().selected;                    		
                    		
                    		//获取grid的store  
                    		var store = grid.getStore();
                    		var rowlength=store.getCount();
                    		
                    		var params = '';

                    		//遍历所有记录
               		        for(var i = rowlength - 1; i >= 0; i--)
               		        {
               		        	//如果选择集里有所有记录里被选择的记录的索引
               		    	    if(selmodels.isSelected(i))
               		    	    {
               		    	         //获取索引所对应的模型下的值
               		                 params = store.getAt(i).get('eid');
               		    	    }
               		        }
                            //利用全局变量记录修改的底层key
                    		eid4Update = params;
                    		
                    		editUser.show();
                    		
                    	}
                    });
                }
            }
            
            
            //删除emp
            function del(button,url)
            {
                //首先获得关联删除操作的grid
                var grid = button.ownerCt.ownerCt;
                //获得被选择的数据模型
                var selmodels= grid.getSelectionModel();
                //返回模型中被记录的数组
                var selections= grid.getSelectionModel().getSelection();
                //如果不大于1条等于没选中
                if(selections.length<1)
                {
                    Ext.Msg.alert('提示','请先选择一行!')
                } else
                {
                	 //如果选中且确认删除则执行操作
                	 Ext.MessageBox.confirm("","确定要删除所选信息吗？",function(choice){
                		 if(choice == 'yes')
                		 {
                			 //得到一个维持所有当前已选择的记录的混合集合
                			 var selecteds= grid.getSelectionModel().selected;
                			 //获取grid的store
                		     var store = grid.getStore();
                		     var rowlength=store.getCount(); var rowlength=store.getCount();
                		     var params='';
                		                 		     
                		     //遍历所有记录
                		     for(var i = rowlength - 1; i >= 0; i--)
                		     {
                		    	//如果选择集里有所有记录里被选择的记录的索引
                		    	 if(selmodels.isSelected(i))
                		    	 {
                		    	     //获取索引所对应的模型下的值
                		             params += store.getAt(i).get('eid') + ',';
                		             //移除对应索引下的值  
                		             store.remove(store.getAt(i));
                		    	 }
                		     }
                		     //处理数据 
                		     //urlparams=params.slice(0,params.lastIndexOf(','));
                		     
                		     Ext.Ajax.request({
                		    	 //发送ajax请求 
                		    	 url : 'http://localhost:8080/EmpManagement01/delBatchEmp',
                		    	 params: {  eids: params  },
                		    	 success: function(response) {
                		    	     var text = response.responseText;
                		    	     grid.view.refresh();
                		    	     if(text == "true")
                		    	     {
                		    	    	 Ext.Msg.alert('成功','删除成功!');
                		    	     } else
                                     {
                		    	    	 Ext.Msg.alert('失败','删除失败!');
                                     }
                		    	 },
                		    	 failure:function(response){
                		    	     Ext.Msg.alert('失败','删除失败!');
                		    	 }
                		     });
                		 }
                	 });
                }
            }
        	
        });
        

        

        
    </script>
    
</head>
<body>

    <div style="width:100%;text-align:center" >
        <div id="empFormMgmt"></div>          
    </div>
    

</body>
</html>