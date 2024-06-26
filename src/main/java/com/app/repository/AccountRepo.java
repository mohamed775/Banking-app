package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}
