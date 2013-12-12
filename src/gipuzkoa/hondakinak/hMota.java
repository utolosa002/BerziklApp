package gipuzkoa.hondakinak;

public class hMota {
	   public String _name;
	   public String _eguna;
	   public String _ordua;
	  public int _irudia;
	    
	    public hMota(String _name, String _eguna, String _ordua, int _irudia) {
			super();
			this._name = _name;
			this._eguna = _eguna;
			this._ordua = _ordua;
			this._irudia = _irudia;
		}	
	    public hMota() {
		}
		public String get_name() {
			return _name;
		}
		public void set_name(String _name) {
			this._name = _name;
		}
		public String get_eguna() {
			return _eguna;
		}
		public void set_eguna(String _eguna) {
			this._eguna = _eguna;
		}
		public String get_ordua() {
			return _ordua;
		}
		public void set_ordua(String _ordua) {
			this._ordua = _ordua;
		}
		public int get_irudia() {
			return _irudia;
		}
		public void set_irudia(int _irudia) {
			this._irudia = _irudia;
		}
		


}
