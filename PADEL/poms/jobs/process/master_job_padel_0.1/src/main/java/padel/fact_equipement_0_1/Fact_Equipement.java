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


package padel.fact_equipement_0_1;

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
 * Job: Fact_Equipement Purpose: <br>
 * Description:  <br>
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status 
 */
public class Fact_Equipement implements TalendJob {

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
	private final String jobName = "Fact_Equipement";
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
				Fact_Equipement.this.exception = e;
			}
		}
		if (!(e instanceof TalendException)) {
		try {
			for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
				if (m.getName().compareTo(currentComponent + "_error") == 0) {
					m.invoke(Fact_Equipement.this, new Object[] { e , currentComponent, globalMap});
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

			public void tDBInput_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBOutput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tAdvancedHash_row2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {
				
				end_Hash.put(errorComponent, System.currentTimeMillis());
				
				status = "failure";
				
					tDBInput_5_onSubJobError(exception, errorComponent, globalMap);
			}
			
			public void tDBInput_5_onSubJobError(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap) throws TalendException {

resumeUtil.addLog("SYSTEM_LOG", "NODE:"+ errorComponent, "", Thread.currentThread().getId()+ "", "FATAL", "", exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception),"");

			}
	






public static class Fact_EquipementStruct implements routines.system.IPersistableRow<Fact_EquipementStruct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Equipement = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Equipement = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public int Id_Padel;

				public int getId_Padel () {
					return this.Id_Padel;
				}
				
			    public int DateKey;

				public int getDateKey () {
					return this.DateKey;
				}
				
			    public int Id_Player;

				public int getId_Player () {
					return this.Id_Player;
				}
				
			    public int Id_vendor;

				public int getId_vendor () {
					return this.Id_vendor;
				}
				
			    public Integer Id_Fact;

				public Integer getId_Fact () {
					return this.Id_Fact;
				}
				
			    public Float price;

				public Float getPrice () {
					return this.price;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.Id_Fact == null) ? 0 : this.Id_Fact.hashCode());
					
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
		final Fact_EquipementStruct other = (Fact_EquipementStruct) obj;
		
						if (this.Id_Fact == null) {
							if (other.Id_Fact != null)
								return false;
						
						} else if (!this.Id_Fact.equals(other.Id_Fact))
						
							return false;
					

		return true;
    }

	public void copyDataTo(Fact_EquipementStruct other) {

		other.Id_Padel = this.Id_Padel;
	            other.DateKey = this.DateKey;
	            other.Id_Player = this.Id_Player;
	            other.Id_vendor = this.Id_vendor;
	            other.Id_Fact = this.Id_Fact;
	            other.price = this.price;
	            
	}

	public void copyKeysDataTo(Fact_EquipementStruct other) {

		other.Id_Fact = this.Id_Fact;
	            	
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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
			        this.Id_Padel = dis.readInt();
					
			        this.DateKey = dis.readInt();
					
			        this.Id_Player = dis.readInt();
					
			        this.Id_vendor = dis.readInt();
					
						this.Id_Fact = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
			        this.Id_Padel = dis.readInt();
					
			        this.DateKey = dis.readInt();
					
			        this.Id_Player = dis.readInt();
					
			        this.Id_vendor = dis.readInt();
					
						this.Id_Fact = readInteger(dis);
					
			            length = dis.readByte();
           				if (length == -1) {
           	    			this.price = null;
           				} else {
           			    	this.price = dis.readFloat();
           				}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeData(ObjectOutputStream dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.Id_Padel);
					
					// int
				
		            	dos.writeInt(this.DateKey);
					
					// int
				
		            	dos.writeInt(this.Id_Player);
					
					// int
				
		            	dos.writeInt(this.Id_vendor);
					
					// Integer
				
						writeInteger(this.Id_Fact,dos);
					
					// Float
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.price);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// int
				
		            	dos.writeInt(this.Id_Padel);
					
					// int
				
		            	dos.writeInt(this.DateKey);
					
					// int
				
		            	dos.writeInt(this.Id_Player);
					
					// int
				
		            	dos.writeInt(this.Id_vendor);
					
					// Integer
				
						writeInteger(this.Id_Fact,dos);
					
					// Float
				
						if(this.price == null) {
			                dos.writeByte(-1);
						} else {
               				dos.writeByte(0);
           			    	dos.writeFloat(this.price);
		            	}
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }


    public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("[");
		sb.append("Id_Padel="+String.valueOf(Id_Padel));
		sb.append(",DateKey="+String.valueOf(DateKey));
		sb.append(",Id_Player="+String.valueOf(Id_Player));
		sb.append(",Id_vendor="+String.valueOf(Id_vendor));
		sb.append(",Id_Fact="+String.valueOf(Id_Fact));
		sb.append(",price="+String.valueOf(price));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(Fact_EquipementStruct other) {

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
    final static byte[] commonByteArrayLock_PADEL_Fact_Equipement = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Equipement = new byte[0];

	
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
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

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

public static class after_tDBInput_5Struct implements routines.system.IPersistableRow<after_tDBInput_5Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Equipement = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Equipement = new byte[0];

	
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
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

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
    public int compareTo(after_tDBInput_5Struct other) {

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


		tDBInput_3Process(globalMap);
		tDBInput_2Process(globalMap);
		tDBInput_1Process(globalMap);
		tDBInput_4Process(globalMap);

		row1Struct row1 = new row1Struct();
Fact_EquipementStruct Fact_Equipement = new Fact_EquipementStruct();





	
	/**
	 * [tDBOutput_1 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBOutput_1", false);
		start_Hash.put("tDBOutput_1", System.currentTimeMillis());
		
	
	currentComponent="tDBOutput_1";

	
					if(execStat) {
						runStat.updateStatOnConnection(resourceMap,iterateId,0,0,"Fact_Equipement");
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

String tableName_tDBOutput_1 = "Fact_Equipement";
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
		

		 
	final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:CHV4jot49S0DdZ2BaRRAo8n8rbwNYMAjXWFGWA==");

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
                                    if(table_tDBOutput_1.equalsIgnoreCase("Fact_Equipement")) {
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
                                    stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1 + "`(`Id_Padel` INT(10)   not null ,`DateKey` INT(10)   not null ,`Id_Player` INT(10)   not null ,`Id_vendor` INT(10)   not null ,`Id_Fact` INT(10)  ,`price` FLOAT(5,2)  ,primary key(`Id_Fact`))");
                                }

				String insert_tDBOutput_1 = "INSERT INTO `" + "Fact_Equipement" + "` (`Id_Padel`,`DateKey`,`Id_Player`,`Id_vendor`,`Id_Fact`,`price`) VALUES (?,?,?,?,?,?)";
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
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) 
					globalMap.get( "tHash_Lookup_row3" ))
					;					
					
	

row3Struct row3HashKey = new row3Struct();
row3Struct row3Default = new row3Struct();
	
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
	
		org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) 
				((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) 
					globalMap.get( "tHash_Lookup_row2" ))
					;					
					
	

row2Struct row2HashKey = new row2Struct();
row2Struct row2Default = new row2Struct();
// ###############################        

// ###############################
// # Vars initialization
class  Var__tMap_1__Struct  {
}
Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
Fact_EquipementStruct Fact_Equipement_tmp = new Fact_EquipementStruct();
// ###############################

        
        



        









 



/**
 * [tMap_1 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_5 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:J+ngphhCjtQqp5Tt2IApI4F7MrlZHieaEZaeTg==");
				
				String dbPwd_tDBInput_5 = decryptedPassword_tDBInput_5;
				
        String properties_tDBInput_5 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_5 == null || properties_tDBInput_5.trim().length() == 0) {
            properties_tDBInput_5 = "";
        }
        String url_tDBInput_5 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "padel_analytics" + "?" + properties_tDBInput_5;
				
				conn_tDBInput_5 = java.sql.DriverManager.getConnection(url_tDBInput_5,dbUser_tDBInput_5,dbPwd_tDBInput_5);
		        
		    
			java.sql.Statement stmt_tDBInput_5 = conn_tDBInput_5.createStatement();

		    String dbquery_tDBInput_5 = "SELECT \n  `sa_padel_info_ofc`.`title`, \n  `sa_padel_info_ofc`.`handle`, \n  `sa_padel_info_ofc`.`vendor`, \n  `sa_padel_i"
+"nfo_ofc`.`product_type`, \n  `sa_padel_info_ofc`.`tags`, \n  `sa_padel_info_ofc`.`created_at`, \n  `sa_padel_info_ofc`.`pri"
+"ce`, \n  `sa_padel_info_ofc`.`sku`, \n  `sa_padel_info_ofc`.`image`, \n  `sa_padel_info_ofc`.`weight`, \n  `sa_padel_info_of"
+"c`.`shape`, \n  `sa_padel_info_ofc`.`foam`, \n  `sa_padel_info_ofc`.`collection`, \n  `sa_padel_info_ofc`.`game_level`, \n  "
+"`sa_padel_info_ofc`.`frame`, \n  `sa_padel_info_ofc`.`surface`, \n  `sa_padel_info_ofc`.`professional_player`, \n  `sa_pade"
+"l_info_ofc`.`color`, \n  `sa_padel_info_ofc`.`racket_type`, \n  `sa_padel_info_ofc`.`balance`, \n  `sa_padel_info_ofc`.`gen"
+"der`, \n  `sa_padel_info_ofc`.`racket_cover`, \n  `sa_padel_info_ofc`.`player_name`\nFROM `sa_padel_info_ofc`";
		    

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
								row1.title = null;
							} else {
	                         		
        	row1.title = routines.system.JDBCUtil.getString(rs_tDBInput_5, 1, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 2) {
								row1.handle = null;
							} else {
	                         		
        	row1.handle = routines.system.JDBCUtil.getString(rs_tDBInput_5, 2, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 3) {
								row1.vendor = null;
							} else {
	                         		
        	row1.vendor = routines.system.JDBCUtil.getString(rs_tDBInput_5, 3, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 4) {
								row1.product_type = null;
							} else {
	                         		
        	row1.product_type = routines.system.JDBCUtil.getString(rs_tDBInput_5, 4, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 5) {
								row1.tags = null;
							} else {
	                         		
        	row1.tags = routines.system.JDBCUtil.getString(rs_tDBInput_5, 5, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 6) {
								row1.created_at = null;
							} else {
										
				if(rs_tDBInput_5.getString(6) != null) {
					String dateString_tDBInput_5 = rs_tDBInput_5.getString(6);
					if (!("0000-00-00").equals(dateString_tDBInput_5) && !("0000-00-00 00:00:00").equals(dateString_tDBInput_5)) {
						row1.created_at = rs_tDBInput_5.getTimestamp(6);
					} else {
						row1.created_at = (java.util.Date) year0_tDBInput_5.clone();
					}
				} else {
					row1.created_at =  null;
				}
		                    }
							if(colQtyInRs_tDBInput_5 < 7) {
								row1.price = null;
							} else {
		                          
            row1.price = rs_tDBInput_5.getFloat(7);
            if(rs_tDBInput_5.wasNull()){
                    row1.price = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 8) {
								row1.sku = null;
							} else {
		                          
            row1.sku = rs_tDBInput_5.getInt(8);
            if(rs_tDBInput_5.wasNull()){
                    row1.sku = null;
            }
		                    }
							if(colQtyInRs_tDBInput_5 < 9) {
								row1.image = null;
							} else {
	                         		
        	row1.image = routines.system.JDBCUtil.getString(rs_tDBInput_5, 9, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 10) {
								row1.weight = null;
							} else {
	                         		
        	row1.weight = routines.system.JDBCUtil.getString(rs_tDBInput_5, 10, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 11) {
								row1.shape = null;
							} else {
	                         		
        	row1.shape = routines.system.JDBCUtil.getString(rs_tDBInput_5, 11, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 12) {
								row1.foam = null;
							} else {
	                         		
        	row1.foam = routines.system.JDBCUtil.getString(rs_tDBInput_5, 12, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 13) {
								row1.collection = null;
							} else {
	                         		
        	row1.collection = routines.system.JDBCUtil.getString(rs_tDBInput_5, 13, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 14) {
								row1.game_level = null;
							} else {
	                         		
        	row1.game_level = routines.system.JDBCUtil.getString(rs_tDBInput_5, 14, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 15) {
								row1.frame = null;
							} else {
	                         		
        	row1.frame = routines.system.JDBCUtil.getString(rs_tDBInput_5, 15, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 16) {
								row1.surface = null;
							} else {
	                         		
        	row1.surface = routines.system.JDBCUtil.getString(rs_tDBInput_5, 16, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 17) {
								row1.professional_player = null;
							} else {
	                         		
        	row1.professional_player = routines.system.JDBCUtil.getString(rs_tDBInput_5, 17, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 18) {
								row1.color = null;
							} else {
	                         		
        	row1.color = routines.system.JDBCUtil.getString(rs_tDBInput_5, 18, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 19) {
								row1.racket_type = null;
							} else {
	                         		
        	row1.racket_type = routines.system.JDBCUtil.getString(rs_tDBInput_5, 19, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 20) {
								row1.balance = null;
							} else {
	                         		
        	row1.balance = routines.system.JDBCUtil.getString(rs_tDBInput_5, 20, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 21) {
								row1.gender = null;
							} else {
	                         		
        	row1.gender = routines.system.JDBCUtil.getString(rs_tDBInput_5, 21, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 22) {
								row1.racket_cover = null;
							} else {
	                         		
        	row1.racket_cover = routines.system.JDBCUtil.getString(rs_tDBInput_5, 22, false);
		                    }
							if(colQtyInRs_tDBInput_5 < 23) {
								row1.player_name = null;
							} else {
	                         		
        	row1.player_name = routines.system.JDBCUtil.getString(rs_tDBInput_5, 23, false);
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
				// Starting Lookup Table "row3" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow3 = false;
       		  	    	
       		  	    	
 							row3Struct row3ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row3HashKey.vendor = row1.vendor ;
                        		    		

								
		                        	row3HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row3.lookup( row3HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3' and it contains more one result from keys :  row3.vendor = '" + row3HashKey.vendor + "'");
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
				// Starting Lookup Table "row4" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow4 = false;
       		  	    	
       		  	    	
 							row4Struct row4ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row4HashKey.Name = row1.player_name.toUpperCase().trim();
                        		    		

								
		                        	row4HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row4.lookup( row4HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4' and it contains more one result from keys :  row4.Name = '" + row4HashKey.Name + "'");
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
								
                        		    		    row5HashKey.DateValue = row1.created_at == null ? null : new java.util.Date(row1.created_at.getTime());
                        		    		

								
		                        	row5HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row5.lookup( row5HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5' and it contains more one result from keys :  row5.DateValue = '" + row5HashKey.DateValue + "'");
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
				// Starting Lookup Table "row2" 
				///////////////////////////////////////////////


				
				
                            
 					    boolean forceLooprow2 = false;
       		  	    	
       		  	    	
 							row2Struct row2ObjectFromLookup = null;
                          
		           		  	if(!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								
								hasCasePrimitiveKeyWithNull_tMap_1 = false;
								
                        		    		    row2HashKey.title = row1.title;
                        		    		
                        		    		    row2HashKey.handle = row1.handle;
                        		    		

								
		                        	row2HashKey.hashCodeDirty = true;
                        		
	  					
	  							
			  					
			  					
	  					
		  							tHash_Lookup_row2.lookup( row2HashKey );

	  							

	  							

 								
		  				
	  								
						
									
  									  		
 								



							} // G_TM_M_020
			           		  	  
							
				           		if(tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071
			  							
			  						
									 		
									//System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2' and it contains more one result from keys :  row2.title = '" + row2HashKey.title + "', row2.handle = '" + row2HashKey.handle + "'");
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
							
							
							
			  							
								
	                    		  	
		                    
	            	
	            	
	            // ###############################
        { // start of Var scope
        
	        // ###############################
        	// # Vars tables
        
Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
        // ###############################
        // # Output tables

Fact_Equipement = null;


// # Output table : 'Fact_Equipement'
Fact_Equipement_tmp.Id_Padel = row2.Id_Padel ;
Fact_Equipement_tmp.DateKey = row5.DateKey ;
Fact_Equipement_tmp.Id_Player = row4.Id_Player ;
Fact_Equipement_tmp.Id_vendor = row3.Id_vendor ;
Fact_Equipement_tmp.Id_Fact = Numeric.sequence("s1",1,1) ;
Fact_Equipement_tmp.price = row1.price ;
Fact_Equipement = Fact_Equipement_tmp;
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
// Start of branch "Fact_Equipement"
if(Fact_Equipement != null) { 



	
	/**
	 * [tDBOutput_1 main ] start
	 */

	

	
	
	currentComponent="tDBOutput_1";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"Fact_Equipement"
						
						);
					}
					



        whetherReject_tDBOutput_1 = false;
                            pstmt_tDBOutput_1.setInt(1, Fact_Equipement.Id_Padel);

                            pstmt_tDBOutput_1.setInt(2, Fact_Equipement.DateKey);

                            pstmt_tDBOutput_1.setInt(3, Fact_Equipement.Id_Player);

                            pstmt_tDBOutput_1.setInt(4, Fact_Equipement.Id_vendor);

                            if(Fact_Equipement.Id_Fact == null) {
pstmt_tDBOutput_1.setNull(5, java.sql.Types.INTEGER);
} else {pstmt_tDBOutput_1.setInt(5, Fact_Equipement.Id_Fact);
}

                            if(Fact_Equipement.price == null) {
pstmt_tDBOutput_1.setNull(6, java.sql.Types.FLOAT);
} else {pstmt_tDBOutput_1.setFloat(6, Fact_Equipement.price);
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

} // End of branch "Fact_Equipement"




	
	/**
	 * [tMap_1 process_data_end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	

 



/**
 * [tMap_1 process_data_end ] stop
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
	 * [tMap_1 end ] start
	 */

	

	
	
	currentComponent="tMap_1";

	


