//package com.example.coffee_order_app.Presenter;
//import com.example.coffee_order_app.Database.DatabaseHelper;
//
//import com.example.coffee_order_app.Model.Beverage;
//
//
//public class TablePresenter {
//    private DatabaseHelper databaseHelper; // Reference to your database helper
//
//    public Presenter() {
//        this.databaseHelper = new DatabaseHelper(); // Initialize the database helper
//    }
//
//    public void queryBeverages(String query) {
//        // Assuming you have a method in your database helper to query based on a search text
//        ArrayList<Beverage> filteredBeverages = databaseHelper.searchBeverages(query);
//
//        // Update your UI with the filtered results, this can be done via a callback or directly from the presenter
//        updateOrderList(filteredBeverages);
//    }
//
//    private void updateOrderList(ArrayList<Beverage> beverages) {
//        // Logic to update your order list, either by notifying the adapter or passing data to the activity
//        // Assuming you are using a method like this in your adapter:
//        orderHistoryActivityAdapter.updateData(beverages);
//    }
//}
