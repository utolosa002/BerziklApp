package gipuzkoa.hondakinak;

public class Hondakina {
	    //private variables
	    int _id;
	    String _name;
	    String _non;
	    String _info;
	 
	    // Empty constructor
	    public Hondakina(){
	 
	    }
	    // constructor
	    public Hondakina(int id, String name, String _non, String _info){
	        this._id = id;
	        this._name = name;
	        this._non = _non;
	        this._info = _info;
	    }
	 
	    // constructor
	    public Hondakina(String name, String _non,String _info ){
	        this._name = name;
	        this._non = _non;
	        this._info = _info;
	    }
	    // getting ID
	    public int getID(){
	        return this._id;
	    }
	 
	    // setting id
	    public void setID(int id){
	        this._id = id;
	    }
	 
	    // getting name
	    public String getName(){
	        return this._name;
	    }
	 
	    // setting name
	    public void setName(String name){
	        this._name = name;
	    }
	 
	    // getting non
	    public String getNon(){
	        return this._non;
	    }
	 
	    // setting non
	    public void setNon(String non){
	        this._non = non;
	    }	    
	    // getting info
	    public String getInfo(){
	        return this._info;
	    }
	 
	    // setting info
	    public void setInfo(String info){
	        this._info = info;
	    }
	}