// ###############################
// # Lookup hashes releasing
					if(tHash_Lookup_row3 != null) {
						tHash_Lookup_row3.endGet();
					}
					globalMap.remove( "tHash_Lookup_row3" );

					
					
				
					if(tHash_Lookup_row4 != null) {
						tHash_Lookup_row4.endGet();
					}
					globalMap.remove( "tHash_Lookup_row4" );

					
					
				
					if(tHash_Lookup_row5 != null) {
						tHash_Lookup_row5.endGet();
					}
					globalMap.remove( "tHash_Lookup_row5" );

					
					
				
					if(tHash_Lookup_row2 != null) {
						tHash_Lookup_row2.endGet();
					}
					globalMap.remove( "tHash_Lookup_row2" );

					
					
				
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
			  		runStat.updateStat(resourceMap,iterateId,2,0,"Fact_Equipement");
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
					     			globalMap.remove("tHash_Lookup_row3"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row4"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row5"); 
				     			
					     			//free memory for "tMap_1"
					     			globalMap.remove("tHash_Lookup_row2"); 
				     			
				try{
					
	
	/**
	 * [tDBInput_5 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_5";

	

 



/**
 * [tDBInput_5 finally ] stop
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
		

		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 1);
	}
	


public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Equipement = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Equipement = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String vendor;

				public String getVendor () {
					return this.vendor;
				}
				
			    public int Id_vendor;

				public int getId_vendor () {
					return this.Id_vendor;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.vendor == null) ? 0 : this.vendor.hashCode());
					
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
		
						if (this.vendor == null) {
							if (other.vendor != null)
								return false;
						
						} else if (!this.vendor.equals(other.vendor))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row3Struct other) {

		other.vendor = this.vendor;
	            other.Id_vendor = this.Id_vendor;
	            
	}

	public void copyKeysDataTo(row3Struct other) {

		other.vendor = this.vendor;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
					this.vendor = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
					this.vendor = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.vendor,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.vendor,dos);
					
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
		
			            this.Id_vendor = dis.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
			            this.Id_vendor = objectIn.readInt();
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
		            	dos.writeInt(this.Id_vendor);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					objectOut.writeInt(this.Id_vendor);
					
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
		sb.append("vendor="+vendor);
		sb.append(",Id_vendor="+String.valueOf(Id_vendor));
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row3Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.vendor, other.vendor);
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
			   		// source node:tDBInput_3 - inputs:(after_tDBInput_5) outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row3,row4,row5,row2) outputs:(Fact_Equipement)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row3Struct>getLookup(matchingModeEnum_row3);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row3 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:jURVtTnAeDviqZsCHUYM05EhspNlp4nbKRfUwg==");
				
				String dbPwd_tDBInput_3 = decryptedPassword_tDBInput_3;
				
        String properties_tDBInput_3 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_3 == null || properties_tDBInput_3.trim().length() == 0) {
            properties_tDBInput_3 = "";
        }
        String url_tDBInput_3 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_3;
				
				conn_tDBInput_3 = java.sql.DriverManager.getConnection(url_tDBInput_3,dbUser_tDBInput_3,dbPwd_tDBInput_3);
		        
		    
			java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

		    String dbquery_tDBInput_3 = "SELECT \n  `dim_vendor`.`vendor`, \n  `dim_vendor`.`Id_vendor`\nFROM `dim_vendor`";
		    

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
								row3.vendor = null;
							} else {
	                         		
        	row3.vendor = routines.system.JDBCUtil.getString(rs_tDBInput_3, 1, false);
		                    }
							if(colQtyInRs_tDBInput_3 < 2) {
								row3.Id_vendor = 0;
							} else {
		                          
            row3.Id_vendor = rs_tDBInput_3.getInt(2);
            if(rs_tDBInput_3.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
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
	 * [tAdvancedHash_row3 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row3";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row3"
						
						);
					}
					


			   
			   

					row3Struct row3_HashRow = new row3Struct();
		   	   	   
				
				row3_HashRow.vendor = row3.vendor;
				
				row3_HashRow.Id_vendor = row3.Id_vendor;
				
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
	 * [tDBInput_3 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_3";

	

 



/**
 * [tDBInput_3 finally ] stop
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
		

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
	}
	


public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Equipement = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Equipement = new byte[0];
	protected static final int DEFAULT_HASHCODE = 1;
    protected static final int PRIME = 31;
    protected int hashCode = DEFAULT_HASHCODE;
    public boolean hashCodeDirty = true;

    public String loopKey;



	
			    public String Name;

				public String getName () {
					return this.Name;
				}
				
			    public Integer Points;

				public Integer getPoints () {
					return this.Points;
				}
				
			    public Integer Position;

				public Integer getPosition () {
					return this.Position;
				}
				
			    public Integer Move;

				public Integer getMove () {
					return this.Move;
				}
				
			    public String Genre;

				public String getGenre () {
					return this.Genre;
				}
				
			    public int Id_Country;

				public int getId_Country () {
					return this.Id_Country;
				}
				
			    public int Id_Player;

				public int getId_Player () {
					return this.Id_Player;
				}
				
			    public String Level;

				public String getLevel () {
					return this.Level;
				}
				


	@Override
	public int hashCode() {
		if (this.hashCodeDirty) {
			final int prime = PRIME;
			int result = DEFAULT_HASHCODE;
	
						result = prime * result + ((this.Name == null) ? 0 : this.Name.hashCode());
					
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
		
						if (this.Name == null) {
							if (other.Name != null)
								return false;
						
						} else if (!this.Name.equals(other.Name))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row4Struct other) {

		other.Name = this.Name;
	            other.Points = this.Points;
	            other.Position = this.Position;
	            other.Move = this.Move;
	            other.Genre = this.Genre;
	            other.Id_Country = this.Id_Country;
	            other.Id_Player = this.Id_Player;
	            other.Level = this.Level;
	            
	}

	public void copyKeysDataTo(row4Struct other) {

		other.Name = this.Name;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
					this.Name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
					this.Name = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.Name,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.Name,dos);
					
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
		
						this.Points = readInteger(dis,ois);
					
						this.Position = readInteger(dis,ois);
					
						this.Move = readInteger(dis,ois);
					
						this.Genre = readString(dis,ois);
					
			            this.Id_Country = dis.readInt();
					
			            this.Id_Player = dis.readInt();
					
						this.Level = readString(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
						this.Points = readInteger(dis,objectIn);
					
						this.Position = readInteger(dis,objectIn);
					
						this.Move = readInteger(dis,objectIn);
					
						this.Genre = readString(dis,objectIn);
					
			            this.Id_Country = objectIn.readInt();
					
			            this.Id_Player = objectIn.readInt();
					
						this.Level = readString(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
					writeInteger(this.Points, dos, oos);
					
					writeInteger(this.Position, dos, oos);
					
					writeInteger(this.Move, dos, oos);
					
						writeString(this.Genre, dos, oos);
					
		            	dos.writeInt(this.Id_Country);
					
		            	dos.writeInt(this.Id_Player);
					
						writeString(this.Level, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
					writeInteger(this.Points, dos, objectOut);
					
					writeInteger(this.Position, dos, objectOut);
					
					writeInteger(this.Move, dos, objectOut);
					
						writeString(this.Genre, dos, objectOut);
					
					objectOut.writeInt(this.Id_Country);
					
					objectOut.writeInt(this.Id_Player);
					
						writeString(this.Level, dos, objectOut);
					
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
		sb.append("Name="+Name);
		sb.append(",Points="+String.valueOf(Points));
		sb.append(",Position="+String.valueOf(Position));
		sb.append(",Move="+String.valueOf(Move));
		sb.append(",Genre="+Genre);
		sb.append(",Id_Country="+String.valueOf(Id_Country));
		sb.append(",Id_Player="+String.valueOf(Id_Player));
		sb.append(",Level="+Level);
	    sb.append("]");

	    return sb.toString();
    }

    /**
     * Compare keys
     */
    public int compareTo(row4Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.Name, other.Name);
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
public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

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
			   		// source node:tDBInput_2 - inputs:(after_tDBInput_5) outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row3,row4,row5,row2) outputs:(Fact_Equipement)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row4Struct>getLookup(matchingModeEnum_row4);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row4 begin ] stop
 */



	
	/**
	 * [tDBInput_2 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_2", false);
		start_Hash.put("tDBInput_2", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_2";

	
		int tos_count_tDBInput_2 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_2 = java.util.Calendar.getInstance();
		    calendar_tDBInput_2.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_2 = calendar_tDBInput_2.getTime();
		    int nb_line_tDBInput_2 = 0;
		    java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "root";
				
				 
	final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:yRUf2ogz5phXh3vP1GypJxMKVK47wUIHBXYfHw==");
				
				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;
				
        String properties_tDBInput_2 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_2 == null || properties_tDBInput_2.trim().length() == 0) {
            properties_tDBInput_2 = "";
        }
        String url_tDBInput_2 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_2;
				
				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2,dbUser_tDBInput_2,dbPwd_tDBInput_2);
		        
		    
			java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

		    String dbquery_tDBInput_2 = "SELECT \n  `dim_player_ofc`.`Name`, \n  `dim_player_ofc`.`Points`, \n  `dim_player_ofc`.`Position`, \n  `dim_player_ofc`.`M"
+"ove`, \n  `dim_player_ofc`.`Genre`, \n  `dim_player_ofc`.`Id_Country`, \n  `dim_player_ofc`.`Id_Player`, \n  `dim_player_ofc"
+"`.`Level`\nFROM `dim_player_ofc`";
		    

            	globalMap.put("tDBInput_2_QUERY",dbquery_tDBInput_2);
		    java.sql.ResultSet rs_tDBInput_2 = null;

		    try {
		    	rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
		    	int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

		    String tmpContent_tDBInput_2 = null;
		    
		    
		    while (rs_tDBInput_2.next()) {
		        nb_line_tDBInput_2++;
		        
							if(colQtyInRs_tDBInput_2 < 1) {
								row4.Name = null;
							} else {
	                         		
        	row4.Name = routines.system.JDBCUtil.getString(rs_tDBInput_2, 1, false);
		                    }
							if(colQtyInRs_tDBInput_2 < 2) {
								row4.Points = null;
							} else {
		                          
            row4.Points = rs_tDBInput_2.getInt(2);
            if(rs_tDBInput_2.wasNull()){
                    row4.Points = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 3) {
								row4.Position = null;
							} else {
		                          
            row4.Position = rs_tDBInput_2.getInt(3);
            if(rs_tDBInput_2.wasNull()){
                    row4.Position = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 4) {
								row4.Move = null;
							} else {
		                          
            row4.Move = rs_tDBInput_2.getInt(4);
            if(rs_tDBInput_2.wasNull()){
                    row4.Move = null;
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 5) {
								row4.Genre = null;
							} else {
	                         		
        	row4.Genre = routines.system.JDBCUtil.getString(rs_tDBInput_2, 5, false);
		                    }
							if(colQtyInRs_tDBInput_2 < 6) {
								row4.Id_Country = 0;
							} else {
		                          
            row4.Id_Country = rs_tDBInput_2.getInt(6);
            if(rs_tDBInput_2.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 7) {
								row4.Id_Player = 0;
							} else {
		                          
            row4.Id_Player = rs_tDBInput_2.getInt(7);
            if(rs_tDBInput_2.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_2 < 8) {
								row4.Level = null;
							} else {
	                         		
        	row4.Level = routines.system.JDBCUtil.getString(rs_tDBInput_2, 8, false);
		                    }
					

 



/**
 * [tDBInput_2 begin ] stop
 */
	
	/**
	 * [tDBInput_2 main ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 


	tos_count_tDBInput_2++;

/**
 * [tDBInput_2 main ] stop
 */
	
	/**
	 * [tDBInput_2 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 process_data_begin ] stop
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
		   	   	   
				
				row4_HashRow.Name = row4.Name;
				
				row4_HashRow.Points = row4.Points;
				
				row4_HashRow.Position = row4.Position;
				
				row4_HashRow.Move = row4.Move;
				
				row4_HashRow.Genre = row4.Genre;
				
				row4_HashRow.Id_Country = row4.Id_Country;
				
				row4_HashRow.Id_Player = row4.Id_Player;
				
				row4_HashRow.Level = row4.Level;
				
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
	 * [tDBInput_2 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_2 end ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

	}
}finally{
	if (rs_tDBInput_2 != null) {
		rs_tDBInput_2.close();
	}
	if (stmt_tDBInput_2 != null) {
		stmt_tDBInput_2.close();
	}
		if(conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {
			
			conn_tDBInput_2.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_2_NB_LINE",nb_line_tDBInput_2);
		


 

ok_Hash.put("tDBInput_2", true);
end_Hash.put("tDBInput_2", System.currentTimeMillis());




/**
 * [tDBInput_2 end ] stop
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
	 * [tDBInput_2 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_2";

	

 



/**
 * [tDBInput_2 finally ] stop
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
		

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}
	


public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Equipement = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Equipement = new byte[0];
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
		final row5Struct other = (row5Struct) obj;
		
						if (this.DateValue == null) {
							if (other.DateValue != null)
								return false;
						
						} else if (!this.DateValue.equals(other.DateValue))
						
							return false;
					

		return true;
    }

	public void copyDataTo(row5Struct other) {

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

	public void copyKeysDataTo(row5Struct other) {

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

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
					this.DateValue = readDate(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

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
    public int compareTo(row5Struct other) {

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
			   		// source node:tDBInput_1 - inputs:(after_tDBInput_5) outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row3,row4,row5,row2) outputs:(Fact_Equipement)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row5Struct>getLookup(matchingModeEnum_row5);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row5 begin ] stop
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
				
				 
	final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:WCjavh3DsKpwcWACPSG42gwiY0Mv/ewq4IcYtg==");
				
				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;
				
        String properties_tDBInput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_1 == null || properties_tDBInput_1.trim().length() == 0) {
            properties_tDBInput_1 = "";
        }
        String url_tDBInput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_1;
				
				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1,dbUser_tDBInput_1,dbPwd_tDBInput_1);
		        
		    
			java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

		    String dbquery_tDBInput_1 = "SELECT \n  `dim_date`.`DateKey`, \n  `dim_date`.`DateValue`, \n  `dim_date`.`Year`, \n  `dim_date`.`Quarter`, \n  `dim_date`"
+".`Month`, \n  `dim_date`.`MonthName`, \n  `dim_date`.`Day`, \n  `dim_date`.`DayOfWeek`, \n  `dim_date`.`DayOfWeekName`, \n  `"
+"dim_date`.`WeekOfYear`, \n  `dim_date`.`IsWeekend`\nFROM `dim_date`";
		    

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
								row5.DateKey = 0;
							} else {
		                          
            row5.DateKey = rs_tDBInput_1.getInt(1);
            if(rs_tDBInput_1.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 2) {
								row5.DateValue = null;
							} else {
										
				if(rs_tDBInput_1.getString(2) != null) {
					String dateString_tDBInput_1 = rs_tDBInput_1.getString(2);
					if (!("0000-00-00").equals(dateString_tDBInput_1) && !("0000-00-00 00:00:00").equals(dateString_tDBInput_1)) {
						row5.DateValue = rs_tDBInput_1.getTimestamp(2);
					} else {
						row5.DateValue = (java.util.Date) year0_tDBInput_1.clone();
					}
				} else {
					row5.DateValue =  null;
				}
		                    }
							if(colQtyInRs_tDBInput_1 < 3) {
								row5.Year = null;
							} else {
		                          
            row5.Year = rs_tDBInput_1.getInt(3);
            if(rs_tDBInput_1.wasNull()){
                    row5.Year = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 4) {
								row5.Quarter = null;
							} else {
		                          
            row5.Quarter = rs_tDBInput_1.getInt(4);
            if(rs_tDBInput_1.wasNull()){
                    row5.Quarter = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 5) {
								row5.Month = null;
							} else {
		                          
            row5.Month = rs_tDBInput_1.getInt(5);
            if(rs_tDBInput_1.wasNull()){
                    row5.Month = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 6) {
								row5.MonthName = null;
							} else {
	                         		
        	row5.MonthName = routines.system.JDBCUtil.getString(rs_tDBInput_1, 6, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 7) {
								row5.Day = null;
							} else {
		                          
            row5.Day = rs_tDBInput_1.getInt(7);
            if(rs_tDBInput_1.wasNull()){
                    row5.Day = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 8) {
								row5.DayOfWeek = null;
							} else {
		                          
            row5.DayOfWeek = rs_tDBInput_1.getInt(8);
            if(rs_tDBInput_1.wasNull()){
                    row5.DayOfWeek = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 9) {
								row5.DayOfWeekName = null;
							} else {
	                         		
        	row5.DayOfWeekName = routines.system.JDBCUtil.getString(rs_tDBInput_1, 9, false);
		                    }
							if(colQtyInRs_tDBInput_1 < 10) {
								row5.WeekOfYear = null;
							} else {
		                          
            row5.WeekOfYear = rs_tDBInput_1.getInt(10);
            if(rs_tDBInput_1.wasNull()){
                    row5.WeekOfYear = null;
            }
		                    }
							if(colQtyInRs_tDBInput_1 < 11) {
								row5.IsWeekend = null;
							} else {
	                         		
            row5.IsWeekend = rs_tDBInput_1.getBoolean(11);
            if(rs_tDBInput_1.wasNull()){
                    row5.IsWeekend = null;
            }
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
	 * [tAdvancedHash_row5 main ] start
	 */

	

	
	
	currentComponent="tAdvancedHash_row5";

	
					if(execStat){
						runStat.updateStatOnConnection(iterateId,1,1
						
							,"row5"
						
						);
					}
					


			   
			   

					row5Struct row5_HashRow = new row5Struct();
		   	   	   
				
				row5_HashRow.DateKey = row5.DateKey;
				
				row5_HashRow.DateValue = row5.DateValue;
				
				row5_HashRow.Year = row5.Year;
				
				row5_HashRow.Quarter = row5.Quarter;
				
				row5_HashRow.Month = row5.Month;
				
				row5_HashRow.MonthName = row5.MonthName;
				
				row5_HashRow.Day = row5.Day;
				
				row5_HashRow.DayOfWeek = row5.DayOfWeek;
				
				row5_HashRow.DayOfWeekName = row5.DayOfWeekName;
				
				row5_HashRow.WeekOfYear = row5.WeekOfYear;
				
				row5_HashRow.IsWeekend = row5.IsWeekend;
				
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
	 * [tDBInput_1 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_1";

	

 



/**
 * [tDBInput_1 finally ] stop
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
		

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}
	


public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
    final static byte[] commonByteArrayLock_PADEL_Fact_Equipement = new byte[0];
    static byte[] commonByteArray_PADEL_Fact_Equipement = new byte[0];
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
	
						result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
					
						result = prime * result + ((this.handle == null) ? 0 : this.handle.hashCode());
					
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
					

		return true;
    }

	public void copyDataTo(row2Struct other) {

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

	public void copyKeysDataTo(row2Struct other) {

		other.title = this.title;
	            	other.handle = this.handle;
	            	
	}




	private String readString(ObjectInputStream dis) throws IOException{
		String strReturn = null;
		int length = 0;
        length = dis.readInt();
		if (length == -1) {
			strReturn = null;
		} else {
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			dis.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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
			if(length > commonByteArray_PADEL_Fact_Equipement.length) {
				if(length < 1024 && commonByteArray_PADEL_Fact_Equipement.length == 0) {
   					commonByteArray_PADEL_Fact_Equipement = new byte[1024];
				} else {
   					commonByteArray_PADEL_Fact_Equipement = new byte[2 * length];
   				}
			}
			unmarshaller.readFully(commonByteArray_PADEL_Fact_Equipement, 0, length);
			strReturn = new String(commonByteArray_PADEL_Fact_Equipement, 0, length, utf8Charset);
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

	private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException{
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
	
	private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller ) throws IOException{
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

	private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException{
		if(date1 == null) {
            dos.writeByte(-1);
		} else {
			dos.writeByte(0);
	    	dos.writeLong(date1.getTime());
    	}
	}
	
	private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller) throws IOException{
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

    public void readKeysData(ObjectInputStream dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }
    
    public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

		synchronized(commonByteArrayLock_PADEL_Fact_Equipement) {

        	try {

        		int length = 0;
		
					this.title = readString(dis);
					
					this.handle = readString(dis);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

      }


    }

    public void writeKeysData(ObjectOutputStream dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        }


    }
    
    public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
        try {

		
					// String
				
						writeString(this.title,dos);
					
					// String
				
						writeString(this.handle,dos);
					
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
		
						this.product_type = readString(dis,ois);
					
						this.tags = readString(dis,ois);
					
						this.created_at = readDate(dis,ois);
					
						this.sku = readInteger(dis,ois);
					
			            this.Id_Padel = dis.readInt();
					
						this.image = readString(dis,ois);
					
						this.weight = readString(dis,ois);
					
						this.shape = readString(dis,ois);
					
						this.foam = readString(dis,ois);
					
						this.collection = readString(dis,ois);
					
						this.game_level = readString(dis,ois);
					
						this.frame = readString(dis,ois);
					
						this.surface = readString(dis,ois);
					
						this.color = readString(dis,ois);
					
						this.racket_type = readString(dis,ois);
					
						this.balance = readString(dis,ois);
					
						this.racket_cover = readString(dis,ois);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }
    
    public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
        try {
			int length = 0;
		
						this.product_type = readString(dis,objectIn);
					
						this.tags = readString(dis,objectIn);
					
						this.created_at = readDate(dis,objectIn);
					
						this.sku = readInteger(dis,objectIn);
					
			            this.Id_Padel = objectIn.readInt();
					
						this.image = readString(dis,objectIn);
					
						this.weight = readString(dis,objectIn);
					
						this.shape = readString(dis,objectIn);
					
						this.foam = readString(dis,objectIn);
					
						this.collection = readString(dis,objectIn);
					
						this.game_level = readString(dis,objectIn);
					
						this.frame = readString(dis,objectIn);
					
						this.surface = readString(dis,objectIn);
					
						this.color = readString(dis,objectIn);
					
						this.racket_type = readString(dis,objectIn);
					
						this.balance = readString(dis,objectIn);
					
						this.racket_cover = readString(dis,objectIn);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);

		

        }

		

    }

    /**
     * Return a byte array which represents Values data.
     */
    public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
        try {

		
						writeString(this.product_type, dos, oos);
					
						writeString(this.tags, dos, oos);
					
						writeDate(this.created_at, dos, oos);
					
					writeInteger(this.sku, dos, oos);
					
		            	dos.writeInt(this.Id_Padel);
					
						writeString(this.image, dos, oos);
					
						writeString(this.weight, dos, oos);
					
						writeString(this.shape, dos, oos);
					
						writeString(this.foam, dos, oos);
					
						writeString(this.collection, dos, oos);
					
						writeString(this.game_level, dos, oos);
					
						writeString(this.frame, dos, oos);
					
						writeString(this.surface, dos, oos);
					
						writeString(this.color, dos, oos);
					
						writeString(this.racket_type, dos, oos);
					
						writeString(this.balance, dos, oos);
					
						writeString(this.racket_cover, dos, oos);
					
        	} catch (IOException e) {
	            throw new RuntimeException(e);
        	}

    }
    
    public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut){
                try {

		
						writeString(this.product_type, dos, objectOut);
					
						writeString(this.tags, dos, objectOut);
					
						writeDate(this.created_at, dos, objectOut);
					
					writeInteger(this.sku, dos, objectOut);
					
					objectOut.writeInt(this.Id_Padel);
					
						writeString(this.image, dos, objectOut);
					
						writeString(this.weight, dos, objectOut);
					
						writeString(this.shape, dos, objectOut);
					
						writeString(this.foam, dos, objectOut);
					
						writeString(this.collection, dos, objectOut);
					
						writeString(this.game_level, dos, objectOut);
					
						writeString(this.frame, dos, objectOut);
					
						writeString(this.surface, dos, objectOut);
					
						writeString(this.color, dos, objectOut);
					
						writeString(this.racket_type, dos, objectOut);
					
						writeString(this.balance, dos, objectOut);
					
						writeString(this.racket_cover, dos, objectOut);
					
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
    public int compareTo(row2Struct other) {

		int returnValue = -1;
		
						returnValue = checkNullsAndCompare(this.title, other.title);
						if(returnValue != 0) {
							return returnValue;
						}

					
						returnValue = checkNullsAndCompare(this.handle, other.handle);
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
public void tDBInput_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
	globalMap.put("tDBInput_4_SUBPROCESS_STATE", 0);

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
			   		// source node:tDBInput_4 - inputs:(after_tDBInput_5) outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2) outputs:()
			   		// linked node: tMap_1 - inputs:(row1,row3,row4,row5,row2) outputs:(Fact_Equipement)
			   
			   		org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = 
			   			org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;
			   			
			   
	   			org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 =org.talend.designer.components.lookup.memory.AdvancedMemoryLookup.
	   						<row2Struct>getLookup(matchingModeEnum_row2);
	   						   
		   	   	   globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);
		   	   	   
				
           

 



/**
 * [tAdvancedHash_row2 begin ] stop
 */



	
	/**
	 * [tDBInput_4 begin ] start
	 */

	

	
		
		ok_Hash.put("tDBInput_4", false);
		start_Hash.put("tDBInput_4", System.currentTimeMillis());
		
	
	currentComponent="tDBInput_4";

	
		int tos_count_tDBInput_4 = 0;
		
	
	
		    java.util.Calendar calendar_tDBInput_4 = java.util.Calendar.getInstance();
		    calendar_tDBInput_4.set(0, 0, 0, 0, 0, 0);
		    java.util.Date year0_tDBInput_4 = calendar_tDBInput_4.getTime();
		    int nb_line_tDBInput_4 = 0;
		    java.sql.Connection conn_tDBInput_4 = null;
				String driverClass_tDBInput_4 = "com.mysql.cj.jdbc.Driver";
			    java.lang.Class jdbcclazz_tDBInput_4 = java.lang.Class.forName(driverClass_tDBInput_4);
				String dbUser_tDBInput_4 = "root";
				
				 
	final String decryptedPassword_tDBInput_4 = routines.system.PasswordEncryptUtil.decryptPassword("enc:routine.encryption.key.v1:abdQ1iodTe0ywf3jaAu/gB0qJfS1wH4beCyzhw==");
				
				String dbPwd_tDBInput_4 = decryptedPassword_tDBInput_4;
				
        String properties_tDBInput_4 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
        if (properties_tDBInput_4 == null || properties_tDBInput_4.trim().length() == 0) {
            properties_tDBInput_4 = "";
        }
        String url_tDBInput_4 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "DW_padel_analytics" + "?" + properties_tDBInput_4;
				
				conn_tDBInput_4 = java.sql.DriverManager.getConnection(url_tDBInput_4,dbUser_tDBInput_4,dbPwd_tDBInput_4);
		        
		    
			java.sql.Statement stmt_tDBInput_4 = conn_tDBInput_4.createStatement();

		    String dbquery_tDBInput_4 = "SELECT \n  `dim_padel_info`.`title`, \n  `dim_padel_info`.`handle`, \n  `dim_padel_info`.`product_type`, \n  `dim_padel_inf"
+"o`.`tags`, \n  `dim_padel_info`.`created_at`, \n  `dim_padel_info`.`sku`, \n  `dim_padel_info`.`Id_Padel`, \n  `dim_padel_in"
+"fo`.`image`, \n  `dim_padel_info`.`weight`, \n  `dim_padel_info`.`shape`, \n  `dim_padel_info`.`foam`, \n  `dim_padel_info`."
+"`collection`, \n  `dim_padel_info`.`game_level`, \n  `dim_padel_info`.`frame`, \n  `dim_padel_info`.`surface`, \n  `dim_pade"
+"l_info`.`color`, \n  `dim_padel_info`.`racket_type`, \n  `dim_padel_info`.`balance`, \n  `dim_padel_info`.`racket_cover`\nFR"
+"OM `dim_padel_info`";
		    

            	globalMap.put("tDBInput_4_QUERY",dbquery_tDBInput_4);
		    java.sql.ResultSet rs_tDBInput_4 = null;

		    try {
		    	rs_tDBInput_4 = stmt_tDBInput_4.executeQuery(dbquery_tDBInput_4);
		    	java.sql.ResultSetMetaData rsmd_tDBInput_4 = rs_tDBInput_4.getMetaData();
		    	int colQtyInRs_tDBInput_4 = rsmd_tDBInput_4.getColumnCount();

		    String tmpContent_tDBInput_4 = null;
		    
		    
		    while (rs_tDBInput_4.next()) {
		        nb_line_tDBInput_4++;
		        
							if(colQtyInRs_tDBInput_4 < 1) {
								row2.title = null;
							} else {
	                         		
        	row2.title = routines.system.JDBCUtil.getString(rs_tDBInput_4, 1, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 2) {
								row2.handle = null;
							} else {
	                         		
        	row2.handle = routines.system.JDBCUtil.getString(rs_tDBInput_4, 2, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 3) {
								row2.product_type = null;
							} else {
	                         		
        	row2.product_type = routines.system.JDBCUtil.getString(rs_tDBInput_4, 3, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 4) {
								row2.tags = null;
							} else {
	                         		
        	row2.tags = routines.system.JDBCUtil.getString(rs_tDBInput_4, 4, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 5) {
								row2.created_at = null;
							} else {
										
				if(rs_tDBInput_4.getString(5) != null) {
					String dateString_tDBInput_4 = rs_tDBInput_4.getString(5);
					if (!("0000-00-00").equals(dateString_tDBInput_4) && !("0000-00-00 00:00:00").equals(dateString_tDBInput_4)) {
						row2.created_at = rs_tDBInput_4.getTimestamp(5);
					} else {
						row2.created_at = (java.util.Date) year0_tDBInput_4.clone();
					}
				} else {
					row2.created_at =  null;
				}
		                    }
							if(colQtyInRs_tDBInput_4 < 6) {
								row2.sku = null;
							} else {
		                          
            row2.sku = rs_tDBInput_4.getInt(6);
            if(rs_tDBInput_4.wasNull()){
                    row2.sku = null;
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 7) {
								row2.Id_Padel = 0;
							} else {
		                          
            row2.Id_Padel = rs_tDBInput_4.getInt(7);
            if(rs_tDBInput_4.wasNull()){
                    throw new RuntimeException("Null value in non-Nullable column");
            }
		                    }
							if(colQtyInRs_tDBInput_4 < 8) {
								row2.image = null;
							} else {
	                         		
        	row2.image = routines.system.JDBCUtil.getString(rs_tDBInput_4, 8, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 9) {
								row2.weight = null;
							} else {
	                         		
        	row2.weight = routines.system.JDBCUtil.getString(rs_tDBInput_4, 9, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 10) {
								row2.shape = null;
							} else {
	                         		
        	row2.shape = routines.system.JDBCUtil.getString(rs_tDBInput_4, 10, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 11) {
								row2.foam = null;
							} else {
	                         		
        	row2.foam = routines.system.JDBCUtil.getString(rs_tDBInput_4, 11, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 12) {
								row2.collection = null;
							} else {
	                         		
        	row2.collection = routines.system.JDBCUtil.getString(rs_tDBInput_4, 12, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 13) {
								row2.game_level = null;
							} else {
	                         		
        	row2.game_level = routines.system.JDBCUtil.getString(rs_tDBInput_4, 13, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 14) {
								row2.frame = null;
							} else {
	                         		
        	row2.frame = routines.system.JDBCUtil.getString(rs_tDBInput_4, 14, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 15) {
								row2.surface = null;
							} else {
	                         		
        	row2.surface = routines.system.JDBCUtil.getString(rs_tDBInput_4, 15, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 16) {
								row2.color = null;
							} else {
	                         		
        	row2.color = routines.system.JDBCUtil.getString(rs_tDBInput_4, 16, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 17) {
								row2.racket_type = null;
							} else {
	                         		
        	row2.racket_type = routines.system.JDBCUtil.getString(rs_tDBInput_4, 17, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 18) {
								row2.balance = null;
							} else {
	                         		
        	row2.balance = routines.system.JDBCUtil.getString(rs_tDBInput_4, 18, false);
		                    }
							if(colQtyInRs_tDBInput_4 < 19) {
								row2.racket_cover = null;
							} else {
	                         		
        	row2.racket_cover = routines.system.JDBCUtil.getString(rs_tDBInput_4, 19, false);
		                    }
					

 



/**
 * [tDBInput_4 begin ] stop
 */
	
	/**
	 * [tDBInput_4 main ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 


	tos_count_tDBInput_4++;

/**
 * [tDBInput_4 main ] stop
 */
	
	/**
	 * [tDBInput_4 process_data_begin ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 



/**
 * [tDBInput_4 process_data_begin ] stop
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
		   	   	   
				
				row2_HashRow.title = row2.title;
				
				row2_HashRow.handle = row2.handle;
				
				row2_HashRow.product_type = row2.product_type;
				
				row2_HashRow.tags = row2.tags;
				
				row2_HashRow.created_at = row2.created_at;
				
				row2_HashRow.sku = row2.sku;
				
				row2_HashRow.Id_Padel = row2.Id_Padel;
				
				row2_HashRow.image = row2.image;
				
				row2_HashRow.weight = row2.weight;
				
				row2_HashRow.shape = row2.shape;
				
				row2_HashRow.foam = row2.foam;
				
				row2_HashRow.collection = row2.collection;
				
				row2_HashRow.game_level = row2.game_level;
				
				row2_HashRow.frame = row2.frame;
				
				row2_HashRow.surface = row2.surface;
				
				row2_HashRow.color = row2.color;
				
				row2_HashRow.racket_type = row2.racket_type;
				
				row2_HashRow.balance = row2.balance;
				
				row2_HashRow.racket_cover = row2.racket_cover;
				
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
	 * [tDBInput_4 process_data_end ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 



/**
 * [tDBInput_4 process_data_end ] stop
 */
	
	/**
	 * [tDBInput_4 end ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

	}
}finally{
	if (rs_tDBInput_4 != null) {
		rs_tDBInput_4.close();
	}
	if (stmt_tDBInput_4 != null) {
		stmt_tDBInput_4.close();
	}
		if(conn_tDBInput_4 != null && !conn_tDBInput_4.isClosed()) {
			
			conn_tDBInput_4.close();
			
			if("com.mysql.cj.jdbc.Driver".equals((String)globalMap.get("driverClass_"))
			    && routines.system.BundleUtils.inOSGi()) {
			        Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread").
			            getMethod("checkedShutdown").invoke(null, (Object[]) null);
			}
			
		}
		
}

		   globalMap.put("tDBInput_4_NB_LINE",nb_line_tDBInput_4);
		


 

ok_Hash.put("tDBInput_4", true);
end_Hash.put("tDBInput_4", System.currentTimeMillis());




/**
 * [tDBInput_4 end ] stop
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
	 * [tDBInput_4 finally ] start
	 */

	

	
	
	currentComponent="tDBInput_4";

	

 



/**
 * [tDBInput_4 finally ] stop
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
		

		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 1);
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
        final Fact_Equipement Fact_EquipementClass = new Fact_Equipement();

        int exitCode = Fact_EquipementClass.runJobInTOS(args);

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
            java.io.InputStream inContext = Fact_Equipement.class.getClassLoader().getResourceAsStream("padel/fact_equipement_0_1/contexts/" + contextStr + ".properties");
            if (inContext == null) {
                inContext = Fact_Equipement.class.getClassLoader().getResourceAsStream("config/contexts/" + contextStr + ".properties");
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
errorCode = null;tDBInput_5Process(globalMap);
if(!"failure".equals(status)) { status = "end"; }
}catch (TalendException e_tDBInput_5) {
globalMap.put("tDBInput_5_SUBPROCESS_STATE", -1);

e_tDBInput_5.printStackTrace();

}

this.globalResumeTicket = true;//to run tPostJob




        end = System.currentTimeMillis();

        if (watch) {
            System.out.println((end-startTime)+" milliseconds");
        }

        endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        if (false) {
            System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Fact_Equipement");
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
 *     206560 characters generated by Talend Open Studio for Data Integration 
 *     on the March 27, 2026 at 12:42:45 AM CET
 ************************************************************************************************/