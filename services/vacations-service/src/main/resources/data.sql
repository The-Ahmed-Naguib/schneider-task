CREATE TABLE vacation_type
(
    type varchar(255) primary key not null
        check (type in ('SICK', 'ANNUAL'))
);

insert into vacation_type(type)
values ('SICK');
insert into vacation_type(type)
values ('ANNUAL');


CREATE TABLE employee
(
    id                      bigint auto_increment primary key not null,
    name                    varchar(255)                      not null,
    annual_vacation_balance int default 21                    not null,
    sick_vacation_balance   int default 15                    not null
);
insert into employee(name)
values ('Naguib');
insert into employee(name)
values ('Another Employee');

CREATE TABLE vacation
(
    id                  bigint auto_increment primary key not null,
    employee_id         bigint                            not null,
    vacation_type_id    varchar                               not null,
    vacation_start_date date                              not null,
    vacation_end_date   date                              not null,
    number_of_days      int                               not null,
    foreign key (employee_id) references employee (id),
    foreign key (vacation_type_id) references vacation_type (type)
);

insert into vacation(employee_id, vacation_type_id, vacation_start_date, vacation_end_date, number_of_days)
values (1,'SICK',CURRENT_DATE() -7,CURRENT_DATE() -5,3);

insert into vacation(employee_id, vacation_type_id, vacation_start_date, vacation_end_date, number_of_days)
values (2,'ANNUAL',CURRENT_DATE() -6,CURRENT_DATE() -6,1);

CREATE TABLE weekends_holidays
(
    id       bigint auto_increment primary key not null,
    day_date date                              not null
);


insert into weekends_holidays(day_date)
values ('2022-09-23');


insert into weekends_holidays(day_date)
values ('2022-09-24');

insert into weekends_holidays(day_date)
values ('2022-09-30');

insert into weekends_holidays(day_date)
values ('2022-10-01');

insert into weekends_holidays(day_date)
values ('2022-10-07');

insert into weekends_holidays(day_date)
values ('2022-10-08');
