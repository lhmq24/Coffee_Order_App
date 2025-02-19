package com.example.coffee_order_app.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.coffee_order_app.Model.DAO.BeverageDAO;
import com.example.coffee_order_app.Model.DAO.OrderDAO;
import com.example.coffee_order_app.Model.DAO.OrderItemDAO;
import com.example.coffee_order_app.Model.DAO.ReceiptDAO;
import com.example.coffee_order_app.Model.DAO.TableDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Table.class, Receipt.class, OrderItem.class, Order.class, Beverage.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //Khai bao Executor de chay duoi nen (Background)
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile AppDatabase Database;
    //Initialize database
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@androidx.annotation.NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                AppDatabase dbInstance = Database;
                if (dbInstance != null) {
                    BeverageDAO beverageDAO = dbInstance.beverageDAO();
                    TableDAO tableDAO = dbInstance.tableDAO();

                    // Insert default beverages
                    beverageDAO.addBeverage(new Beverage("Espresso", 25000));
                    beverageDAO.addBeverage(new Beverage("Americano", 30000));
                    beverageDAO.addBeverage(new Beverage("Cappuccino", 35000));
                    beverageDAO.addBeverage(new Beverage("Latte", 40000));
                    beverageDAO.addBeverage(new Beverage("Mocha", 45000));
                    beverageDAO.addBeverage(new Beverage("Matcha Latte", 42000));
                    beverageDAO.addBeverage(new Beverage("Caramel Macchiato", 48000));
                    beverageDAO.addBeverage(new Beverage("Iced Coffee", 32000));
                    beverageDAO.addBeverage(new Beverage("Hot Chocolate", 38000));
                    beverageDAO.addBeverage(new Beverage("Tea", 18000));
                    beverageDAO.addBeverage(new Beverage("Green Tea", 25000));
                    beverageDAO.addBeverage(new Beverage("Black Coffee", 18000));
                    beverageDAO.addBeverage(new Beverage("Vanilla Latte", 43000));
                    beverageDAO.addBeverage(new Beverage("Affogato", 50000));
                    beverageDAO.addBeverage(new Beverage("Frappuccino", 55000));

                    // Insert default tables
                    for (int i = 1; i <= 15; i++) {
                        tableDAO.addTable(new Table(i, i));
                    }
                }
            });
        }
    };

    // Singleton để đảm bảo chỉ có một instance của database
    public static AppDatabase getDatabase(Context context) {
        if (Database == null) {
            synchronized (AppDatabase.class) { //Tranh tao nhieu Database khi chay da luong
                if (Database == null) {
                    Database = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "coffee_order_db")
                            .fallbackToDestructiveMigration() // Xóa dữ liệu cũ khi nâng cấp database
                            .addCallback(sRoomDatabaseCallback) // Gan Callback de khoi tao du lieu
                            .build();
                }
            }
        }
        return Database;
    }

    // Khai bao cac DAO
    public abstract TableDAO tableDAO();

    public abstract OrderDAO orderDAO();

    public abstract OrderItemDAO orderItemDAO();

    public abstract ReceiptDAO receiptDAO();

    public abstract BeverageDAO beverageDAO();
}

