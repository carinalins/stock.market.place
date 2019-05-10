package stockmarketplace.stock.market.place.exception;

import java.io.Serializable;

public class InvestorNotFound extends Exception implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvestorNotFound() {
		super("Investor Not Found");
	}
}
