package stockmarketplace.stock.market.place.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import stockmarketplace.stock.market.place.dto.CompanieDTO;
import stockmarketplace.stock.market.place.dto.CompanyCapital;
import stockmarketplace.stock.market.place.model.Companie;

@Repository
public interface CompanieRepository extends CrudRepository<Companie, Integer> {
	
	
	@Query( value = "select count(*) from companie\r\n" + 
			"inner join trade\r\n" + 
			"on trade.id_companie = companie.id\r\n" + 
			"where companie.id = ?1 and trade.dt_transaction = current_date ", nativeQuery = true)
	BigInteger countTradeCompanieToday (Integer idCompanie);
	
	@Query(value = "select id, share_prices as price , shares, name from companie ", nativeQuery = true)
	List<CompanieDTO> allCompanieAsc ();
	
	@Query(value = "select  id, dt_add, dt_delete, dt_update, name, share_prices, shares from companie\r\n"  
			,  nativeQuery = true)
	List<Companie> allCompany();
	
	
	@Query(value = "select c.name, c.shares ,round (sum (t.price))\r\n" + 
			"	as capital\r\n" + 
			"from companie c\r\n" + 
			" inner join trade t\r\n" + 
			" on t.id_companie = c.id\r\n" + 
			" group by c.name, c.shares\r\n" + 
			"	order by sum (t.price)  desc"  , nativeQuery = true)
	List<CompanyCapital> listHighestCapital ();
	

	@Query(value = "select c.name,c.shares  ,round ( sum (t.price))\r\n" + 
			"	as capital\r\n" + 
			"from companie c\r\n" + 
			" inner join trade t\r\n" + 
			" on t.id_companie = c.id\r\n" + 
			" group by c.name, c.shares \r\n" + 
			"	order by sum (t.price)  asc"  , nativeQuery = true)
	List<CompanyCapital> listlowestCapital ();
		
	
}
