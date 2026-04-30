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


package padel.dim_padel_info_0_1;

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
 * Job: Dim_Padel_Info Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class Dim_Padel_Info implements TalendJob {

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
	private final String jobName = "Dim_Padel_Info";
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
				Dim_Padel_Info.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(Dim_Padel_Info.this, new Object[] { e , currentComponent, globalMap});
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

			public void tFileInputExcel_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tUniqRow_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class saaaStruct implements routines.system.IPersistableRow<saaaStruct> {
    final static byte[] commonByteArrayLock_PADEL_Dim_Padel_Info = new byte[0];
    static byte[] commonByteArray_PADEL_Dim_Padel_Info = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public java.util.Date created_at;

				public java.util.Date getCreated_at () {
					return this.created_at;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public int Id_Padel;

				public int getId_Padel () {
					return this.Id_Padel;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
							result = prime * result + (int) this.Id_Padel;
						
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
		final saaaStruct other = (saaaStruct) obj;
		
						if (this.Id_Padel != other.Id_Padel)
							return false;
					

		return true;
    }

	public void copyDataTo(saaaStruct other) {

		other.title = this.title;
	            other.handle = this.handle;
	            other.product_type = this.product_type;
	            other.tags = this.tags;
	            other.created_at = this.created_at;
	            other.sku = this.sku;
	            other.Id_Padel = this.Id_Padel;
	            other.image = this.image;
	            other.weight = this.weight;
	            other.shape = this.shape;
	            other.foam = this.foam;
	            other.collection = this.collection;
	            other.game_level = this.game_level;
	            other.frame = this.frame;
	            other.surface = this.surface;
	            other.color = this.color;
	            other.racket_type = this.racket_type;
	            other.balance = this.balance;
	            other.racket_cover = this.racket_cover;
	            
	}

	public void copyKeysDataTo(saaaStruct other) {

		other.Id_Padel = this.Id_Padel;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Dim_Padel_Info.length) {
				if(length < 1024 && commonByteArray_PADEL_Dim_Padel_Info.length == 0) {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[1024];
				} else {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Dim_Padel_Info, 0, length);
			strReturn = new String(commonByteArray_PADEL_Dim_Padel_Info, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Dim_Padel_Info.length) {
				if(length < 1024 && commonByteArray_PADEL_Dim_Padel_Info.length == 0) {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[1024];
				} else {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Dim_Padel_Info, 0, length);
			strReturn = new String(commonByteArray_PADEL_Dim_Padel_Info, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Dim_Padel_Info) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
						this.sku = readInteger(dis);
					
			        this.Id_Padel = dis.readInt();
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.racket_cover = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Dim_Padel_Info) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
						this.sku = readInteger(dis);
					
			        this.Id_Padel = dis.readInt();
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.racket_cover = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// int
				
		            	dos.writeInt(this.Id_Padel);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// int
				
		            	dos.writeInt(this.Id_Padel);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",handle="+handle);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+String.valueOf(created_at));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",Id_Padel="+String.valueOf(Id_Padel));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",racket_cover="+racket_cover);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(saaaStruct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Id_Padel, other.Id_Padel);
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

