create materialized view books_per_author as
select a.author_id as author_id,
       count(b.book_id) as num_books
from author a
    left join book b on a.author_id = b.author_id
group by a.author_id;

create materialized view authors_per_country as
select c.country_id as country_id,
       count(a.author_id) as num_authors
from country c
         left join author a on c.country_id = a.country_id
group by c.country_id;