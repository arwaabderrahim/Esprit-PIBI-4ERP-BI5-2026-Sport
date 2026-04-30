// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.


package padel.fact_performance_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;
 





@SuppressWarnings("unused")

/**
 * Job: Fact_Performance Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class Fact_Performance implements TalendJob {

protected static void logIgnoredError(String message, Throwable cause) {
       System.err.println(message);
       if (cause != null) {
               cause.printStackTrace();
       }

}


	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
	
	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	
	private final static String utf8Charset = "UTF-8";
	//contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String,String> propertyTypes = new java.util.HashMap<>();
		
		public PropertiesWithType(java.util.Properties properties){
			super(properties);
		}
		public PropertiesWithType(){
			super();
		}
		
		public void setContextType(String key, String type) {
			propertyTypes.put(key,type);
		}
	
		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}
	
	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();
	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties){
			super(properties);
		}
		public ContextProperties(){
			super();
		}

		public void synchronizeContext(){
			
		}
		
		//if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if(NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}
	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.
	public ContextProperties getContext() {
		return this.context;
	}
	private final String jobVersion = "0.1";
	private final String jobName = "Fact_Performance";
	private final String projectName = "PADEL";
	public Integer errorCode = null;
	private String currentComponent = "";
	
		private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
        private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();
	
		private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
		private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
		public  final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();
	

private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";
	
	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(), new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}
	
	public void setDataSourceReferences(List serviceReferences) throws Exception{
		
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();
		
		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils.getServices(serviceReferences,  javax.sql.DataSource.class).entrySet()) {
                    dataSources.put(entry.getKey(), entry.getValue());
                    talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}


private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

public String getExceptionStackTrace() {
	if ("failure".equals(this.getStatus())) {
		errorMessagePS.flush();
		return baos.toString();
	}
	return null;
}

private Exception exception;

public Exception getException() {
	if ("failure".equals(this.getStatus())) {
		return this.exception;
	}
	return null;
}

private class TalendException extends Exception {

	private static final long serialVersionUID = 1L;

	private java.util.Map<String, Object> globalMap = null;
	private Exception e = null;
	private String currentComponent = null;
	private String virtualComponentName = null;
	
	public void setVirtualComponentName (String virtualComponentName){
		this.virtualComponentName = virtualComponentName;
	}

	private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
		this.currentComponent= errorComponent;
		this.globalMap = globalMap;
		this.e = e;
	}

	public Exception getException() {
		return this.e;
	}

	public String getCurrentComponent() {
		return this.currentComponent;
	}

	
    public String getExceptionCauseMessage(Exception e){
        Throwable cause = e;
        String message = null;
        int i = 10;
        while (null != cause && 0 < i--) {
            message = cause.getMessage();
            if (null == message) {
                cause = cause.getCause();
            } else {
                break;          
            }
        }
        if (null == message) {
            message = e.getClass().getName();
        }   
        return message;
    }

	@Override
	public void printStackTrace() {
		if (!(e instanceof TalendException || e instanceof TDieException)) {
			if(virtualComponentName!=null && currentComponent.indexOf(virtualComponentName+"_")==0){
				globalMap.put(virtualComponentName+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			}
			globalMap.put(currentComponent+"_ERROR_MESSAGE",getExceptionCauseMessage(e));
			System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
		}
		if (!(e instanceof TDieException)) {
			if(e instanceof TalendException){
				e.printStackTrace();
			} else {
				e.printStackTrace();
				e.printStackTrace(errorMessagePS);
				Fact_Performance.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(Fact_Performance.this, new Object[] { e , currentComponent, globalMap});
					break;
				}
			}

			if(!(e instanceof TDieException)){
			}
		} catch (Exception e) {
			this.e.printStackTrace();
		}
		}
	}
}

			public void tDBInput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_8_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_9_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row7_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int DateKey;

				public int getDateKey () {
					return this.DateKey;
				}
				
			    public int Id_gender;

				public int getId_gender () {
					return this.Id_gender;
				}
				
			    public int Id_type_match;

				public int getId_type_match () {
					return this.Id_type_match;
				}
				
			    public int Id_tr_name;

				public int getId_tr_name () {
					return this.Id_tr_name;
				}
				
			    public int Id_Fact;

				public int getId_Fact () {
					return this.Id_Fact;
				}
				
			    public String Score;

				public String getScore () {
					return this.Score;
				}
				
			    public Integer Points__Loser;

				public Integer getPoints__Loser () {
					return this.Points__Loser;
				}
				
			    public Integer Points__Winner;

				public Integer getPoints__Winner () {
					return this.Points__Winner;
				}
				
			    public int Id_Country_player1_winner;

				public int getId_Country_player1_winner () {
					return this.Id_Country_player1_winner;
				}
				
			    public int Id_Country_player2_winner;

				public int getId_Country_player2_winner () {
					return this.Id_Country_player2_winner;
				}
				
			    public int Id_Player_1_winner;

				public int getId_Player_1_winner () {
					return this.Id_Player_1_winner;
				}
				
			    public int Id_Player_2_winner;

				public int getId_Player_2_winner () {
					return this.Id_Player_2_winner;
				}
				
			    public int Id_Player_1_loser;

				public int getId_Player_1_loser () {
					return this.Id_Player_1_loser;
				}
				
			    public int Id_Player_2_loser;

				public int getId_Player_2_loser () {
					return this.Id_Player_2_loser;
				}
				
			    public int Id_Country_player1_loser;

				public int getId_Country_player1_loser () {
					return this.Id_Country_player1_loser;
				}
				
			    public int Id_Country_player2_loser;

				public int getId_Country_player2_loser () {
					return this.Id_Country_player2_loser;
				}
				
			    public String Prize__Winner;

				public String getPrize__Winner () {
					return this.Prize__Winner;
				}
				
			    public Integer Prize__Loser;

				public Integer getPrize__Loser () {
					return this.Prize__Loser;
				}
				
			    public String Tournament_Name;

				public String getTournament_Name () {
					return this.Tournament_Name;
				}
				
			    public java.util.Date Date;

				public java.util.Date getDate () {
					return this.Date;
				}
				
			    public int Id_team_Loser;

				public int getId_team_Loser () {
					return this.Id_team_Loser;
				}
				
			    public int Id_team_Winner;

				public int getId_team_Winner () {
					return this.Id_team_Winner;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
							result = prime * result + (int) this.Id_Fact;
						
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final out1Struct other = (out1Struct) obj;
		
						if (this.Id_Fact != other.Id_Fact)
							return false;
					

		return true;
    }

	public void copyDataTo(out1Struct other) {

		other.DateKey = this.DateKey;
	            other.Id_gender = this.Id_gender;
	            other.Id_type_match = this.Id_type_match;
	            other.Id_tr_name = this.Id_tr_name;
	            other.Id_Fact = this.Id_Fact;
	            other.Score = this.Score;
	            other.Points__Loser = this.Points__Loser;
	            other.Points__Winner = this.Points__Winner;
	            other.Id_Country_player1_winner = this.Id_Country_player1_winner;
	            other.Id_Country_player2_winner = this.Id_Country_player2_winner;
	            other.Id_Player_1_winner = this.Id_Player_1_winner;
	            other.Id_Player_2_winner = this.Id_Player_2_winner;
	            other.Id_Player_1_loser = this.Id_Player_1_loser;
	            other.Id_Player_2_loser = this.Id_Player_2_loser;
	            other.Id_Country_player1_loser = this.Id_Country_player1_loser;
	            other.Id_Country_player2_loser = this.Id_Country_player2_loser;
	            other.Prize__Winner = this.Prize__Winner;
	            other.Prize__Loser = this.Prize__Loser;
	            other.Tournament_Name = this.Tournament_Name;
	            other.Date = this.Date;
	            other.Id_team_Loser = this.Id_team_Loser;
	            other.Id_team_Winner = this.Id_team_Winner;
	            
	}

	public void copyKeysDataTo(out1Struct other) {

		other.Id_Fact = this.Id_Fact;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
			        this.DateKey = dis.readInt();
					
			        this.Id_gender = dis.readInt();
					
			        this.Id_type_match = dis.readInt();
					
			        this.Id_tr_name = dis.readInt();
					
			        this.Id_Fact = dis.readInt();
					
					this.Score = readString(dis);
					
						this.Points__Loser = readInteger(dis);
					
						this.Points__Winner = readInteger(dis);
					
			        this.Id_Country_player1_winner = dis.readInt();
					
			        this.Id_Country_player2_winner = dis.readInt();
					
			        this.Id_Player_1_winner = dis.readInt();
					
			        this.Id_Player_2_winner = dis.readInt();
					
			        this.Id_Player_1_loser = dis.readInt();
					
			        this.Id_Player_2_loser = dis.readInt();
					
			        this.Id_Country_player1_loser = dis.readInt();
					
			        this.Id_Country_player2_loser = dis.readInt();
					
					this.Prize__Winner = readString(dis);
					
						this.Prize__Loser = readInteger(dis);
					
					this.Tournament_Name = readString(dis);
					
					this.Date = readDate(dis);
					
			        this.Id_team_Loser = dis.readInt();
					
			        this.Id_team_Winner = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
			        this.DateKey = dis.readInt();
					
			        this.Id_gender = dis.readInt();
					
			        this.Id_type_match = dis.readInt();
					
			        this.Id_tr_name = dis.readInt();
					
			        this.Id_Fact = dis.readInt();
					
					this.Score = readString(dis);
					
						this.Points__Loser = readInteger(dis);
					
						this.Points__Winner = readInteger(dis);
					
			        this.Id_Country_player1_winner = dis.readInt();
					
			        this.Id_Country_player2_winner = dis.readInt();
					
			        this.Id_Player_1_winner = dis.readInt();
					
			        this.Id_Player_2_winner = dis.readInt();
					
			        this.Id_Player_1_loser = dis.readInt();
					
			        this.Id_Player_2_loser = dis.readInt();
					
			        this.Id_Country_player1_loser = dis.readInt();
					
			        this.Id_Country_player2_loser = dis.readInt();
					
					this.Prize__Winner = readString(dis);
					
						this.Prize__Loser = readInteger(dis);
					
					this.Tournament_Name = readString(dis);
					
					this.Date = readDate(dis);
					
			        this.Id_team_Loser = dis.readInt();
					
			        this.Id_team_Winner = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.DateKey);
					
					// int
				
		            	dos.writeInt(this.Id_gender);
					
					// int
				
		            	dos.writeInt(this.Id_type_match);
					
					// int
				
		            	dos.writeInt(this.Id_tr_name);
					
					// int
				
		            	dos.writeInt(this.Id_Fact);
					
					// String
				
						writeString(this.Score,dos);
					
					// Integer
				
						writeInteger(this.Points__Loser,dos);
					
					// Integer
				
						writeInteger(this.Points__Winner,dos);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player1_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player2_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Player_1_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Player_2_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Player_1_loser);
					
					// int
				
		            	dos.writeInt(this.Id_Player_2_loser);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player1_loser);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player2_loser);
					
					// String
				
						writeString(this.Prize__Winner,dos);
					
					// Integer
				
						writeInteger(this.Prize__Loser,dos);
					
					// String
				
						writeString(this.Tournament_Name,dos);
					
					// java.util.Date
				
						writeDate(this.Date,dos);
					
					// int
				
		            	dos.writeInt(this.Id_team_Loser);
					
					// int
				
		            	dos.writeInt(this.Id_team_Winner);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.DateKey);
					
					// int
				
		            	dos.writeInt(this.Id_gender);
					
					// int
				
		            	dos.writeInt(this.Id_type_match);
					
					// int
				
		            	dos.writeInt(this.Id_tr_name);
					
					// int
				
		            	dos.writeInt(this.Id_Fact);
					
					// String
				
						writeString(this.Score,dos);
					
					// Integer
				
						writeInteger(this.Points__Loser,dos);
					
					// Integer
				
						writeInteger(this.Points__Winner,dos);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player1_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player2_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Player_1_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Player_2_winner);
					
					// int
				
		            	dos.writeInt(this.Id_Player_1_loser);
					
					// int
				
		            	dos.writeInt(this.Id_Player_2_loser);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player1_loser);
					
					// int
				
		            	dos.writeInt(this.Id_Country_player2_loser);
					
					// String
				
						writeString(this.Prize__Winner,dos);
					
					// Integer
				
						writeInteger(this.Prize__Loser,dos);
					
					// String
				
						writeString(this.Tournament_Name,dos);
					
					// java.util.Date
				
						writeDate(this.Date,dos);
					
					// int
				
		            	dos.writeInt(this.Id_team_Loser);
					
					// int
				
		            	dos.writeInt(this.Id_team_Winner);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("DateKey="+String.valueOf(DateKey));
		sb.append(",Id_gender="+String.valueOf(Id_gender));
		sb.append(",Id_type_match="+String.valueOf(Id_type_match));
		sb.append(",Id_tr_name="+String.valueOf(Id_tr_name));
		sb.append(",Id_Fact="+String.valueOf(Id_Fact));
		sb.append(",Score="+Score);
		sb.append(",Points__Loser="+String.valueOf(Points__Loser));
		sb.append(",Points__Winner="+String.valueOf(Points__Winner));
		sb.append(",Id_Country_player1_winner="+String.valueOf(Id_Country_player1_winner));
		sb.append(",Id_Country_player2_winner="+String.valueOf(Id_Country_player2_winner));
		sb.append(",Id_Player_1_winner="+String.valueOf(Id_Player_1_winner));
		sb.append(",Id_Player_2_winner="+String.valueOf(Id_Player_2_winner));
		sb.append(",Id_Player_1_loser="+String.valueOf(Id_Player_1_loser));
		sb.append(",Id_Player_2_loser="+String.valueOf(Id_Player_2_loser));
		sb.append(",Id_Country_player1_loser="+String.valueOf(Id_Country_player1_loser));
		sb.append(",Id_Country_player2_loser="+String.valueOf(Id_Country_player2_loser));
		sb.append(",Prize__Winner="+Prize__Winner);
		sb.append(",Prize__Loser="+String.valueOf(Prize__Loser));
		sb.append(",Tournament_Name="+Tournament_Name);
		sb.append(",Date="+String.valueOf(Date));
		sb.append(",Id_team_Loser="+String.valueOf(Id_team_Loser));
		sb.append(",Id_team_Winner="+String.valueOf(Id_team_Winner));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(out1Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Id_Fact, other.Id_Fact);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];

	
			    public String Match_type;

				public String getMatch_type () {
					return this.Match_type;
				}
				
			    public String Team_1;

				public String getTeam_1 () {
					return this.Team_1;
				}
				
			    public String Team_2;

				public String getTeam_2 () {
					return this.Team_2;
				}
				
			    public String Winner;

				public String getWinner () {
					return this.Winner;
				}
				
			    public String Loser;

				public String getLoser () {
					return this.Loser;
				}
				
			    public String Score;

				public String getScore () {
					return this.Score;
				}
				
			    public Integer Points__Winner;

				public Integer getPoints__Winner () {
					return this.Points__Winner;
				}
				
			    public String Prize__Winner;

				public String getPrize__Winner () {
					return this.Prize__Winner;
				}
				
			    public Integer Points__Loser;

				public Integer getPoints__Loser () {
					return this.Points__Loser;
				}
				
			    public Integer Prize__Loser;

				public Integer getPrize__Loser () {
					return this.Prize__Loser;
				}
				
			    public java.util.Date Date;

				public java.util.Date getDate () {
					return this.Date;
				}
				
			    public String Tournament_Name;

				public String getTournament_Name () {
					return this.Tournament_Name;
				}
				
			    public String Gender;

				public String getGender () {
					return this.Gender;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Match_type = readString(dis);
					
					this.Team_1 = readString(dis);
					
					this.Team_2 = readString(dis);
					
					this.Winner = readString(dis);
					
					this.Loser = readString(dis);
					
					this.Score = readString(dis);
					
						this.Points__Winner = readInteger(dis);
					
					this.Prize__Winner = readString(dis);
					
						this.Points__Loser = readInteger(dis);
					
						this.Prize__Loser = readInteger(dis);
					
					this.Date = readDate(dis);
					
					this.Tournament_Name = readString(dis);
					
					this.Gender = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Match_type = readString(dis);
					
					this.Team_1 = readString(dis);
					
					this.Team_2 = readString(dis);
					
					this.Winner = readString(dis);
					
					this.Loser = readString(dis);
					
					this.Score = readString(dis);
					
						this.Points__Winner = readInteger(dis);
					
					this.Prize__Winner = readString(dis);
					
						this.Points__Loser = readInteger(dis);
					
						this.Prize__Loser = readInteger(dis);
					
					this.Date = readDate(dis);
					
					this.Tournament_Name = readString(dis);
					
					this.Gender = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Match_type,dos);
					
					// String
				
						writeString(this.Team_1,dos);
					
					// String
				
						writeString(this.Team_2,dos);
					
					// String
				
						writeString(this.Winner,dos);
					
					// String
				
						writeString(this.Loser,dos);
					
					// String
				
						writeString(this.Score,dos);
					
					// Integer
				
						writeInteger(this.Points__Winner,dos);
					
					// String
				
						writeString(this.Prize__Winner,dos);
					
					// Integer
				
						writeInteger(this.Points__Loser,dos);
					
					// Integer
				
						writeInteger(this.Prize__Loser,dos);
					
					// java.util.Date
				
						writeDate(this.Date,dos);
					
					// String
				
						writeString(this.Tournament_Name,dos);
					
					// String
				
						writeString(this.Gender,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Match_type,dos);
					
					// String
				
						writeString(this.Team_1,dos);
					
					// String
				
						writeString(this.Team_2,dos);
					
					// String
				
						writeString(this.Winner,dos);
					
					// String
				
						writeString(this.Loser,dos);
					
					// String
				
						writeString(this.Score,dos);
					
					// Integer
				
						writeInteger(this.Points__Winner,dos);
					
					// String
				
						writeString(this.Prize__Winner,dos);
					
					// Integer
				
						writeInteger(this.Points__Loser,dos);
					
					// Integer
				
						writeInteger(this.Prize__Loser,dos);
					
					// java.util.Date
				
						writeDate(this.Date,dos);
					
					// String
				
						writeString(this.Tournament_Name,dos);
					
					// String
				
						writeString(this.Gender,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Match_type="+Match_type);
		sb.append(",Team_1="+Team_1);
		sb.append(",Team_2="+Team_2);
		sb.append(",Winner="+Winner);
		sb.append(",Loser="+Loser);
		sb.append(",Score="+Score);
		sb.append(",Points__Winner="+String.valueOf(Points__Winner));
		sb.append(",Prize__Winner="+Prize__Winner);
		sb.append(",Points__Loser="+String.valueOf(Points__Loser));
		sb.append(",Prize__Loser="+String.valueOf(Prize__Loser));
		sb.append(",Date="+String.valueOf(Date));
		sb.append(",Tournament_Name="+Tournament_Name);
		sb.append(",Gender="+Gender);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}

public static class after_tDBInput_1Struct implements routines.system.IPersistableRow<after_tDBInput_1Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];

	
			    public String Match_type;

				public String getMatch_type () {
					return this.Match_type;
				}
				
			    public String Team_1;

				public String getTeam_1 () {
					return this.Team_1;
				}
				
			    public String Team_2;

				public String getTeam_2 () {
					return this.Team_2;
				}
				
			    public String Winner;

				public String getWinner () {
					return this.Winner;
				}
				
			    public String Loser;

				public String getLoser () {
					return this.Loser;
				}
				
			    public String Score;

				public String getScore () {
					return this.Score;
				}
				
			    public Integer Points__Winner;

				public Integer getPoints__Winner () {
					return this.Points__Winner;
				}
				
			    public String Prize__Winner;

				public String getPrize__Winner () {
					return this.Prize__Winner;
				}
				
			    public Integer Points__Loser;

				public Integer getPoints__Loser () {
					return this.Points__Loser;
				}
				
			    public Integer Prize__Loser;

				public Integer getPrize__Loser () {
					return this.Prize__Loser;
				}
				
			    public java.util.Date Date;

				public java.util.Date getDate () {
					return this.Date;
				}
				
			    public String Tournament_Name;

				public String getTournament_Name () {
					return this.Tournament_Name;
				}
				
			    public String Gender;

				public String getGender () {
					return this.Gender;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }
	private Integer readInteger(ObjectInputStream dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
	    	intReturn = dis.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}

	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Match_type = readString(dis);
					
					this.Team_1 = readString(dis);
					
					this.Team_2 = readString(dis);
					
					this.Winner = readString(dis);
					
					this.Loser = readString(dis);
					
					this.Score = readString(dis);
					
						this.Points__Winner = readInteger(dis);
					
					this.Prize__Winner = readString(dis);
					
						this.Points__Loser = readInteger(dis);
					
						this.Prize__Loser = readInteger(dis);
					
					this.Date = readDate(dis);
					
					this.Tournament_Name = readString(dis);
					
					this.Gender = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Match_type = readString(dis);
					
					this.Team_1 = readString(dis);
					
					this.Team_2 = readString(dis);
					
					this.Winner = readString(dis);
					
					this.Loser = readString(dis);
					
					this.Score = readString(dis);
					
						this.Points__Winner = readInteger(dis);
					
					this.Prize__Winner = readString(dis);
					
						this.Points__Loser = readInteger(dis);
					
						this.Prize__Loser = readInteger(dis);
					
					this.Date = readDate(dis);
					
					this.Tournament_Name = readString(dis);
					
					this.Gender = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Match_type,dos);
					
					// String
				
						writeString(this.Team_1,dos);
					
					// String
				
						writeString(this.Team_2,dos);
					
					// String
				
						writeString(this.Winner,dos);
					
					// String
				
						writeString(this.Loser,dos);
					
					// String
				
						writeString(this.Score,dos);
					
					// Integer
				
						writeInteger(this.Points__Winner,dos);
					
					// String
				
						writeString(this.Prize__Winner,dos);
					
					// Integer
				
						writeInteger(this.Points__Loser,dos);
					
					// Integer
				
						writeInteger(this.Prize__Loser,dos);
					
					// java.util.Date
				
						writeDate(this.Date,dos);
					
					// String
				
						writeString(this.Tournament_Name,dos);
					
					// String
				
						writeString(this.Gender,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Match_type,dos);
					
					// String
				
						writeString(this.Team_1,dos);
					
					// String
				
						writeString(this.Team_2,dos);
					
					// String
				
						writeString(this.Winner,dos);
					
					// String
				
						writeString(this.Loser,dos);
					
					// String
				
						writeString(this.Score,dos);
					
					// Integer
				
						writeInteger(this.Points__Winner,dos);
					
					// String
				
						writeString(this.Prize__Winner,dos);
					
					// Integer
				
						writeInteger(this.Points__Loser,dos);
					
					// Integer
				
						writeInteger(this.Prize__Loser,dos);
					
					// java.util.Date
				
						writeDate(this.Date,dos);
					
					// String
				
						writeString(this.Tournament_Name,dos);
					
					// String
				
						writeString(this.Gender,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Match_type="+Match_type);
		sb.append(",Team_1="+Team_1);
		sb.append(",Team_2="+Team_2);
		sb.append(",Winner="+Winner);
		sb.append(",Loser="+Loser);
		sb.append(",Score="+Score);
		sb.append(",Points__Winner="+String.valueOf(Points__Winner));
		sb.append(",Prize__Winner="+Prize__Winner);
		sb.append(",Points__Loser="+String.valueOf(Points__Loser));
		sb.append(",Prize__Loser="+String.valueOf(Prize__Loser));
		sb.append(",Date="+String.valueOf(Date));
		sb.append(",Tournament_Name="+Tournament_Name);
		sb.append(",Gender="+Gender);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(after_tDBInput_1Struct other) {

		int returnValue = -1;
		
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;


		tDBInput_3Process(globalMap);
		tDBInput_5Process(globalMap);
		tDBInput_7Process(globalMap);
		tDBInput_8Process(globalMap);
		tDBInput_9Process(globalMap);
		tDBInput_6Process(globalMap);

		row1Struct row1 = new row1Struct();
out1Struct out1 = new out1Struct();





	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"out1");
					}
				
		int tos_count_tDBOutput_1 = 0;
		






int nb_line_tDBOutput_1 = 0;
int nb_line_update_tDBOutput_1 = 0;
int nb_line_inserted_tDBOutput_1 = 0;
int nb_line_deleted_tDBOutput_1 = 0;
int nb_line_rejected_tDBOutput_1 = 0;

int deletedCount_tDBOutput_1=0;
int updatedCount_tDBOutput_1=0;
int insertedCount_tDBOutput_1=0;
int rowsToCommitCount_tDBOutput_1=0;
int rejectedCount_tDBOutput_1=0;

String tableName_tDBOutput_1 = "Fact_Performance";
boolean whetherReject_tDBOutput_1 = false;

java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
long date_tDBOutput_1;

java.sql.Connection conn_tDBOutput_1 = null;
		
        String properties_tDBOutput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
            properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
        }else {
            if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
                properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
            }

            if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
                properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
            }
        }

        String url_tDBOutput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBOutput_1;
		
		String driverClass_tDBOutput_1 = "com.mysql.cj.jdbc.Driver";
		
		String dbUser_tDBOutput_1 = "root";
		

		 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:enZ/j4AgxnvSQPXWfeQXBhjWUNt5TeboqmmSNQ==");

		String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
		java.lang.Class.forName(driverClass_tDBOutput_1);
		
		conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1, dbPwd_tDBOutput_1);
		
	
	resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
        conn_tDBOutput_1.setAutoCommit(false);
        int commitEvery_tDBOutput_1 = 10000;
        int commitCounter_tDBOutput_1 = 0;

int count_tDBOutput_1=0;
    	
                                java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
                                    java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables("DW_padel_analytics", null, null, new String[]{"TABLE"});
                                boolean whetherExist_tDBOutput_1 = false;
                                while(rsTable_tDBOutput_1.next()) {
                                    String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
                                    if(table_tDBOutput_1.equalsIgnoreCase("Fact_Performance")) {
                                        whetherExist_tDBOutput_1 = true;
                                        break;
                                    }
                                }
                                if(whetherExist_tDBOutput_1) {
                                    try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
                                        stmtDrop_tDBOutput_1.execute("DROP TABLE `" + tableName_tDBOutput_1 + "`" );
                                    }
                                }
                                try(java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
                                    stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1 + "`(`DateKey` INT(10)   not null ,`Id_gender` INT(10)   not null ,`Id_type_match` INT(10)   not null ,`Id_tr_name` INT(10)   not null ,`Id_Fact` INT(10)   not null ,`Score` VARCHAR(255)  ,`Points__Loser` INT(10)  ,`Points__Winner` INT(10)  ,`Id_Country_player1_winner` INT(10)   not null ,`Id_Country_player2_winner` INT(10)   not null ,`Id_Player_1_winner` INT(10)   not null ,`Id_Player_2_winner` INT(10)   not null ,`Id_Player_1_loser` INT(10)   not null ,`Id_Player_2_loser` INT(10)   not null ,`Id_Country_player1_loser` INT(10)   not null ,`Id_Country_player2_loser` INT(10)   not null ,`Prize__Winner` VARCHAR(255)  ,`Prize__Loser` INT(10)  ,`Tournament_Name` VARCHAR(255)  ,`Date` DATETIME ,`Id_team_Loser` INT(10)   not null ,`Id_team_Winner` INT(10)   not null ,primary key(`Id_Fact`))");
                                }

				String insert_tDBOutput_1 = "INSERT INTO `" + "Fact_Performance" + "` (`DateKey`,`Id_gender`,`Id_type_match`,`Id_tr_name`,`Id_Fact`,`Score`,`Points__Loser`,`Points__Winner`,`Id_Country_player1_winner`,`Id_Country_player2_winner`,`Id_Player_1_winner`,`Id_Player_2_winner`,`Id_Player_1_loser`,`Id_Player_2_loser`,`Id_Country_player1_loser`,`Id_Country_player2_loser`,`Prize__Winner`,`Prize__Loser`,`Tournament_Name`,`Date`,`Id_team_Loser`,`Id_team_Winner`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		        int batchSize_tDBOutput_1 = 100;
	   			int batchSizeCounter_tDBOutput_1=0;
		            
		        java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
		        resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);


 



/**
 * [tDBOutput_1 begin ] stop
 */



	
	/**
	 * [tMap_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tMap_1", false);
		start_Hash.put("tMap_1", System.currentTimeMillis());
		
	
	currentComponent="tMap_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row1");
					}
				
		int tos_count_tMap_1 = 0;
		




// ###############################
// # Lookup's keys initialization
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) 
					globalMap.get( "tHash_Lookup_row2" ))
					;					
					
	

row2Struct row2HashKey = new row2Struct();
row2Struct row2Default = new row2Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) 
					globalMap.get( "tHash_Lookup_row4" ))
					;					
					
	

row4Struct row4HashKey = new row4Struct();
row4Struct row4Default = new row4Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) 
					globalMap.get( "tHash_Lookup_row5" ))
					;					
					
	

row5Struct row5HashKey = new row5Struct();
row5Struct row5Default = new row5Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) 
					globalMap.get( "tHash_Lookup_row6" ))
					;					
					
	

row6Struct row6HashKey = new row6Struct();
row6Struct row6Default = new row6Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
					globalMap.get( "tHash_Lookup_row3" ))
					;					
					
	

row3Struct row3HashKey = new row3Struct();
row3Struct row3Default = new row3Struct();
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) 
					globalMap.get( "tHash_Lookup_row7" ))
					;					
					
	

row7Struct row7HashKey = new row7Struct();
row7Struct row7Default = new row7Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
out1Struct out1_tmp = new out1Struct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
 */



	
	/**
	 * [tDBInput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_1", false);
		start_Hash.put("tDBInput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_1";

	
		int tos_count_tDBInput_1 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_1 = java.util.Calendar.getInstance();
		    calendar_tDBInput_1.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_1 = calendar_tDBInput_1.getTime();
		    int nb_line_tDBInput_1 = 0;
		    java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "root";
				
				 
	final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:YbUryekqkhGDHxfdvZcvAlsGdWNGwo575mPvhw==");
				
				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;
				
        String properties_tDBInput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_1 == null || properties_tDBInput_1.trim().length() == 0) {
            properties_tDBInput_1 = "";
        }
        String url_tDBInput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "padel_analytics" + "?" + properties_tDBInput_1;
				
				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1,dbUser_tDBInput_1,dbPwd_tDBInput_1);
		        
		    
			java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

		    String dbquery_tDBInput_1 = "SELECT \n  `saavrelll`.`Match_type`, \n  `saavrelll`.`Team_1`, \n  `saavrelll`.`Team_2`, \n  `saavrelll`.`Winner`, \n  `saav"
+"relll`.`Loser`, \n  `saavrelll`.`Score`, \n  `saavrelll`.`Points__Winner`, \n  `saavrelll`.`Prize__Winner`, \n  `saavrelll`."
+"`Points__Loser`, \n  `saavrelll`.`Prize__Loser`, \n  `saavrelll`.`Date`, \n  `saavrelll`.`Tournament_Name`, \n  `saavrelll`."
+"`Gender`\nFROM `saavrelll`";
		    

            	globalMap.put("tDBInput_1_QUERY",dbquery_tDBInput_1);
		    java.sql.ResultSet rs_tDBInput_1 = null;

		    try {
		    	rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
		    	int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

		    String tmpContent_tDBInput_1 = null;
		    
		    
		    while (rs_tDBInput_1.next()) {
		        nb_line_tDBInput_1++;
		        
							if(colQtyInRs_tDBInput_1 < 1) {
								row1.Match_type = null;
							} else {
	                         		
        	row1.Match_type = routines.system.JDBCUtil.getString(rs_tDBInput_1, 1, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 2) {
								row1.Team_1 = null;
							} else {
	                         		
        	row1.Team_1 = routines.system.JDBCUtil.getString(rs_tDBInput_1, 2, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 3) {
								row1.Team_2 = null;
							} else {
	                         		
        	row1.Team_2 = routines.system.JDBCUtil.getString(rs_tDBInput_1, 3, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 4) {
								row1.Winner = null;
							} else {
	                         		
        	row1.Winner = routines.system.JDBCUtil.getString(rs_tDBInput_1, 4, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 5) {
								row1.Loser = null;
							} else {
	                         		
        	row1.Loser = routines.system.JDBCUtil.getString(rs_tDBInput_1, 5, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 6) {
								row1.Score = null;
							} else {
	                         		
        	row1.Score = routines.system.JDBCUtil.getString(rs_tDBInput_1, 6, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 7) {
								row1.Points__Winner = null;
							} else {
		                          
            row1.Points__Winner = rs_tDBInput_1.getInt(7);
            if(rs_tDBInput_1.wasNull()){
                    row1.Points__Winner = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 8) {
								row1.Prize__Winner = null;
							} else {
	                         		
        	row1.Prize__Winner = routines.system.JDBCUtil.getString(rs_tDBInput_1, 8, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 9) {
								row1.Points__Loser = null;
							} else {
		                          
            row1.Points__Loser = rs_tDBInput_1.getInt(9);
            if(rs_tDBInput_1.wasNull()){
                    row1.Points__Loser = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 10) {
								row1.Prize__Loser = null;
							} else {
		                          
            row1.Prize__Loser = rs_tDBInput_1.getInt(10);
            if(rs_tDBInput_1.wasNull()){
                    row1.Prize__Loser = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 11) {
								row1.Date = null;
							} else {
										
				if(rs_tDBInput_1.getString(11) != null) {
					String dateString_tDBInput_1 = rs_tDBInput_1.getString(11);
					if (!("0000-00-00").equals(dateString_tDBInput_1) && !("0000-00-00 00:00:00").equals(dateString_tDBInput_1)) {
						row1.Date = rs_tDBInput_1.getTimestamp(11);
					} else {
						row1.Date = (java.util.Date) year0_tDBInput_1.clone();
					}
				} else {
					row1.Date =  null;
				}
		                    }
							if(colQtyInRs_tDBInput_1 < 12) {
								row1.Tournament_Name = null;
							} else {
	                         		
        	row1.Tournament_Name = routines.system.JDBCUtil.getString(rs_tDBInput_1, 12, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 13) {
								row1.Gender = null;
							} else {
	                         		
        	row1.Gender = routines.system.JDBCUtil.getString(rs_tDBInput_1, 13, false);
		                    }
					

 



/**
 * [tDBInput_1 begin ] stop
 */
	
	/**
	 * [tDBInput_1 main ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 


	tos_count_tDBInput_1++;

/**
 * [tDBInput_1 main ] stop
 */
	
	/**
	 * [tDBInput_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 process_data_begin ] stop
 */

	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row1"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_1 = false;
		  boolean mainRowRejected_tMap_1 = false;
            				    								  
		

				///////////////////////////////////////////////
				// Starting Lookup Table "row2" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow2 = false;
       		  	    	
       		  	    	
 							row2Struct row2ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row2HashKey.DateValue = row1.Date  == null ? null : new java.util.Date(row1.Date .getTime());
                        		    		

								
		                        	row2HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row2.lookup( row2HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row2.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2' and it contains more one result from keys :  row2.DateValue = '" + row2HashKey.DateValue + "'");
								} // G 071
							

							row2Struct row2 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row2Struct fromLookup_row2 = null;
							row2 = row2Default;
										 
							
								 
							
							
								if (tHash_Lookup_row2 !=null && tHash_Lookup_row2.hasNext()) { // G 099
								
							
								
								fromLookup_row2 = tHash_Lookup_row2.next();

							
							
								} // G 099
							
							

							if(fromLookup_row2 != null) {
								row2 = fromLookup_row2;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row4" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow4 = false;
       		  	    	
       		  	    	
 							row4Struct row4ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row4HashKey.Gender = row1.Gender ;
                        		    		

								
		                        	row4HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row4.lookup( row4HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row4.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4' and it contains more one result from keys :  row4.Gender = '" + row4HashKey.Gender + "'");
								} // G 071
							

							row4Struct row4 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row4Struct fromLookup_row4 = null;
							row4 = row4Default;
										 
							
								 
							
							
								if (tHash_Lookup_row4 !=null && tHash_Lookup_row4.hasNext()) { // G 099
								
							
								
								fromLookup_row4 = tHash_Lookup_row4.next();

							
							
								} // G 099
							
							

							if(fromLookup_row4 != null) {
								row4 = fromLookup_row4;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row5" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow5 = false;
       		  	    	
       		  	    	
 							row5Struct row5ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row5HashKey.Match_type = row1.Match_type;
                        		    		

								
		                        	row5HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row5.lookup( row5HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row5.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5' and it contains more one result from keys :  row5.Match_type = '" + row5HashKey.Match_type + "'");
								} // G 071
							

							row5Struct row5 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row5Struct fromLookup_row5 = null;
							row5 = row5Default;
										 
							
								 
							
							
								if (tHash_Lookup_row5 !=null && tHash_Lookup_row5.hasNext()) { // G 099
								
							
								
								fromLookup_row5 = tHash_Lookup_row5.next();

							
							
								} // G 099
							
							

							if(fromLookup_row5 != null) {
								row5 = fromLookup_row5;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row6" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow6 = false;
       		  	    	
       		  	    	
 							row6Struct row6ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row6HashKey.Tournament_Name = row1.Tournament_Name ;
                        		    		

								
		                        	row6HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row6.lookup( row6HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row6.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row6 != null && tHash_Lookup_row6.getCount(row6HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row6' and it contains more one result from keys :  row6.Tournament_Name = '" + row6HashKey.Tournament_Name + "'");
								} // G 071
							

							row6Struct row6 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row6Struct fromLookup_row6 = null;
							row6 = row6Default;
										 
							
								 
							
							
								if (tHash_Lookup_row6 !=null && tHash_Lookup_row6.hasNext()) { // G 099
								
							
								
								fromLookup_row6 = tHash_Lookup_row6.next();

							
							
								} // G 099
							
							

							if(fromLookup_row6 != null) {
								row6 = fromLookup_row6;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row3" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow3 = false;
       		  	    	
       		  	    	
 							row3Struct row3ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row3HashKey.Team_1 = row1.Winner ;
                        		    		

								
		                        	row3HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row3.lookup( row3HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row3.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3' and it contains more one result from keys :  row3.Team_1 = '" + row3HashKey.Team_1 + "'");
								} // G 071
							

							row3Struct row3 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row3Struct fromLookup_row3 = null;
							row3 = row3Default;
										 
							
								 
							
							
								if (tHash_Lookup_row3 !=null && tHash_Lookup_row3.hasNext()) { // G 099
								
							
								
								fromLookup_row3 = tHash_Lookup_row3.next();

							
							
								} // G 099
							
							

							if(fromLookup_row3 != null) {
								row3 = fromLookup_row3;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	           	
	            	
	            	
	            

				///////////////////////////////////////////////
				// Starting Lookup Table "row7" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow7 = false;
       		  	    	
       		  	    	
 							row7Struct row7ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row7HashKey.Team_1 = row1.Loser;
                        		    		

								
		                        	row7HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row7.lookup( row7HashKey );

	  							

	  							

 								
								  
								  if(!tHash_Lookup_row7.hasNext()) { // G_TM_M_090

  								
		  				
	  								
			  							rejectedInnerJoin_tMap_1 = true;
	  								
						
									
  									  		
 								
								  
								  } // G_TM_M_090

  								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row7 != null && tHash_Lookup_row7.getCount(row7HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row7' and it contains more one result from keys :  row7.Team_1 = '" + row7HashKey.Team_1 + "'");
								} // G 071
							

							row7Struct row7 = null;
                    		  	 
							   
                    		  	 
	       		  	    	row7Struct fromLookup_row7 = null;
							row7 = row7Default;
										 
							
								 
							
							
								if (tHash_Lookup_row7 !=null && tHash_Lookup_row7.hasNext()) { // G 099
								
							
								
								fromLookup_row7 = tHash_Lookup_row7.next();

							
							
								} // G 099
							
							

							if(fromLookup_row7 != null) {
								row7 = fromLookup_row7;
							}
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

out1 = null;

if(!rejectedInnerJoin_tMap_1 ) {

// # Output table : 'out1'
out1_tmp.DateKey = row2.DateKey ;
out1_tmp.Id_gender = row4.Id_gender ;
out1_tmp.Id_type_match = row5.Id_type_match ;
out1_tmp.Id_tr_name = row6.Id_tr_name ;
out1_tmp.Id_Fact = Numeric.sequence("s1",1,1) ;
out1_tmp.Score = row1.Score ;
out1_tmp.Points__Loser = row1.Points__Loser ;
out1_tmp.Points__Winner = row1.Points__Winner ;
out1_tmp.Id_Country_player1_winner = row3.Id_Country_player1 ;
out1_tmp.Id_Country_player2_winner = row3.Id_Country_player2 ;
out1_tmp.Id_Player_1_winner = row3.Id_Player_1 ;
out1_tmp.Id_Player_2_winner = row3.Id_Player_2 ;
out1_tmp.Id_Player_1_loser = row7.Id_Player_1 ;
out1_tmp.Id_Player_2_loser = row7.Id_Player_2 ;
out1_tmp.Id_Country_player1_loser = row7.Id_Country_player1 ;
out1_tmp.Id_Country_player2_loser = row7.Id_Country_player2 ;
out1_tmp.Prize__Winner = row1.Prize__Winner ;
out1_tmp.Prize__Loser = row1.Prize__Loser ;
out1_tmp.Tournament_Name = row1.Tournament_Name ;
out1_tmp.Date = row1.Date ;
out1_tmp.Id_team_Loser = row7.Id_team ;
out1_tmp.Id_team_Winner = row3.Id_team ;
out1 = out1_tmp;
}  // closing inner join bracket (2)
// ###############################

} // end of Var scope

rejectedInnerJoin_tMap_1 = false;










 


	tos_count_tMap_1++;

/**
 * [tMap_1 main ] stop
 */
	
	/**
	 * [tMap_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_begin ] stop
 */
// Start of branch "out1"
if(out1 != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"out1"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;
                            pstmt_tDBOutput_1.setInt(1, out1.DateKey);

                            pstmt_tDBOutput_1.setInt(2, out1.Id_gender);

                            pstmt_tDBOutput_1.setInt(3, out1.Id_type_match);

                            pstmt_tDBOutput_1.setInt(4, out1.Id_tr_name);

                            pstmt_tDBOutput_1.setInt(5, out1.Id_Fact);

                            if(out1.Score == null) {
pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(6, out1.Score);
}

                            if(out1.Points__Loser == null) {
pstmt_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(7, out1.Points__Loser);
}

                            if(out1.Points__Winner == null) {
pstmt_tDBOutput_1.setNull(8, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(8, out1.Points__Winner);
}

                            pstmt_tDBOutput_1.setInt(9, out1.Id_Country_player1_winner);

                            pstmt_tDBOutput_1.setInt(10, out1.Id_Country_player2_winner);

                            pstmt_tDBOutput_1.setInt(11, out1.Id_Player_1_winner);

                            pstmt_tDBOutput_1.setInt(12, out1.Id_Player_2_winner);

                            pstmt_tDBOutput_1.setInt(13, out1.Id_Player_1_loser);

                            pstmt_tDBOutput_1.setInt(14, out1.Id_Player_2_loser);

                            pstmt_tDBOutput_1.setInt(15, out1.Id_Country_player1_loser);

                            pstmt_tDBOutput_1.setInt(16, out1.Id_Country_player2_loser);

                            if(out1.Prize__Winner == null) {
pstmt_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(17, out1.Prize__Winner);
}

                            if(out1.Prize__Loser == null) {
pstmt_tDBOutput_1.setNull(18, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(18, out1.Prize__Loser);
}

                            if(out1.Tournament_Name == null) {
pstmt_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(19, out1.Tournament_Name);
}

                            if(out1.Date != null) {
date_tDBOutput_1 = out1.Date.getTime();
if(date_tDBOutput_1 < year1_tDBOutput_1 || date_tDBOutput_1 >= year10000_tDBOutput_1) {
pstmt_tDBOutput_1.setString(20, "0000-00-00 00:00:00");
} else {pstmt_tDBOutput_1.setTimestamp(20, new java.sql.Timestamp(date_tDBOutput_1));
}
} else {
pstmt_tDBOutput_1.setNull(20, java.sql.Types.DATE);
}

                            pstmt_tDBOutput_1.setInt(21, out1.Id_team_Loser);

                            pstmt_tDBOutput_1.setInt(22, out1.Id_team_Winner);

                    pstmt_tDBOutput_1.addBatch();
                    nb_line_tDBOutput_1++;

						
                      batchSizeCounter_tDBOutput_1++;
                if ( batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
                try {
                        int countSum_tDBOutput_1 = 0;
                        for(int countEach_tDBOutput_1: pstmt_tDBOutput_1.executeBatch()) {
                            countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0 : 1);
                        }
                        rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
                        insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
                }catch (java.sql.BatchUpdateException e){
globalMap.put("tDBOutput_1_ERROR_MESSAGE",e.getMessage());
                    int countSum_tDBOutput_1 = 0;
                    for(int countEach_tDBOutput_1: e.getUpdateCounts()) {
                        countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
                    }
                    rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
                    insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
                    System.err.println(e.getMessage());
                }

                batchSizeCounter_tDBOutput_1 = 0;
            }
                commitCounter_tDBOutput_1++;

                if(commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

                try {
                        int countSum_tDBOutput_1 = 0;
                        for(int countEach_tDBOutput_1: pstmt_tDBOutput_1.executeBatch()) {
                            countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : 1);
                        }
                        rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
                        insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
                }catch (java.sql.BatchUpdateException e){
globalMap.put("tDBOutput_1_ERROR_MESSAGE",e.getMessage());
                    int countSum_tDBOutput_1 = 0;
                    for(int countEach_tDBOutput_1: e.getUpdateCounts()) {
                        countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
                    }
                    rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
                    insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
                    System.err.println(e.getMessage());

                }
                    if(rowsToCommitCount_tDBOutput_1 != 0){
                    }
                    conn_tDBOutput_1.commit();
                    if(rowsToCommitCount_tDBOutput_1 != 0){
                        rowsToCommitCount_tDBOutput_1 = 0;
                    }
                    commitCounter_tDBOutput_1=0;

                }


 


	tos_count_tDBOutput_1++;

/**
 * [tDBOutput_1 main ] stop
 */
	
	/**
	 * [tDBOutput_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	

 



/**
 * [tDBOutput_1 process_data_begin ] stop
 */
	
	/**
	 * [tDBOutput_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	

 



/**
 * [tDBOutput_1 process_data_end ] stop
 */

} // End of branch "out1"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_1 end ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

	}
}finally{
	if (rs_tDBInput_1 != null) {
		rs_tDBInput_1.close();
	}
	if (stmt_tDBInput_1 != null) {
		stmt_tDBInput_1.close();
	}
		if(conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {
			
			conn_tDBInput_1.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_1_NB_LINE",nb_line_tDBInput_1);
		


 

ok_Hash.put("tDBInput_1", true);
end_Hash.put("tDBInput_1", System.currentTimeMillis());




/**
 * [tDBInput_1 end ] stop
 */

	
	/**
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row2 != null) {
						tHash_Lookup_row2.endGet();
					}
					globalMap.remove( "tHash_Lookup_row2" );

					
					
				
					if(tHash_Lookup_row4 != null) {
						tHash_Lookup_row4.endGet();
					}
					globalMap.remove( "tHash_Lookup_row4" );

					
					
				
					if(tHash_Lookup_row5 != null) {
						tHash_Lookup_row5.endGet();
					}
					globalMap.remove( "tHash_Lookup_row5" );

					
					
				
					if(tHash_Lookup_row6 != null) {
						tHash_Lookup_row6.endGet();
					}
					globalMap.remove( "tHash_Lookup_row6" );

					
					
				
					if(tHash_Lookup_row3 != null) {
						tHash_Lookup_row3.endGet();
					}
					globalMap.remove( "tHash_Lookup_row3" );

					
					
				
					if(tHash_Lookup_row7 != null) {
						tHash_Lookup_row7.endGet();
					}
					globalMap.remove( "tHash_Lookup_row7" );

					
					
				
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row1");
			  	}
			  	
 

ok_Hash.put("tMap_1", true);
end_Hash.put("tMap_1", System.currentTimeMillis());




/**
 * [tMap_1 end ] stop
 */

	
	/**
	 * [tDBOutput_1 end ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	



                try {
                		if (batchSizeCounter_tDBOutput_1 != 0) {
							int countSum_tDBOutput_1 = 0;
							
							for(int countEach_tDBOutput_1: pstmt_tDBOutput_1.executeBatch()) {
								countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0 : 1);
							}
							rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
							
	            	    	
	            	    		insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
	            	    	
            	    	}

                }catch (java.sql.BatchUpdateException e){
                    globalMap.put(currentComponent+"_ERROR_MESSAGE",e.getMessage());
                	
                	int countSum_tDBOutput_1 = 0;
					for(int countEach_tDBOutput_1: e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
					
            	    insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
	    	    	
                	System.err.println(e.getMessage());
                	
                }
                batchSizeCounter_tDBOutput_1 = 0;
    		
	

        if(pstmt_tDBOutput_1 != null) {
			
				pstmt_tDBOutput_1.close();
				resourceMap.remove("pstmt_tDBOutput_1");
			
        }
    resourceMap.put("statementClosed_tDBOutput_1", true);
    	if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {
    		
    	}
    	conn_tDBOutput_1.commit();
    	if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {
    		
			rowsToCommitCount_tDBOutput_1 = 0;
    	}
		commitCounter_tDBOutput_1 = 0;
    	
		
    	conn_tDBOutput_1 .close();
    	
    	resourceMap.put("finish_tDBOutput_1", true);
    	

	nb_line_deleted_tDBOutput_1=nb_line_deleted_tDBOutput_1+ deletedCount_tDBOutput_1;
	nb_line_update_tDBOutput_1=nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
	nb_line_inserted_tDBOutput_1=nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
	nb_line_rejected_tDBOutput_1=nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;
	
        globalMap.put("tDBOutput_1_NB_LINE",nb_line_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_UPDATED",nb_line_update_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_INSERTED",nb_line_inserted_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_DELETED",nb_line_deleted_tDBOutput_1);
        globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);
    

	

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"out1");
			  	}
			  	
 

ok_Hash.put("tDBOutput_1", true);
end_Hash.put("tDBOutput_1", System.currentTimeMillis());




/**
 * [tDBOutput_1 end ] stop
 */






				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row2"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row4"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row5"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row6"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row3"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row7"); 
				     			
				try{
					
	
	/**
	 * [tDBInput_1 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 finally ] stop
 */

	
	/**
	 * [tMap_1 finally ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 finally ] stop
 */

	
	/**
	 * [tDBOutput_1 finally ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	



    try {
    if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
                java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
                if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap.remove("pstmt_tDBOutput_1")) != null) {
                    pstmtToClose_tDBOutput_1.close();
                }
    }
    } finally {
        if(resourceMap.get("finish_tDBOutput_1") == null){
            java.sql.Connection ctn_tDBOutput_1 = null;
            if((ctn_tDBOutput_1 = (java.sql.Connection)resourceMap.get("conn_tDBOutput_1")) != null){
                try {
                    ctn_tDBOutput_1.close();
                } catch (java.sql.SQLException sqlEx_tDBOutput_1) {
                    String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :" + sqlEx_tDBOutput_1.getMessage();
                    System.err.println(errorMessage_tDBOutput_1);
                }
            }
        }
    }
 



/**
 * [tDBOutput_1 finally ] stop
 */






				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}
	


public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int DateKey;

				public int getDateKey () {
					return this.DateKey;
				}
				
			    public java.util.Date DateValue;

				public java.util.Date getDateValue () {
					return this.DateValue;
				}
				
			    public Integer Year;

				public Integer getYear () {
					return this.Year;
				}
				
			    public Integer Quarter;

				public Integer getQuarter () {
					return this.Quarter;
				}
				
			    public Integer Month;

				public Integer getMonth () {
					return this.Month;
				}
				
			    public String MonthName;

				public String getMonthName () {
					return this.MonthName;
				}
				
			    public Integer Day;

				public Integer getDay () {
					return this.Day;
				}
				
			    public Integer DayOfWeek;

				public Integer getDayOfWeek () {
					return this.DayOfWeek;
				}
				
			    public String DayOfWeekName;

				public String getDayOfWeekName () {
					return this.DayOfWeekName;
				}
				
			    public Integer WeekOfYear;

				public Integer getWeekOfYear () {
					return this.WeekOfYear;
				}
				
			    public Boolean IsWeekend;

				public Boolean getIsWeekend () {
					return this.IsWeekend;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.DateValue == null) ? 0 : this.DateValue.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row2Struct other = (row2Struct) obj;
		
						if (this.DateValue == null) {
							if (other.DateValue != null)
								return false;
						
						} else if (!this.DateValue.equals(other.DateValue))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row2Struct other) {

		other.DateKey = this.DateKey;
	            other.DateValue = this.DateValue;
	            other.Year = this.Year;
	            other.Quarter = this.Quarter;
	            other.Month = this.Month;
	            other.MonthName = this.MonthName;
	            other.Day = this.Day;
	            other.DayOfWeek = this.DayOfWeek;
	            other.DayOfWeekName = this.DayOfWeekName;
	            other.WeekOfYear = this.WeekOfYear;
	            other.IsWeekend = this.IsWeekend;
	            
	}

	public void copyKeysDataTo(row2Struct other) {

		other.DateValue = this.DateValue;
	            	
	}




	private java.util.Date readDate(ObjectInputStream dis) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(dis.readLong());
		}
		return dateReturn;
	}
	
	private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		java.util.Date dateReturn = null;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			dateReturn = null;
		} else {
	    	dateReturn = new Date(unmarshaller.readLong());
		}
		return dateReturn;
	}

    private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
    }
    
    private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(date1 == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeLong(date1.getTime());
    	}
    }
	private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException{
		Integer intReturn;
        int length = 0;
        length = dis.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = dis.readInt();
		}
		return intReturn;
	}
	
	private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		Integer intReturn;
        int length = 0;
        length = unmarshaller.readByte();
		if (length == -1) {
			intReturn = null;
		} else {
			intReturn = unmarshaller.readInt();
		}
		return intReturn;
	}

	private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(intNum == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeInt(intNum);
    	}
	}
	
	private void writeInteger(Integer intNum, DataOutputStream dos,org.jboss.marshalling.Marshaller marshaller ) throws IOException{
		if(intNum == null) {
			marshaller.writeByte(-1);
		} else {
			marshaller.writeByte(0);
			marshaller.writeInt(intNum);
    	}
	}
	
	private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			dis.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			byte[] byteArray = new byte[length];
			unmarshaller.read(byteArray);
			strReturn = new String(byteArray, utf8Charset);
		}
		return strReturn;
	}
	
	private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
	}

	private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
	}

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.DateValue = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.DateValue = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// java.util.Date
				
						writeDate(this.DateValue,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// java.util.Date
				
						writeDate(this.DateValue,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.DateKey = dis.readInt();
					
						this.Year = readInteger(dis,ois);
					
						this.Quarter = readInteger(dis,ois);
					
						this.Month = readInteger(dis,ois);
					
						this.MonthName = readString(dis,ois);
					
						this.Day = readInteger(dis,ois);
					
						this.DayOfWeek = readInteger(dis,ois);
					
						this.DayOfWeekName = readString(dis,ois);
					
						this.WeekOfYear = readInteger(dis,ois);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.IsWeekend = null;
           				} else {
           			    	this.IsWeekend = dis.readBoolean();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.DateKey = objectIn.readInt();
					
						this.Year = readInteger(dis,objectIn);
					
						this.Quarter = readInteger(dis,objectIn);
					
						this.Month = readInteger(dis,objectIn);
					
						this.MonthName = readString(dis,objectIn);
					
						this.Day = readInteger(dis,objectIn);
					
						this.DayOfWeek = readInteger(dis,objectIn);
					
						this.DayOfWeekName = readString(dis,objectIn);
					
						this.WeekOfYear = readInteger(dis,objectIn);
					
			            length = objectIn.readByte();
           				if (length == -1) {
           	    			this.IsWeekend = null;
           				} else {
           			    	this.IsWeekend = objectIn.readBoolean();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.DateKey);
					
					writeInteger(this.Year, dos, oos);
					
					writeInteger(this.Quarter, dos, oos);
					
					writeInteger(this.Month, dos, oos);
					
						writeString(this.MonthName, dos, oos);
					
					writeInteger(this.Day, dos, oos);
					
					writeInteger(this.DayOfWeek, dos, oos);
					
						writeString(this.DayOfWeekName, dos, oos);
					
					writeInteger(this.WeekOfYear, dos, oos);
					
						if(this.IsWeekend == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeBoolean(this.IsWeekend);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.DateKey);
					
					writeInteger(this.Year, dos, objectOut);
					
					writeInteger(this.Quarter, dos, objectOut);
					
					writeInteger(this.Month, dos, objectOut);
					
						writeString(this.MonthName, dos, objectOut);
					
					writeInteger(this.Day, dos, objectOut);
					
					writeInteger(this.DayOfWeek, dos, objectOut);
					
						writeString(this.DayOfWeekName, dos, objectOut);
					
					writeInteger(this.WeekOfYear, dos, objectOut);
					
						if(this.IsWeekend == null) {
							objectOut.writeByte(-1);
						} else {
							objectOut.writeByte(0);
							objectOut.writeBoolean(this.IsWeekend);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("DateKey="+String.valueOf(DateKey));
		sb.append(",DateValue="+String.valueOf(DateValue));
		sb.append(",Year="+String.valueOf(Year));
		sb.append(",Quarter="+String.valueOf(Quarter));
		sb.append(",Month="+String.valueOf(Month));
		sb.append(",MonthName="+MonthName);
		sb.append(",Day="+String.valueOf(Day));
		sb.append(",DayOfWeek="+String.valueOf(DayOfWeek));
		sb.append(",DayOfWeekName="+DayOfWeekName);
		sb.append(",WeekOfYear="+String.valueOf(WeekOfYear));
		sb.append(",IsWeekend="+String.valueOf(IsWeekend));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row2Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.DateValue, other.DateValue);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_3_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row2Struct row2 = new row2Struct();




	
	/**
	 * [tAdvancedHash_row2 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row2", false);
		start_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row2";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row2");
					}
				
		int tos_count_tAdvancedHash_row2 = 0;
		

			   		// connection name:row2
			   		// source node:tDBInput_3 - inputs:(after_tDBInput_1) outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row3,row7) outputs:(out1)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row2Struct>getLookup(matchingModeEnum_row2);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row2 begin ] stop
 */



	
	/**
	 * [tDBInput_3 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_3", false);
		start_Hash.put("tDBInput_3", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_3";

	
		int tos_count_tDBInput_3 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_3 = java.util.Calendar.getInstance();
		    calendar_tDBInput_3.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_3 = calendar_tDBInput_3.getTime();
		    int nb_line_tDBInput_3 = 0;
		    java.sql.Connection conn_tDBInput_3 = null;
				String driverClass_tDBInput_3 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_3 = java.lang.Class.forName(driverClass_tDBInput_3);
				String dbUser_tDBInput_3 = "root";
				
				 
	final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:pVtBYk4XXMoW+zzYGcsZqhUsX/3znpuGZ0Pvnw==");
				
				String dbPwd_tDBInput_3 = decryptedPassword_tDBInput_3;
				
        String properties_tDBInput_3 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_3 == null || properties_tDBInput_3.trim().length() == 0) {
            properties_tDBInput_3 = "";
        }
        String url_tDBInput_3 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_3;
				
				conn_tDBInput_3 = java.sql.DriverManager.getConnection(url_tDBInput_3,dbUser_tDBInput_3,dbPwd_tDBInput_3);
		        
		    
			java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

		    String dbquery_tDBInput_3 = "SELECT \n  `dim_date`.`DateKey`, \n  `dim_date`.`DateValue`, \n  `dim_date`.`Year`, \n  `dim_date`.`Quarter`, \n  `dim_date`"
+".`Month`, \n  `dim_date`.`MonthName`, \n  `dim_date`.`Day`, \n  `dim_date`.`DayOfWeek`, \n  `dim_date`.`DayOfWeekName`, \n  `"
+"dim_date`.`WeekOfYear`, \n  `dim_date`.`IsWeekend`\nFROM `dim_date`";
		    

            	globalMap.put("tDBInput_3_QUERY",dbquery_tDBInput_3);
		    java.sql.ResultSet rs_tDBInput_3 = null;

		    try {
		    	rs_tDBInput_3 = stmt_tDBInput_3.executeQuery(dbquery_tDBInput_3);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_3 = rs_tDBInput_3.getMetaData();
		    	int colQtyInRs_tDBInput_3 = rsmd_tDBInput_3.getColumnCount();

		    String tmpContent_tDBInput_3 = null;
		    
		    
		    while (rs_tDBInput_3.next()) {
		        nb_line_tDBInput_3++;
		        
							if(colQtyInRs_tDBInput_3 < 1) {
								row2.DateKey = 0;
							} else {
		                          
            row2.DateKey = rs_tDBInput_3.getInt(1);
            if(rs_tDBInput_3.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 2) {
								row2.DateValue = null;
							} else {
										
				if(rs_tDBInput_3.getString(2) != null) {
					String dateString_tDBInput_3 = rs_tDBInput_3.getString(2);
					if (!("0000-00-00").equals(dateString_tDBInput_3) && !("0000-00-00 00:00:00").equals(dateString_tDBInput_3)) {
						row2.DateValue = rs_tDBInput_3.getTimestamp(2);
					} else {
						row2.DateValue = (java.util.Date) year0_tDBInput_3.clone();
					}
				} else {
					row2.DateValue =  null;
				}
		                    }
							if(colQtyInRs_tDBInput_3 < 3) {
								row2.Year = null;
							} else {
		                          
            row2.Year = rs_tDBInput_3.getInt(3);
            if(rs_tDBInput_3.wasNull()){
                    row2.Year = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 4) {
								row2.Quarter = null;
							} else {
		                          
            row2.Quarter = rs_tDBInput_3.getInt(4);
            if(rs_tDBInput_3.wasNull()){
                    row2.Quarter = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 5) {
								row2.Month = null;
							} else {
		                          
            row2.Month = rs_tDBInput_3.getInt(5);
            if(rs_tDBInput_3.wasNull()){
                    row2.Month = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 6) {
								row2.MonthName = null;
							} else {
	                         		
        	row2.MonthName = routines.system.JDBCUtil.getString(rs_tDBInput_3, 6, false);
		                    }
							if(colQtyInRs_tDBInput_3 < 7) {
								row2.Day = null;
							} else {
		                          
            row2.Day = rs_tDBInput_3.getInt(7);
            if(rs_tDBInput_3.wasNull()){
                    row2.Day = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 8) {
								row2.DayOfWeek = null;
							} else {
		                          
            row2.DayOfWeek = rs_tDBInput_3.getInt(8);
            if(rs_tDBInput_3.wasNull()){
                    row2.DayOfWeek = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 9) {
								row2.DayOfWeekName = null;
							} else {
	                         		
        	row2.DayOfWeekName = routines.system.JDBCUtil.getString(rs_tDBInput_3, 9, false);
		                    }
							if(colQtyInRs_tDBInput_3 < 10) {
								row2.WeekOfYear = null;
							} else {
		                          
            row2.WeekOfYear = rs_tDBInput_3.getInt(10);
            if(rs_tDBInput_3.wasNull()){
                    row2.WeekOfYear = null;
            }
		                    }
							if(colQtyInRs_tDBInput_3 < 11) {
								row2.IsWeekend = null;
							} else {
	                         		
            row2.IsWeekend = rs_tDBInput_3.getBoolean(11);
            if(rs_tDBInput_3.wasNull()){
                    row2.IsWeekend = null;
            }
		                    }
					

 



/**
 * [tDBInput_3 begin ] stop
 */
	
	/**
	 * [tDBInput_3 main ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 


	tos_count_tDBInput_3++;

/**
 * [tDBInput_3 main ] stop
 */
	
	/**
	 * [tDBInput_3 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 



/**
 * [tDBInput_3 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row2 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
						);
					}
					


			   
			   

					row2Struct row2_HashRow = new row2Struct();
		   	   	   
				
				row2_HashRow.DateKey = row2.DateKey;
				
				row2_HashRow.DateValue = row2.DateValue;
				
				row2_HashRow.Year = row2.Year;
				
				row2_HashRow.Quarter = row2.Quarter;
				
				row2_HashRow.Month = row2.Month;
				
				row2_HashRow.MonthName = row2.MonthName;
				
				row2_HashRow.Day = row2.Day;
				
				row2_HashRow.DayOfWeek = row2.DayOfWeek;
				
				row2_HashRow.DayOfWeekName = row2.DayOfWeekName;
				
				row2_HashRow.WeekOfYear = row2.WeekOfYear;
				
				row2_HashRow.IsWeekend = row2.IsWeekend;
				
			tHash_Lookup_row2.put(row2_HashRow);
			
            




 


	tos_count_tAdvancedHash_row2++;

/**
 * [tAdvancedHash_row2 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row2 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

 



/**
 * [tAdvancedHash_row2 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row2 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

 



/**
 * [tAdvancedHash_row2 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_3 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 



/**
 * [tDBInput_3 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_3 end ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

	}
}finally{
	if (rs_tDBInput_3 != null) {
		rs_tDBInput_3.close();
	}
	if (stmt_tDBInput_3 != null) {
		stmt_tDBInput_3.close();
	}
		if(conn_tDBInput_3 != null && !conn_tDBInput_3.isClosed()) {
			
			conn_tDBInput_3.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_3_NB_LINE",nb_line_tDBInput_3);
		


 

ok_Hash.put("tDBInput_3", true);
end_Hash.put("tDBInput_3", System.currentTimeMillis());




/**
 * [tDBInput_3 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row2 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

tHash_Lookup_row2.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row2");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row2", true);
end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());




/**
 * [tAdvancedHash_row2 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_3 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 



/**
 * [tDBInput_3 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row2 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row2";

	

 



/**
 * [tAdvancedHash_row2 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
	}
	


public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String Gender;

				public String getGender () {
					return this.Gender;
				}
				
			    public int Id_gender;

				public int getId_gender () {
					return this.Id_gender;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.Gender == null) ? 0 : this.Gender.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row4Struct other = (row4Struct) obj;
		
						if (this.Gender == null) {
							if (other.Gender != null)
								return false;
						
						} else if (!this.Gender.equals(other.Gender))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row4Struct other) {

		other.Gender = this.Gender;
	            other.Id_gender = this.Id_gender;
	            
	}

	public void copyKeysDataTo(row4Struct other) {

		other.Gender = this.Gender;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Gender = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Gender = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Gender,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Gender,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.Id_gender = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.Id_gender = objectIn.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.Id_gender);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.Id_gender);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Gender="+Gender);
		sb.append(",Id_gender="+String.valueOf(Id_gender));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row4Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Gender, other.Gender);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_5_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row4Struct row4 = new row4Struct();




	
	/**
	 * [tAdvancedHash_row4 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row4", false);
		start_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row4";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row4");
					}
				
		int tos_count_tAdvancedHash_row4 = 0;
		

			   		// connection name:row4
			   		// source node:tDBInput_5 - inputs:(after_tDBInput_1) outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row3,row7) outputs:(out1)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row4Struct>getLookup(matchingModeEnum_row4);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row4 begin ] stop
 */



	
	/**
	 * [tDBInput_5 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_5", false);
		start_Hash.put("tDBInput_5", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_5";

	
		int tos_count_tDBInput_5 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_5 = java.util.Calendar.getInstance();
		    calendar_tDBInput_5.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_5 = calendar_tDBInput_5.getTime();
		    int nb_line_tDBInput_5 = 0;
		    java.sql.Connection conn_tDBInput_5 = null;
				String driverClass_tDBInput_5 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_5 = java.lang.Class.forName(driverClass_tDBInput_5);
				String dbUser_tDBInput_5 = "root";
				
				 
	final String decryptedPassword_tDBInput_5 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:GJbBEOltesI2+wUOsaemNUoMihyCHZzLtAexKQ==");
				
				String dbPwd_tDBInput_5 = decryptedPassword_tDBInput_5;
				
        String properties_tDBInput_5 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_5 == null || properties_tDBInput_5.trim().length() == 0) {
            properties_tDBInput_5 = "";
        }
        String url_tDBInput_5 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_5;
				
				conn_tDBInput_5 = java.sql.DriverManager.getConnection(url_tDBInput_5,dbUser_tDBInput_5,dbPwd_tDBInput_5);
		        
		    
			java.sql.Statement stmt_tDBInput_5 = conn_tDBInput_5.createStatement();

		    String dbquery_tDBInput_5 = "SELECT \n  `dim_gender`.`Gender`, \n  `dim_gender`.`Id_gender`\nFROM `dim_gender`";
		    

            	globalMap.put("tDBInput_5_QUERY",dbquery_tDBInput_5);
		    java.sql.ResultSet rs_tDBInput_5 = null;

		    try {
		    	rs_tDBInput_5 = stmt_tDBInput_5.executeQuery(dbquery_tDBInput_5);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_5 = rs_tDBInput_5.getMetaData();
		    	int colQtyInRs_tDBInput_5 = rsmd_tDBInput_5.getColumnCount();

		    String tmpContent_tDBInput_5 = null;
		    
		    
		    while (rs_tDBInput_5.next()) {
		        nb_line_tDBInput_5++;
		        
							if(colQtyInRs_tDBInput_5 < 1) {
								row4.Gender = null;
							} else {
	                         		
        	row4.Gender = routines.system.JDBCUtil.getString(rs_tDBInput_5, 1, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 2) {
								row4.Id_gender = 0;
							} else {
		                          
            row4.Id_gender = rs_tDBInput_5.getInt(2);
            if(rs_tDBInput_5.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
					

 



/**
 * [tDBInput_5 begin ] stop
 */
	
	/**
	 * [tDBInput_5 main ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 


	tos_count_tDBInput_5++;

/**
 * [tDBInput_5 main ] stop
 */
	
	/**
	 * [tDBInput_5 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 



/**
 * [tDBInput_5 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row4"
						
						);
					}
					


			   
			   

					row4Struct row4_HashRow = new row4Struct();
		   	   	   
				
				row4_HashRow.Gender = row4.Gender;
				
				row4_HashRow.Id_gender = row4.Id_gender;
				
			tHash_Lookup_row4.put(row4_HashRow);
			
            




 


	tos_count_tAdvancedHash_row4++;

/**
 * [tAdvancedHash_row4 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row4 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

 



/**
 * [tAdvancedHash_row4 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row4 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

 



/**
 * [tAdvancedHash_row4 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_5 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 



/**
 * [tDBInput_5 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_5 end ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

	}
}finally{
	if (rs_tDBInput_5 != null) {
		rs_tDBInput_5.close();
	}
	if (stmt_tDBInput_5 != null) {
		stmt_tDBInput_5.close();
	}
		if(conn_tDBInput_5 != null && !conn_tDBInput_5.isClosed()) {
			
			conn_tDBInput_5.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_5_NB_LINE",nb_line_tDBInput_5);
		


 

ok_Hash.put("tDBInput_5", true);
end_Hash.put("tDBInput_5", System.currentTimeMillis());




/**
 * [tDBInput_5 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

tHash_Lookup_row4.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row4");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row4", true);
end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());




/**
 * [tAdvancedHash_row4 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_5 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 



/**
 * [tDBInput_5 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row4 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row4";

	

 



/**
 * [tAdvancedHash_row4 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 1);
	}
	


public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String Match_type;

				public String getMatch_type () {
					return this.Match_type;
				}
				
			    public int Id_type_match;

				public int getId_type_match () {
					return this.Id_type_match;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.Match_type == null) ? 0 : this.Match_type.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row5Struct other = (row5Struct) obj;
		
						if (this.Match_type == null) {
							if (other.Match_type != null)
								return false;
						
						} else if (!this.Match_type.equals(other.Match_type))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row5Struct other) {

		other.Match_type = this.Match_type;
	            other.Id_type_match = this.Id_type_match;
	            
	}

	public void copyKeysDataTo(row5Struct other) {

		other.Match_type = this.Match_type;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Match_type = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Match_type = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Match_type,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Match_type,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.Id_type_match = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.Id_type_match = objectIn.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.Id_type_match);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.Id_type_match);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Match_type="+Match_type);
		sb.append(",Id_type_match="+String.valueOf(Id_type_match));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row5Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Match_type, other.Match_type);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_7Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_7_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row5Struct row5 = new row5Struct();




	
	/**
	 * [tAdvancedHash_row5 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row5", false);
		start_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row5";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row5");
					}
				
		int tos_count_tAdvancedHash_row5 = 0;
		

			   		// connection name:row5
			   		// source node:tDBInput_7 - inputs:(after_tDBInput_1) outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row3,row7) outputs:(out1)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row5Struct>getLookup(matchingModeEnum_row5);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row5 begin ] stop
 */



	
	/**
	 * [tDBInput_7 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_7", false);
		start_Hash.put("tDBInput_7", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_7";

	
		int tos_count_tDBInput_7 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_7 = java.util.Calendar.getInstance();
		    calendar_tDBInput_7.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_7 = calendar_tDBInput_7.getTime();
		    int nb_line_tDBInput_7 = 0;
		    java.sql.Connection conn_tDBInput_7 = null;
				String driverClass_tDBInput_7 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_7 = java.lang.Class.forName(driverClass_tDBInput_7);
				String dbUser_tDBInput_7 = "root";
				
				 
	final String decryptedPassword_tDBInput_7 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:r8ZxNDf2rl+qaP8jz3bjL9ZEfLipLRKyQd8RxQ==");
				
				String dbPwd_tDBInput_7 = decryptedPassword_tDBInput_7;
				
        String properties_tDBInput_7 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_7 == null || properties_tDBInput_7.trim().length() == 0) {
            properties_tDBInput_7 = "";
        }
        String url_tDBInput_7 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_7;
				
				conn_tDBInput_7 = java.sql.DriverManager.getConnection(url_tDBInput_7,dbUser_tDBInput_7,dbPwd_tDBInput_7);
		        
		    
			java.sql.Statement stmt_tDBInput_7 = conn_tDBInput_7.createStatement();

		    String dbquery_tDBInput_7 = "SELECT \n  `dim_type_match`.`Match_type`, \n  `dim_type_match`.`Id_type_match`\nFROM `dim_type_match`";
		    

            	globalMap.put("tDBInput_7_QUERY",dbquery_tDBInput_7);
		    java.sql.ResultSet rs_tDBInput_7 = null;

		    try {
		    	rs_tDBInput_7 = stmt_tDBInput_7.executeQuery(dbquery_tDBInput_7);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_7 = rs_tDBInput_7.getMetaData();
		    	int colQtyInRs_tDBInput_7 = rsmd_tDBInput_7.getColumnCount();

		    String tmpContent_tDBInput_7 = null;
		    
		    
		    while (rs_tDBInput_7.next()) {
		        nb_line_tDBInput_7++;
		        
							if(colQtyInRs_tDBInput_7 < 1) {
								row5.Match_type = null;
							} else {
	                         		
        	row5.Match_type = routines.system.JDBCUtil.getString(rs_tDBInput_7, 1, false);
		                    }
							if(colQtyInRs_tDBInput_7 < 2) {
								row5.Id_type_match = 0;
							} else {
		                          
            row5.Id_type_match = rs_tDBInput_7.getInt(2);
            if(rs_tDBInput_7.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
					

 



/**
 * [tDBInput_7 begin ] stop
 */
	
	/**
	 * [tDBInput_7 main ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 


	tos_count_tDBInput_7++;

/**
 * [tDBInput_7 main ] stop
 */
	
	/**
	 * [tDBInput_7 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 



/**
 * [tDBInput_7 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row5"
						
						);
					}
					


			   
			   

					row5Struct row5_HashRow = new row5Struct();
		   	   	   
				
				row5_HashRow.Match_type = row5.Match_type;
				
				row5_HashRow.Id_type_match = row5.Id_type_match;
				
			tHash_Lookup_row5.put(row5_HashRow);
			
            




 


	tos_count_tAdvancedHash_row5++;

/**
 * [tAdvancedHash_row5 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row5 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

 



/**
 * [tAdvancedHash_row5 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row5 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

 



/**
 * [tAdvancedHash_row5 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_7 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 



/**
 * [tDBInput_7 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_7 end ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

	}
}finally{
	if (rs_tDBInput_7 != null) {
		rs_tDBInput_7.close();
	}
	if (stmt_tDBInput_7 != null) {
		stmt_tDBInput_7.close();
	}
		if(conn_tDBInput_7 != null && !conn_tDBInput_7.isClosed()) {
			
			conn_tDBInput_7.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_7_NB_LINE",nb_line_tDBInput_7);
		


 

ok_Hash.put("tDBInput_7", true);
end_Hash.put("tDBInput_7", System.currentTimeMillis());




/**
 * [tDBInput_7 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

tHash_Lookup_row5.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row5");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row5", true);
end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());




/**
 * [tAdvancedHash_row5 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_7 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_7";

	

 



/**
 * [tDBInput_7 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row5 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	

 



/**
 * [tAdvancedHash_row5 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_7_SUBPROCESS_STATE", 1);
	}
	


public static class row6Struct implements routines.system.IPersistableComparableLookupRow<row6Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String Tournament_Name;

				public String getTournament_Name () {
					return this.Tournament_Name;
				}
				
			    public int Id_tr_name;

				public int getId_tr_name () {
					return this.Id_tr_name;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.Tournament_Name == null) ? 0 : this.Tournament_Name.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row6Struct other = (row6Struct) obj;
		
						if (this.Tournament_Name == null) {
							if (other.Tournament_Name != null)
								return false;
						
						} else if (!this.Tournament_Name.equals(other.Tournament_Name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row6Struct other) {

		other.Tournament_Name = this.Tournament_Name;
	            other.Id_tr_name = this.Id_tr_name;
	            
	}

	public void copyKeysDataTo(row6Struct other) {

		other.Tournament_Name = this.Tournament_Name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Tournament_Name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Tournament_Name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Tournament_Name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Tournament_Name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.Id_tr_name = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.Id_tr_name = objectIn.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.Id_tr_name);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.Id_tr_name);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Tournament_Name="+Tournament_Name);
		sb.append(",Id_tr_name="+String.valueOf(Id_tr_name));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row6Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Tournament_Name, other.Tournament_Name);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_8Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_8_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row6Struct row6 = new row6Struct();




	
	/**
	 * [tAdvancedHash_row6 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row6", false);
		start_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row6";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row6");
					}
				
		int tos_count_tAdvancedHash_row6 = 0;
		

			   		// connection name:row6
			   		// source node:tDBInput_8 - inputs:(after_tDBInput_1) outputs:(row6,row6) | target node:tAdvancedHash_row6 - inputs:(row6) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row3,row7) outputs:(out1)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row6 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row6Struct>getLookup(matchingModeEnum_row6);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row6", tHash_Lookup_row6);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row6 begin ] stop
 */



	
	/**
	 * [tDBInput_8 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_8", false);
		start_Hash.put("tDBInput_8", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_8";

	
		int tos_count_tDBInput_8 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_8 = java.util.Calendar.getInstance();
		    calendar_tDBInput_8.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_8 = calendar_tDBInput_8.getTime();
		    int nb_line_tDBInput_8 = 0;
		    java.sql.Connection conn_tDBInput_8 = null;
				String driverClass_tDBInput_8 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_8 = java.lang.Class.forName(driverClass_tDBInput_8);
				String dbUser_tDBInput_8 = "root";
				
				 
	final String decryptedPassword_tDBInput_8 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:CaxTIb8WesbMMiSVfrsZPkfwihRoVO6dCjQLcQ==");
				
				String dbPwd_tDBInput_8 = decryptedPassword_tDBInput_8;
				
        String properties_tDBInput_8 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_8 == null || properties_tDBInput_8.trim().length() == 0) {
            properties_tDBInput_8 = "";
        }
        String url_tDBInput_8 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_8;
				
				conn_tDBInput_8 = java.sql.DriverManager.getConnection(url_tDBInput_8,dbUser_tDBInput_8,dbPwd_tDBInput_8);
		        
		    
			java.sql.Statement stmt_tDBInput_8 = conn_tDBInput_8.createStatement();

		    String dbquery_tDBInput_8 = "SELECT \n  `dim_tournmant`.`Tournament_Name`, \n  `dim_tournmant`.`Id_tr_name`\nFROM `dim_tournmant`";
		    

            	globalMap.put("tDBInput_8_QUERY",dbquery_tDBInput_8);
		    java.sql.ResultSet rs_tDBInput_8 = null;

		    try {
		    	rs_tDBInput_8 = stmt_tDBInput_8.executeQuery(dbquery_tDBInput_8);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_8 = rs_tDBInput_8.getMetaData();
		    	int colQtyInRs_tDBInput_8 = rsmd_tDBInput_8.getColumnCount();

		    String tmpContent_tDBInput_8 = null;
		    
		    
		    while (rs_tDBInput_8.next()) {
		        nb_line_tDBInput_8++;
		        
							if(colQtyInRs_tDBInput_8 < 1) {
								row6.Tournament_Name = null;
							} else {
	                         		
        	row6.Tournament_Name = routines.system.JDBCUtil.getString(rs_tDBInput_8, 1, false);
		                    }
							if(colQtyInRs_tDBInput_8 < 2) {
								row6.Id_tr_name = 0;
							} else {
		                          
            row6.Id_tr_name = rs_tDBInput_8.getInt(2);
            if(rs_tDBInput_8.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
					

 



/**
 * [tDBInput_8 begin ] stop
 */
	
	/**
	 * [tDBInput_8 main ] start
	 */

	

	
	
	currentComponent="tDBInput_8";

	

 


	tos_count_tDBInput_8++;

/**
 * [tDBInput_8 main ] stop
 */
	
	/**
	 * [tDBInput_8 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_8";

	

 



/**
 * [tDBInput_8 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row6 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row6"
						
						);
					}
					


			   
			   

					row6Struct row6_HashRow = new row6Struct();
		   	   	   
				
				row6_HashRow.Tournament_Name = row6.Tournament_Name;
				
				row6_HashRow.Id_tr_name = row6.Id_tr_name;
				
			tHash_Lookup_row6.put(row6_HashRow);
			
            




 


	tos_count_tAdvancedHash_row6++;

/**
 * [tAdvancedHash_row6 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row6 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

 



/**
 * [tAdvancedHash_row6 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row6 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

 



/**
 * [tAdvancedHash_row6 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_8 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_8";

	

 



/**
 * [tDBInput_8 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_8 end ] start
	 */

	

	
	
	currentComponent="tDBInput_8";

	

	}
}finally{
	if (rs_tDBInput_8 != null) {
		rs_tDBInput_8.close();
	}
	if (stmt_tDBInput_8 != null) {
		stmt_tDBInput_8.close();
	}
		if(conn_tDBInput_8 != null && !conn_tDBInput_8.isClosed()) {
			
			conn_tDBInput_8.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_8_NB_LINE",nb_line_tDBInput_8);
		


 

ok_Hash.put("tDBInput_8", true);
end_Hash.put("tDBInput_8", System.currentTimeMillis());




/**
 * [tDBInput_8 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row6 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

tHash_Lookup_row6.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row6");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row6", true);
end_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());




/**
 * [tAdvancedHash_row6 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_8 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_8";

	

 



/**
 * [tDBInput_8 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row6 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row6";

	

 



/**
 * [tAdvancedHash_row6 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_8_SUBPROCESS_STATE", 1);
	}
	


public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String Team_1;

				public String getTeam_1 () {
					return this.Team_1;
				}
				
			    public int Id_Player_1;

				public int getId_Player_1 () {
					return this.Id_Player_1;
				}
				
			    public int Id_Player_2;

				public int getId_Player_2 () {
					return this.Id_Player_2;
				}
				
			    public int Id_team;

				public int getId_team () {
					return this.Id_team;
				}
				
			    public int Id_Country_player1;

				public int getId_Country_player1 () {
					return this.Id_Country_player1;
				}
				
			    public int Id_Country_player2;

				public int getId_Country_player2 () {
					return this.Id_Country_player2;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.Team_1 == null) ? 0 : this.Team_1.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row3Struct other = (row3Struct) obj;
		
						if (this.Team_1 == null) {
							if (other.Team_1 != null)
								return false;
						
						} else if (!this.Team_1.equals(other.Team_1))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row3Struct other) {

		other.Team_1 = this.Team_1;
	            other.Id_Player_1 = this.Id_Player_1;
	            other.Id_Player_2 = this.Id_Player_2;
	            other.Id_team = this.Id_team;
	            other.Id_Country_player1 = this.Id_Country_player1;
	            other.Id_Country_player2 = this.Id_Country_player2;
	            
	}

	public void copyKeysDataTo(row3Struct other) {

		other.Team_1 = this.Team_1;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Team_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Team_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Team_1,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Team_1,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.Id_Player_1 = dis.readInt();
					
			            this.Id_Player_2 = dis.readInt();
					
			            this.Id_team = dis.readInt();
					
			            this.Id_Country_player1 = dis.readInt();
					
			            this.Id_Country_player2 = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.Id_Player_1 = objectIn.readInt();
					
			            this.Id_Player_2 = objectIn.readInt();
					
			            this.Id_team = objectIn.readInt();
					
			            this.Id_Country_player1 = objectIn.readInt();
					
			            this.Id_Country_player2 = objectIn.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.Id_Player_1);
					
		            	dos.writeInt(this.Id_Player_2);
					
		            	dos.writeInt(this.Id_team);
					
		            	dos.writeInt(this.Id_Country_player1);
					
		            	dos.writeInt(this.Id_Country_player2);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.Id_Player_1);
					
					objectOut.writeInt(this.Id_Player_2);
					
					objectOut.writeInt(this.Id_team);
					
					objectOut.writeInt(this.Id_Country_player1);
					
					objectOut.writeInt(this.Id_Country_player2);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Team_1="+Team_1);
		sb.append(",Id_Player_1="+String.valueOf(Id_Player_1));
		sb.append(",Id_Player_2="+String.valueOf(Id_Player_2));
		sb.append(",Id_team="+String.valueOf(Id_team));
		sb.append(",Id_Country_player1="+String.valueOf(Id_Country_player1));
		sb.append(",Id_Country_player2="+String.valueOf(Id_Country_player2));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Team_1, other.Team_1);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_9Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_9_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row3Struct row3 = new row3Struct();




	
	/**
	 * [tAdvancedHash_row3 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row3", false);
		start_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row3";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row3");
					}
				
		int tos_count_tAdvancedHash_row3 = 0;
		

			   		// connection name:row3
			   		// source node:tDBInput_9 - inputs:(after_tDBInput_1) outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row3,row7) outputs:(out1)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row3Struct>getLookup(matchingModeEnum_row3);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row3 begin ] stop
 */



	
	/**
	 * [tDBInput_9 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_9", false);
		start_Hash.put("tDBInput_9", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_9";

	
		int tos_count_tDBInput_9 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_9 = java.util.Calendar.getInstance();
		    calendar_tDBInput_9.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_9 = calendar_tDBInput_9.getTime();
		    int nb_line_tDBInput_9 = 0;
		    java.sql.Connection conn_tDBInput_9 = null;
				String driverClass_tDBInput_9 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_9 = java.lang.Class.forName(driverClass_tDBInput_9);
				String dbUser_tDBInput_9 = "root";
				
				 
	final String decryptedPassword_tDBInput_9 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:NKOo+/NxKD0zxYQ7+Ou+md8gugrGAybTfeQnIQ==");
				
				String dbPwd_tDBInput_9 = decryptedPassword_tDBInput_9;
				
        String properties_tDBInput_9 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_9 == null || properties_tDBInput_9.trim().length() == 0) {
            properties_tDBInput_9 = "";
        }
        String url_tDBInput_9 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_9;
				
				conn_tDBInput_9 = java.sql.DriverManager.getConnection(url_tDBInput_9,dbUser_tDBInput_9,dbPwd_tDBInput_9);
		        
		    
			java.sql.Statement stmt_tDBInput_9 = conn_tDBInput_9.createStatement();

		    String dbquery_tDBInput_9 = "SELECT \n  `dim_team_ofc`.`Team_1`, \n  `dim_team_ofc`.`Id_Player_1`, \n  `dim_team_ofc`.`Id_Player_2`, \n  `dim_team_ofc`."
+"`Id_team`, \n  `dim_team_ofc`.`Id_Country_player1`, \n  `dim_team_ofc`.`Id_Country_player2`\nFROM `dim_team_ofc`";
		    

            	globalMap.put("tDBInput_9_QUERY",dbquery_tDBInput_9);
		    java.sql.ResultSet rs_tDBInput_9 = null;

		    try {
		    	rs_tDBInput_9 = stmt_tDBInput_9.executeQuery(dbquery_tDBInput_9);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_9 = rs_tDBInput_9.getMetaData();
		    	int colQtyInRs_tDBInput_9 = rsmd_tDBInput_9.getColumnCount();

		    String tmpContent_tDBInput_9 = null;
		    
		    
		    while (rs_tDBInput_9.next()) {
		        nb_line_tDBInput_9++;
		        
							if(colQtyInRs_tDBInput_9 < 1) {
								row3.Team_1 = null;
							} else {
	                         		
        	row3.Team_1 = routines.system.JDBCUtil.getString(rs_tDBInput_9, 1, false);
		                    }
							if(colQtyInRs_tDBInput_9 < 2) {
								row3.Id_Player_1 = 0;
							} else {
		                          
            row3.Id_Player_1 = rs_tDBInput_9.getInt(2);
            if(rs_tDBInput_9.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_9 < 3) {
								row3.Id_Player_2 = 0;
							} else {
		                          
            row3.Id_Player_2 = rs_tDBInput_9.getInt(3);
            if(rs_tDBInput_9.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_9 < 4) {
								row3.Id_team = 0;
							} else {
		                          
            row3.Id_team = rs_tDBInput_9.getInt(4);
            if(rs_tDBInput_9.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_9 < 5) {
								row3.Id_Country_player1 = 0;
							} else {
		                          
            row3.Id_Country_player1 = rs_tDBInput_9.getInt(5);
            if(rs_tDBInput_9.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_9 < 6) {
								row3.Id_Country_player2 = 0;
							} else {
		                          
            row3.Id_Country_player2 = rs_tDBInput_9.getInt(6);
            if(rs_tDBInput_9.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
					

 



/**
 * [tDBInput_9 begin ] stop
 */
	
	/**
	 * [tDBInput_9 main ] start
	 */

	

	
	
	currentComponent="tDBInput_9";

	

 


	tos_count_tDBInput_9++;

/**
 * [tDBInput_9 main ] stop
 */
	
	/**
	 * [tDBInput_9 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_9";

	

 



/**
 * [tDBInput_9 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row3 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row3"
						
						);
					}
					


			   
			   

					row3Struct row3_HashRow = new row3Struct();
		   	   	   
				
				row3_HashRow.Team_1 = row3.Team_1;
				
				row3_HashRow.Id_Player_1 = row3.Id_Player_1;
				
				row3_HashRow.Id_Player_2 = row3.Id_Player_2;
				
				row3_HashRow.Id_team = row3.Id_team;
				
				row3_HashRow.Id_Country_player1 = row3.Id_Country_player1;
				
				row3_HashRow.Id_Country_player2 = row3.Id_Country_player2;
				
			tHash_Lookup_row3.put(row3_HashRow);
			
            




 


	tos_count_tAdvancedHash_row3++;

/**
 * [tAdvancedHash_row3 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row3 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

 



/**
 * [tAdvancedHash_row3 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row3 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

 



/**
 * [tAdvancedHash_row3 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_9 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_9";

	

 



/**
 * [tDBInput_9 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_9 end ] start
	 */

	

	
	
	currentComponent="tDBInput_9";

	

	}
}finally{
	if (rs_tDBInput_9 != null) {
		rs_tDBInput_9.close();
	}
	if (stmt_tDBInput_9 != null) {
		stmt_tDBInput_9.close();
	}
		if(conn_tDBInput_9 != null && !conn_tDBInput_9.isClosed()) {
			
			conn_tDBInput_9.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_9_NB_LINE",nb_line_tDBInput_9);
		


 

ok_Hash.put("tDBInput_9", true);
end_Hash.put("tDBInput_9", System.currentTimeMillis());




/**
 * [tDBInput_9 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row3 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

tHash_Lookup_row3.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row3");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row3", true);
end_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());




/**
 * [tAdvancedHash_row3 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_9 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_9";

	

 



/**
 * [tDBInput_9 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row3 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	

 



/**
 * [tAdvancedHash_row3 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_9_SUBPROCESS_STATE", 1);
	}
	


public static class row7Struct implements routines.system.IPersistableComparableLookupRow<row7Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Performance = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Performance = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String Team_1;

				public String getTeam_1 () {
					return this.Team_1;
				}
				
			    public int Id_Player_1;

				public int getId_Player_1 () {
					return this.Id_Player_1;
				}
				
			    public int Id_Player_2;

				public int getId_Player_2 () {
					return this.Id_Player_2;
				}
				
			    public int Id_team;

				public int getId_team () {
					return this.Id_team;
				}
				
			    public int Id_Country_player1;

				public int getId_Country_player1 () {
					return this.Id_Country_player1;
				}
				
			    public int Id_Country_player2;

				public int getId_Country_player2 () {
					return this.Id_Country_player2;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.Team_1 == null) ? 0 : this.Team_1.hashCode());
					
    		this.hashCode = result;
    		this.hashCodeDirty = false;
		}
		return this.hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final row7Struct other = (row7Struct) obj;
		
						if (this.Team_1 == null) {
							if (other.Team_1 != null)
								return false;
						
						} else if (!this.Team_1.equals(other.Team_1))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row7Struct other) {

		other.Team_1 = this.Team_1;
	            other.Id_Player_1 = this.Id_Player_1;
	            other.Id_Player_2 = this.Id_Player_2;
	            other.Id_team = this.Id_team;
	            other.Id_Country_player1 = this.Id_Country_player1;
	            other.Id_Country_player2 = this.Id_Country_player2;
	            
	}

	public void copyKeysDataTo(row7Struct other) {

		other.Team_1 = this.Team_1;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}
	
	private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException{
		String strReturn = null;
		int length = 0;
        length = unmarshaller.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Performance.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Performance.length == 0) {
   					commonByteArray_PADEL_Fact_Performance = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Performance = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Performance, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Performance, 0, length, utf8Charset);
		}
		return strReturn;
	}

    private void writeString(String str, ObjectOutputStream dos) throws IOException{
		if(str == null) {
            dos.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
	    	dos.writeInt(byteArray.length);
			dos.write(byteArray);
    	}
    }
    
    private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException{
		if(str == null) {
			marshaller.writeInt(-1);
		} else {
            byte[] byteArray = str.getBytes(utf8Charset);
            marshaller.writeInt(byteArray.length);
            marshaller.write(byteArray);
    	}
    }

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Team_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Performance) {

        	try {

        		int length = 0;
		
					this.Team_1 = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Team_1,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Team_1,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }



    /**
     * Fill Values data by reading ObjectInputStream.
     */
    public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
        try {

			int length = 0;
		
			            this.Id_Player_1 = dis.readInt();
					
			            this.Id_Player_2 = dis.readInt();
					
			            this.Id_team = dis.readInt();
					
			            this.Id_Country_player1 = dis.readInt();
					
			            this.Id_Country_player2 = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.Id_Player_1 = objectIn.readInt();
					
			            this.Id_Player_2 = objectIn.readInt();
					
			            this.Id_team = objectIn.readInt();
					
			            this.Id_Country_player1 = objectIn.readInt();
					
			            this.Id_Country_player2 = objectIn.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.Id_Player_1);
					
		            	dos.writeInt(this.Id_Player_2);
					
		            	dos.writeInt(this.Id_team);
					
		            	dos.writeInt(this.Id_Country_player1);
					
		            	dos.writeInt(this.Id_Country_player2);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.Id_Player_1);
					
					objectOut.writeInt(this.Id_Player_2);
					
					objectOut.writeInt(this.Id_team);
					
					objectOut.writeInt(this.Id_Country_player1);
					
					objectOut.writeInt(this.Id_Country_player2);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}
    }


    
    public boolean supportMarshaller(){
        return true;
    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Team_1="+Team_1);
		sb.append(",Id_Player_1="+String.valueOf(Id_Player_1));
		sb.append(",Id_Player_2="+String.valueOf(Id_Player_2));
		sb.append(",Id_team="+String.valueOf(Id_team));
		sb.append(",Id_Country_player1="+String.valueOf(Id_Country_player1));
		sb.append(",Id_Country_player2="+String.valueOf(Id_Country_player2));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row7Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Team_1, other.Team_1);
						if(returnValue != 0) {
							return returnValue;
						}

					
	    return returnValue;
    }


    private int checkNullsAndCompare(Object object1, Object object2) {
        int returnValue = 0;
		if (object1 instanceof Comparable && object2 instanceof Comparable) {
            returnValue = ((Comparable) object1).compareTo(object2);
        } else if (object1 != null && object2 != null) {
            returnValue = compareStrings(object1.toString(), object2.toString());
        } else if (object1 == null && object2 != null) {
            returnValue = 1;
        } else if (object1 != null && object2 == null) {
            returnValue = -1;
        } else {
            returnValue = 0;
        }

        return returnValue;
    }

    private int compareStrings(String string1, String string2) {
        return string1.compareTo(string2);
    }


}
public void tDBInput_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_6_SUBPROCESS_STATE", 0);

 final boolean execStat = this.execStat;
	
		String iterateId = "";
	
	
	String currentComponent = "";
	java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

	try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { //start the resume
				globalResumeTicket = true;



		row7Struct row7 = new row7Struct();




	
	/**
	 * [tAdvancedHash_row7 begin ] start
	 */

	

	
		
		ok_Hash.put("tAdvancedHash_row7", false);
		start_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());
		
	
	currentComponent="tAdvancedHash_row7";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row7");
					}
				
		int tos_count_tAdvancedHash_row7 = 0;
		

			   		// connection name:row7
			   		// source node:tDBInput_6 - inputs:(after_tDBInput_1) outputs:(row7,row7) | target node:tAdvancedHash_row7 - inputs:(row7) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row2,row4,row5,row6,row3,row7) outputs:(out1)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row7 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row7Struct>getLookup(matchingModeEnum_row7);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row7", tHash_Lookup_row7);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row7 begin ] stop
 */



	
	/**
	 * [tDBInput_6 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_6", false);
		start_Hash.put("tDBInput_6", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_6";

	
		int tos_count_tDBInput_6 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_6 = java.util.Calendar.getInstance();
		    calendar_tDBInput_6.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_6 = calendar_tDBInput_6.getTime();
		    int nb_line_tDBInput_6 = 0;
		    java.sql.Connection conn_tDBInput_6 = null;
				String driverClass_tDBInput_6 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_6 = java.lang.Class.forName(driverClass_tDBInput_6);
				String dbUser_tDBInput_6 = "root";
				
				 
	final String decryptedPassword_tDBInput_6 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:qWML8HaiGCrwXtjVzAZFCbRolYt/us8Zf1JVZA==");
				
				String dbPwd_tDBInput_6 = decryptedPassword_tDBInput_6;
				
        String properties_tDBInput_6 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_6 == null || properties_tDBInput_6.trim().length() == 0) {
            properties_tDBInput_6 = "";
        }
        String url_tDBInput_6 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_6;
				
				conn_tDBInput_6 = java.sql.DriverManager.getConnection(url_tDBInput_6,dbUser_tDBInput_6,dbPwd_tDBInput_6);
		        
		    
			java.sql.Statement stmt_tDBInput_6 = conn_tDBInput_6.createStatement();

		    String dbquery_tDBInput_6 = "SELECT \n  `dim_team_ofc`.`Team_1`, \n  `dim_team_ofc`.`Id_Player_1`, \n  `dim_team_ofc`.`Id_Player_2`, \n  `dim_team_ofc`."
+"`Id_team`, \n  `dim_team_ofc`.`Id_Country_player1`, \n  `dim_team_ofc`.`Id_Country_player2`\nFROM `dim_team_ofc`";
		    

            	globalMap.put("tDBInput_6_QUERY",dbquery_tDBInput_6);
		    java.sql.ResultSet rs_tDBInput_6 = null;

		    try {
		    	rs_tDBInput_6 = stmt_tDBInput_6.executeQuery(dbquery_tDBInput_6);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_6 = rs_tDBInput_6.getMetaData();
		    	int colQtyInRs_tDBInput_6 = rsmd_tDBInput_6.getColumnCount();

		    String tmpContent_tDBInput_6 = null;
		    
		    
		    while (rs_tDBInput_6.next()) {
		        nb_line_tDBInput_6++;
		        
							if(colQtyInRs_tDBInput_6 < 1) {
								row7.Team_1 = null;
							} else {
	                         		
        	row7.Team_1 = routines.system.JDBCUtil.getString(rs_tDBInput_6, 1, false);
		                    }
							if(colQtyInRs_tDBInput_6 < 2) {
								row7.Id_Player_1 = 0;
							} else {
		                          
            row7.Id_Player_1 = rs_tDBInput_6.getInt(2);
            if(rs_tDBInput_6.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 3) {
								row7.Id_Player_2 = 0;
							} else {
		                          
            row7.Id_Player_2 = rs_tDBInput_6.getInt(3);
            if(rs_tDBInput_6.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 4) {
								row7.Id_team = 0;
							} else {
		                          
            row7.Id_team = rs_tDBInput_6.getInt(4);
            if(rs_tDBInput_6.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 5) {
								row7.Id_Country_player1 = 0;
							} else {
		                          
            row7.Id_Country_player1 = rs_tDBInput_6.getInt(5);
            if(rs_tDBInput_6.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_6 < 6) {
								row7.Id_Country_player2 = 0;
							} else {
		                          
            row7.Id_Country_player2 = rs_tDBInput_6.getInt(6);
            if(rs_tDBInput_6.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
					

 



/**
 * [tDBInput_6 begin ] stop
 */
	
	/**
	 * [tDBInput_6 main ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 


	tos_count_tDBInput_6++;

/**
 * [tDBInput_6 main ] stop
 */
	
	/**
	 * [tDBInput_6 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 



/**
 * [tDBInput_6 process_data_begin ] stop
 */

	
	/**
	 * [tAdvancedHash_row7 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row7"
						
						);
					}
					


			   
			   

					row7Struct row7_HashRow = new row7Struct();
		   	   	   
				
				row7_HashRow.Team_1 = row7.Team_1;
				
				row7_HashRow.Id_Player_1 = row7.Id_Player_1;
				
				row7_HashRow.Id_Player_2 = row7.Id_Player_2;
				
				row7_HashRow.Id_team = row7.Id_team;
				
				row7_HashRow.Id_Country_player1 = row7.Id_Country_player1;
				
				row7_HashRow.Id_Country_player2 = row7.Id_Country_player2;
				
			tHash_Lookup_row7.put(row7_HashRow);
			
            




 


	tos_count_tAdvancedHash_row7++;

/**
 * [tAdvancedHash_row7 main ] stop
 */
	
	/**
	 * [tAdvancedHash_row7 process_data_begin ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

 



/**
 * [tAdvancedHash_row7 process_data_begin ] stop
 */
	
	/**
	 * [tAdvancedHash_row7 process_data_end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

 



/**
 * [tAdvancedHash_row7 process_data_end ] stop
 */



	
	/**
	 * [tDBInput_6 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 



/**
 * [tDBInput_6 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_6 end ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

	}
}finally{
	if (rs_tDBInput_6 != null) {
		rs_tDBInput_6.close();
	}
	if (stmt_tDBInput_6 != null) {
		stmt_tDBInput_6.close();
	}
		if(conn_tDBInput_6 != null && !conn_tDBInput_6.isClosed()) {
			
			conn_tDBInput_6.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_6_NB_LINE",nb_line_tDBInput_6);
		


 

ok_Hash.put("tDBInput_6", true);
end_Hash.put("tDBInput_6", System.currentTimeMillis());




/**
 * [tDBInput_6 end ] stop
 */

	
	/**
	 * [tAdvancedHash_row7 end ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

tHash_Lookup_row7.endPut();

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row7");
			  	}
			  	
 

ok_Hash.put("tAdvancedHash_row7", true);
end_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());




/**
 * [tAdvancedHash_row7 end ] stop
 */



				}//end the resume

				



	
			}catch(java.lang.Exception e){	
				
				TalendException te = new TalendException(e, currentComponent, globalMap);
				
				throw te;
			}catch(java.lang.Error error){	
				
					runStat.stopThreadStat();
				
				throw error;
			}finally{
				
				try{
					
	
	/**
	 * [tDBInput_6 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_6";

	

 



/**
 * [tDBInput_6 finally ] stop
 */

	
	/**
	 * [tAdvancedHash_row7 finally ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row7";

	

 



/**
 * [tAdvancedHash_row7 finally ] stop
 */



				}catch(java.lang.Exception e){	
					//ignore
				}catch(java.lang.Error error){
					//ignore
				}
				resourceMap = null;
			}
		

		globalMap.put("tDBInput_6_SUBPROCESS_STATE", 1);
	}
	
    public String resuming_logs_dir_path = null;
    public String resuming_checkpoint_path = null;
    public String parent_part_launcher = null;
    private String resumeEntryMethodName = null;
    private boolean globalResumeTicket = false;

    public boolean watch = false;
    // portStats is null, it means don't execute the statistics
    public Integer portStats = null;
    public int portTraces = 4334;
    public String clientHost;
    public String defaultClientHost = "localhost";
    public String contextStr = "Default";
    public boolean isDefaultContext = true;
    public String pid = "0";
    public String rootPid = null;
    public String fatherPid = null;
    public String fatherNode = null;
    public long startTime = 0;
    public boolean isChildJob = false;
    public String log4jLevel = "";
    
    private boolean enableLogStash;

    private boolean execStat = true;

    private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
        protected java.util.Map<String, String> initialValue() {
            java.util.Map<String,String> threadRunResultMap = new java.util.HashMap<String, String>();
            threadRunResultMap.put("errorCode", null);
            threadRunResultMap.put("status", "");
            return threadRunResultMap;
        };
    };


    protected PropertiesWithType context_param = new PropertiesWithType();
    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

    public String status= "";
    

    public static void main(String[] args){
        final Fact_Performance Fact_PerformanceClass = new Fact_Performance();

        int exitCode = Fact_PerformanceClass.runJobInTOS(args);

        System.exit(exitCode);
    }


    public String[][] runJob(String[] args) {

        int exitCode = runJobInTOS(args);
        String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

        return bufferValue;
    }

    public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;
    	
        return hastBufferOutput;
    }

    public int runJobInTOS(String[] args) {
	   	// reset status
	   	status = "";
	   	
        String lastStr = "";
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--context_param")) {
                lastStr = arg;
            } else if (lastStr.equals("")) {
                evalParam(arg);
            } else {
                evalParam(lastStr + " " + arg);
                lastStr = "";
            }
        }
        enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

    	
    	

        if(clientHost == null) {
            clientHost = defaultClientHost;
        }

        if(pid == null || "0".equals(pid)) {
            pid = TalendString.getAsciiRandomString(6);
        }

        if (rootPid==null) {
            rootPid = pid;
        }
        if (fatherPid==null) {
            fatherPid = pid;
        }else{
            isChildJob = true;
        }

        if (portStats != null) {
            // portStats = -1; //for testing
            if (portStats < 0 || portStats > 65535) {
                // issue:10869, the portStats is invalid, so this client socket can't open
                System.err.println("The statistics socket port " + portStats + " is invalid.");
                execStat = false;
            }
        } else {
            execStat = false;
        }
        boolean inOSGi = routines.system.BundleUtils.inOSGi();

        if (inOSGi) {
            java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

            if (jobProperties != null && jobProperties.get("context") != null) {
                contextStr = (String)jobProperties.get("context");
            }
        }

        try {
            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead.
            java.io.InputStream inContext = Fact_Performance.class.getClassLoader().getResourceAsStream("padel/fact_performance_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = Fact_Performance.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
            }
            if (inContext != null) {
                try {
                    //defaultProps is in order to keep the original context value
                    if(context != null && context.isEmpty()) {
	                defaultProps.load(inContext);
	                context = new ContextProperties(defaultProps);
                    }
                } finally {
                    inContext.close();
                }
            } else if (!isDefaultContext) {
                //print info and job continue to run, for case: context_param is not empty.
                System.err.println("Could not find the context " + contextStr);
            }

            if(!context_param.isEmpty()) {
                context.putAll(context_param);
				//set types for params from parentJobs
				for (Object key: context_param.keySet()){
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
            }
            class ContextProcessing {
                private void processContext_0() {
                } 
                public void processAllContext() {
                        processContext_0();
                }
            }

            new ContextProcessing().processAllContext();
        } catch (java.io.IOException ie) {
            System.err.println("Could not load context "+contextStr);
            ie.printStackTrace();
        }

        // get context value from parent directly
        if (parentContextMap != null && !parentContextMap.isEmpty()) {
        }

        //Resume: init the resumeUtil
        resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
        resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
        resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
        //Resume: jobStart
        resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","","","",resumeUtil.convertToJsonText(context,parametersToEncrypt));

if(execStat) {
    try {
        runStat.openSocket(!isChildJob);
        runStat.setAllPID(rootPid, fatherPid, pid, jobName);
        runStat.startThreadStat(clientHost, portStats);
        runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
    } catch (java.io.IOException ioException) {
        ioException.printStackTrace();
    }
}



	
	    java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
	    globalMap.put("concurrentHashMap", concurrentHashMap);
	

    long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long endUsedMemory = 0;
    long end = 0;

    startTime = System.currentTimeMillis();


this.globalResumeTicket = true;//to run tPreJob





this.globalResumeTicket = false;//to run others jobs

try {
errorCode = null;tDBInput_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tDBInput_1) {
globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

e_tDBInput_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Fact_Performance");
        }



if (execStat) {
    runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
    runStat.stopThreadStat();
}
    int returnCode = 0;


    if(errorCode == null) {
         returnCode = status != null && status.equals("failure") ? 1 : 0;
    } else {
         returnCode = errorCode.intValue();
    }
    resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "","" + returnCode,"","","");

    return returnCode;

  }

    // only for OSGi env
    public void destroy() {


    }














    private java.util.Map<String, Object> getSharedConnections4REST() {
        java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();






        return connections;
    }

    private void evalParam(String arg) {
        if (arg.startsWith("--resuming_logs_dir_path")) {
            resuming_logs_dir_path = arg.substring(25);
        } else if (arg.startsWith("--resuming_checkpoint_path")) {
            resuming_checkpoint_path = arg.substring(27);
        } else if (arg.startsWith("--parent_part_launcher")) {
            parent_part_launcher = arg.substring(23);
        } else if (arg.startsWith("--watch")) {
            watch = true;
        } else if (arg.startsWith("--stat_port=")) {
            String portStatsStr = arg.substring(12);
            if (portStatsStr != null && !portStatsStr.equals("null")) {
                portStats = Integer.parseInt(portStatsStr);
            }
        } else if (arg.startsWith("--trace_port=")) {
            portTraces = Integer.parseInt(arg.substring(13));
        } else if (arg.startsWith("--client_host=")) {
            clientHost = arg.substring(14);
        } else if (arg.startsWith("--context=")) {
            contextStr = arg.substring(10);
            isDefaultContext = false;
        } else if (arg.startsWith("--father_pid=")) {
            fatherPid = arg.substring(13);
        } else if (arg.startsWith("--root_pid=")) {
            rootPid = arg.substring(11);
        } else if (arg.startsWith("--father_node=")) {
            fatherNode = arg.substring(14);
        } else if (arg.startsWith("--pid=")) {
            pid = arg.substring(6);
        } else if (arg.startsWith("--context_type")) {
            String keyValue = arg.substring(15);
			int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.setContextType(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }

            }

		} else if (arg.startsWith("--context_param")) {
            String keyValue = arg.substring(16);
            int index = -1;
            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
                if (fatherPid==null) {
                    context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
                } else { // the subjob won't escape the especial chars
                    context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1) );
                }
            }
        } else if (arg.startsWith("--log4jLevel=")) {
            log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {//for trunjob call
		    final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
    }
    
    private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

    private final String[][] escapeChars = {
        {"\\\\","\\"},{"\\n","\n"},{"\\'","\'"},{"\\r","\r"},
        {"\\f","\f"},{"\\b","\b"},{"\\t","\t"}
        };
    private String replaceEscapeChars (String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0],currIndex);
				if (index>=0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
    }

    public Integer getErrorCode() {
        return errorCode;
    }


    public String getStatus() {
        return status;
    }

    ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 *     235050 characters generated by Talend Open Studio for Data Integration 
 *     on the March 27, 2026 at 12:42:45 AM CET
 ************************************************************************************************/