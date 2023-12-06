package com.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.socialmedia.entity.Users;

import jakarta.transaction.Transactional;

import java.util.Optional;
import java.sql.Date;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);

	Optional<Users> findByUserId(String userId);

	boolean existsByEmail(String email);

	@Query("SELECT userId from Users")
	String getUserId();

	@Query("SELECT profilePhoto FROM Users")
	String getUserOldPhotoName();

	@Modifying
	@Transactional
	@Query(value = "UPDATE smusers SET email=?1,accountverified=?2,emailverified=?3,updateon=?4 WHERE userid=?5", nativeQuery = true)
	Integer updateUserEmail(String newEmail, Boolean accountverified, Boolean emailverified, Date updateon,
			String userid);

	@Modifying
	@Transactional
	@Query(value = "UPDATE smusers SET password=?1,updateon=?2 WHERE userid=?3", nativeQuery = true)
	Integer updateUserPassword(String Password, Date updateOn, String userid);

	@Modifying
	@Transactional
	@Query(value = "UPDATE smusers SET profilephoto=?1,updateon=?2 WHERE userid=?3", nativeQuery = true)
	Integer updateProfilePhoto(String profilephoto, Date updateOn, String userid);

}
