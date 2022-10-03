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
            db.insertData("Box" , "Plastic");
            db.insertData("Food Storage Containers" , "Plastic");
            db.insertData("Toy Block" , "Plastic");

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
            db.insertData("Construction Paper" , "Hard Paper");
            db.insertData("Paper Product" , "Hard Paper");

            //Recyclable
            db.insertData("Beverage Can" , "Recyclable");
            db.insertData("Aluminum Can" , "Recyclable");
            db.insertData("Plastic Bottle" , "Recyclable");

            //Residual
            db.insertData("Paper" , "Residual");
            db.insertData("Paper Towel" , "Residual");
            db.insertData("Dishware" , "Residual");
            db.insertData("Household Supply" , "Residual");
            db.insertData("Pen" , "Residual");
            db.insertData("Pencil" , "Residual");
            db.insertData("Ball Pen" , "Residual");
            db.insertData("Writing Implement" , "Residual");
            db.insertData("Brush" , "Residual");
            db.insertData("Toy" , "Residual");
        }
}

