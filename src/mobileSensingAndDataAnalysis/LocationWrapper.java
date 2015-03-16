//Code adapted from http://commons.apache.org/proper/commons-math/userguide/ml.html

package mobileSensingAndDataAnalysis;

import org.apache.commons.math3.ml.clustering.Clusterable;

public class LocationWrapper implements Clusterable {
    private double[] points;

    public LocationWrapper(String latitude, String longitude) {
        this.points = new double[] { Double.parseDouble(latitude), Double.parseDouble(longitude)};
    }

    public double[] getPoint() {
        return points;
    }
    
    public String printPoint() {
    	return (String.valueOf(this.points[0]) + ", " + String.valueOf(this.points[1]));
    }
}