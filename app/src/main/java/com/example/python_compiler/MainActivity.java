package com.example.python_compiler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;


import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {
    TextView tv_output;
    EditText edt_txt_input;
    Button btn_run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_output=(TextView) findViewById(R.id.tv_output);
        edt_txt_input=(EditText) findViewById(R.id.edt_txt_input);
        btn_run=(Button) findViewById(R.id.btn_run);


      //  tv_text=(TextView) findViewById(R.id.tv_text);
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        //now on cliking run btn we extract data from input and send to py script
        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Python py=Python.getInstance();
                //here we call our script with name myscript
                PyObject pyobj=py.getModule("myscript");
                PyObject obj=pyobj.callAttr("main",edt_txt_input.getText().toString());
                tv_output.setText(obj.toString());

            }
        });



    }

}