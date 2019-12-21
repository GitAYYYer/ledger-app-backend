drop table ledger."pool";
drop table ledger."pooluserxref";
drop table ledger."transactions";
drop table ledger."users"

CREATE TABLE ledger."pool" (
  "pool_id" serial,
  "pool_name" text,
  "pool_desc" text,
  PRIMARY KEY ("pool_id")
);

CREATE TABLE ledger."users" (
  "user_id" text,
  "pswd" text,
  "first_name" text,
  "last_name" text,
  PRIMARY KEY ("user_id")
);

CREATE TABLE ledger."pooluserxref" (
  "pool_id" integer references ledger.pool(pool_id),
  "user_id" text references ledger.users(user_id),
  primary key ("pool_id", "user_id")
);

CREATE TABLE ledger."transactions" (
  "transaction_id" integer,
  "pool_id" integer references ledger.pool(pool_id),
  "payee_id" text references ledger.users(user_id),
  "owee_id" text references ledger.users(user_id),
  "pay_amount" decimal,
  "payee_vrfy" boolean,
  "owee_vrfy" boolean,
  PRIMARY KEY ("transaction_id")
);



