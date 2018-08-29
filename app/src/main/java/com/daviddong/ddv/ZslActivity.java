package com.daviddong.ddv;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ZslActivity extends AppCompatActivity {
    private Button btn;
    private EditText et;
    private TextView tv;
    //新建Handler的对象，在这里接收Message，然后更新TextView控件的内容
     private Handler handler = new Handler() {

                 @Override
         public void handleMessage(Message msg) {
                         super.handleMessage(msg);
//                         switch (msg.what) {
//                             case SHOW_RESPONSE:
//                                     String response = (String) msg.obj;
//                                     tv.setText(response);
//                                     break;
//
//                             default:
//                                     break;
//                             }
                     }

             };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zsl);
        btn = findViewById(R.id.button3);
        et = findViewById(R.id.editText2);
        tv = findViewById(R.id.textView2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.baidu.com/s?wd="+et.getText();

//                asktaskpost(ZslActivity.this,et.getText().toString());
            }
        });
    }
    public void updateData(String response){
        tv.setText(response);
    }
    public void asktaskpost(Context context, final String url, final HashMap<String,String> param, final Object callback){
        String result="su";
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        //JsonArrayRequestjsonArrayRequest=newJsonArrayRequest获取json数据
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,
            new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    updateData(response);
//                    callback.start();
                    Log.d("tag","response->"+response);

                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.e("tag",error.getMessage(),error);
                }
            }){
            @Override
            protected Map<String,String>getParams(){
                //在这里设置需要post的参数
//                Map<String,String> map=new HashMap<String,String>();
//                map.put("wd",url);
//                map.put("aaa","我是post参数");


                //returnparams;
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

}
