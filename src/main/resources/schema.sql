create table T_AUTHOR (
    id identity,
    name varchar(50) not null,
    bio varchar(2000) not null,
    twitter_name varchar(20) not null
);

create table T_BOOK (
    id identity,
    title varchar(100) not null,
    author_id integer not null,
    foreign key (author_id) references T_AUTHOR(id)
);