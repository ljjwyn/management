package com.instructional.system.management.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ljjwyn
 */
@Data
@TableName("students")
public class Students {
    private String studentId;

    private String name;

    private String sex;

    private String phoneNumber;

    private String classId;

    private String className;

    private String grade;

    private String dormitory;

    private String parentName;

    private String parentPhone;
}
