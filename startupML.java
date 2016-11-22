package tryakash;
import java.util.List;

import processing.core.PApplet;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.MarkerFactory;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

 
public class startupML extends PApplet {

	UnfoldingMap map;

	 Location startLocation = new Location( 40.09623f, 116.21619f);
	
	public void settings() {
		size(800, 600, P2D);
	}
	
	public void setup() {
		size(800,600);
		map = new UnfoldingMap(this);
		AbstractMapProvider provider = new Google.GoogleMapProvider();
		map = new UnfoldingMap(this, 50, 50, 700, 500, provider); 
         MapUtils.createDefaultEventDispatcher(this, map);

		
	 	map.zoomAndPanTo(10, startLocation);

		List<Feature> features = FileLineReader.loadData(this, " ");
		MarkerFactory markerFactory = new MarkerFactory();
		markerFactory.setLineClass(ColoredLinesMarker.class);
		List<Marker> markers = markerFactory.createMarkers(features);
		map.addMarkers(markers);
	}

	public void draw() {
		map.draw();
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { GPXSpeedTrackApp.class.getName() });
	}
}