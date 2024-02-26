-- 1. The titles and release dates of movies that Tom Hanks has appeared in. 
-- Order the results by release date, newest to oldest.
-- (47 rows)
SELECT m.title, m.release_date
FROM movie m
INNER JOIN movie_actor ma ON m.movie_id = ma.movie_id
INNER JOIN person pn on ma.actor_id = pn.person_id
WHERE person_name = 'Tom Hanks'
ORDER BY m.release_date DESC;

