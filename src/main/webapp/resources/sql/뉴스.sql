drop table tbl_news;

create table tbl_news (
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    href varchar2(300) not null,
    img varchar2(500),
    regdate varchar2(100) not null
);

commit;