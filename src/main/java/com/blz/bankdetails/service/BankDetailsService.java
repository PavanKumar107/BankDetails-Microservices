package com.blz.bankdetails.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.blz.bankdetails.DTO.BankDetailsDTO;
import com.blz.bankdetails.exception.CustomNotFoundException;
import com.blz.bankdetails.model.BankDetailsModel;
import com.blz.bankdetails.repository.BankDetailsRepository;

@Service
public class BankDetailsService implements IBankDetailsService {

	@Autowired 
	BankDetailsRepository bankDetailsRepository;

	//	@Autowired
	//	TokenUtil tokenUtil;
	//
	//	@Autowired
	//	AdminRepository adminRepository;

	@Autowired
	MailService mailService;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public BankDetailsModel addBankDetails(BankDetailsDTO bankDetailsDTO,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		//			Optional<AdminModel> isAdminPresent = adminRepository.findById(adminId);
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8077/bankdetails/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			BankDetailsModel model = new BankDetailsModel();
			//			if(isAdminPresent.isPresent()) {
			//				model.setCreatorUser(isAdminPresent.get());
			//			}
			bankDetailsRepository.save(model);
			String body = "Bank details added successfully with Id"+model.getId();
			String subject = "Bank details added Successfull";
			mailService.send(model.getEmailId(), subject, body);
			return model;
		}
		throw new CustomNotFoundException(400,"Token not present");
	}

	@Override
	public BankDetailsModel updateBankDetails(BankDetailsDTO bankDetailsDTO, Long id,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		//			Optional<AdminModel>isIdPresent = adminRepository.findById(adminId);
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8077/bankdetails/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BankDetailsModel>isDetailsPresent = bankDetailsRepository.findById(id);
			//			if(isIdPresent.isPresent()) {
			//				isDetailsPresent.get().setUpdatedUser(isIdPresent.get());
			//			}

			if(isDetailsPresent.isPresent()) {
				isDetailsPresent.get().setBankName(bankDetailsDTO.getBankName());
				isDetailsPresent.get().setAccountno(bankDetailsDTO.getAccountno());
				isDetailsPresent.get().setIfsccode(bankDetailsDTO.getIfsccode());
				isDetailsPresent.get().setBranch(bankDetailsDTO.getBranch());
				isDetailsPresent.get().setLinkedMobNo(bankDetailsDTO.getLinkedMobNo());
				isDetailsPresent.get().setEmailId(bankDetailsDTO.getEmailId());
				isDetailsPresent.get().setAccountHolderName(bankDetailsDTO.getAccountHolderName());
				isDetailsPresent.get().setUpdatedDateTime(bankDetailsDTO.getUpdatedDateTime().now());
				bankDetailsRepository.save(isDetailsPresent.get());
				String body = "Bank details updated successfully with bank Id"+isDetailsPresent.get().getId();
				String subject = "Bank details updated Successfully";
				mailService.send(isDetailsPresent.get().getEmailId(), subject, body);
				return isDetailsPresent.get();
			}
			throw new CustomNotFoundException(400,"Bank details not present");
		}
		throw new CustomNotFoundException(400,"Token Invalid");
	}

	@Override
	public List<BankDetailsModel> getAllBankDetails(String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8077/bankdetails/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<BankDetailsModel>getAllBankDetails = bankDetailsRepository.findAll();
			if(getAllBankDetails.size()>0) {
				return getAllBankDetails;
			}else {
				throw new CustomNotFoundException(400,"Bank details not present");
			}
		}
		throw new CustomNotFoundException(400,"Token not present");
	}

	@Override
	public BankDetailsModel deleteBankDetails(Long id,String token) {
		//		Long admId = tokenUtil.decodeToken(token);
		//		Optional<AdminModel> isTokenPresent = adminRepository.findById(admId);
		//		if(isTokenPresent.isPresent()) {
		boolean isUserPresent = restTemplate.getForObject("http://localhost:8077/bankdetails/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BankDetailsModel> isDetailsPresent = bankDetailsRepository.findById(id);
			if(isDetailsPresent.isPresent()) {
				bankDetailsRepository.delete(isDetailsPresent.get());
				String body = "Bank details deleted successfully with Id"+isDetailsPresent.get().getId();
				String subject = "Bank details deleted Successfully";
				mailService.send(isDetailsPresent.get().getEmailId(), subject, body);
				return isDetailsPresent.get();
			}
			throw new CustomNotFoundException(400,"Bank details not present");
		}
		throw new CustomNotFoundException(400,"Token not present");
	}
}
