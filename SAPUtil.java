package com.gnhj.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sap.mw.jco.JCO;
import com.weaver.integration.datesource.SAPInterationOutUtil;
import com.weaver.integration.log.LogInfo;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;

public class SAPUtil {

	/**
	 * 读取配置文件SAP数据源来获取连接
	 * @return
	 */
	public static JCO.Client  getSAPcon(){
		String datasourceid="1";
		SAPInterationOutUtil sapUtil = new SAPInterationOutUtil();
		JCO.Client  myConnection =null;
		myConnection = (JCO.Client)sapUtil.getConnection(datasourceid, new LogInfo());
		return myConnection;
	}
	/**
	 * 根据传入参数获取SAP连接
	 * @param datasourceid
	 * @return
	 */
	public static JCO.Client  getSAPcon(String  datasourceid){
		SAPInterationOutUtil sapUtil = new SAPInterationOutUtil();
		JCO.Client  myConnection =null;
		myConnection = (JCO.Client)sapUtil.getConnection(datasourceid, new LogInfo());
		return myConnection;
	}
	public static String getTablename(RequestInfo requestInfo){
		String workflowid = requestInfo.getWorkflowid();
		String requestid = requestInfo.getRequestid();
		String formid="";
		String sql1="select formid from workflow_base where id="+workflowid;
		System.out.println(sql1);
		RecordSet rs = new RecordSet();
		rs.execute(sql1);
		if(rs.next()){
			formid = rs.getString("formid");
		}
		formid = formid.replaceAll("-", "");
		String tablename="formtable_main_"+formid;
		return tablename;
		
	}
	//获得tablename
	public static String getTablenameBywfid(Integer workflowid){
		String formid="";
		String sql1="select formid from workflow_base where id="+workflowid;
		System.out.println(sql1);
		RecordSet rs = new RecordSet();
		rs.execute(sql1);
		if(rs.next()){
			formid = rs.getString("formid");
		}
		formid = formid.replaceAll("-", "");
		String tablename="formtable_main_"+formid;
		return tablename;
		
	}
	/*
	 * 获取今天的日期-长格式2013-06-13
	 */
	public static String getTodayWithLdt(){
		Calendar c1 = Calendar.getInstance();  
        c1.setTime(new Date()); 
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        return format.format(c1.getTime());
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String changeCurrency(String str){
		if(str.equals("RMB")){
			return "CNY";
		}
		return str;
	}
	/**
	 * 
	 * @return
	 */
	public  static void releaseClient(JCO.Client myConnection){
		if(null!=myConnection){
			JCO.releaseClient(myConnection);
		}
	}
}
