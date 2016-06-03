CREATE table `itemsdatabase`.`discountTable` (
`discountId` INT,
`typeOfDiscount` VARCHAR(45) NULL,
`discountAmount` DECIMAL(10,2) NOT NULL,
primary key (`discountId`));

insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (1, 'F', 5.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (2, 'F', 10.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (3, 'F', 15.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (4, 'F', 20.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (5, 'F', 25.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (6, 'F', 30.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (7, 'F', 35.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (8, 'F', 40.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (9, 'F', 45.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (10, 'F', 50.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (11, 'Z', 1.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (12, 'Z', 2.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (13, 'Z', 3.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (14, 'Z', 4.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (15, 'Z', 5.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (16, 'Z', 6.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (17, 'Z', 7.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (18, 'Z', 8.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (19, 'Z', 9.00);
insert into itemsdatabase.discountTable (discountId, typeOfDiscount, discountAmount) value (20, 'Z', 10.00);


CREATE table itemsDatabase.itemstable (
`itemCode` INT NOT NULL,
`itemName` VARCHAR(45	) NULL,
`itemPrice` DECIMAL(10,2) NOT NULL,
`discountId` INT,
primary key (`itemCode`),
FOREIGN KEY (`discountId`) REFERENCES itemsdatabase.discountTable(`discountId`));



insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (1,'Kava', 10.00, 1);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (2,'Arbata', 7.00, 3);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (3,'Sausainiai', 4.00, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (4,'Tortas', 9.00, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (5,'Saldainiai', 3.00, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (6,'Agurkas', 1.50, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (7,'Pomidorai', 1.60, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (8,'Suris', 2.40, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (9,'Arbuzas', 2.98, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (10,'Mesa', 7.49, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (11,'Duona', 0.79, null);
insert into itemsdatabase.itemstable (itemCode,itemName,itemPrice, discountId) value (12,'Bulka', 0.69, null);

CREATE table `itemsdatabase`.`purchase` (
`Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
`Date` VARCHAR(45),
`Type` VARCHAR(45),
`Sum` decimal(10,2),
primary key (`Id`));

CREATE table itemsDatabase.Item (
`Id` INT NOT NULL AUTO_INCREMENT,
`itemName` VARCHAR(45),
`itemPrice` DECIMAL(10,2),
`numberOfItems` INT,
`sum` DECIMAL(10,2),
`purchaseId` INT UNSIGNED NOT NUll,
primary key (`Id`),
FOREIGN KEY (`purchaseId`) REFERENCES itemsdatabase.purchase(`Id`));






