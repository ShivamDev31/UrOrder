package com.shivamdev.urorder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shivamdev.urorder.L;

/**
 * Created by shivamchopra on 06/09/15.
 */


public class HelperAdapter {

	Context context;
	DbHelper helper;

	public HelperAdapter (Context context) {
		this.context = context;
		helper = new DbHelper(context);
	}

	public long insertValues(String name, String mobileNo, String address, String pincode, String orderDetails) {

		ContentValues cv = new ContentValues();
		cv.put(DbHelper.NAME, name);
		cv.put(DbHelper.MOBILE_NO, mobileNo);
		cv.put(DbHelper.ADDRESS, address);
		cv.put(DbHelper.PINCODE, pincode);
		cv.put(DbHelper.ORDER_DETAILS, orderDetails);

		SQLiteDatabase db = helper.getWritableDatabase();
		long id = db.insert(DbHelper.TABLE_NAME, null, cv);

		return id;
	}


	private class DbHelper extends SQLiteOpenHelper {

		private static final String DATABASE_NAME = "urorder";
		private static final String TABLE_NAME = "order_details";
		private static final int DB_VERSION = 1;
		private static final String UID = "_id";
		private static final String NAME = "name";
		private static final String MOBILE_NO = "mobile_no";
		private static final String ADDRESS = "address";
		private static final String PINCODE = "pincode";
		private static final String ORDER_DETAILS = "service";
		private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTO INCREMENT, " +
				NAME + " VARCHAR(20), " +
				MOBILE_NO + " VARCHAR(10), " +
				ADDRESS + " VARCHAR(255), " +
				PINCODE + " VARCHAR(7), " +
				ORDER_DETAILS + " VARCHAR(255) );";
		private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + " ;";

		private Context context;

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DB_VERSION);
			this.context = context;
			L.t(context, "Helper constructor called");
		}


		@Override
		public void onCreate(SQLiteDatabase sqLiteDatabase) {

			try {
				sqLiteDatabase.execSQL(CREATE_TABLE);
			} catch (SQLException e) {
				L.t(context, " Exception in onCreate Db : " + e);
				L.l(" Exception in onCreate Db : " + e);
			}
			L.t(context, "Helper onCreate called");
		}

		@Override
		public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
			try {
				sqLiteDatabase.execSQL(DROP_TABLE);
				onCreate(sqLiteDatabase);
			} catch (SQLException e) {
				L.t(context, " Exception in onUpdate Db : " + e);
			}
			L.t(context, "Helper onUpdate called");

		}
	}
}