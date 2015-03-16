//Code adapted from http://commons.apache.org/proper/commons-math/userguide/ml.html

package mobileSensingAndDataAnalysis;

import javax.tools.JavaFileManager.Location;
import org.apache.commons.math3.ml.clustering.Clusterable;

public class LocationWrapper implements Clusterable {
    private double[] points;

    public LocationWrapper(String latitude, String longitude) {
        this.points = new double[] { Double.valueOf(latitude), Double.valueOf(longitude)};
    }

    public double[] getPoint() {
        return points;
    }
}