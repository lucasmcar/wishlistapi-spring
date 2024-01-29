package com.codeexpertssistemas.wishlist.repository;

import com.codeexpertssistemas.wishlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  JpaRepository<User, Long> {
}
