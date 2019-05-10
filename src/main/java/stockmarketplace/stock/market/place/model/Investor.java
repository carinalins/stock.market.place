package stockmarketplace.stock.market.place.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
public class Investor extends Audit {

	@Column
//	@DecimalMin(value = "1000", message = "value min is 1000")
//	@DecimalMax(value = "10000", message = "value max is 10000")
	private Double budget;

	@Column
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "investor")
	private List<Trade> trades;

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

}
