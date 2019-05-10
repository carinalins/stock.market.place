package stockmarketplace.stock.market.place.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stockmarketplace.stock.market.place.dto.AnyTradeDTO;
import stockmarketplace.stock.market.place.dto.ListTradeDTO;
import stockmarketplace.stock.market.place.model.Trade;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Integer> {
	
	@Query(value = "select c.id , round (c.share_prices) as ov, c.name, round (c.share_prices -  (c.share_prices * 0.02)) \r\n" + 
			"		as nv   from companie c  \r\n" + 
			"									left join trade t \r\n" + 
			"									on t.id_companie = c.id  \r\n" + 
			"									where t.dt_transaction = current_date \r\n" + 
			"						          group by  t.id_companie  ,c.name, c.share_prices, c.id  \r\n" + 
			"						          having t.id_companie < 10 or t.id_companie is null ", nativeQuery = true)
	List<AnyTradeDTO> listCompanyAnyTradeYesterday ();
	
	@Query(value = "select trade.dt_transaction as transaction, trade.id_companie, companie.name, trade.id_investor, investor.name as investor from trade \r\n" + 
			"					inner join companie on companie.id = trade.id_companie\r\n" + 
			"                    inner join investor on investor.id = trade.id_investor\r\n" + 
			"			        order by dt_transaction desc", nativeQuery = true)
	List<ListTradeDTO> listTrade ();

}
