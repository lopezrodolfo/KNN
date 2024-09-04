/*Handles all calculations/operations for KNN alg learned in class */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class KNNClassifier {
final String class1 = "Iris-setosa";
final String class2 = "Iris-versicolor";
final String class3 = "Iris-virginica";

private double sepalLengthRange;
private double sepalWidthRange;
private double petalLengthRange;
private double petalWidthRange;

private ArrayList<Data> trainingDatas, testDatas;
private int k;

/*constructor to intialize main classifier*/
public KNNClassifier(ArrayList<Data> trainingDatas, ArrayList<Data> testDatas, int k) {
        if(trainingDatas.size() == 0 || testDatas.size() == 0 ) {
                System.out.println("KNNClassifier Error: Instaces are empty!");
                return;
        }
        this.trainingDatas = trainingDatas;
        this.testDatas = testDatas;
        this.k = k;
        calculateRanges();
}

public void calculateRanges() {
    double sepalLengthRangeMax = Integer.MIN_VALUE;
    double sepalLengthRangeMin = Integer.MAX_VALUE;
    double sepalWidthRangeMax = Integer.MIN_VALUE;
    double sepalWidthRangeMin = Integer.MAX_VALUE;
    double petalLengthRangeMax = Integer.MIN_VALUE;
    double petalLengthRangeMin = Integer.MAX_VALUE;
    double petalWidthRangeMax = Integer.MIN_VALUE;
    double petalWidthRangeMin = Integer.MAX_VALUE;

    for (int i = 0; i < trainingDatas.size(); i++) {
            if (trainingDatas.get(i).getSepalLength() > sepalLengthRangeMax) sepalLengthRangeMax = trainingDatas.get(i).getSepalLength();
            if (trainingDatas.get(i).getSepalLength() < sepalLengthRangeMin) sepalLengthRangeMin = trainingDatas.get(i).getSepalLength();
            if (trainingDatas.get(i).getSepalWidth() > sepalWidthRangeMax) sepalWidthRangeMax = trainingDatas.get(i).getSepalWidth();
            if (trainingDatas.get(i).getSepalWidth() < sepalWidthRangeMin) sepalWidthRangeMin = trainingDatas.get(i).getSepalWidth();
            if (trainingDatas.get(i).getPetalLength() > petalLengthRangeMax) petalLengthRangeMax = trainingDatas.get(i).getPetalLength();
            if (trainingDatas.get(i).getPetalLength() < petalLengthRangeMin) petalLengthRangeMin = trainingDatas.get(i).getPetalLength();
            if (trainingDatas.get(i).getSepalWidth() > petalWidthRangeMax) petalWidthRangeMax = trainingDatas.get(i).getSepalWidth();
            if (trainingDatas.get(i).getSepalWidth() < petalWidthRangeMin) petalWidthRangeMin = trainingDatas.get(i).getSepalWidth();

    sepalLengthRange = sepalLengthRangeMax - sepalLengthRangeMin;
    sepalWidthRange = sepalWidthRangeMax - sepalWidthRangeMin;
    petalLengthRange = petalLengthRangeMax - petalLengthRangeMin;
    petalWidthRange = petalWidthRangeMax - petalWidthRangeMin;
}
}

/*Prime method (calls on helper methods below) calculating Euclidean distance measure */
public ArrayList<String> classifyClasses(){
        ArrayList<String> classifiedClassNames = new ArrayList<String>();
        for (int i = 0; i < testDatas.size(); i++) {
                ArrayList<DistanceData> distanceDatas = new ArrayList<DistanceData>();
                Data testData = testDatas.get(i);
                for (int j = 0; j < trainingDatas.size(); j++) {
                        Data trainingData = trainingDatas.get(j);
                        double distance = calculateDistance(testData, trainingData);
                        String className = trainingDatas.get(j).getClasssName();
                        distanceDatas.add(new DistanceData(distance, className));
                }
                Collections.sort(distanceDatas, new distanceComparator());
                String classifiedClassName = calculateCount(distanceDatas, k);

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("iris-test.txt", true));
                    writer.write("Test object " + (i + 1) + " is classified as: " + classifiedClassName + ", actual result is: " + testDatas.get(i).getClasssName()+ "\n");
                    writer.close();
                } catch (Exception e) {System.out.println(e);}
                classifiedClassNames.add(classifiedClassName);
        }
        return classifiedClassNames;
}

public String calculateCount(ArrayList<DistanceData> distanceDatas, int k) {
        String classifiedClassName;
        ArrayList<CountData> CountDatas = new ArrayList<CountData>();
        CountDatas.add(new CountData("Iris-setosa"));
        CountDatas.add(new CountData("Iris-versicolor"));
        CountDatas.add(new CountData("Iris-virginica"));
        for(int j = 0; j < k; j++) {
                if (distanceDatas.get(j).getClasssName().equals(class1)) CountDatas.get(0).increase();
                else if (distanceDatas.get(j).getClasssName().equals(class2)) CountDatas.get(1).increase();
                else CountDatas.get(2).increase();
        }
        Collections.sort(CountDatas, new CountComparator());
        if(CountDatas.get(0).getCount() == CountDatas.get(1).getCount()) {
                System.out.println("Found same count of classes. Updating k value to: " + (k + 1));
                classifiedClassName = calculateCount(distanceDatas, k + 1);
        }
        else classifiedClassName = CountDatas.get(0).getClasssName();
        return classifiedClassName;
}

public Double calculateDistance(Data a, Data b) {
        double distance;
        distance = Math.pow((a.getSepalLength() - b.getSepalLength()),2)/Math.pow(sepalLengthRange,2) +
                   Math.pow((a.getSepalWidth() - b.getSepalWidth()),2)/Math.pow(sepalWidthRange,2) +
                   Math.pow((a.getPetalLength() - b.getPetalLength()),2)/Math.pow(petalLengthRange,2) +
                   Math.pow((a.getPetalWidth() - b.getPetalWidth()),2)/Math.pow(petalWidthRange,2);
        distance = Math.sqrt(distance);
        return distance;
}

class distanceComparator implements Comparator<DistanceData> {
public int compare(DistanceData a, DistanceData b) {
        if(a.getDistance() < b.getDistance()) {
                return -1;
        } else if(a.getDistance() > b.getDistance()) {
                return 1;
        } else {
                return 0;
        }
}
}

class CountComparator implements Comparator<CountData> {
public int compare(CountData a, CountData b) {
        if(a.getCount() < b.getCount()) {
                return 1;
        } else if(a.getCount() > b.getCount()) {
                return -1;
        } else {
                return 0;
        }
}
}
}