public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
    final static byte[] commonByteArrayLock_PADEL_Dim_Padel_Info = new byte[0];
    static byte[] commonByteArray_PADEL_Dim_Padel_Info = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public java.util.Date created_at;

				public java.util.Date getCreated_at () {
					return this.created_at;
				}
				
			    public Float price;

				public Float getPrice () {
					return this.price;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String professional_player;

				public String getProfessional_player () {
					return this.professional_player;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				
			    public String player_name;

				public String getPlayer_name () {
					return this.player_name;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Dim_Padel_Info.length) {
				if(length < 1024 && commonByteArray_PADEL_Dim_Padel_Info.length == 0) {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[1024];
				} else {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Dim_Padel_Info, 0, length);
			strReturn = new String(commonByteArray_PADEL_Dim_Padel_Info, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Dim_Padel_Info.length) {
				if(length < 1024 && commonByteArray_PADEL_Dim_Padel_Info.length == 0) {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[1024];
				} else {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Dim_Padel_Info, 0, length);
			strReturn = new String(commonByteArray_PADEL_Dim_Padel_Info, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Dim_Padel_Info) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readFloat();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.player_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Dim_Padel_Info) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readFloat();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.player_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Float
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.player_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Float
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.player_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",handle="+handle);
		sb.append(",vendor="+vendor);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+String.valueOf(created_at));
		sb.append(",price="+String.valueOf(price));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",professional_player="+professional_player);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",gender="+gender);
		sb.append(",racket_cover="+racket_cover);
		sb.append(",player_name="+player_name);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row2Struct other) {

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

public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
    final static byte[] commonByteArrayLock_PADEL_Dim_Padel_Info = new byte[0];
    static byte[] commonByteArray_PADEL_Dim_Padel_Info = new byte[0];

	
			    public String title;

				public String getTitle () {
					return this.title;
				}
				
			    public String handle;

				public String getHandle () {
					return this.handle;
				}
				
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public String product_type;

				public String getProduct_type () {
					return this.product_type;
				}
				
			    public String tags;

				public String getTags () {
					return this.tags;
				}
				
			    public java.util.Date created_at;

				public java.util.Date getCreated_at () {
					return this.created_at;
				}
				
			    public Float price;

				public Float getPrice () {
					return this.price;
				}
				
			    public Integer sku;

				public Integer getSku () {
					return this.sku;
				}
				
			    public String image;

				public String getImage () {
					return this.image;
				}
				
			    public String weight;

				public String getWeight () {
					return this.weight;
				}
				
			    public String shape;

				public String getShape () {
					return this.shape;
				}
				
			    public String foam;

				public String getFoam () {
					return this.foam;
				}
				
			    public String collection;

				public String getCollection () {
					return this.collection;
				}
				
			    public String game_level;

				public String getGame_level () {
					return this.game_level;
				}
				
			    public String frame;

				public String getFrame () {
					return this.frame;
				}
				
			    public String surface;

				public String getSurface () {
					return this.surface;
				}
				
			    public String professional_player;

				public String getProfessional_player () {
					return this.professional_player;
				}
				
			    public String color;

				public String getColor () {
					return this.color;
				}
				
			    public String racket_type;

				public String getRacket_type () {
					return this.racket_type;
				}
				
			    public String balance;

				public String getBalance () {
					return this.balance;
				}
				
			    public String gender;

				public String getGender () {
					return this.gender;
				}
				
			    public String racket_cover;

				public String getRacket_cover () {
					return this.racket_cover;
				}
				
			    public String player_name;

				public String getPlayer_name () {
					return this.player_name;
				}
				



	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Dim_Padel_Info.length) {
				if(length < 1024 && commonByteArray_PADEL_Dim_Padel_Info.length == 0) {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[1024];
				} else {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Dim_Padel_Info, 0, length);
			strReturn = new String(commonByteArray_PADEL_Dim_Padel_Info, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Dim_Padel_Info.length) {
				if(length < 1024 && commonByteArray_PADEL_Dim_Padel_Info.length == 0) {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[1024];
				} else {
   					commonByteArray_PADEL_Dim_Padel_Info = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Dim_Padel_Info, 0, length);
			strReturn = new String(commonByteArray_PADEL_Dim_Padel_Info, 0, length, utf8Charset);
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

    public void readData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Dim_Padel_Info) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readFloat();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.player_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Dim_Padel_Info) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
					this.vendor = readString(dis);
					
					this.product_type = readString(dis);
					
					this.tags = readString(dis);
					
					this.created_at = readDate(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readFloat();
           				}
					
						this.sku = readInteger(dis);
					
					this.image = readString(dis);
					
					this.weight = readString(dis);
					
					this.shape = readString(dis);
					
					this.foam = readString(dis);
					
					this.collection = readString(dis);
					
					this.game_level = readString(dis);
					
					this.frame = readString(dis);
					
					this.surface = readString(dis);
					
					this.professional_player = readString(dis);
					
					this.color = readString(dis);
					
					this.racket_type = readString(dis);
					
					this.balance = readString(dis);
					
					this.gender = readString(dis);
					
					this.racket_cover = readString(dis);
					
					this.player_name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Float
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.player_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
					// String
				
						writeString(this.vendor,dos);
					
					// String
				
						writeString(this.product_type,dos);
					
					// String
				
						writeString(this.tags,dos);
					
					// java.util.Date
				
						writeDate(this.created_at,dos);
					
					// Float
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.price);
		            	}
					
					// Integer
				
						writeInteger(this.sku,dos);
					
					// String
				
						writeString(this.image,dos);
					
					// String
				
						writeString(this.weight,dos);
					
					// String
				
						writeString(this.shape,dos);
					
					// String
				
						writeString(this.foam,dos);
					
					// String
				
						writeString(this.collection,dos);
					
					// String
				
						writeString(this.game_level,dos);
					
					// String
				
						writeString(this.frame,dos);
					
					// String
				
						writeString(this.surface,dos);
					
					// String
				
						writeString(this.professional_player,dos);
					
					// String
				
						writeString(this.color,dos);
					
					// String
				
						writeString(this.racket_type,dos);
					
					// String
				
						writeString(this.balance,dos);
					
					// String
				
						writeString(this.gender,dos);
					
					// String
				
						writeString(this.racket_cover,dos);
					
					// String
				
						writeString(this.player_name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("title="+title);
		sb.append(",handle="+handle);
		sb.append(",vendor="+vendor);
		sb.append(",product_type="+product_type);
		sb.append(",tags="+tags);
		sb.append(",created_at="+String.valueOf(created_at));
		sb.append(",price="+String.valueOf(price));
		sb.append(",sku="+String.valueOf(sku));
		sb.append(",image="+image);
		sb.append(",weight="+weight);
		sb.append(",shape="+shape);
		sb.append(",foam="+foam);
		sb.append(",collection="+collection);
		sb.append(",game_level="+game_level);
		sb.append(",frame="+frame);
		sb.append(",surface="+surface);
		sb.append(",professional_player="+professional_player);
		sb.append(",color="+color);
		sb.append(",racket_type="+racket_type);
		sb.append(",balance="+balance);
		sb.append(",gender="+gender);
		sb.append(",racket_cover="+racket_cover);
		sb.append(",player_name="+player_name);
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
public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

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



		row1Struct row1 = new row1Struct();
row2Struct row2 = new row2Struct();
saaaStruct saaa = new saaaStruct();






	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"saaa");
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

