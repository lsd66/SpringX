package com.lsd.myoauth2.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {

    private String account;

    private String description;

    private String password;

    private String name;

}
