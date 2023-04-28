package com.webshop.PaymentMs.repository;


import com.webshop.PaymentMs.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<TransactionDetails,Long> {

TransactionDetails findByOrderId(long id);

}