String tableName_tDBOutput_1 = "dim_padel_info";
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
		

		 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:KDJWB77pY8wylMwDd9JC9/HbJlChtEfjZH8ezQ==");

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
                                    if(table_tDBOutput_1.equalsIgnoreCase("dim_padel_info")) {
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
                                    stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1 + "`(`title` VARCHAR(255)  ,`handle` VARCHAR(255)  ,`product_type` VARCHAR(255)  ,`tags` VARCHAR(255)  ,`created_at` DATETIME ,`sku` INT(255)  ,`Id_Padel` INT(10)   not null ,`image` VARCHAR(255)  ,`weight` VARCHAR(255)  ,`shape` VARCHAR(7)  ,`foam` VARCHAR(255)  ,`collection` VARCHAR(255)  ,`game_level` VARCHAR(255)  ,`frame` VARCHAR(255)  ,`surface` VARCHAR(255)  ,`color` VARCHAR(255)  ,`racket_type` VARCHAR(255)  ,`balance` VARCHAR(255)  ,`racket_cover` VARCHAR(255)  ,primary key(`Id_Padel`))");
                                }

				String insert_tDBOutput_1 = "INSERT INTO `" + "dim_padel_info" + "` (`title`,`handle`,`product_type`,`tags`,`created_at`,`sku`,`Id_Padel`,`image`,`weight`,`shape`,`foam`,`collection`,`game_level`,`frame`,`surface`,`color`,`racket_type`,`balance`,`racket_cover`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row2");
					}
				
		int tos_count_tMap_1 = 0;
		




// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
saaaStruct saaa_tmp = new saaaStruct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
 */



	
	/**
	 * [tUniqRow_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tUniqRow_1", false);
		start_Hash.put("tUniqRow_1", System.currentTimeMillis());
		
	
	currentComponent="tUniqRow_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"row1");
					}
				
		int tos_count_tUniqRow_1 = 0;
		

	
		class KeyStruct_tUniqRow_1 {
	
			private static final int DEFAULT_HASHCODE = 1;
		    private static final int PRIME = 31;
		    private int hashCode = DEFAULT_HASHCODE;
		    public boolean hashCodeDirty = true;
	
	        
					String title;
					String handle;
					java.util.Date created_at;        
	        
		    @Override
			public int hashCode() {
				if (this.hashCodeDirty) {
					final int prime = PRIME;
					int result = DEFAULT_HASHCODE;
			
								result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
								
								result = prime * result + ((this.handle == null) ? 0 : this.handle.hashCode());
								
								result = prime * result + ((this.created_at == null) ? 0 : this.created_at.hashCode());
								
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
				final KeyStruct_tUniqRow_1 other = (KeyStruct_tUniqRow_1) obj;
				
									if (this.title == null) {
										if (other.title != null) 
											return false;
								
									} else if (!this.title.equals(other.title))
								 
										return false;
								
									if (this.handle == null) {
										if (other.handle != null) 
											return false;
								
									} else if (!this.handle.equals(other.handle))
								 
										return false;
								
									if (this.created_at == null) {
										if (other.created_at != null) 
											return false;
								
									} else if (!this.created_at.equals(other.created_at))
								 
										return false;
								
				
				return true;
			}
	  
	        
		}

	
int nb_uniques_tUniqRow_1 = 0;
int nb_duplicates_tUniqRow_1 = 0;
KeyStruct_tUniqRow_1 finder_tUniqRow_1 = new KeyStruct_tUniqRow_1();
java.util.Set<KeyStruct_tUniqRow_1> keystUniqRow_1 = new java.util.HashSet<KeyStruct_tUniqRow_1>(); 

 



/**
 * [tUniqRow_1 begin ] stop
 */



	
	/**
	 * [tFileInputExcel_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tFileInputExcel_1", false);
		start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());
		
	
	currentComponent="tFileInputExcel_1";

	
		int tos_count_tFileInputExcel_1 = 0;
		

 
	final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:evbpPvVPT5AqDTrUjTJ8BMMSgYTcduZeosmbMg==");
        String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
        if (password_tFileInputExcel_1.isEmpty()){
            password_tFileInputExcel_1 = null;
        }
			class RegexUtil_tFileInputExcel_1 {

		    	public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName, boolean useRegex) {

			        java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

			        if(useRegex){//this part process the regex issue

				        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
				        for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
				            String sheetName = sheet.getSheetName();
				            java.util.regex.Matcher matcher = pattern.matcher(sheetName);
				            if (matcher.matches()) {
				            	if(sheet != null){
				                	list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
				                }
				            }
				        }

			        }else{
			        	org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook.getSheet(oneSheetName);
		            	if(sheet != null){
		                	list.add(sheet);
		                }

			        }

			        return list;
			    }

			    public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
			    	java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list =  new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
			    	org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook.getSheetAt(index);
	            	if(sheet != null){
	                	list.add(sheet);
	                }
			    	return list;
			    }

			}
		RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

		Object source_tFileInputExcel_1 = "C:/Users/arwaa/Downloads/padel_rackets_with_player.xlsx";
		org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

		if(source_tFileInputExcel_1 instanceof String){
			workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory.create(new java.io.File((String)source_tFileInputExcel_1), password_tFileInputExcel_1, true);
		} else if(source_tFileInputExcel_1 instanceof java.io.InputStream) {
     		workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory.create((java.io.InputStream)source_tFileInputExcel_1, password_tFileInputExcel_1);
		} else{
			workbook_tFileInputExcel_1 = null;
			throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
		}
		try {

    	java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
    	for(org.apache.poi.ss.usermodel.Sheet sheet_tFileInputExcel_1 : workbook_tFileInputExcel_1){
   			sheetList_tFileInputExcel_1.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet_tFileInputExcel_1);
    	}
    	if(sheetList_tFileInputExcel_1.size() <= 0){
            throw new RuntimeException("Special sheets not exist!");
        }

		java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
		for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
			if(sheet_FilterNull_tFileInputExcel_1!=null && sheetList_FilterNull_tFileInputExcel_1.iterator()!=null && sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()){
				sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
			}
		}
		sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
	if(sheetList_tFileInputExcel_1.size()>0){
		int nb_line_tFileInputExcel_1 = 0;

        int begin_line_tFileInputExcel_1 = 1;

        int footer_input_tFileInputExcel_1 = 0;

        int end_line_tFileInputExcel_1=0;
        for(org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1:sheetList_tFileInputExcel_1){
			end_line_tFileInputExcel_1+=(sheet_tFileInputExcel_1.getLastRowNum()+1);
        }
        end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
        int limit_tFileInputExcel_1 = -1;
        int start_column_tFileInputExcel_1 = 1-1;
        int end_column_tFileInputExcel_1 = -1;

        org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
        org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0);
        int rowCount_tFileInputExcel_1 = 0;
        int sheetIndex_tFileInputExcel_1 = 0;
        int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum()+1);

		//for the number format
        java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat("#.####################################");
        char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols().getDecimalSeparator();
		
        for(int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++){

        	int emptyColumnCount_tFileInputExcel_1 = 0;

        	if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
        		break;
        	}

            while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
                rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
                sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(++sheetIndex_tFileInputExcel_1);
                currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum()+1);
            }
            globalMap.put("tFileInputExcel_1_CURRENT_SHEET",sheet_tFileInputExcel_1.getSheetName());
            if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
                row_tFileInputExcel_1 = sheet_tFileInputExcel_1.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
            }
		    row1 = null;
					int tempRowLength_tFileInputExcel_1 = 23;
				
				int columnIndex_tFileInputExcel_1 = 0;
			
			String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
			int excel_end_column_tFileInputExcel_1;
			if(row_tFileInputExcel_1==null){
				excel_end_column_tFileInputExcel_1=0;
			}else{
				excel_end_column_tFileInputExcel_1=row_tFileInputExcel_1.getLastCellNum();
			}
			int actual_end_column_tFileInputExcel_1;
			if(end_column_tFileInputExcel_1 == -1){
				actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
			}
			else{
				actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 >	excel_end_column_tFileInputExcel_1 ? excel_end_column_tFileInputExcel_1 : end_column_tFileInputExcel_1;
			}
			org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
			for(int i=0;i<tempRowLength_tFileInputExcel_1;i++){
				if(i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1){
					org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1.getCell(i + start_column_tFileInputExcel_1);
					if(cell_tFileInputExcel_1!=null){
					switch (cell_tFileInputExcel_1.getCellType()) {
                        case STRING:
                            temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getRichStringCellValue().getString();
                            break;
                        case NUMERIC:
                            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell_tFileInputExcel_1)) {
									temp_row_tFileInputExcel_1[i] =cell_tFileInputExcel_1.getDateCellValue().toString();
                            } else {
                                temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1.format(cell_tFileInputExcel_1.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            temp_row_tFileInputExcel_1[i] =String.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
                            break;
                        case FORMULA:
        					switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
                                case STRING:
                                    temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getRichStringCellValue().getString();
                                    break;
                                case NUMERIC:
                                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell_tFileInputExcel_1)) {
											temp_row_tFileInputExcel_1[i] =cell_tFileInputExcel_1.getDateCellValue().toString();
                                    } else {
	                                    ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(cell_tFileInputExcel_1.getNumericCellValue());
										temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1.getStringValue();
                                    }
                                    break;
                                case BOOLEAN:
                                    temp_row_tFileInputExcel_1[i] =String.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
                                    break;
                                default:
                            		temp_row_tFileInputExcel_1[i] = "";
                            }
                            break;
                        default:
                            temp_row_tFileInputExcel_1[i] = "";
                        }
                	}
                	else{
                		temp_row_tFileInputExcel_1[i]="";
                	}

				}else{
					temp_row_tFileInputExcel_1[i]="";
				}
			}
			boolean whetherReject_tFileInputExcel_1 = false;
			row1 = new row1Struct();
			int curColNum_tFileInputExcel_1 = -1;
			String curColName_tFileInputExcel_1 = "";
			try{
							columnIndex_tFileInputExcel_1 = 0;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "title";

				row1.title = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.title = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 1;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "handle";

				row1.handle = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.handle = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 2;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "vendor";

				row1.vendor = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.vendor = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 3;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "product_type";

				row1.product_type = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.product_type = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 4;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "tags";

				row1.tags = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.tags = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 5;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "created_at";

				if(5<actual_end_column_tFileInputExcel_1){
					try{
						if(row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1+ start_column_tFileInputExcel_1).getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC && org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1+ start_column_tFileInputExcel_1))){
							row1.created_at = row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1+ start_column_tFileInputExcel_1).getDateCellValue();
						}
						else{
                            java.util.Date tempDate_tFileInputExcel_1 = ParserUtils.parseTo_Date(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], "dd-MM-yyyy");
                            if(tempDate_tFileInputExcel_1.after((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS")).parse("9999/12/31 23:59:59.999"))||tempDate_tFileInputExcel_1.before((new SimpleDateFormat("yyyy/MM/dd")).parse("1900/01/01"))){
                                throw new RuntimeException("The cell format is not Date in ( Row. "+(nb_line_tFileInputExcel_1+1)+ " and ColumnNum. " + curColNum_tFileInputExcel_1 + " )");
                            }else{
                                row1.created_at = tempDate_tFileInputExcel_1;
                            }
						}
					}catch(java.lang.Exception e){
globalMap.put("tFileInputExcel_1_ERROR_MESSAGE",e.getMessage());
						
						throw new RuntimeException("The cell format is not Date in ( Row. "+(nb_line_tFileInputExcel_1+1)+ " and ColumnNum. " + curColNum_tFileInputExcel_1 + " )");
					}
				}

			}else{
				row1.created_at = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 6;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "price";

				row1.price = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.price = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 7;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "sku";

				row1.sku = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null, '.'==decimalChar_tFileInputExcel_1 ? null : decimalChar_tFileInputExcel_1));
			}else{
				row1.sku = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 8;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "image";

				row1.image = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.image = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 9;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "weight";

				row1.weight = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.weight = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 10;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "shape";

				row1.shape = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.shape = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 11;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "foam";

				row1.foam = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.foam = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 12;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "collection";

				row1.collection = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.collection = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 13;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "game_level";

				row1.game_level = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.game_level = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 14;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "frame";

				row1.frame = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.frame = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 15;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "surface";

				row1.surface = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.surface = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 16;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "professional_player";

				row1.professional_player = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.professional_player = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 17;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "color";

				row1.color = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.color = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 18;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "racket_type";

				row1.racket_type = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.racket_type = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 19;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "balance";

				row1.balance = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.balance = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 20;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "gender";

				row1.gender = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.gender = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 21;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "racket_cover";

				row1.racket_cover = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.racket_cover = null;
				emptyColumnCount_tFileInputExcel_1++;
			}
							columnIndex_tFileInputExcel_1 = 22;
						
			if( temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
				curColNum_tFileInputExcel_1=columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1 + 1;
				curColName_tFileInputExcel_1 = "player_name";

				row1.player_name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
			}else{
				row1.player_name = null;
				emptyColumnCount_tFileInputExcel_1++;
			}

				nb_line_tFileInputExcel_1++;
				
			}catch(java.lang.Exception e){
globalMap.put("tFileInputExcel_1_ERROR_MESSAGE",e.getMessage());
			whetherReject_tFileInputExcel_1 = true;
					 System.err.println(e.getMessage());
					 row1 = null;
			}


		



 



/**
 * [tFileInputExcel_1 begin ] stop
 */
	
	/**
	 * [tFileInputExcel_1 main ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 


	tos_count_tFileInputExcel_1++;

/**
 * [tFileInputExcel_1 main ] stop
 */
	
	/**
	 * [tFileInputExcel_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 process_data_begin ] stop
 */
