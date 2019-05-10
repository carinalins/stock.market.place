package stockmarketplace.stock.market.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import stockmarketplace.stock.market.place.component.TradeBuild;
import stockmarketplace.stock.market.place.dao.CompanieRepository;
import stockmarketplace.stock.market.place.dao.InvestorRepository;
import stockmarketplace.stock.market.place.exception.CompanieShareNotEnough;
import stockmarketplace.stock.market.place.exception.InvestorNotFound;
import stockmarketplace.stock.market.place.model.Companie;
import stockmarketplace.stock.market.place.model.Investor;
import stockmarketplace.stock.market.place.model.Trade;
import stockmarketplace.stock.market.place.service.ITradeService;

@Component
public class JobSimulationTrade {

	@Autowired
	private InvestorRepository investorRepository;

	@Autowired
	private CompanieRepository companieRepository;

	@Autowired
	private ITradeService tradeService;

	@Autowired
	private TradeBuild tradeBuild;

	@Scheduled(initialDelay = 1000, fixedRate = 100000)
	public void simulation() throws InvestorNotFound, CompanieShareNotEnough {

		List<Companie> listCompanie = companieRepository.allCompany();

		List<Investor> listInvestor = investorRepository.listInvestor();

		for (Investor investor : listInvestor)
			for (Companie companie : listCompanie) {

				buySharesInvestor(investor, companie);
			}

	}

	
	private void buySharesInvestor(Investor investor, Companie companie)
			throws InvestorNotFound, CompanieShareNotEnough {
		
		investor = investorRepository.findById(investor.getId()).get();
		companie = companieRepository.findById(companie.getId()).get();
		
		if ( investor.getBudget() > 0.0 && investor.getBudget() > companie.getSharePrices()) {

			Trade trade = tradeBuild.getCompanie(companie.getId())
					.getInvestor(investor.getId()).newTradeBuild();
			tradeService.tradeDay(trade);
			
			buySharesInvestor(investor, companie);

		}

	}

}
