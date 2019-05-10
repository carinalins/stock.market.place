package stockmarketplace.stock.market.place.service;

import java.math.BigInteger;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stockmarketplace.stock.market.place.dao.CompanieRepository;
import stockmarketplace.stock.market.place.model.Companie;

@Service("IcompanieService")
public class CompanieService implements IcompanieService {

	@Autowired
	private CompanieRepository companieRepository;

	@Override
	public void checkSellShares(Companie companie) {

		BigInteger qtdTradeToday = companieRepository.countTradeCompanieToday(companie.getId());

		if (qtdTradeToday.intValue() % 10 == 0) {

			companie.setSharePrices(companie.getSharePrices() * 2);
			companie.setDtUpdate(new Date());
			try {

				companieRepository.save(companie);
			} catch (ConstraintViolationException e) {
				// TODO: handle exception
			}

		}

	}

}
