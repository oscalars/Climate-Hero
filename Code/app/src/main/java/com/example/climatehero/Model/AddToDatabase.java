package com.example.climatehero.Model;

public class AddToDatabase {

        public static void add(DatabaseHelper db){

            //Compost
            db.insertData("Banana" , "Compost");
            db.insertData("Apple" , "Compost");
            db.insertData("Pear" , "Compost");
            db.insertData("Fruit" , "Compost");
            db.insertData("Food" , "Compost");
            db.insertData("Plant" , "Compost");
            db.insertData("Salad" , "Compost");
            db.insertData("Vegetable" , "Compost");
            db.insertData("Dog" , "Compost");

            //Plastic
            db.insertData("Plastic Bag" , "Plastic");
            db.insertData("Plastic" , "Plastic");
            db.insertData("Fork" , "Plastic");

            //Glass
            db.insertData("Glass Bottle" , "Glass");
            db.insertData("Bottle" , "Glass");
            db.insertData("Green" , "Glass");
            db.insertData("Brown" , "Glass");
            db.insertData("Clear" , "Glass");
            db.insertData("Transparent Material" , "Glass");
            db.insertData("Beer Bottle" , "Glass");
            db.insertData("Wine Bottle" , "Glass");

            //Paper
            db.insertData("Cardboard" , "Hard Paper");
            db.insertData("Carton" , "Hard Paper");
            db.insertData("Paper Product" , "Hard Paper");
            db.insertData("Shipping Box" , "Hard Paper");
            db.insertData("Packing Materials" , "Hard Paper");

            //Recyclable
            db.insertData("Beverage Can" , "Recyclable");
            db.insertData("Aluminum Can" , "Recyclable");
            db.insertData("Plastic Bottle" , "Recyclable");

            //Residual
            db.insertData("Paper" , "Residual");
        }
}

