package com.blz.bankdetails.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blz.bankdetails.DTO.BankDetailsDTO;
import com.blz.bankdetails.model.BankDetailsModel;
import com.blz.bankdetails.service.IBankDetailsService;
import com.blz.bankdetails.util.Response;

/**
 * Purpose: BankDetails controller to process BankDetails Data APIs.
 * @version: 4.15.1.RELEASE
 * @author: Pavan Kumar G V  
 */
@RestController
@RequestMapping("bankdetails")
public class BankDetailsController {

	@Autowired
	IBankDetailsService bankDetailsService;

	/**
	 * Purpose: To Create Bankdetails
	 * @Param: bankDetailsDTO, token and adminId
	 */
	@PostMapping("/addbankdetais")
	public ResponseEntity<Response> addBankDetails(@Valid@RequestBody BankDetailsDTO bankDetailsDTO, @RequestHeader String token) {
		BankDetailsModel bankDetailsModel = bankDetailsService.addBankDetails(bankDetailsDTO, token);
		Response response = new Response("BankDetails inserted successfully", 200, bankDetailsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}


	/**
	 * Purpose: To Update Bankdetails by id
	 * @Param: bankDetailsDTO, id, token and adminId
	 */
	@PutMapping("/updatebankdetails/{id}")
	public ResponseEntity<Response> updateBankDetails(@Valid@RequestBody BankDetailsDTO bankDetailsDTO,@PathVariable Long id, @RequestHeader String token) {
		BankDetailsModel bankDetailsModel = bankDetailsService.updateBankDetails(bankDetailsDTO,id,token);
		Response response = new Response("BankDetails updated successfully", 200, bankDetailsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: To get all Bankdetails 
	 * @Param: token
	 */
	@GetMapping("/getallbankdetails")
	public ResponseEntity<Response> getAllBankDetails(@RequestHeader String token) {
		List<BankDetailsModel> bankDetailsModel = bankDetailsService.getAllBankDetails(token);
		Response response = new Response("getting all the bankdetails successfully", 200, bankDetailsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: To delete Bankdetails by id 
	 * @Param: id and token
	 */
	@DeleteMapping("/deletebankdetals/{id}")
	public ResponseEntity<Response> deleteBankDetails(@PathVariable Long id, @RequestHeader String token) {
		BankDetailsModel bankDetailsModel = bankDetailsService.deleteBankDetails(id,token);
		Response response = new Response("BankDetails deleted successfully", 200, bankDetailsModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
