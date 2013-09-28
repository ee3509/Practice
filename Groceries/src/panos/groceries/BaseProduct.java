package panos.groceries;

public class BaseProduct {
	
	protected String name;
	protected Float price;
	protected Float savings=0f;
	protected int count; 
	
	
	public void increaseCount(){
		count++;
	}
	
	public BaseProduct(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getSavings() {
		return savings;
	}
	public void setSavings(float savings) {
		this.savings = savings;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
