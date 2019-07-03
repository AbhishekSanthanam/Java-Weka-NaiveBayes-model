import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.DriverManager;

import weka.classifiers.trees.RandomForest;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.Id3;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.experiment.InstanceQuery;
import weka.classifiers.functions.LibSVM;

public class Model {
	public static void main(String[] args) throws Exception {

		// Input test data from txt
		DriverManager.getConnection("jdbc:mysql://localhost/friendloan", "root", "mysql");
		InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("mysql");
		query.setDatabaseURL("jdbc:mysql://localhost/friendloan?#characterEncoding=UTF-8&autoReconnect=true&useSSL=false");
		query.setQuery("select * from traindata");
		Instances train = query.retrieveInstances();
		train.setClassIndex(train.numAttributes() - 1);
		//System.out.println(train);
		// Finish input test
		// Building the model using Naive Bayes
		Classifier classifier = new J48();
		//J48,
		/** */
		classifier.buildClassifier(train);
		SerializationHelper.write(new FileOutputStream("C:\\Abhi\\ML\\Hackathons\\DataScientistSalary\\Classifierfileprdeictornb.txt"),classifier); 
	}
}
