/*Holds the data found in traing file. Also used for testing */
public class Data {
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private String className;
    
    public Data(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String className) {
            this.sepalLength = sepalLength;
            this.sepalWidth = sepalWidth;
            this.petalLength = petalLength;
            this.petalWidth = petalWidth;
            this.className = className;
    }
    
    public Double getSepalLength() {
            return sepalLength;
    }
    
    public Double getSepalWidth() {
            return sepalWidth;
    }
    
    public Double getPetalLength() {
            return petalLength;
    }
    
    public Double getPetalWidth() {
            return petalWidth;
    }
    
    public String getClasssName() {
            return className;
    }
}
    
