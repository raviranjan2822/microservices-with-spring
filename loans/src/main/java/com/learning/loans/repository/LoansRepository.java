package com.learning.loans.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learning.loans.model.Loans;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {
	List<Loans> findByCustomerIdOrderByStartDateDesc(int customerId);

}
