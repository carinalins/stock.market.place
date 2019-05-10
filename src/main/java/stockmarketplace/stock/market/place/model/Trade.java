package stockmarketplace.stock.market.place.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "trade")
public class Trade extends Audit {


	@Column
	@Temporal(TemporalType.DATE)
	private Date dtTransaction;

	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Double price;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_companie", nullable = false)
	private Companie companie;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_investor", nullable = false)
	private Investor investor;

	public Companie getCompanie() {
		return companie;
	}

	public void setCompanie(Companie companie) {
		this.companie = companie;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public Date getDtTransaction() {
		return dtTransaction;
	}

	public void setDtTransaction(Date dtTransaction) {
		this.dtTransaction = dtTransaction;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
}
