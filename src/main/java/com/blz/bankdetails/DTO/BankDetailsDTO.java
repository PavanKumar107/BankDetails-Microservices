package com.blz.bankdetails.DTO;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BankDetailsDTO {

	@Pattern(regexp = "^[A-Z]{1,}[a-z]{1,}$", message = "Bank name should start with uppercase and having atleast 2 characters!")
	private String bankName;

	@NotBlank(message = "Account no cannot be empty")
	private Long Accountno;

	@NotBlank(message = "Ifsc code cannot be empty")
	private String Ifsccode;

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Branch name Invalid")
	private String Branch;

	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$",message = "Enter valid mobile number")
	private String linkedMobNo;

	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",message ="Enter valid email address")
	private String emailId;

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Account holder name invalid")
	private String AccountHolderName;

	private LocalDateTime CreatorDateTime;

	private LocalDateTime UpdatedDateTime;
}
