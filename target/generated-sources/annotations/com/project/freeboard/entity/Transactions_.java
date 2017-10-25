package com.project.freeboard.entity;

import javax.persistence.metamodel.*;

@StaticMetamodel(Transactions.class)
public class Transactions_
{
    public static volatile SingularAttribute<Transactions, Long> serialVersionUID;
    public static volatile SingularAttribute<Transactions, java.lang.String> referenceCode;
    public static volatile SingularAttribute<Transactions, java.lang.String> description;
    public static volatile SingularAttribute<Transactions, Double> amount;
    public static volatile SingularAttribute<Transactions, Double> tax;
    public static volatile SingularAttribute<Transactions, Integer> taxReturnBase;
    public static volatile SingularAttribute<Transactions, java.lang.String> currency;
    public static volatile SingularAttribute<Transactions, java.lang.String> buyerFullName;
    public static volatile SingularAttribute<Transactions, java.lang.String> buyerEmail;
    public static volatile SingularAttribute<Transactions, java.lang.String> test;
    public static volatile SingularAttribute<Transactions, java.lang.String> payHash;
    public static volatile SingularAttribute<Transactions, java.lang.Integer> statePol;
    public static volatile SingularAttribute<Transactions, java.lang.String> responseCodePol;
    public static volatile SingularAttribute<Transactions, java.lang.Integer> responseMessageCol;
    public static volatile SingularAttribute<Transactions, java.lang.Integer> paymentMethodType;
    public static volatile SingularAttribute<Transactions, java.util.Date> transactionDate;
    public static volatile SingularAttribute<Transactions, java.lang.String> paymentMethodName;
    public static volatile SingularAttribute<Transactions, java.lang.String> transactionscol;
    public static volatile SingularAttribute<Transactions, java.util.Date> created;
    public static volatile SingularAttribute<Transactions, java.util.Date> updated;
    public static volatile SingularAttribute<Transactions, com.project.freeboard.entity.Offers> offersIdoffers;
}
