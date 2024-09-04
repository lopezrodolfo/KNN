/*Helper class used to count number of classes */
public class CountData {
    private int count;
    private String className;
    
    public CountData(String className) {
            this.className = className;
            this.count = 0;
    }
    
    public void increase() {
            this.count++;
    }
    
    public void decrease() {
            this.count--;
    }
    
    public int getCount() {
            return this.count;
    }
    
    public String getClasssName() {
            return this.className;
    }
}
