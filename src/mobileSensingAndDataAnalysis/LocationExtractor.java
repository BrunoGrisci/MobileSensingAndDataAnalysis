package mobileSensingAndDataAnalysis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaFileManager.Location;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import com.opencsv.CSVReader;

public class LocationExtractor {
	
	//Indexes for easy access to the attributes
	final static int PRIORITY = 0;
	final static int ARRIVAL_TIME = 1;
	final static int REMOVAL_TIME = 2;
	final static int CLICKED = 3;
	final static int SENDER_PACKAGE = 4;
	final static int SENDER_APP_NAME = 5;
	final static int PHONE_sTATUS = 6;
	final static int RINGER_MODE = 7;
	final static int INTERRUPTION_FILTER = 8;
	final static int FILTER_CLEARED = 9;
	final static int LED = 10;
	final static int VIBRATE = 11;
	final static int SOUND = 12;
	final static int LATITUDE = 13;
	final static int LONGITUDE = 14;
	final static int ACTIVITY = 15;
	final static int SURROUNDING_SOUND = 16;
	final static int WIFI = 17;
	final static int PROXIMITY = 18;
	
	public static void main(String[] args) throws IOException {
		String csvFilename = "Bruno_Grisci_notifications.csv";
		CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
		ArrayList<String[]> noti = new ArrayList<String[]>();
		String[] notification = null;
		
		notification = csvReader.readNext();
		while((notification = csvReader.readNext()) != null) {
			noti.add(notification);
		}
		csvReader.close();
		
		//Adapted from http://commons.apache.org/proper/commons-math/userguide/ml.html
		List<LocationWrapper> clusterInput = new ArrayList<LocationWrapper>(noti.size());
		for (String[] location : noti) {
		    clusterInput.add(new LocationWrapper(location[LATITUDE], location[LONGITUDE]));
		}
		
		DBSCANClusterer<LocationWrapper> clusterer = new DBSCANClusterer<LocationWrapper>(1.0, 100);
		List<Cluster<LocationWrapper>> clusterResults = clusterer.cluster(clusterInput);

		// output the clusters
		PrintWriter writer = new PrintWriter("clusters.txt", "UTF-8");
		for (int i = 0; i < clusterResults.size(); i++) {
		    System.out.println("CLUSTER " + i);
		    writer.println("CLUSTER " + i);
		    writer.println();
		    for (LocationWrapper locationWrapper : clusterResults.get(i).getPoints()) {
		        writer.println(locationWrapper.printPoint());
		    }
		}
		writer.close();
	}
}