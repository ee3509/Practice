package panos.groceries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import panos.groceries.Product.OFFER_TYPE;

public class CheckOut {
	private static boolean VERBOSE = true;

	private final static String separator = "===========================================================================";
	// Holds information on products including any offers
	private Map<String, Product> productMap = new HashMap<String, Product>(); 
	// Data holder for the products basket
	private List<BaseProduct> basket = new ArrayList<BaseProduct>();
	// Total Cost
	private Float total;
	private Float totalBeforeOffers;
	
	public static void main(String[] args) {
		//Start the checkout
		CheckOut checkout = new CheckOut();
	}
	
	public CheckOut() {
		//Manually populate basket with goods
		setUpBasket(basket);
		// Setup the offers on the products we have
		setUpPricesAndOffers();
		// Calculate the totals before offers
		calculateTotals();
		calculateOffers();
	}
	
	/***
	 * Sets up a new Basket, normally this would be the input of the program coming
	 * from some data source, but for demo purposes we set it up manually
	 * We only need the product name (or we could use an ID field but it is out of scope)
	 * the proper price will be set up in setUpOffers
	 */
	private List<BaseProduct> setUpBasket(List<BaseProduct> basket) {
		//This input would test most scenarios
		// 2 x Biscuits = 1.29 x 2 = 2.58 => final = 2.58
		// 4 x Juice = 2.12 => with offer 1.59, savings 0.53 - 3 for 2
		// 3 x Meals = 10.5 => with offer 8.5, saving 2 - 2 for £5
		// Total Without offers = 15.2 with offers 12.67, savings 2.53
		basket.add(new BaseProduct("Biscuits"));
		basket.add(new BaseProduct("Biscuits"));
		basket.add(new BaseProduct("Juice"));
		basket.add(new BaseProduct("Juice"));
		basket.add(new BaseProduct("Juice"));
		basket.add(new BaseProduct("Juice"));
		basket.add(new BaseProduct("Meal"));
		basket.add(new BaseProduct("Meal"));
		basket.add(new BaseProduct("Meal"));
		return basket;
	}
	/***
	 * Sets up a Map of Products including offers and prices, this would normally come from a data source
	 * Moreover instead of populating with all the offers and prices we would only populate
	 * with the type of items in the basket.
	 * In this example products are built manually and passed into the map
	 * Offer types are declared in the OFFER_TYPE enum inside the offers class
	 */
	private void setUpPricesAndOffers() {
		
		Product product;
		
		product = new Product("Biscuits", 1.29f, OFFER_TYPE.NONE);
		productMap.put(product.getName(), product);
		
		product = new Product("Juice", 0.53f, OFFER_TYPE.THREE_FOR_TWO);
		productMap.put(product.getName(), product);
		
		product = new Product("Meal", 3.50f, OFFER_TYPE.TWO_FOR_LESS_MONEY);
		productMap.put(product.getName(), product);
		
	}

	/***
	 * Calculates the totals before any offers and count the number of items
	 * We need the item count to calculate the offers later
	 */
	private void calculateTotals() {
		Product temp;
		total = 0f;
		
		// This is a smart way to remove all map items not in the basket
		// If the prices and offers came from a data source we wouldn't have to do that
		productMap.values().retainAll(basket);
		
		// We get the price per item and and add it to the total
		for (BaseProduct item : basket) {
			// get the product from the map
			temp = productMap.get(item.getName());
			// Count the items
			temp.increaseCount();
			// add the price
			total+= temp.getPrice();
			
			if(VERBOSE){
				System.out.println("Item : "+temp.getName()+", Price : £"+temp.getPrice());
			}
		}
		totalBeforeOffers=total;  // save the value
		String totalString = String.format("%.2f", totalBeforeOffers);
		System.out.println(separator);
		System.out.println("Total Before applying offers is = £"+totalString);
		System.out.println(separator);
		
	}

	/***
	 * Calculates total prices with offers deducting offer value from total
	 */
	private void calculateOffers() {
		// calculate and deduct the offers value from the total
		for (Product item: productMap.values()) {
			
			// THREE_FOR_TWO implementation, check how many groups of 3 you have
			// and deduct x price from the total
			if(item.getOffer().equals(OFFER_TYPE.THREE_FOR_TWO)){
				int div = item.getCount() / 3;
				total = total - div*item.getPrice();
				item.setSavings(div*item.getPrice());
				printItemFinal(item);
			}
			
			// TWO_FOR_LESS_MONEY can be implemented in a smarter way
			// by taking "less" as an argument, I think it's out of scope for the test though
			// We check how many groups of 2 there are and deduct £2 from the total
			// for each of them
			if(item.getOffer().equals(OFFER_TYPE.TWO_FOR_LESS_MONEY)){
				int div = item.getCount() / 2;
				total = total - div*2;
				item.setSavings(div*2);
				printItemFinal(item);
			}
		}
		// format the savings string
		String savings = String.format("%.2f", totalBeforeOffers-total);
		String totalString = String.format("%.2f", total);
		System.out.println(separator);
		System.out.println("Total After Applying Offers is : £"+totalString+ ", Savings : £"+savings);
		System.out.println(separator);
		
		
		
	}

	/***
	 * Prints the final outpot for item
	 * This includes price, total cost, savings, final cost
	 * @param item
	 */
	private void printItemFinal(Product item) {
		String finalCost = String.format("%.2f", (item.price*item.getCount()-item.getSavings()));
		System.out.println("Item : "+item.getName()+
				", Price : £"+item.getPrice()+
				" x "+item.getCount()+
				" = £"+item.price*item.getCount()+
				", Savings : £"+item.getSavings()+
				", You Pay : £"+ finalCost
				);
	}

}
