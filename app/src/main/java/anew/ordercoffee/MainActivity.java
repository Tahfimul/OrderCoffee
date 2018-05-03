package anew.ordercoffee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity;
    int price;
    CheckBox topping1;
    CheckBox topping2;
    String message;
    int whipCream = 0;
    int chocolate = 0;
    EditText name;
    String toppings;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topping1 = (CheckBox) findViewById(R.id.whipCream);
        topping2 = (CheckBox) findViewById(R.id.chocolate);
        name = (EditText) findViewById(R.id.Name);

    }

    public boolean whipCream(View view) {
        return topping1.isChecked();
    }

    public boolean chocolate(View view) {
        return topping2.isChecked();
    }

    /**
     * Adds quantity and calculates price
     * <p>
     * Sets the price and quantity to their textviews
     *
     * @param view
     */
    public void add(View view) {
        if (quantity == 100) {
            Toast.makeText(this,"More than 100 coffees can not be ordered!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (whipCream(view)) {
            whipCream = 1;
            toppings = "a";
        } else {
            whipCream = 0;
        }
        if (chocolate(view)) {
            chocolate = 1;
            toppings = "b";
        } else {
            chocolate = 0;
        }
        if (whipCream(view) && chocolate(view)) {
            toppings = "c";
        }
        quantity++;
        price = calculatePrice();
        setQuantity(quantity);
        setPrice();
    }

    /**
     * Decrements the quantity and price
     * <p>
     * Sets the quantity and price to their textviews
     * <p>
     * if quantity is less than 1, then quantity is set to 0 as well as price
     * <p>
     * if quantity is greater than 0, then quantity is decremented by 1 and price is set accordingly
     */

    public void rm(View view) {
        if (quantity == 1) {
            Toast.makeText(this,"Less than 1 coffee can not be ordered!",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        if (whipCream(view)) {
            whipCream = 1;
            toppings = "a";
        } else {
            whipCream = 0;
        }
        if (chocolate(view)) {
            chocolate = 1;
            toppings = "b";
        } else {
            chocolate = 0;
        }
        if (whipCream(view) && chocolate(view)) {
            toppings = "c";
        }
        price = calculatePrice();
        setQuantity(quantity);
        setPrice();
    }


    /**
     * Displays the summary message when submitOrder button is clicked
     */

    public void submitOrder(View view) {
       displayMessage(createOrderSummary());
    }


    /**
     * Takes user name and creates summary message
     *
     * @return message
     */
    private String createOrderSummary() {
        Name = name.getText().toString();
        if (Name == "") {
            message = "[Enter your name above]";
        } else {
            message = "Hey " + Name + "!";
        }
        switch (toppings) {
            case "a":
                toppings = "whipCream";
                break;
            case "b":
                toppings = "chocolate";
                break;
            case "c":
                toppings = "chocolate and whipCream";
                break;
            default:
                toppings = "none";
                break;
        }
        message += "\n\nThanks for ordering: " + quantity
                + " coffee with toppings: " + toppings + "!  " + "It will cost: " + NumberFormat.getCurrencyInstance().format(price);

        return message;
    }

    /**
     * Takes the input @param message from submitOrder
     * <p>
     * Then passes it on to message textview     *
     */

    private void displayMessage(String message) {
        TextView Message = (TextView) findViewById(R.id.message);
        Message.setText(message);

    }


    /**
     * Takes quantity input @param num value from add method
     * <p>
     * Then passes it to quantity_text_view     *
     */

    private void setQuantity(int num) {
        TextView quantity = (TextView) findViewById(R.id.quantity_text_view);
        quantity.setText(String.valueOf(num));
    }


    /**
     * Takes value from quantity variable
     * Then multiplies the value by 5 and calculates the price per cup of coffee
     *
     * @return integer value
     */

    private int calculatePrice() {
        int value = quantity * ((5) + (whipCream * 1) + (chocolate * 2));
        return value;


    }

    /**
     * Passes value of price variable to price_text_view
     */

    private void setPrice() {
        TextView Price = (TextView) findViewById(R.id.price_text_view);
        Price.setText(String.valueOf(NumberFormat.getCurrencyInstance().format(price)));
    }


}