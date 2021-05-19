/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Software
 * Created: 15 avr. 2021
 */

CREATE DATABASE IF NOT EXISTS bankx;

CREATE TABLE IF NOT EXISTS `bank_user`(
    id INTEGER NOT NULL AUTO_INCREMENT,
    lastname VARCHAR(200) NOT NULL,
    firstname VARCHAR(200) NOT NULL,
    password TEXT NOT NULL,

    PRIMARY KEY(id)

)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `bank_account`(
    id INTEGER NOT NULL AUTO_INCREMENT,
    numero VARCHAR(100) NOT NULL,
    category VARCHAR(20),
    amount INTEGER NOT NULL,
    rate DOUBLE NOT NULL,
    status VARVHAR(20) DEFAULT 'valide',
    bank_user_id INTEGER NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_bank_account_user
    FOREIGN KEY (bank_user_id) REFERENCES bank_user(id);
)ENGINE=InnoDB;


CREATE TABLE IF NOT EXISTS `account_operation` (
    id INTEGER NOT NULL AUTO_INCREMENT,
    payment_way VARCHAR(100) NOT NULL,
    amount INTEGER NOT NULL DEFAULT 0,
    theme VARCHAR(100) NOT NULL,
    account_id INTEGER NOT NULL,
    amount_bf INTEGER NOT NULL,
    amount_af INTEGER NOT NULL,
    

    PRIMARY KEY (id),
    
    CONSTRAINT fk_operation_account
    FOREIGN KEY (account_id) REFERENCES bank_account(id)
)ENGINE=InnoDB;

INSERT INTO bank_account
    (numero, category, amount, rate, bank_user_id) 
VALUES('CARD 1234 5677 5677 2423', 'Joint', 200, 0, 1);