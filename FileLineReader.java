 package tryakash;
import java.io.IOException;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;
import java.util.stream.Stream;

import processing.core.PApplet;
import processing.data.XML;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.Feature.FeatureType;
import de.fhpotsdam.unfolding.data.GPXReader;
import de.fhpotsdam.unfolding.data.GeoDataReader;
import de.fhpotsdam.unfolding.data.ShapeFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.utils.GeoUtils;
import de.fhpotsdam.utils.StringUtils;
 
public class FileLineReader extends GeoDataReader  {

	 
	public static List<Feature> loadData(PApplet p, String gpxFilename) {
		List<Feature> trackFeatures = new ArrayList<Feature>();

		 ShapeFeature trackFeature = new ShapeFeature(FeatureType.LINES);
		List<Double> speedList = new ArrayList<Double>();
		    
		   
         ArrayList<Float> lati = new ArrayList<>();
        ArrayList<Float> longi = new ArrayList<>();
        int readMaxLines = 2000;
        Path file = Paths.get("/Users/akash/Documents/eclipseWorkspace/RFIDLabwithLLRWReader/data/sahoo/test"); //folder for all txt file data
		if(readMaxLines > 0){
			
        try(Stream<Path> paths = Files.walk(file)) {
        	paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath)) {
		        	
		        	try{
			            Stream<String> lines = Files.lines( filePath, StandardCharsets.UTF_8 );
			            for(  String line : (Iterable<String>) lines::iterator )
			            {	
		        	
		        	
			            	String csvParser[] = null;
			            	 
             	  csvParser = line.split(",");
            	  lati.add(Float.parseFloat(csvParser[2]));
            	  longi.add(Float.parseFloat(csvParser[3]));
                
			            }
		            }catch(Exception e){
		            	
		            }
		         
		           
		            
		            
		        }
		    });
		} 
         
 catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	} 
        System.out.println(lati.size()+" "+longi.size());
		for (int i = 0; i < lati.size()-1; i++) {

			 
			float lat = longi.get(i);
			float lon = lati.get(i);
			 
			Location location = new Location(lat, lon);

			 
			double speed = 0;
 
			speedList.add(speed);
			trackFeature.addLocation(location);
		}
		trackFeature.putProperty("speedList", speedList);
		trackFeatures.add(trackFeature);
		
		
	

		return trackFeatures;
	}

}

