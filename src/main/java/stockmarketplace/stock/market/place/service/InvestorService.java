package stockmarketplace.stock.market.place.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockmarketplace.stock.market.place.dao.InvestorRepository;
import stockmarketplace.stock.market.place.exception.InvestorNotFound;
import stockmarketplace.stock.market.place.model.Investor;

@Service("IInvestorService")
public class InvestorService implements IInvestorService {
	
	@Autowired
	private InvestorRepository investorRepository;
	
	@Override
	public Boolean isBalanceEnougth(Investor investor, Double sharePrices) throws InvestorNotFound {
		
		if (investor.getBudget()  == null && investor.getId() != null ) {
			Optional<Investor> optionalInvestor = investorRepository.findById(investor.getId());
			investor = optionalInvestor.orElseThrow(InvestorNotFound::new);
		}else if (investor.getId() == null)
			throw new RuntimeException(" Investor not found");
		
		return sharePrices > investor.getBudget() ? Boolean.FALSE : Boolean.TRUE;
	}

	
}
