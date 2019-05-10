package stockmarketplace.stock.market.place.exception;

import java.io.Serializable;

public class CompanieShareNotEnough extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompanieShareNotEnough() {
		super("share is not Enough to sell ");
	}
	
}
