package com.gn.diesel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class TankfActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String version = "";
	private String app_version = "";
	private String update_link = "";
	private String distance = "";
	private String message = "";
	private double i = 0;
	private double a = 0;
	private double b = 0;
	private String last = "";
	private String alert = "";
	private double dis = 0;
	private double totaldis = 0;
	private double sensordis = 0;
	private double empty = 0;
	private double low = 0;
	private double medium = 0;
	private double full = 0;
	private String valuee = "";
	private String totadis = "";
	private String sensordisss = "";
	private String emptyyy = "";
	private String lowww = "";
	private String mediummm = "";
	private String fullll = "";
	private double num = 0;
	private boolean miss_you_error_404 = false;
	private String currentTime = "";
	private HashMap<String, Object> tank = new HashMap<>();
	
	private LinearLayout mainnn;
	private LinearLayout moni;
	private TextView title;
	private TextView monitor;
	private LinearLayout linear1;
	private TextView distitle;
	private TextView lastvalu;
	private TextView timee;
	
	private SharedPreferences shar;
	private DatabaseReference db = _firebase.getReference("db");
	private ChildEventListener _db_child_listener;
	private Intent it = new Intent();
	private TimerTask t;
	private SharedPreferences value;
	private DatabaseReference data = _firebase.getReference("data");
	private ChildEventListener _data_child_listener;
	private TimerTask time;
	private TimerTask tjs;
	private ObjectAnimator anim = new ObjectAnimator();
	private Intent hometoabout = new Intent();
	private Intent hometocontact = new Intent();
	
	private OnCompleteListener fcm_onCompleteListener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tankf);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		mainnn = findViewById(R.id.mainnn);
		moni = findViewById(R.id.moni);
		title = findViewById(R.id.title);
		monitor = findViewById(R.id.monitor);
		linear1 = findViewById(R.id.linear1);
		distitle = findViewById(R.id.distitle);
		lastvalu = findViewById(R.id.lastvalu);
		timee = findViewById(R.id.timee);
		shar = getSharedPreferences("shar", Activity.MODE_PRIVATE);
		value = getSharedPreferences("value", Activity.MODE_PRIVATE);
		
		_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals("version update")) {
					version = _childValue.get("version").toString();
					update_link = _childValue.get("link").toString();
					if (version.equals(app_version)) {
						
					}
					else {
						if (!version.equals(app_version)) {
							final AlertDialog sucess = new AlertDialog.Builder(TankfActivity.this).create();
							LayoutInflater inflater = getLayoutInflater();
							
							View convertView = (View) inflater.inflate(R.layout.dialog, null);
							sucess.setView(convertView);
							
							sucess.requestWindowFeature(Window.FEATURE_NO_TITLE);  sucess.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
							
							LinearLayout lin1 = (LinearLayout) convertView.findViewById(R.id.linear1);
							
							TextView txt2 = (TextView) convertView.findViewById(R.id.textview2);
							
							TextView txt3 = (TextView) convertView.findViewById(R.id.textview3);
							
							ImageView b_img = (ImageView) convertView.findViewById(R.id.imageview1);
							
							android.graphics.drawable.GradientDrawable a = new android.graphics.drawable.GradientDrawable();
							a.setColor(Color.parseColor("#000000"));
							a.setCornerRadius(50);
							lin1.setBackground(a);
							
							b_img.setElevation(5);
							
							sucess.show();
							
							txt2.setOnClickListener(new View.OnClickListener(){
								    public void onClick(View v){
									sucess.dismiss();
								}});
							
							txt3.setOnClickListener(new View.OnClickListener(){
								    public void onClick(View v){
									
									it.setAction(Intent.ACTION_VIEW);
									it.setData(Uri.parse(update_link));
									startActivity(it);
								}});
							
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		db.addChildEventListener(_db_child_listener);
		
		_data_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				distance = _childValue.get("message").toString();
				if (distance.equals("level5")) {
					linear1.setBackgroundResource(R.drawable.full);
					monitor.setText("Level 5");
					monitor.setTextColor(0xFF43A047);
				}
				if (distance.equals("level4")) {
					linear1.setBackgroundResource(R.drawable.medium);
					monitor.setText("Level 4");
					monitor.setTextColor(0xFF3F51B5);
				}
				if (distance.equals("level3")) {
					linear1.setBackgroundResource(R.drawable.medium);
					monitor.setText("Level 3");
					monitor.setTextColor(0xFF3F51B5);
				}
				if (distance.equals("level2")) {
					linear1.setBackgroundResource(R.drawable.low);
					monitor.setText("Low Level 2");
					monitor.setTextColor(0xFFF44336);
				}
				if (distance.equals("level1")) {
					title.setText("Kindly fill the Diesel");
					monitor.setText("Warning ⚠️   Empty Level 1");
					monitor.setTextColor(0xFFD32F2F);
					linear1.setBackgroundResource(R.drawable.empty);
				}
				message = _childValue.get("distance").toString();
				currentTime = _childValue.get("currentTime").toString();
				dis = Double.parseDouble(message);
				i = Double.parseDouble(message);
				a = dis * 2;
				b = a;
				last = String.valueOf((long)(b));
				lastvalu.setText(last);
				timee.setText(currentTime);
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				distance = _childValue.get("message").toString();
				if (distance.equals("level5")) {
					linear1.setBackgroundResource(R.drawable.full);
					monitor.setText("Level 5");
					monitor.setTextColor(0xFF43A047);
				}
				if (distance.equals("level4")) {
					linear1.setBackgroundResource(R.drawable.medium);
					monitor.setText("Level 4");
					monitor.setTextColor(0xFF3F51B5);
				}
				if (distance.equals("level3")) {
					linear1.setBackgroundResource(R.drawable.medium);
					monitor.setText("Level 3");
					monitor.setTextColor(0xFF3F51B5);
				}
				if (distance.equals("level2")) {
					linear1.setBackgroundResource(R.drawable.low);
					monitor.setText("Low Level 2");
					monitor.setTextColor(0xFFF44336);
				}
				if (distance.equals("level1")) {
					title.setText("Kindly fill the Diesel");
					monitor.setText("Warning ⚠️   Empty Level 1");
					monitor.setTextColor(0xFFD32F2F);
					linear1.setBackgroundResource(R.drawable.empty);
				}
				message = _childValue.get("distance").toString();
				message = _childValue.get("currentTime").toString();
				dis = Double.parseDouble(message);
				i = Double.parseDouble(message);
				a = dis * 2;
				b = a;
				last = String.valueOf((long)(b));
				lastvalu.setText(last);
				timee.setText(currentTime);
				if (15 > dis) {
					_push_notification("Warning ⚠️", "Kindly fill the diesel to use the generator🧐");
				}
				if (20 < dis) {
					_push_notification("Warning ⚠️", "Diesel tank low level 2😐");
					if (25 < dis) {
						_push_notification("Alert", "Diesel tank is Medium 😋");
						if (30 < dis) {
							_push_notification("Alert", "Ready to use Generator 😍");
						}
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		data.addChildEventListener(_data_child_listener);
		
		fcm_onCompleteListener = new OnCompleteListener<InstanceIdResult>() {
			@Override
			public void onComplete(Task<InstanceIdResult> task) {
				final boolean _success = task.isSuccessful();
				final String _token = task.getResult().getToken();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 
			Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(0xFF008375); w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); }
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 1);
		monitor.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
		distitle.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 0);
		lastvalu.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/sofiapro_regular.ttf"), 1);
		String urc = "com.gn.gowater";
		
		android.content.pm.PackageManager pm = getPackageManager(); 
		try { android.content.pm.PackageInfo pInfo = pm.getPackageInfo(urc, android.content.pm.PackageManager.GET_ACTIVITIES); 
			String version = pInfo.versionName;
			app_version = version;
		} catch (android.content.pm.PackageManager.NameNotFoundException e) { }
		db.addChildEventListener(_db_child_listener);
	}
	
	public void _push_notification(final String _title, final String _message) {
		final Context context = getApplicationContext();
		
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Intent intent = new Intent(this, MainActivity.class); 
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP); 
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0); 
		androidx.core.app.NotificationCompat.Builder builder; 
		
		    
		    String channelId = "channel-01";
		    String channelName = "Channel Name";
		    int importance = NotificationManager.IMPORTANCE_HIGH;
		
		    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
			        NotificationChannel mChannel = new NotificationChannel(
			                channelId, channelName, importance);
			        notificationManager.createNotificationChannel(mChannel);
			    }
		 androidx.core.app.NotificationCompat.Builder mBuilder = new androidx.core.app.NotificationCompat.Builder(context, channelId)
		            .setSmallIcon(R.drawable.img2)
		            .setContentTitle(_title)
		            .setContentText(_message)
		            .setAutoCancel(true)
		            .setOngoing(false)
		            .setContentIntent(pendingIntent);
		    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		    stackBuilder.addNextIntent(intent);
		    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		    );
		    mBuilder.setContentIntent(resultPendingIntent);
		
		    notificationManager.notify((int)num, mBuilder.build());
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}