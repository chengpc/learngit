package com.gnhj.util;

import weaver.general.StaticObj;

import java.sql.Connection;
import java.sql.SQLException;

import weaver.general.BaseBean;
public class CBSUtil {
	/**
	 * ��ȡ�Զ��������Դ���ӡ�
	 * @param datasourceid  ����Դid
	 * @return  ���ݿ�����
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try{
			 weaver.interfaces.datasource.DataSource ds = (weaver.interfaces.datasource.DataSource) StaticObj.getServiceByFullname(("datasource.CBS_TEST"),weaver.interfaces.datasource.DataSource.class);
			 //weaver.interfaces.datasource.DataSource ds = (weaver.interfaces.datasource.DataSource) StaticObj.getServiceByFullname(("datasource.cbs"),weaver.interfaces.datasource.DataSource.class);
			 conn = ds.getConnection(); //������Դȡ������
		}catch(Exception e){
			
		}
		return conn;
	}
	/**
	 * �ر����ݿ�����
	 * @param conn
	 */
	public void closeConnection(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			}catch(SQLException e){
				new BaseBean().writeLog("�ر����ݿ����ӳ���."+e);
			}
		}
	}
	/**
	 * ����Ψһ����
	 * @return
	 */
	public static String getUniqueID(){
		String UniqueID="GNHJ-RECNUM"+System.currentTimeMillis();
		return UniqueID;
	}
	/**
	 * ����У����
	 * @param ERP_PAYMENT_ID--ƾ֤ID-����
	 * @param RECORD_STATUS--ָ��״̬--Ĭ���Ǵ�֧����
	 * @param PAYMENT_ACCOUNTS--����������˺�-
	 * @param DEPOSIT_ACCOUNTS--�տ�����˺�
	 * @param AMOUNTS--���׽��
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