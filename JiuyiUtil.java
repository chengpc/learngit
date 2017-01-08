package com.gnhj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

public class JiuyiUtil {
	//���tableName
	public static String getTablename(RequestInfo requestInfo){
		String workflowid = requestInfo.getWorkflowid();
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
	
	//���tablename
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

	//�ж��Ƿ�Ϊʱ������
	public static String parsedata(Object value){
		if (value instanceof Date) {
			return TimeUtil.getDateString((Date)value);
		}else{
			if(value==null)value="";
			return value+"";
		}
	}
	/**
	 * ����userid ��ȡ name ����ʽ
	 * @param userid
	 * @return
	 */
	public static String getLastNameByUserId(String userid){
		RecordSet rs = new RecordSet();
		String result="";
		String lastname = "";
		if(rs.execute("select lastname from hrmresource where id = '"+userid+"'")&&rs.next()){
			lastname=Util.null2String(rs.getString("lastname"));
		}
		//System.out.println(browseid);
		System.out.println("����");
		result=lastname;
		return result;
		}
	/**
	 * ����userid ��ȡ name ����ʽ
	 * @param userid
	 * @return
	 */
	public static String getLoginidByUserId(String userid){
		RecordSet rs = new RecordSet();
		String result="";
		String loginid = "";
		if(rs.execute("select loginid from hrmresource where id = '"+userid+"'")&&rs.next()){
			loginid=Util.null2String(rs.getString("loginid"));
		}
		//System.out.println(browseid);
		result=loginid;
		return result;
		}
//	public static String getCellValue(HSSFCell cell)
//	  {
//	    String cellValue = "";
//	    if (cell == null)
//	      return "";
//	    switch (cell.getCellType()) {
//	    case 4:
//	      cellValue = String.valueOf(cell.getBooleanCellValue());
//	      break;
//	    case 0:
//	      if (HSSFDateUtil.isCellDateFormatted(cell)) {
//	        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd");
//	        cellValue = String.valueOf(sft.format(cell.getDateCellValue()));
//	      } else {
//	        cellValue = String.valueOf(new Double(cell.getNumericCellValue()));
//	        if (cellValue.endsWith(".0"))
//	          cellValue = cellValue.substring(0, cellValue.indexOf("."));
//	      }
//	      break;
//	    case 2:
//	      cellValue = cell.getCellFormula();
//	      break;
//	    case 1:
//	      cellValue = cell.getStringCellValue();
//	    case 3:
//	    }
//
//	    return cellValue;
//	  }
	/**
	 * ���ʻ�����
	 * @param c
	 * @param langu
	 * @return
	 */
	public static String SystemLangu(Integer c,Integer langu){
		RecordSet rs = new RecordSet();
		String sql = "select lastname from Systemlangu where indexid="+c+" and language="+langu;
		rs.execute(sql);
		rs.next();
		return rs.getString("lastname");
	}
	/**
	 * ���ݲ���ID��ȡ��������
	 * @param id ����ID
	 * @return ��������
	 */
	public static String getDepartmentById(String id,String zlglbfyzl){
		RecordSet rs = new RecordSet();
		String result="";
		rs.executeSql("select * from HrmDepartment where id = "+id);
		if(rs.next()){
			result= rs.getString("departmentname");
		}
		if(result.equals("���»�")){
			result="������";
		}
		if(result.equals("ѧ����")){
			result="�г���";
		}
		if(result.equals("�������ϲ�")){
			if(zlglbfyzl.equals("0")){
				result+="-����";
			}else if(zlglbfyzl.equals("1")){
				result+="-����";
			}
		}
		return result;
	}
	/**
	 * ���ݲ���ID��ȡ��������
	 * @param id ����ID
	 * @return ��������
	 */
	public static String getDepartById(String id){
		RecordSet rs = new RecordSet();
		String result="";
		rs.executeSql("select * from HrmDepartment where id = "+id);
		if(rs.next()){
			result= rs.getString("departmentname");
		}
		return result;
	}
	/**
	 * ���ݲ���������ȡ����ID
	 * @param ��������
	 * @return id ����ID
	 */
	public static String getIdByDepartment(String departmentname){
		RecordSet rs = new RecordSet();
		String result="";
		if(departmentname.contains("�ɶ�һ")){
			departmentname="�ɶ�һ���´�";
		}
		if(departmentname.contains("�ɶ���")){
			departmentname="�ɶ������´�";
		}
		rs.executeSql("select id from HrmDepartment where departmentname like '%"+departmentname+"%'");
		if(rs.next()){
			result = rs.getString("id");
		}
		return result;
	}
	/**
	 * ��ȡ15λ�ַ���
	 * @param str
	 * @return
	 */
	public static String cutString(String str){
		String strs="";
		strs+="<span onmouseover='showdiv(event,\""+str+"\")' onmouseout='hidediv(event)'>";
		if(str.length()>15){
			strs += str.substring(0, 15);
			strs+="......";
		}else{
			strs+=str;
		}
		strs+="</span>";
		return strs;
	}
	
	/**
	 * ��ȡ��������
	 * @param id
	 * @return
	 */
	public static String getCity(int id){
		RecordSet rs = new RecordSet();
		rs.executeSql("select * from hrmcity where id="+id);
		rs.next();
		String cityname = rs.getString("cityname");
		return cityname;
	}
	/**
	 * ��������Ƿ�ȫ���鵵
	 * @param requestids
	 * @return count
	 */
	public static String getStatus(String requestids){
		RecordSet rs = new RecordSet();
		rs.executeSql("select currentnodetype from workflow_requestbase where requestid in ("+requestids+") and currentnodetype!='3'");
		return ""+rs.getCounts();
	}
	
	/**
	 * ��ȡ�������ֵ
	 * @param id
	 * @return
	 */
	public static String getSelectItem(Integer selectvalue,Integer billid,String fieldname){
		String sql="select * from workflow_selectitem where selectvalue='"+selectvalue+"' and fieldid in (select id from workflow_billfield where billid = '"+billid+"' and fieldname='"+fieldname+"')";
		RecordSet rs = new RecordSet();
		rs.executeSql(sql);
		rs.next();
		
		return ""+rs.getString("selectname");
	}
	
	/**
	 * ����user id ��� lastname
	 * @param id
	 * @return lastname
	 */
	public static String getUserNameById(Integer id){
		String sql="select lastname from hrmresource where id ="+id;
		RecordSet rs = new RecordSet();
		rs.executeSql(sql);
		rs.next();
		return ""+rs.getString("lastname");
	}
	/**
	 * ����lastname ��� user id
	 * @param lastname
	 * @return user id
	 */
	public static String getIdByUserName(String username){
		String sql="select id from hrmresource where lastname ='"+username+"'";
		RecordSet rs = new RecordSet();
		rs.executeSql(sql);
		rs.next();
		return ""+rs.getString("id");
	}
	public static String SystemLanguByHtml(Integer languageid,Integer billid,String fieldname){
		
		String sql="select * from HtmlLabelInfo where indexid in" +
				" (select fieldlabel from workflow_billfield where billid = "+billid+" and fieldname = '"+fieldname+"')" +
				" and languageid = "+languageid;
		RecordSet rs = new RecordSet();
		rs.executeSql(sql);
		rs.next();
		
		return ""+rs.getString("labelname");
	}
	/**
	 * ����user department ��� �����¼�department
	 * @param depid
	 * @return depids
	 */
	public static String getDepidsByDepId(String depid){
		String result=depid;
		while(true){
			String sql="select id from HrmDepartment where supdepid in ("+depid+")";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			if(rs.getCounts()==0){
				break;
			}else{
				depid="";
			}
			while(rs.next()){
				result+=","+rs.getString("id");
				if(depid.equals("")){
					depid=rs.getString("id");
				}else{
					depid+=","+rs.getString("id");
				}
			}
		}
		return result;
	}
	
	/**
	 * ��ȡ����
	 * @param id
	 * @return
	 */
	public static String getCurrency(Integer id){
		RecordSet rs = new RecordSet();
		rs.executeSql("select currencyname from FnaCurrency where id ="+id);
		rs.next();
		return ""+rs.getString("currencyname");
	}
	/**
	 * ����user id ��� �����¼�
	 * @param userid
	 * @return userids
	 */
	public static String getUseridsByUserId(String userid){
		String result=userid;
		while(true){
			String sql="select id from Hrmresource where managerid in ("+userid+")";
			RecordSet rs = new RecordSet();
			rs.executeSql(sql);
			if(rs.getCounts()==0){
				break;
			}else{
				userid="";
			}
			while(rs.next()){
				String id=rs.getString("id");
				if(!pgWpo(id)){//����WPO
					result+=","+id;
					if(userid.equals("")){
						userid=id;
					}else{
						userid+=","+id;
					}
				}
			}
		}
		return result;
	}
	/**
	 * �ж��Ƿ�WPO
	 * @param id
	 * @return
	 */
	public static boolean pgWpo(String userid){
		RecordSet rs = new RecordSet();
		rs.executeSql("select * from formtable_main_148 where position='WPO' and person='"+userid+"'");
		return rs.next();
	}
	
	 /**
    * �������� : ��ȡ�ֶε�ID
    * �����ߣ�weitao
    * @param formid  ����id
    * @param num         ��ϸ��
    * @return Map
     */
    public static Map getFieldId(int formid,String num){
   	 
   	  formid =  Math.abs(formid);
   	  String sql = "";
   	  if("0".equals(num))
  		  {
  			 sql = "select id,fieldname,detailtable from workflow_billfield where billid=-"+formid+" and (detailtable='' or detailtable is null) ";
  		  }else {
  			 sql = "select id,fieldname,detailtable from workflow_billfield where billid=-"+formid+" and detailtable='formtable_main_"+formid+"_dt"+num+"'";
 		  }	
   	  	System.out.println(sql);
         RecordSet rs = new RecordSet();
         rs.execute(sql);
         Map array = new HashMap();
         while(rs.next()){
       	  array.put(Util.null2String(rs.getString("fieldname")).toLowerCase(), Util.null2String(rs.getString("id")));
         }
         return array;
    }



    
 	public static String getlabelId(String name,String formid,String ismain ,String num)
 	{
 		String id = "";
 		String sql = "";
 		if("0".equals(ismain))
 		{
 			sql = "select b.id,fieldname,detailtable from workflow_billfield b ,workflow_base a where b.billid=-"+formid+" and a.formid=b.billid and lower(fieldname)='"+name+"'";
 		}else {
 			sql = "select b.id,fieldname,detailtable from workflow_billfield b ,workflow_base a where b.billid=-"+formid+" and a.formid=b.billid and detailtable='formtable_main_"+formid+"_dt"+num+"' and lower(fieldname)='"+name+"'";
		}
		//System.out.println(sql);
 		RecordSet rs = new RecordSet();
 		rs.execute(sql);
		rs.next();
 		id = Util.null2String(rs.getString("id"));
 		return id ;
 		
 	} 
 	/**
     * ����ת��
     * @param requestids ������
     * @param forwardoperator ��ǰ������
     * @param recipients ��ת����
     * @return
     */
	public static boolean forwardFlow(String requestids,int forwardoperator,String recipients){
		String[] requestid= requestids.split(",");
		try{
		for(int i=0;i<requestid.length;i++){
			weaver.soa.workflow.request.RequestService reqSvc = new weaver.soa.workflow.request.RequestService();
			reqSvc.forwardFlow(Integer.parseInt(requestid[i]) , forwardoperator, recipients, "","");
		}
		}catch(Exception e){
			
		}
		return true;
	}
	
	/**
     * ���ֽ���дת����˼����д��������Ȼ������ʰ�滻����
     * Ҫ�õ�������ʽ
     */
    public static String digitUppercase(double n){
        String fraction[] = {"��", "��"};
        String digit[] = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };
        String unit[][] = {{"Ԫ", "��", "��"},
                     {"", "ʰ", "��", "Ǫ"}};
 
        String head = n < 0? "��": "";
        n = Math.abs(n);
         
        String s = "";
        for (int i = 0; i < fraction.length; i++) {
            s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(��.)+", "");
        }
        if(s.length()<1){
            s = "��";    
        }
        int integerPart = (int)Math.floor(n);
 
        for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
            String p ="";
            for (int j = 0; j < unit[1].length && n > 0; j++) {
                p = digit[integerPart%10]+unit[1][j] + p;
                integerPart = integerPart/10;
            }
            s = p.replaceAll("(��.)*��$", "").replaceAll("^$", "��") + unit[0][i] + s;
        }
        return head + s.replaceAll("(��.)*��Ԫ", "Ԫ").replaceFirst("(��.)+", "").replaceAll("(��.)+", "��").replaceAll("^��$", "��Ԫ��");
    }
    
    
    /**
     * ����ת��
     * @param requestids ������
     * @param forwardoperator ��ǰ������
     * @param recipients ��ת����
     * @return
     */
	public static Integer getManagerByUserid(Integer id){
		RecordSet rs = new RecordSet();
		rs.executeSql("select managerid from hrmresource where id = "+id);
		rs.next();
		return rs.getInt("managerid");
	}
	/**
	 * ͨ���û�id�����û�����
	 * @param userid
	 * @return lastname
	 */
	public static  String getUserDepartmentByUserId(String userid){
		RecordSet rs = new RecordSet();
		String departmentid = "";
		if(rs.execute("select departmentid from hrmresource where id = '"+userid+"'")&&rs.next()){
			departmentid=Util.null2String(rs.getString("departmentid"));
		}
		//System.out.println(browseid);
		return departmentid;
	}
	/**
	 * ��ȡԱ�����ý����
	 * @param SAPygbyjye
	 * @return ygbyjye
	 */
	public static float getYgbyjye(float SAPygbyjye,String requestid,String userid){
		float ygbyjye=0.00f;
		RecordSet rs = new RecordSet();
		String sql="select sum(a.ygbyjye) as ygbyjye from formtable_main_29 a left join workflow_requestbase b on a.requestid=b.requestid where b.currentnodetype='2' and a.sqr='"+userid+"' and a.requestid<>'"+requestid+"'";
		rs.execute(sql);
		rs.next();
		String value=Util.null2String(rs.getString("ygbyjye"));
		if(!value.equals("")){
			ygbyjye=SAPygbyjye-rs.getFloat("ygbyjye");
		}else{
			ygbyjye=SAPygbyjye;
		}
		return ygbyjye;
	}
	/**
	 * ��ȡԱ�����˽�����
	 * @param SAPyggrjkye
	 * @return yggrjkye
	 */
	public static float getYggrjkye(float SAPyggrjkye,String requestid){
		float yggrjkye=0.00f;
		RecordSet rs = new RecordSet();
		String sql="select sum(a.yggrjkye) as yggrjkye from formtable_main_29 a left join workflow_requestbase b on a.requestid=b.requestid where b.currentnodetype='2' and a.requestid<>'"+requestid+"'";
		rs.execute(sql);
		rs.next();
		String value=Util.null2String(rs.getString("yggrjkye"));
		if(!value.equals("")){
			yggrjkye=SAPyggrjkye-rs.getFloat("yggrjkye");
		}else{
			yggrjkye=SAPyggrjkye;
		}
		return yggrjkye;
	}
	/**
	 * ��ȡ��Ӧ��Ԥ�������
	 * @param SAPgysyfkye
	 * @return gysyfkye
	 */
	public static float getGysyfkye(float SAPgysyfkye,String requestid,String userid){
		float gysyfkye=0.00f;
		RecordSet rs = new RecordSet();
		String sql="select sum(a.gysyfkye) as gysyfkye from formtable_main_29 a left join workflow_requestbase b on a.requestid=b.requestid where b.currentnodetype='2' and a.sqr='"+userid+"' and a.requestid<>'"+requestid+"'";
		rs.execute(sql);
		rs.next();
		String value=Util.null2String(rs.getString("gysyfkye"));
		if(!value.equals("")){
			gysyfkye=SAPgysyfkye-rs.getFloat("gysyfkye");
		}else{
			gysyfkye=SAPgysyfkye;
		}
		return gysyfkye;
	}
	/**  
     * ������������֮����������  
     * @param smdate ��С��ʱ�� 
     * @param bdate  �ϴ��ʱ�� 
     * @return ������� 
     * @throws ParseException  
     */    
    public static int daysBetween(String sdate,String edate) throws ParseException    
    {     
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Date smdate=sdf.parse(sdate);  
        Date bdate=sdf.parse(edate);  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }
    /**
	 * ��ȡ����
	 * @param kjkmms
	 * @return waers
	 */
	public static String getWaers(String kjkmms){
		String waers="";
		if(kjkmms.contains("��Ԫ��")){
			waers="USD";
		}else if(!kjkmms.contains("JPY")&&!kjkmms.contains("USD")){
			waers="CNY";
		}
		return waers;
	}

	public static boolean isYjs(String bm) {
		boolean isyjs;
		RecordSet rs = new RecordSet();
		String sjbm="";
		if(bm.equals("29")){
			isyjs=true;
		}else{
			String sql="select supdepid from hrmdepartment where id="+bm;
			rs.execute(sql);
			if(rs.next()){
				sjbm=rs.getString("supdepid");
				if(sjbm.equals("29")){
					isyjs=true;
				}else{
					isyjs=false;
				}
			}else{
				isyjs=false;
			}
		}
		
		return isyjs;
	}
