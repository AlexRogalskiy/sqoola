create table APP_LOGS(
    LOG_ID varchar(100) primary key,
    ENTRY_DATE timestamp,
    LOGGER varchar(100),
    LOG_LEVEL varchar(100),
    MESSAGE TEXT,
    EXCEPTION TEXT
)
create table logins_persistent(
	username varchar(50) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);
