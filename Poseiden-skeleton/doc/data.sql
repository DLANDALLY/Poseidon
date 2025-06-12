
CREATE TABLE BidList (
  BidListId tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bidQuantity DOUBLE,
  askQuantity DOUBLE,
  bid DOUBLE ,
  ask DOUBLE,
  benchmark VARCHAR(125),
  bidListDate TIMESTAMP,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (BidListId)
);

CREATE TABLE Trade (
  TradeId tinyint(4) NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buyQuantity DOUBLE,
  sellQuantity DOUBLE,
  buyPrice DOUBLE ,
  sellPrice DOUBLE,
  tradeDate TIMESTAMP,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creationName VARCHAR(125),
  creationDate TIMESTAMP ,
  revisionName VARCHAR(125),
  revisionDate TIMESTAMP ,
  dealName VARCHAR(125),
  dealType VARCHAR(125),
  sourceListId VARCHAR(125),
  side VARCHAR(125),

  PRIMARY KEY (TradeId)
);

CREATE TABLE CurvePoint (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  CurveId tinyint,
  asOfDate TIMESTAMP,
  term DOUBLE ,
  value DOUBLE ,
  creationDate TIMESTAMP ,

  PRIMARY KEY (Id)
);

CREATE TABLE Rating (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  moodysRating VARCHAR(125),
  sandPRating VARCHAR(125),
  fitchRating VARCHAR(125),
  orderNumber tinyint,

  PRIMARY KEY (Id)
);

CREATE TABLE RuleName (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(125),
  template VARCHAR(512),
  sqlStr VARCHAR(125),
  sqlPart VARCHAR(125),

  PRIMARY KEY (Id)
);

CREATE TABLE Users (
  Id tinyint(4) NOT NULL AUTO_INCREMENT,
  username VARCHAR(125),
  password VARCHAR(125),
  fullname VARCHAR(125),
  role VARCHAR(125),

  PRIMARY KEY (Id)
);

insert into Users(fullname, username, password, role) values("Administrator", "admin", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "ADMIN");
insert into Users(fullname, username, password, role) values("User", "user", "$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", "USER");


-- Trade
INSERT INTO Trade (account, type, buyQuantity, sellQuantity, buyPrice, sellPrice, tradeDate, security, status, trader, benchmark, book, creationName, creationDate, revisionName, revisionDate, dealName, dealType, sourceListId, side)
VALUES
('ACC123', 'Spot', 1000, 0, 99.5, NULL, NOW(), 'AAPL', 'OPEN', 'TraderJoe', 'S&P500', 'Book1', 'System', NOW(), 'System', NOW(), 'Deal1', 'TypeA', 'SRC1', 'BUY'),
('ACC456', 'Forward', NULL, 2000, NULL, 101.2, NOW(), 'GOOG', 'CLOSED', 'TraderJane', 'NASDAQ', 'Book2', 'Admin', NOW(), 'Admin', NOW(), 'Deal2', 'TypeB', 'SRC2', 'SELL'),
('ACC789', 'Option', 1500, 500, 98.0, 100.0, NOW(), 'MSFT', 'PENDING', 'TraderMax', 'S&P100', 'Book3', 'Max', NOW(), 'Max', NOW(), 'Deal3', 'TypeC', 'SRC3', 'BUY'),
('ACC321', 'Swap', 3000, 3000, 97.0, 99.0, NOW(), 'TSLA', 'CANCELLED', 'TraderAnne', 'DJI', 'Book4', 'Anne', NOW(), 'Anne', NOW(), 'Deal4', 'TypeD', 'SRC4', 'SELL'),
('ACC654', 'Future', 500, 500, 96.0, 98.5, NOW(), 'AMZN', 'EXECUTED', 'TraderLeo', 'FTSE', 'Book5', 'Leo', NOW(), 'Leo', NOW(), 'Deal5', 'TypeE', 'SRC5', 'BUY');

-- CurvePoint
INSERT INTO CurvePoint (CurveId, asOfDate, term, value, creationDate)
VALUES
(1, NOW(), 1.0, 0.25, NOW()),
(1, NOW(), 2.0, 0.45, NOW()),
(2, NOW(), 3.0, 0.65, NOW()),
(2, NOW(), 5.0, 0.85, NOW()),
(3, NOW(), 10.0, 1.05, NOW());

-- Rating
INSERT INTO Rating (moodysRating, sandPRating, fitchRating, orderNumber)
VALUES
('Aaa', 'AAA', 'AAA', 1),
('Aa2', 'AA', 'AA+', 2),
('A1', 'A+', 'A+', 3),
('Baa1', 'BBB+', 'BBB', 4),
('Ba2', 'BB', 'BB-', 5);

-- RuleName
INSERT INTO RuleName (name, description, json, template, sqlStr, sqlPart)
VALUES
('Rule1', 'Check price > 100', '{"price":">100"}', 'template1', 'SELECT * FROM Trade WHERE price > 100', 'price > 100'),
('Rule2', 'Check quantity < 500', '{"qty":"<500"}', 'template2', 'SELECT * FROM Trade WHERE qty < 500', 'qty < 500'),
('Rule3', 'Check type Spot', '{"type":"Spot"}', 'template3', 'SELECT * FROM Trade WHERE type = "Spot"', 'type = "Spot"'),
('Rule4', 'Check trader is Joe', '{"trader":"Joe"}', 'template4', 'SELECT * FROM Trade WHERE trader = "Joe"', 'trader = "Joe"'),
('Rule5', 'Check status is OPEN', '{"status":"OPEN"}', 'template5', 'SELECT * FROM Trade WHERE status = "OPEN"', 'status = "OPEN"');


