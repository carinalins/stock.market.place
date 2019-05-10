package stockmarketplace.stock.market.place.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import stockmarketplace.stock.market.place.dao.CompanieRepository;
import stockmarketplace.stock.market.place.dao.InvestorRepository;
import stockmarketplace.stock.market.place.dao.TradeRepository;
import stockmarketplace.stock.market.place.dto.AnyTradeDTO;
import stockmarketplace.stock.market.place.exception.CompanieShareNotEnough;
import stockmarketplace.stock.market.place.exception.InvestorNotFound;
import stockmarketplace.stock.market.place.model.Companie;
import stockmarketplace.stock.market.place.model.Investor;
import stockmarketplace.stock.market.place.model.Trade;

@Service("ITradeService")
public class TradeService implements ITradeService {

	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private CompanieRepository companieRepository;
	
	@Autowired
	private InvestorRepository investorRepository;
	
	@Autowired
	private IcompanieService icompanieService;
	
	@Override
	public void tradeDay(Trade trade) throws InvestorNotFound, CompanieShareNotEnough {

		Companie oldCompanie = trade.getCompanie();
		oldCompanie.setShares(oldCompanie.getShares() - 1);
		trade.setPrice(oldCompanie.getSharePrices());
		trade.setCompanie(oldCompanie);
		
		Investor investor = trade.getInvestor();
		
		investor.setBudget(investor.getBudget() - oldCompanie.getSharePrices()    );
		investor.setDtUpdate(new Date());
		trade.setInvestor(investor);
		
		if (investor.getBudget() > 0.0) 
			investorRepository.save(investor);
			
		
			
		Trade newTrade = tradeRepository.save(trade);
		
		icompanieService.checkSellShares(newTrade.getCompanie());

	}




}
