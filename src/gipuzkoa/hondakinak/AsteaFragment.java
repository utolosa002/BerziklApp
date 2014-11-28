package gipuzkoa.hondakinak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AsteaFragment extends Fragment{
private ImageView im;
	 /** 
	  * Returns a new instance of this fragment for the given section number. 
	  */  
	 public static  AsteaFragment newInstance() {  
		 AsteaFragment fragment = new  AsteaFragment();
	  return fragment;  
	 }   
	  
	 public  AsteaFragment() {
	 }  
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

@Override  
public View onCreateView(LayoutInflater inflater, ViewGroup container,  
 Bundle savedInstanceState) {
	 View rootView = inflater.inflate(R.layout.astea, container, false);
   return rootView;
}

public ImageView getIm() {
	return im;
}
public void setIm(ImageView im) {
	this.im = im;
}
}  