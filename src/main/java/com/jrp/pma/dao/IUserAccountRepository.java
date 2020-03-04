package com.jrp.pma.dao;

import com.jrp.pma.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface IUserAccountRepository extends CrudRepository<UserAccount, Long> {

}
