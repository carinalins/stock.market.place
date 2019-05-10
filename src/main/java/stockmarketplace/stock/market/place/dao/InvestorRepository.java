package stockmarketplace.stock.market.place.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import stockmarketplace.stock.market.place.dto.InvestorDTO;
import stockmarketplace.stock.market.place.dto.InvestorHighestSharesDTO;
import stockmarketplace.stock.market.place.dto.InvestorInvestMostCompaniesDTO;
import stockmarketplace.stock.market.place.model.Investor;

public interface InvestorRepository extends CrudRepository<Investor, Integer> {

	@Query(value = " select  t.id_investor ,count (t.id_investor)  as shares , i.name from investor i  \r\n"
			+ "			        inner join trade t \r\n" + "			        	on i.id = t.id_investor\r\n"
			+ "			        group by  t.id_investor, i.name\r\n"
			+ "                    order by count (t.id_investor) desc\r\n", nativeQuery = true)
	List<InvestorHighestSharesDTO> investorOrderByHighestShares();

	@Query(value = "select id, dt_add, dt_delete, dt_update, budget, name from investor", nativeQuery = true)
	List<Investor> listInvestor();

	@Query(value = " select  t.id_investor ,count (t.id_investor)  as shares , i.name from investor i  \r\n"
			+ "			        inner join trade t \r\n" + "			        	on i.id = t.id_investor\r\n"
			+ "			        group by  t.id_investor, i.name\r\n"
			+ "                    order by count (t.id_investor) asc\r\n", nativeQuery = true)
	List<InvestorHighestSharesDTO> investorOrderByLowertShares();

	@Query(value = "select i.name, count (distinct t.id_companie) as invest from investor i\r\n" + 
			"inner join trade t\r\n" + 
			"on i.id = t.id_investor\r\n" + 
			"inner join companie c\r\n" + 
			"on c.id = t.id_companie\r\n" + 
			"group by i.name \r\n" + 
			"order by count (distinct t.id_companie) desc \r\n" , nativeQuery = true)
	List<InvestorInvestMostCompaniesDTO> investorInvetedMostCompanies();

	@Query(value = "select i.name, count (distinct t.id_companie) as invest from investor i\r\n" + "inner join trade t\r\n"
			+ "on i.id = t.id_investor\r\n" + "inner join companie c\r\n" + "on c.id = t.id_companie\r\n"
			+ "group by i.name \r\n" + "order by count ( distinct t.id_companie) asc\r\n", nativeQuery = true)
	List<InvestorInvestMostCompaniesDTO> investorLeastInvetedMostCompanies();

	@Query(value = "   select i.id, count (t.id_investor) as qtdShares" + " from investor i\r\n"
			+ "  left join trade t\r\n" + "  on i.id = t.id_investor\r\n" + "  group by i.id\r\n"
			+ "  order by max (t.id_investor) desc  ", nativeQuery = true)
	List<InvestorHighestSharesDTO> investorOrderLowetShares();

	@Query(value = "select id, budget, name from investor order by name asc", nativeQuery = true)
	List<InvestorDTO> allInvestorAsc();

}
