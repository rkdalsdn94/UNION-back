package com.develop_ping.union.user.infra;

import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
