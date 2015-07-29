package myapplication.prak.livefreesports;

import android.app.ProgressDialog;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Prakhar on 7/28/2015.
 */
public class discover extends Fragment {

    JSONArray ar = new JSONArray() , ar2 = new JSONArray();
    @Override
    public void onStart() {
        super.onStart();



        final ProgressDialog prg =    ProgressDialog.show(getActivity() , "Loading" , "Please wait !");

        StringRequest rq = new StringRequest("http://192.168.1.5:3000/homescreen", new Response.Listener<String>() {




            @Override
            public void onResponse(String s) {




                Log.d("request", s);


                try {
                    JSONObject objh  = new JSONObject(s);
   ar = objh.getJSONArray("city");
                     ar2 = objh.getJSONArray("category");


spinner1.setAdapter(adap2);
                    spinner2.setAdapter(adap1);
                    spinner3.setAdapter(adap1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                prg.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.d("request" , volleyError + " ");


                prg.dismiss();
                Toast.makeText(getActivity(), "Unable to connect to server", 1000).show();
                getActivity().finish();
            }



        });


        app.getRequestQueue().add(rq);

    }



    SpinnerAdapter adap1 =  new SpinnerAdapter() {
        @Override
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View v3iew = getActivity().getLayoutInflater().inflate(R.layout.spin_bp , viewGroup , false);
            TextView tv = (TextView) v3iew.findViewById(R.id.tv);
            try {
                tv.setText(ar.get(i).toString() + " ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  v3iew;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return ar.length();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            View v3iew = getActivity().getLayoutInflater().inflate(R.layout.spin_bp2 , viewGroup , false);
            TextView tv = (TextView) v3iew.findViewById(R.id.tv);
            try {
                tv.setText(ar.get(i).toString() + " ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  v3iew;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    };
    SpinnerAdapter adap2 =  new SpinnerAdapter() {
        @Override
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View v3iew = getActivity().getLayoutInflater().inflate(R.layout.spin_bp , viewGroup , false);


            TextView tv = (TextView) v3iew.findViewById(R.id.tv);
            try {
                tv.setText(ar2.get(i).toString() + " ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  v3iew;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return ar2.length();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            View v3iew = getActivity().getLayoutInflater().inflate(R.layout.spin_bp2 , viewGroup , false);
            TextView tv = (TextView) v3iew.findViewById(R.id.tv);
            try {
                tv.setText(ar2.get(i).toString() + " ");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return  v3iew;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    };

    View discover;

    AppCompatSpinner spinner1, spinner2 , spinner3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.discover, null);

        discover = view.findViewById(R.id.discover);

        spinner1 = (AppCompatSpinner) view.findViewById(R.id.spin_city);
        spinner2 = (AppCompatSpinner) view.findViewById(R.id.spin_sport);

        spinner3 = (AppCompatSpinner) view.findViewById(R.id.spin_loc);


        spinner2.setAdapter(adap1);
        spinner1.setAdapter(adap2);
        spinner3.setAdapter(adap1);

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dia =    ProgressDialog.show(getActivity(), "Loading", "Please wait");
                URL url =null;
                try {
                    url  =  new URL(("http://192.168.1.5:3000/info?city=" +  ar.get(spinner2.getSelectedItemPosition()) +"&cat=" + ar2.get(spinner1.getSelectedItemPosition())));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StringRequest strt = null;
              {
                    strt = new StringRequest(url.toString() , new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {


                            try {
                                Toast.makeText(getActivity(), "http://192.168.1.5:3000/info?city=" +  ar.get(spinner2.getSelectedItemPosition()) +"&cat=" + ar2.get(spinner1.getSelectedItemPosition()), 1000).show();
                            } catch (JSONException e) {
                                e.printStackTrace();


                                Toast.makeText(getActivity(), "dsa "+ e, 1000).show();
                            }
                            dia.dismiss();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {





                            dia.dismiss();
                            Toast.makeText(getActivity(), "Unable to connect to server " +volleyError, 1000).show();


                        }

                    });
                }

                app.getRequestQueue().add(strt);



            }
        });

        return view;
    }
}
