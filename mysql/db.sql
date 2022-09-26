drop table `Admin`;
drop table `Class`;

CREATE TABLE `Admin` (
  `Username` varchar(10),
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`Username`)
);

CREATE TABLE `Class` (
  `Student` varchar(50),
  `grade` int,
  PRIMARY KEY (`Student`)
);

insert into Admin values ('Aseel', 'aseel123');