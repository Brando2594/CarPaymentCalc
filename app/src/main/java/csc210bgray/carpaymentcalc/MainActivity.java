package csc210bgray.carpaymentcalc;

import android.os.Bundle; // for saving state information
import android.support.v7.app.AppCompatActivity; // base class
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for car price input
import android.widget.TextView; // for displaying text
import java.text.NumberFormat; // for currency formatting

// MainActivity class for the Tip Calculator app
public class MainActivity extends AppCompatActivity {

    // currency and percent formatter objects
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat numberFormat =
            NumberFormat.getNumberInstance();
    private static final NumberFormat integerFormat =
            NumberFormat.getIntegerInstance();

    private double carPrice = 0.0; // car price entered by the user
    private double interest = 0.0; // interest rate entered by the user
    private double plan = 0.0; //payment plan in months entered by the user
    private TextView priceTextView; // shows formatted car price
    private TextView interestTextView; // shows interest rate
    private TextView planTextView; // shows payment plan
    private TextView monthlyTextView; // shows the monthly payment amount
    private TextView totalTextView; // shows the total payment amount

    // called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

        // get references to programmatically manipulated TextViews
        priceTextView = (TextView) findViewById(R.id.priceTextView);
        interestTextView = (TextView) findViewById(R.id.interestTextView);
        planTextView = (TextView) findViewById(R.id.planTextView);
        monthlyTextView = (TextView) findViewById(R.id.monthlyTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        monthlyTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

        // set priceEditText's TextWatcher
        EditText priceEditText =
                (EditText) findViewById(R.id.priceEditText);
        priceEditText.addTextChangedListener(priceEditTextWatcher);
        // set interestEditText's TextWatcher
        EditText interestEditText =
                (EditText) findViewById(R.id.interestEditText);
        interestEditText.addTextChangedListener(interestEditTextWatcher);
        // set planEditText's TextWatcher
        EditText planEditText =
                (EditText) findViewById(R.id.planEditText);
        planEditText.addTextChangedListener(planEditTextWatcher);
    }

    // calculate and display monthly and total amounts
    private void calculate() {
        // format interest and display in interestTextView
        interestTextView.setText(numberFormat.format(interest));

        // calculate the tip and total
        double interestAmount = carPrice * (interest / 100.0);
        double total = carPrice + interestAmount;
        double monthly = total / plan;

        // display monthly and total formatted as currency
        monthlyTextView.setText(currencyFormat.format(monthly));
        totalTextView.setText(currencyFormat.format(total));
    }
    // listener object for the priceEditText's text-changed events
    private final TextWatcher priceEditTextWatcher = new TextWatcher() {
        // called when the user modifies the car price
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get car price and display currency formatted value
                carPrice = Double.parseDouble(s.toString());
                priceTextView.setText(currencyFormat.format(carPrice));
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                priceTextView.setText("");
                carPrice = 0.0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
    // listener object for the interestEditText's text-changed events
    private final TextWatcher interestEditTextWatcher = new TextWatcher() {
        // called when the user modifies the interest rate
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get interest amount and display number formatted value
                interest = Double.parseDouble(s.toString());
                interestTextView.setText(numberFormat.format(interest) + " %");
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                interestTextView.setText("");
                interest = 0;
            }

            calculate(); // update the monthly and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
    // listener object for the planEditText's text-changed events
    private final TextWatcher planEditTextWatcher = new TextWatcher() {
        // called when the user modifies the payment plan
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // get payment plan and display number formatted value
                plan = Double.parseDouble(s.toString());
                planTextView.setText(integerFormat.format(plan) + " month(s)");
            }
            catch (NumberFormatException e) { // if s is empty or non-numeric
                planTextView.setText("");
                plan = 0;
            }

            calculate(); // update the tip and total TextViews
        }

        @Override
        public void afterTextChanged(Editable s) { }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }
    };
}


/*************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