//
//	public static String getSwzy(String sqbm) {
//		RecordSet rs = new RecordSet();
//		String result="";
//		rs.executeSql("select swzy from uf_swzy where charindex(',"+sqbm+",',','+CAST(ssbm as VARCHAR)+',')>0");
//		if(rs.next()){
//			result = rs.getString("swzy");
//		}
//		return result;
//	}
	/**
	 * 
	 * @param date1 <String>
	 * @param date2 <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonthSpace(String minDate, String maxDate)
			throws ParseException {
		ArrayList<String> result = new ArrayList<String>();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//��ʽ��Ϊ����

	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();

	    min.setTime(sdf.parse(minDate));
	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

	    max.setTime(sdf.parse(maxDate));
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

	    Calendar curr = min;
	    while (curr.before(max)) {
	     result.add(sdf.format(curr.getTime()));
	     curr.add(Calendar.MONTH, 1);
	    }
		return result.size();

	}
	/**
	 * ��ȡ�ڵ�����
	 * @param nodeid
	 * @return nodename
	 */
	public static String getNodeName(int nodeid){
		RecordSet rs = new RecordSet();
		String nodename="";
		if(rs.execute("select nodename from workflow_nodebase where id = '"+nodeid+"'")&&rs.next()){
			nodename=Util.null2String(rs.getString("nodename"));
		}
		return nodename;
	}
	/**
	 * ��ȡ��ֹʱ��
	 * @return nodename
	 */
	public static int getJzsj(){
		RecordSet rs = new RecordSet();
		String dqmouth=TimeUtil.getToday().split("-")[1];
		int jsrq=0;
		if(rs.execute("select jsrq from uf_fybxrq where yfsz='"+dqmouth+"'")&&rs.next()){
			jsrq=rs.getInt("jsrq");
		}
		return jsrq;
	}

	public static String getCbzx(String bm, String bukrs) {
		// TODO Auto-generated method stub
		RecordSet rs = new RecordSet();
		String cbzx="";
		if(rs.execute("select cbzx from uf_bmcbzx where bm='"+bm+"' and gsdm='"+bukrs+"'")&&rs.next()){
			cbzx=rs.getString("cbzx");
		}
		return cbzx;
	}
}
