package com.instructional.system.management.model;

import lombok.Data;

/**
 * @author ljjwyn
 */
@Data
public class User {
    private int id;
    private String name;
    private String roles;
    private String introduction;
    private String avatar;
    private String position;
    private String classId;
    private String account;
    private String password;
}
