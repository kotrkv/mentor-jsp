create database mentor
    with owner admin;

create table jsp_project.users
(
    id       serial      not null
        constraint users_pk
            primary key,
    login    varchar(50) not null,
    password varchar(50) not null,
    email    varchar(50) not null,
    birthday date        not null
);

alter table jsp_project.users
    owner to admin;

create unique index users_email_uindex
    on jsp_project.users (email);

create unique index users_id_uindex
    on jsp_project.users (id);

create unique index users_login_uindex
    on jsp_project.users (login);

create unique index users_password_uindex
    on jsp_project.users (password);

