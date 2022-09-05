package com.blz.bankdetails.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blz.bankdetails.model.BankDetailsModel;

public interface BankDetailsRepository extends JpaRepository<BankDetailsModel, Long> {

}
