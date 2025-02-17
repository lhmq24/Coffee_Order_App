//package com.example.coffee_order_app.DAO;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//
//import com.example.coffee_order_app.Model.Receipt;
//import com.example.coffee_order_app.View.ReceiptDetailActivity;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    private SQLiteDatabase database;
//    private String db_name = "coffee_shop_db";
//    private int db_version = 1;
//
//    public DatabaseHelper(Context context) {
//        super(context, db_name , null, db_version);
//    }
//
//    @Override
//    public SQLiteDatabase getWritableDatabase() {
//        return super.getWritableDatabase();
//    }
//
//    // Method to get a readable database
//    @Override
//    public SQLiteDatabase getReadableDatabase() {
//        return super.getReadableDatabase();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Execute your table creation queries here
//        db.execSQL(CREATE_TABLES);
//        db.execSQL(CREATE_BEVERAGES);
//        // Add other table creation statements as needed
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//    public String getReceiptDetails(int receiptId) {
//    }
//
//    public ArrayList<Beverage> searchBeverages(String query) {
//        ArrayList<Beverage> result = new ArrayList<>();
//
//        // SQL query to search for beverages based on the query text
//        String sqlQuery = "SELECT * FROM beverages WHERE name LIKE '%" + query + "%'";
//
//        // Execute the query and fetch results (simplified for example)
//        Cursor cursor = database.rawQuery(sqlQuery, null);
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                // Create Beverage object and add to result list
//                Beverage beverage = new Beverage(
//                        cursor.getInt(cursor.getColumnIndex("id")),
//                        cursor.getString(cursor.getColumnIndex("name")),
//                        cursor.getDouble(cursor.getColumnIndex("price"))
//                );
//                result.add(beverage);
//            }
//            cursor.close();
//        }
//        return result;
//    }
//
//    public ArrayList<Receipt> getAllReceipts() {
//        ArrayList<Receipt> receipts = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT r.id, r.total_price, r.paid_time, t.tbl_number " +
//                "FROM receipts r JOIN tables t ON r.table_id = t.tbl_id " +
//                "ORDER BY r.paid_time DESC";
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                receipts.add(new Receipt(
//                        cursor.getInt(0), // order_id
//                        cursor.getInt(1), // table_id
//                        cursor.getFloat(2) // totalPrice
//                ));
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return receipts;
//    }
//}
