CREATE TABLE ledger."pool" (
  "pool_name" text,
  "pool_desc" text,
  PRIMARY KEY ("pool_name")
);

CREATE TABLE ledger."users" (
  "user_id" text,
  "pswd" text,
  "first_name" text,
  "last_name" text,
  PRIMARY KEY ("user_id")
);

CREATE TABLE ledger."pooluserxref" (
  "pool_name" text references ledger.pool(pool_name),
  "user_id" text references ledger.users(user_id),
  primary key ("pool_name", "user_id")
);

CREATE TABLE ledger."transactions" (
  "transaction_id" integer,
  "pool_name" text references ledger.pool(pool_name),
  "payee_id" text references ledger.users(user_id),
  "owee_id" text references ledger.users(user_id),
  "pay_amount" decimal,
  "payee_vrfy" boolean,
  "owee_vrfy" boolean,
  PRIMARY KEY ("transaction_id")
);



