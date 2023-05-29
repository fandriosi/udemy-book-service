CREATE TABLE book(
  id bigint PRIMARY KEY,
  author varchar(255),
  launch_date timestamp NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(255)
);

create sequence book_sq increment by 1 start with 1;