-- User
INSERT INTO Users (username, password, fullname, role)
VALUES
('jdoe', '$2a$10$WsYGYFpGJaDMnuOTL/qiW.siu6Ibn1Kvzy2cs/kXONu3G05u0UsYO', 'John Doe', 'USER'),
('ajones', '$2a$10$WsYGYFpGJaDMnuOTL/qiW.siu6Ibn1Kvzy2cs/kXONu3G05u0UsYO', 'Alice Jones', 'ADMIN'),
('bsmith', '$2a$10$WsYGYFpGJaDMnuOTL/qiW.siu6Ibn1Kvzy2cs/kXONu3G05u0UsYO', 'Bob Smith', 'USER'),
('mking', '$2a$10$WsYGYFpGJaDMnuOTL/qiW.siu6Ibn1Kvzy2cs/kXONu3G05u0UsYO', 'Mary King', 'MANAGER'),
('tlee', '$2a$10$WsYGYFpGJaDMnuOTL/qiW.siu6Ibn1Kvzy2cs/kXONu3G05u0UsYO', 'Tom Lee', 'USER');

INSERT INTO BidList (
  account, type, bidQuantity, askQuantity, bid, ask, benchmark, bidListDate,
  commentary, security, status, trader, book, creationName, creationDate,
  revisionName, revisionDate, dealName, dealType, sourceListId, side
) VALUES
('ACC001', 'TypeA', 1000.0, 1200.0, 99.5, 100.0, 'Benchmark1', NOW(), 'Commentaire 1', 'SEC123', 'NEW', 'TraderX', 'Book1', 'Admin', NOW(), 'RevUser1', NOW(), 'Deal001', 'Swap', 'SRC001', 'Buy'),
('ACC002', 'TypeB', 500.0, 550.0, 101.0, 102.0, 'Benchmark2', NOW(), 'Commentaire 2', 'SEC124', 'APPROVED', 'TraderY', 'Book2', 'Admin', NOW(), 'RevUser2', NOW(), 'Deal002', 'Option', 'SRC002', 'Sell'),
('ACC003', 'TypeC', 2000.0, 1800.0, 98.0, 98.5, 'Benchmark3', NOW(), 'Commentaire 3', 'SEC125', 'REJECTED', 'TraderZ', 'Book3', 'User1', NOW(), 'User2', NOW(), 'Deal003', 'Future', 'SRC003', 'Buy'),
('ACC004', 'TypeA', 1500.0, 1400.0, 97.5, 98.0, 'Benchmark1', NOW(), 'Commentaire 4', 'SEC126', 'NEW', 'TraderX', 'Book1', 'User2', NOW(), 'User3', NOW(), 'Deal004', 'Swap', 'SRC004', 'Sell'),
('ACC005', 'TypeB', 300.0, 350.0, 100.5, 101.0, 'Benchmark2', NOW(), 'Commentaire 5', 'SEC127', 'APPROVED', 'TraderY', 'Book2', 'Admin', NOW(), 'Admin', NOW(), 'Deal005', 'Option', 'SRC005', 'Buy'),
('ACC006', 'TypeC', 800.0, 750.0, 96.0, 96.5, 'Benchmark3', NOW(), 'Commentaire 6', 'SEC128', 'NEW', 'TraderZ', 'Book3', 'Admin', NOW(), 'User1', NOW(), 'Deal006', 'Future', 'SRC006', 'Sell'),
('ACC007', 'TypeA', 1200.0, 1250.0, 99.0, 99.5, 'Benchmark1', NOW(), 'Commentaire 7', 'SEC129', 'APPROVED', 'TraderX', 'Book1', 'User2', NOW(), 'User3', NOW(), 'Deal007', 'Swap', 'SRC007', 'Buy'),
('ACC008', 'TypeB', 400.0, 420.0, 101.5, 102.5, 'Benchmark2', NOW(), 'Commentaire 8', 'SEC130', 'REJECTED', 'TraderY', 'Book2', 'Admin', NOW(), 'Admin', NOW(), 'Deal008', 'Option', 'SRC008', 'Sell'),
('ACC009', 'TypeC', 1800.0, 1850.0, 97.0, 97.5, 'Benchmark3', NOW(), 'Commentaire 9', 'SEC131', 'NEW', 'TraderZ', 'Book3', 'User1', NOW(), 'User2', NOW(), 'Deal009', 'Future', 'SRC009', 'Buy'),
('ACC010', 'TypeA', 100.0, 110.0, 98.5, 99.0, 'Benchmark1', NOW(), 'Commentaire 10', 'SEC132', 'APPROVED', 'TraderX', 'Book1', 'User2', NOW(), 'User3', NOW(), 'Deal010', 'Swap', 'SRC010', 'Sell');

