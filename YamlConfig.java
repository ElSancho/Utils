import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * YamlConfig.java
 * @author sanchobaya
 * @version 1.0
 * @copyrights all rights reserved
 */
public class YamlConfig 
{
	@SuppressWarnings("unused")
	private String name;
	@SuppressWarnings("unused")
	private String project_name;
	private File file;
	private Properties prop;
	
	public YamlConfig(String name, String project_name)
	{
		this.name = name;
		this.project_name = project_name;
		
		File file_folder = new File("C:/"+project_name+"/config/");
		file_folder.mkdirs();
		file = new File("C:/"+project_name+"/config/"+name+".yml");
		if(!file.exists())
		{
			try 
			{
				file.createNewFile();
			} 
			catch (IOException e) 
			{
				System.err.println("Impossible de creer le fichier demandé : "+e.getMessage());
			}
		}
		prop = new Properties();
	}
	
	public String get(String str)
	{
		String obj = null;
		try 
		{
			FileReader reader = new FileReader(file);
			prop.load(reader);
			obj = prop.getProperty(str, null);
			reader.close();
			return obj;
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Impossible d'ouvrir le fichier demandé"+e.getMessage());
		} 
		catch (IOException e) 
		{
			System.err.println("Impossible d'ouvrir le fichier demandé"+e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}
	
	public int getInt(String str)
	{
		return Integer.parseInt(get(str));
	}
	
	public void set(String str, Object obj)
	{
		if(obj instanceof Integer)
		{
			obj = obj.toString();
		}
		prop.put(str, obj);
		try 
		{
			FileWriter writer = new FileWriter(file);
			prop.store(writer, "config");
			writer.close();
		} 
		catch (IOException e) 
		{
			System.err.println("Impossible d'écrire dans fichier demandé"+e.getMessage());
		}
	}
}
