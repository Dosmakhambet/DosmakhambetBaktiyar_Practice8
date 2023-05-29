package com.dosmakhambetbbaktiyar_practice8.dto;

import com.dosmakhambetbbaktiyar_practice8.model.Role;
import com.dosmakhambetbbaktiyar_practice8.model.Status;
import com.dosmakhambetbbaktiyar_practice8.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String userName;
    private Status status;
    private Role role;

    public static UserDto asDTO(User user){
        return UserDto.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .userName(user.getUserName())
                .status(user.getStatus())
                .role(user.getRole())
                .build();
    }

    public User asEntity(){
        return User.builder()
                .id(id)
                .fullName(fullName)
                .userName(userName)
                .status(status)
                .role(role)
                .build();
    }
}
