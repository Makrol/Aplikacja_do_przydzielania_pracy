drop table users cascade constraints;
drop table tasks cascade constraints;
drop table user_task cascade constraints;
drop table seq_num;
drop sequence user_seq;
drop sequence task_seq;
drop sequence user_task_seq;
create table users(
                      id numeric(4,0) primary key,
                      admin numeric(1,0),
                      name VARCHAR2(20),
                      surname VARCHAR2(20),
                      login VARCHAR2(20),
                      password varchar2(40)
);
create table tasks(
                      id numeric(6,0) primary key,
                      title varchar2(30),
                      description varchar2(250)
);
create table user_task(
                          id numeric(6,0),
                          user_id numeric(4,0),
                          task_id numeric(6,0),
                          foreign key (user_id) references users(id),
                          foreign key (task_id) references tasks(id)
);

create table seq_num(
    max_task Numeric
);
insert into seq_num values (0);

insert into users values (1,1,'jan','kowalski','admin','admin');
insert into users values (2,0,'piotr','nowak','piotr','piotr');
insert into users values (3,0,'adam','szewczyk','adam','adam');

create sequence user_seq start with 4;

create sequence task_seq start with 1;

create sequence user_task_seq start with 1;