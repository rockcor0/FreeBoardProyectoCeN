package com.project.freeboard.entity;

import javax.persistence.metamodel.*;

@StaticMetamodel(Auctions.class)
public class Auctions_
{
    public static volatile SingularAttribute<Auctions, Long> serialVersionUID;
    public static volatile SingularAttribute<Auctions, Integer> idauctions;
    public static volatile SingularAttribute<Auctions, java.lang.String> type;
    public static volatile SingularAttribute<Auctions, java.lang.String> size;
    public static volatile SingularAttribute<Auctions, java.lang.String> mainColor;
    public static volatile SingularAttribute<Auctions, java.lang.String> secundaryColor;
    public static volatile SingularAttribute<Auctions, java.lang.String> description;
    public static volatile SingularAttribute<Auctions, java.util.Date> time;
    public static volatile SingularAttribute<Auctions, java.lang.String> price;
    public static volatile SingularAttribute<Auctions, com.mysql.jdbc.Blob> sketch;
    public static volatile SingularAttribute<Auctions, java.lang.String> companies_nit;
    public static volatile SingularAttribute<Auctions, Integer> winnerOffer;
}
