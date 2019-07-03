import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.DriverManager;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.experiment.InstanceQuery;

public class modelpredictor {
	public static void main(String[] args) throws Exception {
		//Read input test data
		DriverManager.getConnection("jdbc:mysql://localhost/friendloan", "root", "mysql");
		InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("mysql");
		query.setDatabaseURL("jdbc:mysql://localhost/friendloan?#characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
		query.setQuery("select * from testdata");
		Instances test = query.retrieveInstances();
		test.setClassIndex(test.numAttributes() - 1);
		//System.out.println(test);
		//Finish readind test data
		// Loading the prediction model from file
		Classifier classifier=(Classifier)SerializationHelper.read("C:\\Abhi\\ML\\Hackathons\\DataScientistSalary\\Classifierfileprdeictornb.txt");
		//System.out.println(classifier);
		/*try (PrintStream out = new PrintStream(new FileOutputStream("C:\\Abhi\\ML\\FriendLoan\\classifier.csv "))) {
		    out.print(classifier);
		}*/
		//System.out.println(classifier);
		//System.out.println(test.numInstances());
		for (int i = 0; i < test.numInstances() ; i++) {
			//System.out.println(classifier.classifyInstance(test.instance(i)));
		}
		for (int i = 0; i < test.numInstances() ; i++) {
			//System.out.print(test.instance(i).stringValue(test.numAttributes()-1));
			test.instance(i).setClassValue(classifier.classifyInstance(test.instance(i)));
			System.out.print(test.instance(i).stringValue(test.numAttributes()-1));
			
		}
		FileReader filereader = new FileReader("C:\\Abhi\\ML\\Hackathons\\Plague\\list.csv");
		 String[] tempOut = new String[test.numInstances()];
			for(int i=0;i<test.numInstances();i++) {
				
				tempOut[i]= Double.toString(test.instance(i).value(test.numAttributes()-1));
				//System.out.println(test.instance(i).value(test.numAttributes()-1));
				
			}
			
		 String  everything= "";
		 BufferedReader br = new BufferedReader(new FileReader("C:\\Abhi\\ML\\FriendLoan\\list.csv"));
		 try {
		 String line = br.readLine();
		 int i=0;
		 while (line != null && i<test.numInstances()) {
			 //System.out.println(tempOut[i]);
			 everything += line + ",\n" + tempOut[i];
			 line = br.readLine();
			 i++;
		 }
		 } finally {
		 br.close();
		 }
		//System.out.print(everything);
		try (PrintStream out = new PrintStream(new FileOutputStream("C:\\Abhi\\ML\\FriendLoan\\list_copy.csv"))) {
		    out.println(everything);
		}
		filereader.close();
		
		
	
		   //Finish predicting accuracy
		   
		
	}

	private static void writer() {
		// TODO Auto-generated method stub
		
	}
}
