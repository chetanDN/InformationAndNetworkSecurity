package weka;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


import javax.swing.JOptionPane;

import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;

public class Weka 
{

	public static void main(String args[]) throws Exception
	{
		NaiveBayes wnb = new NaiveBayes();   		//weka navieBayes
		
////////////////////////////////////////////////////////////////////////////////		
		Instances train = new Instances(new BufferedReader(new FileReader("D:\\CNE\\3.INS\\labPrograms\\check\\Program8_new\\datasets\\KDDTrain+.arff")));
		
        train.setClassIndex(train.numAttributes()-1);
        wnb.buildClassifier(train);
	        
        //Classifier is displayed in output screen
        System.out.println("CLASSIFIER BUILT FOR TRAINING SET");
        System.out.println(wnb);
///////////////////////////////////////////////////////////////////////////////        
        
        Instances test = new Instances(new BufferedReader(new FileReader("D:\\CNE\\3.INS\\labPrograms\\check\\Program8_new\\datasets\\KDDTest+.arff")));
        test.setClassIndex(test.numAttributes()-1);
 
    //    Instance current;
        
        //Output is written in output.txt file     
        FileWriter wr = new FileWriter("D:\\output.txt");
        wr.write("Classification of test data based on Bayesian Classification\n\n\n");
       
	        
        for(int i=0;i<test.numInstances();i++)
        {
        		Instance current = test.instance(i);
        		double pred = wnb.classifyInstance(current);
        		wr.append("ID: " + current.value(0)+
        				"\t\tactual " + test.classAttribute().value((int) current.classValue()) +
        				"\t\t predicted : " + test.classAttribute().value((int)pred)+ "\n");
        		
       }
///////////////////////////////////////////////////////////////////////////////////	
        
       wr.close();
       JOptionPane.showMessageDialog(null, "Classification complete");
        
	}
}


