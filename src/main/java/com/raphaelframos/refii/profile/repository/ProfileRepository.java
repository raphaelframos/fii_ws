package com.raphaelframos.refii.profile.repository;

import com.raphaelframos.refii.profile.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {}
