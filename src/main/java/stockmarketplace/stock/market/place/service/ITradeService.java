package stockmarketplace.stock.market.place.service;

import stockmarketplace.stock.market.place.exception.CompanieShareNotEnough;
import stockmarketplace.stock.market.place.exception.InvestorNotFound;
import stockmarketplace.stock.market.place.model.Trade;

public interface ITradeService {
	
	void tradeDay(Trade trade )throws InvestorNotFound, CompanieShareNotEnough;
	
}
