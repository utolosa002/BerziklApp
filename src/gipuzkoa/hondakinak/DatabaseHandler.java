package gipuzkoa.hondakinak;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	 
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/gipuzkoa.hondakinak/databases/";
 
    private static String DB_NAME = "hondakinak.db";
 
    private SQLiteDatabase myDataBase; 
 
    private final Context myContext;
    
	// hondakins table name
	private static final String TABLE_HONDAKINAK_EU = "hondakineu";
	private static final String TABLE_HONDAKINAK_ES = "hondakines";

	// hondakins Table Columns names
	private static final String KEY_NAME = "izena";

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DatabaseHandler(Context context) {
 
    	super(context, DB_NAME, null, 1);
        this.myContext = context;
    }	
 
  /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		System.out.println("do nothing - database already exist");
    	}else{
    		this.getReadableDatabase();
        	System.out.println("do not exist");
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		
    		String myPath = DB_PATH + DB_NAME;
    		System.out.println("checking "+myPath);
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
    		System.out.println("database does't exist yet.");

    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open("hondakinak.db");
    	System.out.println("copying IN "+myInput.toString());
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
    	System.out.println("copying OUT "+ outFileName);
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
 
    public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
 
    @Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}

	public ArrayList<Hondakina> getHondakinak(String str) {
		ArrayList<Hondakina> hondakinList = new ArrayList<Hondakina>();
		SQLiteDatabase db = this.getReadableDatabase();
		String pattern = "[a-zA-Z]*[- ()]*[a-zA-Z]*";
		if (!str.matches(pattern)) {
			if(str==""||str==null){
		}else{str = " ";}
		}
		String selectQuery = "SELECT  * FROM " + TABLE_HONDAKINAK_EU
				+ " WHERE izena LIKE '" + str + "%'";
Cursor cursor = db.rawQuery(selectQuery, null);
if (cursor.moveToFirst()) {
		do {
			Hondakina hondakin = new Hondakina();
			hondakin.setID(Integer.parseInt(cursor.getString(0)));
			hondakin.setName(cursor.getString(1));
			hondakin.setNon(cursor.getString(2));
			hondakin.setInfo(cursor.getString(3));
			// Adding hondakin to list
			hondakinList.add(hondakin);
		} while (cursor.moveToNext());
}
		
		// return hondakin list
		return hondakinList;
	}

	public ArrayList<Hondakina> getResiduos(String str) {
		ArrayList<Hondakina> hondakinList = new ArrayList<Hondakina>();
		SQLiteDatabase db = this.getReadableDatabase();
		String pattern = "[a-zA-Z]*[- ()]*[a-zA-Z]*";
		if (!str.matches(pattern)) {
			if(str==""||str==null){
		}else{str = " ";}
		}
		String selectQuery = null;
			selectQuery = "SELECT  * FROM " + TABLE_HONDAKINAK_ES
					+ " WHERE izena LIKE '" + str + "%'";

		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Hondakina hondakin = new Hondakina();
				hondakin.setID(Integer.parseInt(cursor.getString(0)));
				hondakin.setName(cursor.getString(1));
				hondakin.setNon(cursor.getString(2));
				hondakin.setInfo(cursor.getString(3));
				// Adding hondakin to list
				hondakinList.add(hondakin);
			} while (cursor.moveToNext());
		}

		// return hondakin list
		return hondakinList;
	}

	public ArrayList<String> getHondakinIzenak(String str) {
		ArrayList<String> hondakinList = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		String pattern = "[a-zA-Z]*[- ()]*[a-zA-Z]*";
		if (!str.matches(pattern)) {
			if(str==""||str==null){
		}else{str = " ";}
		}
		String selectQuery = "SELECT " + KEY_NAME + " FROM " + TABLE_HONDAKINAK_EU
					+ " WHERE izena LIKE '" + str + "%'";
		
		String Hizena;
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Hizena = cursor.getString(0);
				// Adding hondakin to list
				hondakinList.add(Hizena);
			} while (cursor.moveToNext());
		}

		// return hondakin list
		return hondakinList;
	}	
	public ArrayList<String> getNombresResiduos(String str) {
		ArrayList<String> hondakinList = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		String pattern = "[a-zA-Z]*[- ()]*[a-zA-Z]*";
		if (!str.matches(pattern)) {
			if(str==""||str==null){
		}else{str = " ";}
		}
		
		String selectQuery;
			selectQuery = "SELECT " + KEY_NAME + " FROM " + TABLE_HONDAKINAK_ES
					+ " WHERE izena LIKE '" + str + "%'";
		String Hizena;
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Hizena = cursor.getString(0);
				// Adding hondakin to list
				hondakinList.add(Hizena);
			} while (cursor.moveToNext());
		}
		// return hondakin list
		return hondakinList;
	}
	public void dropDB() {
	// TODO Auto-generated method stub
		File dbFile = new File(DB_PATH + DB_NAME);
		if( dbFile.exists()){
			dbFile.delete();
		}
	}
}