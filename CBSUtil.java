package com.gnhj.util;

import weaver.general.StaticObj;

import java.sql.Connection;
import java.sql.SQLException;

import weaver.general.BaseBean;
public class CBSUtil {
	/**
	 * 获取自定义的数据源连接。
	 * @param datasourceid  数据源id
	 * @return  数据库连接
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try{
			 weaver.interfaces.datasource.DataSource ds = (weaver.interfaces.datasource.DataSource) StaticObj.getServiceByFullname(("datasource.CBS_TEST"),weaver.interfaces.datasource.DataSource.class);
			 //weaver.interfaces.datasource.DataSource ds = (weaver.interfaces.datasource.DataSource) StaticObj.getServiceByFullname(("datasource.cbs"),weaver.interfaces.datasource.DataSource.class);
			 conn = ds.getConnection(); //和数据源取得连接
		}catch(Exception e){
			
		}
		return conn;
	}
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public void closeConnection(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			}catch(SQLException e){
				new BaseBean().writeLog("关闭数据库连接出错."+e);
			}
		}
	}
	/**
	 * 生成唯一主键
	 * @return
	 */
	public static String getUniqueID(){
		String UniqueID="GNHJ-RECNUM"+System.currentTimeMillis();
		return UniqueID;
	}
	/**
	 * 生成校验码
	 * @param ERP_PAYMENT_ID--凭证ID-主键
	 * @param RECORD_STATUS--指令状态--默认是待支付。
	 * @param PAYMENT_ACCOUNTS--付款方银行总账号-
	 * @param DEPOSIT_ACCOUNTS--收款方银行账号
	 * @param AMOUNTS--交易金额
	 * @return
	 */
	public static int getCHECK_CODE(String ERP_PAYMENT_ID,String RECORD_STATUS, String PAYMENT_ACCOUNTS,String DEPOSIT_ACCOUNTS,String AMOUNTS){
		int s=0;
        int asc=0;
        int asc1=0;
        int asc2=0;
        int asc3=0;
        int asc4=0;
		for(int i=0;i<ERP_PAYMENT_ID.length();i++){
	       	 asc = ERP_PAYMENT_ID.charAt(i);
	       	 s= s+asc;
	       	// System.out.println(asc);
	    }
		s=s+39;
       for(int j=0;j<RECORD_STATUS.length();j++){
      	 asc1=RECORD_STATUS.charAt(j);
      	 s=s+asc1;
       }
       s=s+39;
       for(int k=0;k<PAYMENT_ACCOUNTS.length();k++){
      	 asc2=PAYMENT_ACCOUNTS.charAt(k);
      	 s=s+asc2;
       }
       s=s+39;
       for(int l=0;l<DEPOSIT_ACCOUNTS.length();l++){
      	 asc3=DEPOSIT_ACCOUNTS.charAt(l);
      	 s=s+asc3;
       }
       s=s+39;
       for(int m=0;m<AMOUNTS.length();m++){
      	 asc4=AMOUNTS.charAt(m);
      	 s=s+asc4;
       }
       s=s+39;
       s=s+39;//CENTER_TRANS_ID=NULL
       s = ((s % 999) * (s % 2184)) % 9999;
	   return s;
	}
}