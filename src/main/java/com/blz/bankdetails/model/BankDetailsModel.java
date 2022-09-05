package com.blz.bankdetails.model;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.blz.bankdetails.DTO.BankDetailsDTO;

//import com.blz.lms.dto.BankDetailsDTO;
import lombok.Data;

@Entity
@Table(name="bankdetails")
@Data
public class BankDetailsModel {
	@Id
	@GenericGenerator(name = "bankdetails", strategy = "increment")
	@GeneratedValue(generator = "bankdetails")
	private Long id;
	private String bankName;
	private Long Accountno;
	private String Ifsccode;
	private String Branch;
	private String linkedMobNo;
	private String emailId;
	private String AccountHolderName;
	//	@OneToOne
	//	private AdminModel CreatorUser;
	//	@OneToOne
	//	private AdminModel UpdatedUser;
	private LocalDateTime CreatorDateTime;
	private LocalDateTime UpdatedDateTime;

	public BankDetailsModel() {

	}

	public BankDetailsModel(BankDetailsDTO bankDetailsDTO) {
		this.bankName = bankDetailsDTO.getBankName();
		this.Accountno = bankDetailsDTO.getAccountno();
		this.Ifsccode = bankDetailsDTO.getIfsccode();
		this.Branch = bankDetailsDTO.getBranch();
		this.linkedMobNo = bankDetailsDTO.getLinkedMobNo();
		this.emailId = bankDetailsDTO.getEmailId();
		this.AccountHolderName = bankDetailsDTO.getAccountHolderName();
		this.CreatorDateTime = bankDetailsDTO.getCreatorDateTime();
		this.UpdatedDateTime = bankDetailsDTO.getUpdatedDateTime();
	}
}
