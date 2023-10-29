package com.example.pmaapi.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {
        private String firstName;
        private String lastName;
        private String email;
        private String profilePicture;
}
