-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER pokemon_project_owner
WITH PASSWORD 'pokemon';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO pokemon_project_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO pokemon_project_owner;

CREATE USER pokemon_project_appuser
WITH PASSWORD 'pokemon';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO pokemon_project_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO pokemon_project_appuser;
