package visitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;

public class Context {
	private HashMap<String,Integer> variables;
	private final String PropertiesFile="^.+\\.properties";
	private final String positiveInt="[0-9]*";
	
	public Context(String fileName) throws IOException{
		if(!fileName.matches(PropertiesFile)) throw new IllegalArgumentException("Il file deve essere .properties");
		variables=new HashMap<>();
		Properties p=new Properties();
		p.load(new FileInputStream(fileName));
		Object currentKey=null;
		Enumeration<Object> keys = p.keys();
		while (keys.hasMoreElements()) {
             currentKey = keys.nextElement();
             if(!p.getProperty(currentKey.toString()).matches(positiveInt)) throw new IllegalArgumentException();
             variables.put((String)currentKey, Integer.parseInt(p.getProperty(currentKey.toString())));
        }
	}
	
	public HashMap<String,Integer> getVariables(){
		return variables;
	}
	
	public void refresh(String nomeFile) throws FileNotFoundException{
		HashMap<String,Integer> newMap=new HashMap<>();
		File f=new File( nomeFile );
		
			BufferedReader r= new BufferedReader(new FileReader(f));
			for(;;){
				try{
				String linea = r.readLine();
				if(linea==null)break;
				StringTokenizer st = new StringTokenizer(linea,"=",true);
				String variabile= st.nextToken().trim();
				if(!st.hasMoreTokens()) throw new IllegalArgumentException("Formato file scorretto");
				if(!st.nextToken().equals("="))throw new IllegalArgumentException("Formato file scorretto");
				if(!st.hasMoreTokens()) throw new IllegalArgumentException("Formato file scorretto");
				String expr = st.nextToken().trim();
				if(!expr.matches(positiveInt))throw new IllegalArgumentException("Si attendeva un numero positivo");
				if(st.hasMoreTokens()) throw new IllegalArgumentException("Formato file scorretto");
			    int value=Integer.parseInt(expr);
			    newMap.put(variabile, value);
				}catch(IOException e){break;}
				}
		variables=newMap;
	}
	
	public void save(String nomeFile) throws FileNotFoundException{
			FileOutputStream prova = new FileOutputStream(nomeFile);
			 PrintStream pm = new PrintStream(prova);
			 for(String key: variables.keySet())
	          {
	                pm.println(key+"="+variables.get(key));
	          }
		
       
	}

}
