package com.kennenalphateam.genshin.user.repository;

import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.util.OAuthType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthTypeAndOauthId(@Param("oauth_type") OAuthType oauthType, @Param("oauth_id") String oauthId);
}
