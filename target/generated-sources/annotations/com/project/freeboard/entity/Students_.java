package com.project.freeboard.entity;

import javax.persistence.metamodel.*;

@StaticMetamodel(Students.class)
public class Students_
{
    public static volatile SingularAttribute<Students, Long> serialVersionUID;
    public static volatile SingularAttribute<Students, java.lang.String> email;
    public static volatile SingularAttribute<Students, java.lang.String> name;
    public static volatile SingularAttribute<Students, java.lang.String> lastname;
    public static volatile SingularAttribute<Students, java.lang.String> password;
    public static volatile SingularAttribute<Students, java.lang.String> university;
    public static volatile SingularAttribute<Students, java.lang.String> phone;
    public static volatile SingularAttribute<Students, java.lang.String> bankWire;
    public static volatile SingularAttribute<Students, java.lang.String> bank;
    public static volatile SingularAttribute<Students, java.lang.String> accountType;
    public static volatile SingularAttribute<Students, java.lang.String> accountOwner;
    public static volatile SingularAttribute<Students, java.lang.String> experience;
    public static volatile SingularAttribute<Students, java.lang.String> skills;
    public static volatile SingularAttribute<Students, java.lang.String> hash;
    public static volatile SingularAttribute<Students, java.util.Date> created;
    public static volatile SingularAttribute<Students, java.util.Date> updated;
    public static volatile ListAttribute<Students, com.project.freeboard.entity.Offers> offersList;
}
