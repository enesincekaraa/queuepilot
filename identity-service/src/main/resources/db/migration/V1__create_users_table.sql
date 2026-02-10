create table users(
    id UUID PRIMARY KEY,
    email varchar(255) not null unique,
    password_hash varchar(255) not null,
    status varchar(20) not null,
    created_at timestamptz not null
);

