--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-06-25 15:33:57

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4849 (class 1262 OID 16847)
-- Name: usersDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "usersDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "usersDB" OWNER TO postgres;

\connect "usersDB"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16857)
-- Name: users; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA users;


ALTER SCHEMA users OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 17738)
-- Name: mail_verifications; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.mail_verifications (
    uuid uuid NOT NULL,
    code character varying(255),
    mail character varying(255)
);


ALTER TABLE users.mail_verifications OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17745)
-- Name: users; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.users (
    dt_create timestamp(6) without time zone NOT NULL,
    dt_update timestamp(6) without time zone NOT NULL,
    uuid uuid NOT NULL,
    fio character varying(255) NOT NULL,
    mail character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    CONSTRAINT users_status_check CHECK (((status)::text = ANY ((ARRAY['WAITING_ACTIVATION'::character varying, 'ACTIVATED'::character varying, 'DEACTIVATED'::character varying])::text[])))
);


ALTER TABLE users.users OWNER TO postgres;

--
-- TOC entry 4842 (class 0 OID 17738)
-- Dependencies: 216
-- Data for Name: mail_verifications; Type: TABLE DATA; Schema: users; Owner: postgres
--

INSERT INTO users.mail_verifications (uuid, code, mail) VALUES ('f929d818-a105-4900-a3a3-cc89ec348325', '6063', 'markmiklash@gmail.com');
INSERT INTO users.mail_verifications (uuid, code, mail) VALUES ('af60b067-2ae5-4f20-9b33-7ccb9c31debc', '3792', 'markmiklash@gmail.com');
INSERT INTO users.mail_verifications (uuid, code, mail) VALUES ('af190220-29e5-4934-805b-9af7f1b57185', '8264', 'markmiklash@gmail.com');


--
-- TOC entry 4843 (class 0 OID 17745)
-- Dependencies: 217
-- Data for Name: users; Type: TABLE DATA; Schema: users; Owner: postgres
--

INSERT INTO users.users (dt_create, dt_update, uuid, fio, mail, password, role, status) VALUES ('2024-05-26 21:26:40.779', '2024-05-26 21:26:55.958272', '22541f35-9a6b-4139-8691-979a381562c9', 'Миклаш Марк Владиславович', 'markmiklash@gmail.com', '$2a$10$DS/sWUP9.fs36f/Ud9PvfOAKqOKvLzaX4pNap..M8OhsB9nw8KPhe', 'USER', 'ACTIVATED');


--
-- TOC entry 4694 (class 2606 OID 17744)
-- Name: mail_verifications mail_verifications_pkey; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.mail_verifications
    ADD CONSTRAINT mail_verifications_pkey PRIMARY KEY (uuid);


--
-- TOC entry 4696 (class 2606 OID 17755)
-- Name: users users_mail_key; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT users_mail_key UNIQUE (mail);


--
-- TOC entry 4698 (class 2606 OID 17753)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uuid);


-- Completed on 2024-06-25 15:33:58

--
-- PostgreSQL database dump complete
--

