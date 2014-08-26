package com.fwontain.gis;

import com.fwontain.ss.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import org.json.JSONObject;
import android.widget.Toast; 
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup; 

import com.fwontain.gis.GoogleMapUtis;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
 

public class Maps extends FragmentActivity implements LocationListener {
 
	protected static final Context context = null;
    double mLatitude=0;
    double mLongitude=0;
    private SupportMapFragment mapFragment;
	private GoogleMap googleMap;

	private final Handler mHandler = new Handler();
	private List<Marker> markers = new ArrayList<Marker>();
	private Marker selectedMarker;

	private Polyline polyLine;
	private PolylineOptions rectOptions = new PolylineOptions();

	private static final LatLng SMANSA = new LatLng(-6.5973401695917815,106.79324626922607);
	private static final LatLng SMANDA = new LatLng(-6.554762637626104,106.79407634037281);
	private static final LatLng SMANTI = new LatLng(-6.607102609708846,106.81097030639648);
	private static final LatLng SMANPAT = new LatLng(-6.616374617891379,106.80054187774658);
	private static final LatLng SMANLI = new LatLng(-6.556221784724749,106.79696824316903);
	private static final LatLng SMANEM = new LatLng(-6.572520988104901,106.79862159875398);
	private static final LatLng SMANJUH = new LatLng(-6.577204189470242,106.81453936870848);
	private static final LatLng SMANPAN = new LatLng(-6.545143511477557,106.82006447478159);
	private static final LatLng SMANLAN = new LatLng(-6.592778614140557,106.78839683532715);
	private static final LatLng SMANLUH = new LatLng(-6.559495616170994,106.76529760538118);
    
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.maps);
        
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		
        
    ////statement untuk membuat fungsi radio button yang bisa berganti mode maps/////
                
                RadioGroup rgViews = (RadioGroup) findViewById(R.id.rg_views);
                
                rgViews.setOnCheckedChangeListener(new OnCheckedChangeListener() {
         
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.rb_normal){
                            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        }else if(checkedId == R.id.rb_hybrid){
                            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        }else if(checkedId == R.id.rb_terrain){
                            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        }
                    }
                });
    ////statement untuk membuat fungsi radio button yang bisa berganti mode maps/////
    		
    		googleMap = mapFragment.getMap();
    		googleMap.setMyLocationEnabled(true);
    	googleMap.setBuildingsEnabled(true);
    	
    				
    			
    		  googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
    			
    			@Override
    			public void onCameraChange(CameraPosition cameraPosition) {
    				System.out.println(" ***** new position : " + cameraPosition);
    			}
    		});
    		  
    		 addMarkerToMap(SMANSA);
    		 addMarkerToMap(SMANDA);
    		 addMarkerToMap(SMANTI);
    		 addMarkerToMap(SMANPAT);
    		 addMarkerToMap(SMANLI);
    		 addMarkerToMap(SMANEM);
    		 addMarkerToMap(SMANJUH);
    		 addMarkerToMap(SMANPAN);
    		 addMarkerToMap(SMANLAN);
    		 addMarkerToMap(SMANLUH);

    	       		
