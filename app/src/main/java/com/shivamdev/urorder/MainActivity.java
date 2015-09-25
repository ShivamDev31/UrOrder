package com.shivamdev.urorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseObject;
import com.shivamdev.urorder.database.HelperAdapter;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etMobileNo;
    private EditText etAddress;
    private EditText etPincode;
    private EditText etOrderDetails;

    private String name;
    private String mobileNo;
    private String address;
    private String pincode;
    private String orderDetails;

    private HelperAdapter helperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helperAdapter = new HelperAdapter(this);

        etName = (EditText) findViewById(R.id.et_name);
        etMobileNo = (EditText) findViewById(R.id.et_mobile);
        etAddress = (EditText) findViewById(R.id.et_address);
        etPincode = (EditText) findViewById(R.id.et_pincode);
        etOrderDetails = (EditText) findViewById(R.id.et_service_req);

        setupParse();
    }

    private void setupParse() {
        ParseObject parseObject = ParseObject.create("UserDataInput");
        parseObject.put();
    }

    public void addDataIntoSqlite(View view) {
        name = etName.getText().toString();
        mobileNo = etMobileNo.getText().toString();
        address = etAddress.getText().toString();
        pincode = etPincode.getText().toString();
        orderDetails = etOrderDetails.getText().toString();

        insertDataIntoSqlite();
    }

    private void insertDataIntoSqlite() {
        long id = helperAdapter.insertValues(name, mobileNo, address, pincode, orderDetails);

        if (id < 0) {
            L.t(this, "Oops something bad happened, Please try after sometime.");
            return;
        } else {
            L.t(this, "Order placed successfully.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
