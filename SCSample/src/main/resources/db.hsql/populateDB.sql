insert into user(id, version, date_created, date_modified, first_name, last_name, password, status, username) values ('1', 1, CAST ('2013-02-20 21:30:15.786' AS TIMESTAMP), CAST ('2012-02-20 21:30:15.786' AS TIMESTAMP),'Guido', 'Soeldner','test0815',0,'gsoeldner')
insert into user(id, version, date_created, date_modified, first_name, last_name, password, status, username) values ('2', 1, CAST ('2013-02-20 21:30:15.786' AS TIMESTAMP), CAST ('2012-02-20 21:30:15.786' AS TIMESTAMP),'Jens', 'Soeldner','test0815',0,'jsoeldner')
insert into user(id, version, date_created, date_modified, first_name, last_name, password, status, username) values ('3', 1, CAST ('2013-02-20 21:30:15.786' AS TIMESTAMP), CAST ('2012-02-20 21:30:15.786' AS TIMESTAMP),'Constantin', 'Soeldner','test0815',0,'csoeldner')
insert into user(id, version, date_created, date_modified, first_name, last_name, password, status, username) values ('4', 1, CAST ('2013-02-20 21:30:15.786' AS TIMESTAMP), CAST ('2012-02-20 21:30:15.786' AS TIMESTAMP),'Tassilo', 'Soeldner','test0815',0,'tsoeldner')



insert into role(id, version, name) values ('1', 1, 'manager')

insert into permission(id, version, name) values ('1', 1, 'PERM_READ')

insert into role_permissions(role_id, permission_id) values ('1','1')

insert into user_role (user_id, role_id) values ('1', '1')