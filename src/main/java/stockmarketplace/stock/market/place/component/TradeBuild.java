package stockmarketplace.stock.market.place.component;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stockmarketplace.stock.market.place.dao.CompanieRepository;
import stockmarketplace.stock.market.place.dao.InvestorRepository;
import stockmarketplace.stock.market.place.model.Companie;
import stockmarketplace.stock.market.place.model.Investor;
import stockmarketplace.stock.market.place.model.Trade;

@Component
public class TradeBuild {

	@Autowired
	private InvestorRepository investorRepository;
 
	@Autowired 
	private CompanieRepository companieRepository;

	private Investor investor;
	private Companie companie;

	public TradeBuild getInvestor(Integer idInvestor) {

		Optional<Investor> optionalInvestor = investorRepository.findById(idInvestor);
		this.investor = optionalInvestor.get();
		return this;
	}

	public TradeBuild getCompanie(Integer idCompanie) {
		Optional<Companie> optionalCompanie = companieRepository.findById(idCompanie);
		this.companie = optionalCompanie.get();

		return this;
	}

	public Trade newTradeBuild() {
		Trade trade = new Trade();
		trade.setCompanie(companie);
		trade.setInvestor(investor);
		trade.setDtAdd(new Date());
		trade.setDtTransaction(new Date());

		return trade;
	}
}
