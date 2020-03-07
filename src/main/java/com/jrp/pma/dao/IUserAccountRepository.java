package com.jrp.pma.dao;

import com.jrp.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {

}
