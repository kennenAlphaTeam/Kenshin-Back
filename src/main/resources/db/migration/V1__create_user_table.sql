drop schema if exists genshin cascade;
create schema genshin;

create table genshin.user (
    id bigint generated always as identity,
    oauth_id varchar(255) not null,
    oauth_type varchar(255) not null,
    mihoyo_cookie varchar null,
    mihoyo_id varchar(10) null,
    genshin_uid varchar(10) null,
    nickname varchar(255) null,
    create_at timestamp not null,
    modified_at timestamp not null,
    primary key (id)
);