//    	       fixZoom();
    	       
    	       final View mapView = mapFragment.getView();
    	       
    	       if (mapView.getViewTreeObserver().isAlive()) {
    	    	   mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
    	               @SuppressLint("NewApi") // We check which build version we are using.
    	               @Override
    	               public void onGlobalLayout() {
    	                   if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
    	                	   mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    	                   } else {
    	                	   mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    	                   }
    	                   GoogleMapUtis.fixZoomForMarkers(googleMap,markers);
    	               }
    	           });
    	       }	       
    	       
    	       googleMap.setOnMapClickListener(new OnMapClickListener(){

    				@Override
    				public void onMapClick(LatLng arg0) {
    					TextView info = (TextView) findViewById(R.id.info);
    			        info.setText("Current Loc :" + "("+googleMap.getMyLocation().getLatitude()+","+googleMap.getMyLocation().getLongitude()+")");
    		         
    		 
    					// TODO Auto-generated method stub
    					
    				}
    	        	
    	        });
    	       
    	       
    	       googleMap.setInfoWindowAdapter(new InfoWindowAdapter(){

				@Override
				public View getInfoContents(Marker arg0) {
					double Lat1 = googleMap.getMyLocation().getLatitude();
					double Long1 = googleMap.getMyLocation().getLongitude();
					double Lat2 = arg0.getPosition().latitude;
					double Long2 = arg0.getPosition().longitude;

					    	double dLat= Math.toRadians(Lat2-Lat1);
					    	double dLon= Math.toRadians(Long2-Long1);
					    	
					    	double a= Math.sin(dLat/2)*Math.sin(dLat/2)+Math.cos(Math.toRadians(Lat1))*Math.cos(Math.toRadians(Lat2))* Math.sin(dLon/2)*Math.sin(dLon/2);
					    	double c= 2*Math.asin(Math.sqrt(a));
					    	 
					    	double d= (int) (6371*c);
				
					    	
			      
					
					
					
					// TODO Auto-generated method stub
					// Getting view from the layout file info_window_layout
	                View v = getLayoutInflater().inflate(R.layout.info_window, null);
	 
	                // Getting the position from the marker
	                LatLng latLng = arg0.getPosition();
	                
	                
	                
	 
	                // Getting reference to the TextView to set latitude
	                TextView tvLat = (TextView) v.findViewById(R.id.tvlat);
	                Button b= (Button) v.findViewById(R.id.button1);
	                TextView lokasiku = (TextView) v.findViewById(R.id.myloc);
	                lokasiku.setText("Jarak tempuh "+"sekitar "+ d+" KM");
	                

	                TextView pos = (TextView) v.findViewById(R.id.posisi);
					pos.setText("Posisi : " +"("+String.valueOf(arg0.getPosition().latitude)+","+String.valueOf(arg0.getPosition().longitude)+")");		
							///Intent loc = new Intent(MainActivity.this, Lokasi.class);
							//startActivity(loc);// TODO Auto-generated method stub
							
					
	                // Getting reference to the TextView to set longitude
	                TextView tvLng = (TextView) v.findViewById(R.id.tvlang);
	 
	                // Setting the latitude
	                tvLat.setText(arg0.getTitle());
	 
	                // Setting the longitude
	                tvLng.setText(arg0.getSnippet());
	 
	                // Returning the view containing InfoWindow contents
	                return v;
				}

				@Override
				public View getInfoWindow(Marker arg0) {
					// TODO Auto-generated method stub
					return null;
				}
    	    	   
    	       });
    	       
    	       
    	       
    	       //method buat infowindow yang di klik//
    	        googleMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

    				@Override
    				public void onInfoWindowClick( Marker arg0) {
    					try {
    						
    						
    					StringBuilder urlString = new StringBuilder(); 	  					
    						String daddr = (String.valueOf(arg0.getPosition().latitude)+","+String.valueOf(arg0.getPosition().longitude)); 
    						urlString.append("http://maps.google.com/maps?f=d&hl=en"); 
    						urlString.append("&saddr="+String.valueOf(googleMap.getMyLocation().getLatitude())+","+String.valueOf(googleMap.getMyLocation().getLongitude())); 
    						urlString.append("&daddr="+daddr);
    						Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString.toString()));
    						startActivity(i);
    					} catch (Exception ee) {
    						Toast.makeText(getApplicationContext(), "Lokasi Saat Ini Belum Didapatkan, Mohon untuk menyalakan GPS Anda", Toast.LENGTH_LONG).show();
    					}// TODO Auto-generated method stub 
    					
    					
    				}
    	        	
    	        });
    	      //method buat infowindow yang di klik//
    	       
    	       
    	       
    	       
    	       
    	       
    	       
    	       
    	       
    	       
    	}

    	
    	
    	/**
    	 * Adds a marker to the map.
    	 */
    	public void addMarkerToMap(LatLng latLng) {
    		Marker smansa = googleMap.addMarker(new MarkerOptions()
            .position(SMANSA)
            .title("SMANSA")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smansa);
    		Marker smanda = googleMap.addMarker(new MarkerOptions()
            .position(SMANDA)
            .title("SMANDA")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanda);
    		Marker smanti = googleMap.addMarker(new MarkerOptions()
            .position(SMANTI)
            .title("SMANTI")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanti);
    		Marker smanpat = googleMap.addMarker(new MarkerOptions()
            .position(SMANPAT)
            .title("SMANPAT")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanpat);
    		Marker smanli = googleMap.addMarker(new MarkerOptions()
            .position(SMANLI)
            .title("SMANLI")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanli);
    		Marker smanem = googleMap.addMarker(new MarkerOptions()
            .position(SMANEM)
            .title("SMANEM")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanem);
    		Marker smanjuh = googleMap.addMarker(new MarkerOptions()
            .position(SMANJUH)
            .title("SMANJUH")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanjuh);
    		Marker smanpan = googleMap.addMarker(new MarkerOptions()
            .position(SMANPAN)
            .title("SMANPAN")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanpan);
    		Marker smanlan = googleMap.addMarker(new MarkerOptions()
            .position(SMANLAN)
            .title("SMANLAN")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanlan);
    		Marker smanluh = googleMap.addMarker(new MarkerOptions()
            .position(SMANLUH)
            .title("SMANLUH")
            .snippet("Population: 4,137,400")
           );
    		markers.add(smanluh);
    	}
    	
    	/**
    	 * Adds a list of markers to the map.
    	 */
    	public void addMarkersToMap(List<LatLng> latLngs) {
    		for (LatLng latLng : latLngs) {
    			Marker smansa = googleMap.addMarker(new MarkerOptions()
    	        .position(SMANSA)
    	        .title("SMANSA")
    	        .snippet("Population: 4,137,400")
    	       );
    			markers.add(smansa);
    			Marker smanda = googleMap.addMarker(new MarkerOptions()
    	        .position(SMANDA)
    	        .title("SMANDA")
    	        .snippet("Population: 4,137,400")
    	       );
    			markers.add(smanda);
    			Marker smanti = googleMap.addMarker(new MarkerOptions()
    	        .position(SMANTI)
    	        .title("SMANTI")
    	        .snippet("Population: 4,137,400")
    	       );
    			markers.add(smanti);
    			Marker smanpat = googleMap.addMarker(new MarkerOptions()
    	        .position(SMANPAT)
    	        .title("SMANPAT")
    	        .snippet("Population: 4,137,400")
    	       );
    			markers.add(smanpat);
    			
    		}
    	}
    	
    	
    	private void shareIt() {
    		TextView inpoh = (TextView) findViewById(R.id.info);
    		
    		  
    		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
    		sharingIntent.setType("text/plain");
    		String shareBody = (String) inpoh.getText().toString()+" @Iimnd";
    		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "GIS APPLICATION");
    		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
    		startActivity(Intent.createChooser(sharingIntent, "Share via"));
    		}

    	public void startAnimation() {
    		googleMap.animateCamera(
    		CameraUpdateFactory.zoomTo(googleMap.getCameraPosition().zoom + 0.5f), 
    		3000,
    		MyCancelableCallback);						

    		currentPt = 0-1;

    	}
    	
    	public void stopAnimation() {
    		
    	}
    	
    	int currentPt;
    	
    	CancelableCallback MyCancelableCallback = new CancelableCallback(){

    				@Override
    				public void onCancel() {
    					System.out.println("********** oncancel");
    				}

    				@Override
    				public void onFinish() {
    					
    					if(++currentPt < markers.size()){
    						float targetBearing = bearingBetweenLatLngs( googleMap.getCameraPosition().target, markers.get(currentPt).getPosition());
    						LatLng targetLatLng = markers.get(currentPt).getPosition();

    							System.out.println(" ------- " + currentPt + " - " + markers.size() + " - " + targetBearing + " - " + targetLatLng);
    						
    						CameraPosition cameraPosition =
    								new CameraPosition.Builder()
    										.target(targetLatLng)
    										.tilt(currentPt<markers.size()-1 ? 90 : 0)
    					                    .bearing(targetBearing)
    					                    .zoom(googleMap.getCameraPosition().zoom)
    					                    .build();

    						googleMap.animateCamera(
    								CameraUpdateFactory.newCameraPosition(cameraPosition), 
    								3000,
    								currentPt==markers.size()-1 ? FinalCancelableCallback : MyCancelableCallback);

//    						googleMap.moveCamera(
//    								CameraUpdateFactory.newCameraPosition(cameraPosition)); 
    						
    						markers.get(currentPt).showInfoWindow();
    						
    					}
    					
    				}
    		
    	};
    	
    	CancelableCallback FinalCancelableCallback = new CancelableCallback() {
    		
    		@Override
    		public void onFinish() {
    			GoogleMapUtis.fixZoomForMarkers(googleMap,markers);
    		}
    		
    		@Override
    		public void onCancel() {
    			
    		}
    	};
    	
    private Animator animator = new Animator();
    	
    	
    	
    public class Animator implements Runnable {
    		
    	private static final int ANIMATE_SPEEED = 7500;
    	private static final int ANIMATE_SPEEED_TURN = 5000;
    	private static final int BEARING_OFFSET = 20;

    		private final Interpolator interpolator = new LinearInterpolator();
    		
    		int currentIndex = 0;
    		
    		float tilt = 90;
    		float zoom = 13.5f;
    		boolean upward=true;
    		
    		long start = SystemClock.uptimeMillis();
    		
    		LatLng endLatLng = null; 
    		LatLng beginLatLng = null;
    		
    		boolean showPolyline = false;
    		
    		private Marker trackingMarker;
    		
    		public void reset() {
    			resetMarkers();
    			start = SystemClock.uptimeMillis();
    			currentIndex = 0;
    			endLatLng = getEndLatLng();
    			beginLatLng = getBeginLatLng();
    			
    		}
    		
    		public void stop() {
    			trackingMarker.remove();
    			mHandler.removeCallbacks(animator);
    			
    		}

    		public void initialize(boolean showPolyLine) {
    			reset();
    			this.showPolyline = showPolyLine;
    			
    			highLightMarker(0);
    			
    			if (showPolyLine) {
    				polyLine = initializePolyLine();
    			}
    			
    			// We first need to put the camera in the correct position for the first run (we need 2 markers for this).....
    			LatLng markerPos = markers.get(0).getPosition();
    			LatLng secondPos = markers.get(1).getPosition();
    			
    			setupCameraPositionForMovement(markerPos, secondPos);
    			
    		}
    		
    		private void setupCameraPositionForMovement(LatLng markerPos,
    				LatLng secondPos) {
    			
    			float bearing = bearingBetweenLatLngs(markerPos,secondPos);
    			
    			trackingMarker = googleMap.addMarker(new MarkerOptions().position(markerPos)
    					 .title("title")
    					 .snippet("snippet"));

    			CameraPosition cameraPosition =
    					new CameraPosition.Builder()
    							.target(markerPos)
    							.bearing(bearing + BEARING_OFFSET)
    		                    .tilt(90)
    		                    .zoom(googleMap.getCameraPosition().zoom >=16 ? googleMap.getCameraPosition().zoom : 16)
    		                    .build();
    			
    			googleMap.animateCamera(
    					CameraUpdateFactory.newCameraPosition(cameraPosition), 
    					ANIMATE_SPEEED_TURN,
    					new CancelableCallback() {
    						
    						@Override
    						public void onFinish() {
    							System.out.println("finished camera");
    							animator.reset();
    							Handler handler = new Handler();
    							handler.post(animator);	
    						}
    						
    						@Override
    						public void onCancel() {
    							System.out.println("cancelling camera");									
    						}
    					}
    			);
    		}		
    		
    		private Polyline polyLine;
    		private PolylineOptions rectOptions = new PolylineOptions();

    		
    		private Polyline initializePolyLine() {
    			//polyLinePoints = new ArrayList<LatLng>();
    			rectOptions.add(markers.get(0).getPosition());
    			return googleMap.addPolyline(rectOptions);
    		}
    		
    		/**
    		 * Add the marker to the polyline.
    		 */
    		private void updatePolyLine(LatLng latLng) {
    			List<LatLng> points = polyLine.getPoints();
    			points.add(latLng);
    			polyLine.setPoints(points);
    		}
    		

    		public void stopAnimation() {
    			animator.stop();
    		}
    		
    		public void startAnimation(boolean showPolyLine) {
    			if (markers.size()>2) {
    				animator.initialize(showPolyLine);
    			}
    		}		


    		@Override
    		public void run() {
    			
    			long elapsed = SystemClock.uptimeMillis() - start;
    			double t = interpolator.getInterpolation((float)elapsed/ANIMATE_SPEEED);
    			
//    			LatLng endLatLng = getEndLatLng();
//    			LatLng beginLatLng = getBeginLatLng();
    			
    			double lat = t * endLatLng.latitude + (1-t) * beginLatLng.latitude;
    			double lng = t * endLatLng.longitude + (1-t) * beginLatLng.longitude;
    			LatLng newPosition = new LatLng(lat, lng);
    			
    			trackingMarker.setPosition(newPosition);
    			
    			if (showPolyline) {
    				updatePolyLine(newPosition);
    			}
    			
    			// It's not possible to move the marker + center it through a cameraposition update while another camerapostioning was already happening.
    			//navigateToPoint(newPosition,tilt,bearing,currentZoom,false);
    			//navigateToPoint(newPosition,false);

    			if (t< 1) {
    				mHandler.postDelayed(this, 16);
    			} else {
    				
    				System.out.println("Move to next marker.... current = " + currentIndex + " and size = " + markers.size());
    				// imagine 5 elements -  0|1|2|3|4 currentindex must be smaller than 4
    				if (currentIndex<markers.size()-2) {
    				
    					currentIndex++;
    					
    					endLatLng = getEndLatLng();
    					beginLatLng = getBeginLatLng();

    					
    					start = SystemClock.uptimeMillis();

    					LatLng begin = getBeginLatLng();
    					LatLng end = getEndLatLng();
    					
    					float bearingL = bearingBetweenLatLngs(begin, end);
    					
    					highLightMarker(currentIndex);
    					
    					CameraPosition cameraPosition =
    							new CameraPosition.Builder()
    									.target(end) // changed this...
    				                    .bearing(bearingL  + BEARING_OFFSET)
    				                    .tilt(tilt)
    				                    .zoom(googleMap.getCameraPosition().zoom)
    				                    .build();

    					
    					googleMap.animateCamera(
    							CameraUpdateFactory.newCameraPosition(cameraPosition), 
    							ANIMATE_SPEEED_TURN,
    							null
    					);
    					
    					start = SystemClock.uptimeMillis();
    					mHandler.postDelayed(animator, 16);					
    					
    				} else {
    					currentIndex++;
    					highLightMarker(currentIndex);
    					stopAnimation();
    				}
    				
    			}
    		}
    		
    		

    		
    		private LatLng getEndLatLng() {
    			return markers.get(currentIndex+1).getPosition();
    		}
    		
    		private LatLng getBeginLatLng() {
    			return markers.get(currentIndex).getPosition();
    		}
    		
    		private void adjustCameraPosition() {
    			//System.out.println("tilt = " + tilt);
    			//System.out.println("upward = " + upward);
    			//System.out.println("zoom = " + zoom);
    			if (upward) {
    				
    				if (tilt<90) {
    					tilt ++;
    					zoom-=0.01f;
    				} else {
    					upward=false;
    				}
    				
    			} else {
    				if (tilt>0) {
    					tilt --;
    					zoom+=0.01f;
    				} else {
    					upward=true;
    				}
    			}			
    		}
    	}
    	 /**
    	  * Allows us to navigate to a certain point.
    	  */
    	 public void navigateToPoint(LatLng latLng,float tilt, float bearing, float zoom,boolean animate) {
    		 CameraPosition position =
    				 new CameraPosition.Builder().target(latLng)
    	                        .zoom(zoom)
    	                        .bearing(bearing)
    	                        .tilt(tilt)
    	                        .build();
    	    	
    	    	changeCameraPosition(position, animate);
    	    	
    	 }
    	 public void navigateToPoint(LatLng latLng, boolean animate) {
    		 CameraPosition position = new CameraPosition.Builder().target(latLng).build();
    		 changeCameraPosition(position, animate);
    	 }
    	 
    	 private void changeCameraPosition(CameraPosition cameraPosition, boolean animate) {
    		 CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
    		 
    	    	if (animate) {
    	    		googleMap.animateCamera(cameraUpdate);
    	    	} else {
    	    	  	googleMap.moveCamera(cameraUpdate);
    	    	}

    	 }
    	private Location convertLatLngToLocation(LatLng latLng) {
    		Location loc = new Location("someLoc");
    		loc.setLatitude(latLng.latitude);
    		loc.setLongitude(latLng.longitude);
    		return loc;
    	}
    	
    	private float bearingBetweenLatLngs(LatLng begin,LatLng end) {
    		Location beginL= convertLatLngToLocation(begin);
    		Location endL= convertLatLngToLocation(end);
    		return beginL.bearingTo(endL);
    	}	
    	
    	
    private void highLightMarker(Marker marker) {
    		
    		/*
    		for (Marker foundMarker : this.markers) {
    			if (!foundMarker.equals(marker)) {
    				foundMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
    			} else {
    				foundMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
    				foundMarker.showInfoWindow();
    			}
    		}
    		*/
    		marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
    		marker.showInfoWindow();

    		//Utils.bounceMarker(googleMap, marker);
    		
    		this.selectedMarker=marker;
    	}	
    private void highLightMarker(int index) {
    	highLightMarker(markers.get(index));
    }
    private void resetMarkers() {
    	for (Marker marker : this.markers) {
    		marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
    	}
        
 

    }


    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
 
        return super.onCreateOptionsMenu(menu);
    }
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_start:
            animator.startAnimation(true);
            return true;
        case R.id.action_stop:
            // location found
        	animator.stop();
            
            return true;
            
        case R.id.action_sharing:
        	shareIt();
        	return true;
        	
        case R.id.action_ss:
        	Bitmap bitmap = takeScreenshot();
            saveBitmap(bitmap);
        	return true;
        
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
    
    
	public Bitmap takeScreenshot() {
		   View rootView = findViewById(R.id.map).getRootView();
		   rootView.setDrawingCacheEnabled(true);
		   return rootView.getDrawingCache();
		}

		 public void saveBitmap(Bitmap bitmap) {
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			 String currentDateandTime = sdf.format(new Date());
			 
			 sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
		    File imagePath = new File(Environment.getExternalStorageDirectory() + currentDateandTime);
		    FileOutputStream fos;
		    try {
		        fos = new FileOutputStream(imagePath);
		        bitmap.compress(CompressFormat.PNG, 100, fos);
		        fos.flush();
		        fos.close();
		    } catch (FileNotFoundException e) {
		        Log.e("GREC", e.getMessage(), e);
		    } catch (IOException e) {
		        Log.e("GREC", e.getMessage(), e);
		    }
		}   
    
    
		 
		 
    
    
    
    
    
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
    
}
    
     