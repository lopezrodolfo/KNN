/*Authors: Rodolfo Lopez and Andrew Kirraine
 *Last Modified: ll:55pm on June 13
 *K Nearest Neighbor Algoritm aka clustering
 *Driver
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Random;

public class KNN {

public KNN(String training, Scanner sc) {
        try {
            PrintWriter writer = new PrintWriter("iris-text.txt");
            writer.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("e");
        }

        int k = 0;
        System.out.print("Enter the k value: ");
        k = Integer.parseInt(sc.nextLine());


        System.out.println("Enter percentage from 0-1.0");
        double percent = Double.parseDouble(sc.nextLine());
        ArrayList<Data> trainingDatas = loadDatas(training);
        double numLinesTest = Math.round(trainingDatas.size() * percent);

        ArrayList<Data> testDatas = new ArrayList<Data>();
        Random rand = new Random();

        while(numLinesTest >0) {
            int num = rand.nextInt(trainingDatas.size());
            testDatas.add(trainingDatas.get(num));
            trainingDatas.remove(num);
            numLinesTest--;
        }

        KNNClassifier knnc = new KNNClassifier(trainingDatas, testDatas, k);
        ArrayList<String> classifiedClassNames = knnc.classifyClasses();
        int correctCount = 0;
        for (int i = 0; i < testDatas.size(); i++) {
                if (testDatas.get(i).getClasssName().equals(classifiedClassNames.get(i))) correctCount++;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("iris-test.txt", true));
            double accuracy = (double) correctCount/testDatas.size() * 100;
            writer.write("Correct Count: " + correctCount + "/" + testDatas.size() + ", Accuracy: " + accuracy + "%");
            writer.close();
        }
        catch(Exception e) {System.out.println(e);}
        System.out.println("Good!");

}

private ArrayList<Data> loadDatas (String fileName){
        ArrayList<Data> tempDatas = new ArrayList<Data>();
        try{
                BufferedReader bf = new BufferedReader(new FileReader(fileName));
                String line = bf.readLine();
                while(line != null) {
                    String[] data = line.split(",");
                    String sepalLength = data[0].trim();
                    String sepalWidth = data[1].trim();
                    String petalLength = data[2].trim();
                    String petalWidth = data[3].trim();
                    String className = data[4].trim();
    
                    if (sepalLength != null && sepalWidth != null && petalLength != null && petalWidth != null && className != null) {
                        tempDatas.add(new Data(Float.parseFloat(sepalLength), Float.parseFloat(sepalWidth), Float.parseFloat(petalLength), Float.parseFloat(petalWidth), className));
                    }

                    line = bf.readLine();
                }
                bf.close();
        }catch(IOException e) {System.out.println("E: Error occured with loading file. " + e);}
        return tempDatas;
}

public static void main(String[] args) {
        String training = args[0];
        
        System.out.println("Loading data (training:" + training);

        Scanner sc = new Scanner(System.in);
        while(true) {
            new KNN(training, sc);
            System.out.println("Y to run again.");
            String ans = sc.nextLine();
            if(!ans.equals("y")) {
                break;
            }
            sc.close();
        }
}
}
