package com.my.projmanager.repository;

import com.my.projmanager.model.impl.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, String> {
}