// Start of branch "row1"
if(row1 != null) { 



	
	/**
	 * [tUniqRow_1 main ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row1"
						
						);
					}
					
row2 = null;			
if(row1.title == null){
	finder_tUniqRow_1.title = null;
}else{
	finder_tUniqRow_1.title = row1.title.toLowerCase();
}
if(row1.handle == null){
	finder_tUniqRow_1.handle = null;
}else{
	finder_tUniqRow_1.handle = row1.handle.toLowerCase();
}
finder_tUniqRow_1.created_at = row1.created_at;	
finder_tUniqRow_1.hashCodeDirty = true;
if (!keystUniqRow_1.contains(finder_tUniqRow_1)) {
		KeyStruct_tUniqRow_1 new_tUniqRow_1 = new KeyStruct_tUniqRow_1();

		
if(row1.title == null){
	new_tUniqRow_1.title = null;
}else{
	new_tUniqRow_1.title = row1.title.toLowerCase();
}
if(row1.handle == null){
	new_tUniqRow_1.handle = null;
}else{
	new_tUniqRow_1.handle = row1.handle.toLowerCase();
}
new_tUniqRow_1.created_at = row1.created_at;
		
		keystUniqRow_1.add(new_tUniqRow_1);if(row2 == null){ 
	
	row2 = new row2Struct();
}row2.title = row1.title;			row2.handle = row1.handle;			row2.vendor = row1.vendor;			row2.product_type = row1.product_type;			row2.tags = row1.tags;			row2.created_at = row1.created_at;			row2.price = row1.price;			row2.sku = row1.sku;			row2.image = row1.image;			row2.weight = row1.weight;			row2.shape = row1.shape;			row2.foam = row1.foam;			row2.collection = row1.collection;			row2.game_level = row1.game_level;			row2.frame = row1.frame;			row2.surface = row1.surface;			row2.professional_player = row1.professional_player;			row2.color = row1.color;			row2.racket_type = row1.racket_type;			row2.balance = row1.balance;			row2.gender = row1.gender;			row2.racket_cover = row1.racket_cover;			row2.player_name = row1.player_name;					
		nb_uniques_tUniqRow_1++;
	} else {
	  nb_duplicates_tUniqRow_1++;
	}

 


	tos_count_tUniqRow_1++;

/**
 * [tUniqRow_1 main ] stop
 */
	
	/**
	 * [tUniqRow_1 process_data_begin ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 process_data_begin ] stop
 */
// Start of branch "row2"
if(row2 != null) { 



	
	/**
	 * [tMap_1 main ] start
	 */

	

	
	
	currentComponent="tMap_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row2"
						
						);
					}
					

		
		
		boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;
		

        // ###############################
        // # Input tables (lookups)
		  boolean rejectedInnerJoin_tMap_1 = false;
		  boolean mainRowRejected_tMap_1 = false;
            				    								  
		// ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

