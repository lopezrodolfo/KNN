/*Helper class to store distance measure */
public class DistanceData {
    private double distance;
    private String className;
    
    public DistanceData(double distance, String className) {
            this.distance = distance;
            this.className = className;
    }
    
    public Double getDistance() {
            return distance;
    }
    
    public String getClasssName() {
            return className;
    }
    }
