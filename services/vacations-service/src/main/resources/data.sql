CREATE TABLE vacation_type
(
    id   int primary key not null,
    name varchar(255)
);

CREATE TABLE employee
(
    id   bigint auto_increment primary key not null,
    name varchar(255) not null,
    vacation_balance int default 21 not null
);

CREATE TABLE vacation
(
    id   bigint auto_increment primary key not null,
    employee_id bigint default 21 not null,
    vacation_start_day  date  not null,
    vacation_end_day  date  not null,
    foreign key (employee_id) references employee(id)
);

CREATE TABLE weekends_holidays
(
    id   bigint auto_increment primary key not null,
    day_date date not null
);

insert into vacation_type(id, name) values('1','Annual');
insert into vacation_type(id, name) values('2','Sick');
insert into weekends_holidays(day_date) values(CURRENT_DATE()+1);
insert into employee( name) values('Naguib');