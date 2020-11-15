package com.spring.market.dto;

import com.spring.market.entities.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String username;
    private String hobbies;
    private String confirmationPassword;

    public ProfileDto(Profile p) {
        this.id = p.getId();
        this.username = p.getUser().getUsername();
        this.hobbies = p.getHobbies();
    }
}
