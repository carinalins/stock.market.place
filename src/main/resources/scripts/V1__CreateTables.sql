create table companie (id  serial not null, dt_add timestamp, dt_delete timestamp, dt_update timestamp, share_prices float8, shares int4 check (shares>=100 AND shares<=500), primary key (id));
create table investor (id  serial not null, dt_add timestamp, dt_delete timestamp, dt_update timestamp, budget float8, primary key (id));
create table trade (id  serial not null, dt_add timestamp, dt_delete timestamp, dt_update timestamp, dt_transaction date, id_companie int4 not null, id_investor int4 not null, primary key (id));
alter table if exists trade add constraint id_companie foreign key (id_companie) references companie;
alter table if exists trade add constraint id_investor foreign key (id_investor) references investor;
ALTER TABLE investor ADD COLUMN name VARCHAR;
ALTER TABLE companie ADD COLUMN name VARCHAR;