package stockmarketplace.stock.market.place.service;

import stockmarketplace.stock.market.place.exception.InvestorNotFound;
import stockmarketplace.stock.market.place.model.Investor;

public interface IInvestorService {
	
	Boolean isBalanceEnougth (Investor investor, Double sharePrices)throws InvestorNotFound;
}
