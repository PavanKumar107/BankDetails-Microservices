package com.blz.bankdetails.service;
import java.util.List;
import com.blz.bankdetails.DTO.BankDetailsDTO;
import com.blz.bankdetails.model.BankDetailsModel;

public interface IBankDetailsService {

	BankDetailsModel addBankDetails(BankDetailsDTO bankDetailsDTO,String token);

	BankDetailsModel updateBankDetails(BankDetailsDTO bankDetailsDTO,Long id,String token);

	List<BankDetailsModel> getAllBankDetails(String token);

	BankDetailsModel deleteBankDetails(Long id,String token);

}
