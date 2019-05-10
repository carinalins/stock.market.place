package stockmarketplace.stock.market.place.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Companie extends Audit {

	@Column
//	@Max(value = 1000, message = "value max is 1000")
//	@Min(value = 500 , message = "value min is 500")
	private Integer shares;
//	@DecimalMax(value = "100.0", message = "value max is 100.0")
//	@DecimalMin(value = "10.0", message = "value min is 10.0")
	@Column
	private Double sharePrices;
	
	@Column
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companie")
	private List<Trade> trades;

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public Integer getShares() {
		return shares;
	}

	public void setShares(Integer shares) {
		this.shares = shares;
	}

	public Double getSharePrices() {
		return sharePrices;
	}

	public void setSharePrices(Double sharePrices) {
		this.sharePrices = sharePrices;
	}

}
