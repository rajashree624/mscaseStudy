package com.ibm.casestudy.orderms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.casestudy.orderms.model.Token;



public interface TokenRepository extends JpaRepository<Token, String>{

}
