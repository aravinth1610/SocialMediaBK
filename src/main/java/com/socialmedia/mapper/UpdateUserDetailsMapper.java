package com.socialmedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.socialmedia.entity.Users;
import com.socialmedia.modal.UpdateUserDetailsModal;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UpdateUserDetailsMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "accountVerified", ignore = true)
	@Mapping(target = "emailVerified", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "profilePhoto", ignore = true)
	@Mapping(target = "coverPhoto", ignore = true)
	@Mapping(target = "followerCount", ignore = true)
	@Mapping(target = "followingCount", ignore = true)
	@Mapping(target = "createOn", ignore = true)
	@Mapping(target = "createBy", ignore = true)
	@Mapping(target = "updateOn", ignore = true)
	@Mapping(target = "updateBy", ignore = true)
	void updateUserFromUserUpdateDto(UpdateUserDetailsModal updateUserInfoDto, @MappingTarget Users user);

}
