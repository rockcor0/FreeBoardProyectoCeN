package com.project.freeboard.entity;

import javax.persistence.metamodel.*;

@StaticMetamodel(Companies.class)
public class Companies_
{
    public static volatile SingularAttribute<Companies, Long> serialVersionUID;
    public static volatile SingularAttribute<Companies, java.lang.String> email;
    public static volatile SingularAttribute<Companies, java.lang.String> name;
    public static volatile SingularAttribute<Companies, java.lang.String> phone;
    public static volatile SingularAttribute<Companies, java.lang.String> address;
    public static volatile SingularAttribute<Companies, java.lang.String> password;
    public static volatile SingularAttribute<Companies, java.lang.String> contactPerson;
    public static volatile SingularAttribute<Companies, java.lang.String> hash;
    public static volatile SingularAttribute<Companies, java.util.Date> created;
    public static volatile SingularAttribute<Companies, java.util.Date> updated;
    public static volatile ListAttribute<Companies, com.project.freeboard.entity.Auctions> auctionsList;
}
