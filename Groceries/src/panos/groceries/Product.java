package panos.groceries;

public class Product extends BaseProduct{
	public static enum OFFER_TYPE{ NONE,TWO_FOR_LESS_MONEY, THREE_FOR_TWO}
	
	//private BaseProduct item;
	private OFFER_TYPE offer;
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	// implement equals so that we can compate between BaseProduct and Product
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass().equals(BaseProduct.class)){
			return name.equals( ((BaseProduct) obj).name);
		}
		return name.equals( ((Product) obj).name);
	}
	
	// implemented for debugging
	@Override
	public String toString() {
		return "Item : "+this.name+", Price : "+this.price+", Count : "+this.count+", Savings : "+this.savings;
	}
	
	public Product(String name, Float price, OFFER_TYPE offer) {
		super(name);
		this.offer=offer;
		this.price=price;
	}


	public OFFER_TYPE getOffer() {
		return offer;
	}

	public void setOffer(OFFER_TYPE offer) {
		this.offer = offer;
	}
	

}


