package com.Gogedit.converter;

import com.Gogedit.dto.UserDTO;
import com.Gogedit.persistence.entity.AppUser;

public class UserToDTOConverter {
    public static UserDTO toDTO(AppUser user) {
        return new UserDTO(user.getId(), user.getUsername());
    }
}
