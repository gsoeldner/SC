

    drop table permission if exists;

    drop table role if exists;

    drop table role_permissions if exists;

    drop table user if exists;

    drop table user_role if exists;

    create table permission (
        id varchar(255) not null,
        version integer,
        name varchar(255),
        primary key (id)
    );

    create table role (
        id varchar(255) not null,
        version integer,
        name varchar(255),
        primary key (id)
    );

    create table role_permissions (
        role_id varchar(255) not null,
        permission_id varchar(255) not null,
        primary key (role_id, permission_id)
    );

    create table user (
        id varchar(255) not null,
        version integer,
        date_created timestamp,
        date_modified timestamp,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        status integer,
        username varchar(255),
        primary key (id)
    );

    create table user_role (
        user_id varchar(255) not null,
        role_id varchar(255) not null,
        primary key (user_id, role_id)
    );

    alter table role_permissions 
        add constraint FKEAD9D23BC5364601 
        foreign key (permission_id) 
        references permission;

    alter table role_permissions 
        add constraint FKEAD9D23B1776B9E1 
        foreign key (role_id) 
        references role;

    alter table user_role 
        add constraint FK143BF46A1776B9E1 
        foreign key (role_id) 
        references role;

    alter table user_role 
        add constraint FK143BF46ABCA17DC1 
        foreign key (user_id) 
        references user;
