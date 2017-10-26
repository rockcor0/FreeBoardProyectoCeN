package com.project.freeboard.entity;

import javax.persistence.metamodel.*;

@StaticMetamodel(Offers.class)
public class Offers_
{
    public static volatile SingularAttribute<Offers, Long> serialVersionUID;
    public static volatile SingularAttribute<Offers, java.lang.String> idoffers;
    public static volatile SingularAttribute<Offers, java.lang.String> price;
    public static volatile SingularAttribute<Offers, java.lang.Short> paid;
    public static volatile SingularAttribute<Offers, java.lang.String> payHash;
    public static volatile SingularAttribute<Offers, java.lang.String> state;
    public static volatile SingularAttribute<Offers, java.util.Date> created;
    public static volatile SingularAttribute<Offers, java.util.Date> updated;
    public static volatile SingularAttribute<Offers, com.project.freeboard.entity.Auctions> auctionsIdauctions;
    public static volatile SingularAttribute<Offers, com.project.freeboard.entity.Students> studentsId;
    public static volatile ListAttribute<Offers, com.project.freeboard.entity.Transactions> transactionsList;
    public static volatile ListAttribute<Offers, com.project.freeboard.entity.Auctions> auctionsList;
}
