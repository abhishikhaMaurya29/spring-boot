package com.abhishikha.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private @Setter(AccessLevel.PROTECTED) long id;
    private String name;
    private String email;
    private String mobileNo;
}