saaa = null;


// # Output table : 'saaa'
saaa_tmp.title = row2.title ;
saaa_tmp.handle = row2.handle ;
saaa_tmp.product_type = row2.product_type ;
saaa_tmp.tags = row2.tags ;
saaa_tmp.created_at = row2.created_at ;
saaa_tmp.sku = row2.sku ;
saaa_tmp.Id_Padel = Numeric.sequence("s1",1,1) ;
saaa_tmp.image = row2.image ;
saaa_tmp.weight = row2.weight ;
saaa_tmp.shape = row2.shape ;
saaa_tmp.foam = row2.foam ;
saaa_tmp.collection = row2.collection ;
saaa_tmp.game_level = row2.game_level ;
saaa_tmp.frame = row2.frame ;
saaa_tmp.surface = row2.surface ;
saaa_tmp.color = row2.color ;
saaa_tmp.racket_type = row2.racket_type ;
saaa_tmp.balance = row2.balance ;
saaa_tmp.racket_cover = row2.racket_cover ;
saaa = saaa_tmp;
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
// Start of branch "saaa"
if(saaa != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"saaa"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;
                            if(saaa.title == null) {
pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(1, saaa.title);
}

                            if(saaa.handle == null) {
pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(2, saaa.handle);
}

                            if(saaa.product_type == null) {
pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(3, saaa.product_type);
}

                            if(saaa.tags == null) {
pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(4, saaa.tags);
}

                            if(saaa.created_at != null) {
date_tDBOutput_1 = saaa.created_at.getTime();
if(date_tDBOutput_1 < year1_tDBOutput_1 || date_tDBOutput_1 >= year10000_tDBOutput_1) {
pstmt_tDBOutput_1.setString(5, "0000-00-00 00:00:00");
} else {pstmt_tDBOutput_1.setTimestamp(5, new java.sql.Timestamp(date_tDBOutput_1));
}
} else {
pstmt_tDBOutput_1.setNull(5, java.sql.Types.DATE);
}

                            if(saaa.sku == null) {
pstmt_tDBOutput_1.setNull(6, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(6, saaa.sku);
}

                            pstmt_tDBOutput_1.setInt(7, saaa.Id_Padel);

                            if(saaa.image == null) {
pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(8, saaa.image);
}

                            if(saaa.weight == null) {
pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(9, saaa.weight);
}

                            if(saaa.shape == null) {
pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(10, saaa.shape);
}

                            if(saaa.foam == null) {
pstmt_tDBOutput_1.setNull(11, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(11, saaa.foam);
}

                            if(saaa.collection == null) {
pstmt_tDBOutput_1.setNull(12, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(12, saaa.collection);
}

                            if(saaa.game_level == null) {
pstmt_tDBOutput_1.setNull(13, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(13, saaa.game_level);
}

                            if(saaa.frame == null) {
pstmt_tDBOutput_1.setNull(14, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(14, saaa.frame);
}

                            if(saaa.surface == null) {
pstmt_tDBOutput_1.setNull(15, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(15, saaa.surface);
}

                            if(saaa.color == null) {
pstmt_tDBOutput_1.setNull(16, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(16, saaa.color);
}

                            if(saaa.racket_type == null) {
pstmt_tDBOutput_1.setNull(17, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(17, saaa.racket_type);
}

                            if(saaa.balance == null) {
pstmt_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(18, saaa.balance);
}

                            if(saaa.racket_cover == null) {
pstmt_tDBOutput_1.setNull(19, java.sql.Types.VARCHAR);
} else {pstmt_tDBOutput_1.setString(19, saaa.racket_cover);
}

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

} // End of branch "saaa"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
 */

} // End of branch "row2"




	
	/**
	 * [tUniqRow_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 process_data_end ] stop
 */

} // End of branch "row1"




	
	/**
	 * [tFileInputExcel_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 process_data_end ] stop
 */
	
	/**
	 * [tFileInputExcel_1 end ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

			}
			
			
			
			globalMap.put("tFileInputExcel_1_NB_LINE",nb_line_tFileInputExcel_1);
			
				}
			
		} finally { 
				
  				if(!(source_tFileInputExcel_1 instanceof java.io.InputStream)){
  					workbook_tFileInputExcel_1.getPackage().revert();
  				}
				
		}	
		

 

ok_Hash.put("tFileInputExcel_1", true);
end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());




/**
 * [tFileInputExcel_1 end ] stop
 */

	
	/**
	 * [tUniqRow_1 end ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

globalMap.put("tUniqRow_1_NB_UNIQUES",nb_uniques_tUniqRow_1);
globalMap.put("tUniqRow_1_NB_DUPLICATES",nb_duplicates_tUniqRow_1);

				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row1");
			  	}
			  	
 

ok_Hash.put("tUniqRow_1", true);
end_Hash.put("tUniqRow_1", System.currentTimeMillis());




/**
 * [tUniqRow_1 end ] stop
 */

	
	/**
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
// ###############################      





				if(execStat){
			  		runStat.updateStat(resourceMap,iterateId,2,0,"row2");
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"saaa");
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
				
				try{
					
	
	/**
	 * [tFileInputExcel_1 finally ] start
	 */

	

	
	
	currentComponent="tFileInputExcel_1";

	

 



/**
 * [tFileInputExcel_1 finally ] stop
 */

	
	/**
	 * [tUniqRow_1 finally ] start
	 */

	

	
	
	currentComponent="tUniqRow_1";

	

 



/**
 * [tUniqRow_1 finally ] stop
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
		

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
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
        final Dim_Padel_Info Dim_Padel_InfoClass = new Dim_Padel_Info();

        int exitCode = Dim_Padel_InfoClass.runJobInTOS(args);

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
            java.io.InputStream inContext = Dim_Padel_Info.class.getClassLoader().getResourceAsStream("padel/dim_padel_info_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = Dim_Padel_Info.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
errorCode = null;tFileInputExcel_1Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tFileInputExcel_1) {
globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

e_tFileInputExcel_1.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Dim_Padel_Info");
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
 *     124462 characters generated by Talend Open Studio for Data Integration 
 *     on the March 27, 2026 at 12:42:43 AM CET
 ************************************************************************************************/