package stockmarketplace.stock.market.place.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Audit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtAdd;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUpdate;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtDelete;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDtAdd() {
		return dtAdd;
	}
	public void setDtAdd(Date dtAdd) {
		this.dtAdd = dtAdd;
	}
	public Date getDtUpdate() {
		return dtUpdate;
	}
	public void setDtUpdate(Date dtUpdate) {
		this.dtUpdate = dtUpdate;
	}
	public Date getDtDelete() {
		return dtDelete;
	}
	public void setDtDelete(Date dtDelete) {
		this.dtDelete = dtDelete;
	}
	
	
	
}
