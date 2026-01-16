CREATE TABLE SPECIES(
    monId INTEGER PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    canBeFemale BOOLEAN NOT NULL,
    canBeMale BOOLEAN NOT NULL,
    group1Id INTEGER NOT NULL,
    group2Id INTEGER
);

CREATE TABLE MOVES(
    moveId INTEGER PRIMARY KEY,
    name VARCHAR(20) NOT NULL -- TODO: consider adding description, type, power, accuracy, pp, etc.
);

CREATE TABLE LEARNSETS(
    monId INTEGER NOT NULL,
    moveId INTEGER NOT NULL,
    level INTEGER,  -- superfluous?
    eggMove BOOLEAN NOT NULL,
    PRIMARY KEY (monId, moveId),
    FOREIGN KEY (monId) REFERENCES SPECIES(monId),
    FOREIGN KEY (moveId) REFERENCES MOVES(moveId)
);

CREATE TABLE EGGGROUPS(
    groupId INTEGER PRIMARY KEY,
    name varchar(10) NOT NULL
);

CREATE TABLE COMPATIBILITY(
    group1Id INTEGER NOT NULL,
    group2Id INTEGER NOT NULL
);