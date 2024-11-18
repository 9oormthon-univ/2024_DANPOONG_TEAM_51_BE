package com.cone.cone.domain.user.repository;

import com.cone.cone.domain.user.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPlatformId(final String platformId);
}
