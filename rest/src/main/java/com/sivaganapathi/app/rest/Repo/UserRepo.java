package com.sivaganapathi.app.rest.Repo;
import com.sivaganapathi.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepo extends JpaRepository<User, Long> {
}