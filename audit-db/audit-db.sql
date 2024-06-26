--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-06-26 15:28:04

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
-- TOC entry 4860 (class 1262 OID 17051)
-- Name: audit_reportDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "audit_reportDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "audit_reportDB" OWNER TO postgres;

\connect "audit_reportDB"

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
-- TOC entry 6 (class 2615 OID 17052)
-- Name: audit; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA audit;


ALTER SCHEMA audit OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 17771)
-- Name: audit; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE audit.audit (
    dt_create timestamp(6) without time zone NOT NULL,
    user_id uuid NOT NULL,
    uuid uuid NOT NULL,
    essence_id character varying(255),
    text character varying(255) NOT NULL,
    type character varying(255) NOT NULL,
    CONSTRAINT audit_type_check CHECK (((type)::text = ANY ((ARRAY['USER'::character varying, 'REPORT'::character varying])::text[])))
);


ALTER TABLE audit.audit OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17781)
-- Name: report; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE audit.report (
    from_ date,
    to_ date,
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    uuid uuid NOT NULL,
    description character varying(255),
    report_type character varying(255),
    status character varying(255),
    users_id character varying(255)[],
    CONSTRAINT report_report_type_check CHECK (((report_type)::text = 'JOURNAL_AUDIT'::text)),
    CONSTRAINT report_status_check CHECK (((status)::text = ANY ((ARRAY['LOADED'::character varying, 'PROGRESS'::character varying, 'ERROR'::character varying, 'DONE'::character varying, 'REPORT_STATUS'::character varying])::text[])))
);


ALTER TABLE audit.report OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17792)
-- Name: user; Type: TABLE; Schema: audit; Owner: postgres
--

CREATE TABLE audit."user" (
    uuid uuid NOT NULL,
    fio character varying(255),
    mail character varying(255),
    role character varying(255),
    CONSTRAINT user_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'USER'::character varying, 'MANAGER'::character varying, 'SYSTEM'::character varying])::text[])))
);


ALTER TABLE audit."user" OWNER TO postgres;

--
-- TOC entry 4852 (class 0 OID 17771)
-- Dependencies: 216
-- Data for Name: audit; Type: TABLE DATA; Schema: audit; Owner: postgres
--

INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:33:25.461369', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'ecc0582e-fe0d-4dfa-a5e5-7e7290ff8c6b', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:36:39.733945', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', '05182e56-734f-41e8-8af1-277230733fb8', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:37:05.926892', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', '60d0dc88-aee9-4276-92ee-24e49d376837', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:37:06.652502', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', '232261c6-aa82-4442-b621-1e6909b04f78', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:37:07.559229', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', '535d6773-02cb-40c8-9b12-98115a1a41b8', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:37:08.898316', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', '87a41e65-67be-4ec1-b83b-281bc389f42c', 'e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:45:57.912997', '30d14098-926f-4eff-9260-2ddb4827eff3', '218f4269-53a3-4aa3-bb0f-4c2a944654e1', '30d14098-926f-4eff-9260-2ddb4827eff3', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:46:00.276936', '30d14098-926f-4eff-9260-2ddb4827eff3', 'ee4c085e-c6b7-46d1-8c7f-f01c17dcf05d', '30d14098-926f-4eff-9260-2ddb4827eff3', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-10 23:46:01.180369', '30d14098-926f-4eff-9260-2ddb4827eff3', 'fbc2154d-222f-4734-bb82-4528ea4d8172', '30d14098-926f-4eff-9260-2ddb4827eff3', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 15:46:05.373456', '30d14098-926f-4eff-9260-2ddb4827eff3', 'f528d6ac-8272-4d56-ae34-7b9aef9950f8', '30d14098-926f-4eff-9260-2ddb4827eff3', 'SUCCESS: Login', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 15:56:23.922716', '15684970-1339-4c3b-ad33-572e6dfd46ae', '0b43dc0e-d8d4-4d1e-83b0-18e7d07159bc', '15684970-1339-4c3b-ad33-572e6dfd46ae', 'SUCCESS: Getting personal info', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 15:56:37.564947', '15684970-1339-4c3b-ad33-572e6dfd46ae', 'ec3e5707-5bf5-49ca-9317-0c34df2393ab', '15684970-1339-4c3b-ad33-572e6dfd46ae', 'SUCCESS: Add user', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 15:56:53.66508', '15684970-1339-4c3b-ad33-572e6dfd46ae', '09dc8d54-1532-4568-b5ae-f2f73679ac49', '15684970-1339-4c3b-ad33-572e6dfd46ae', 'SUCCESS: Get users', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 15:57:54.481032', '15684970-1339-4c3b-ad33-572e6dfd46ae', '4d6b5bdd-b1c0-40e3-9feb-ec7df1288d67', '15684970-1339-4c3b-ad33-572e6dfd46ae', 'FAILED: Update user', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 15:57:57.113475', '15684970-1339-4c3b-ad33-572e6dfd46ae', '24c27438-140c-4a31-bb03-4ab74fae8e38', '15684970-1339-4c3b-ad33-572e6dfd46ae', 'FAILED: Update user', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:06:53.785119', '41755ed9-e098-4e6a-b99a-8d41fa231e52', '8181452e-5418-43d9-8439-0460a7e0777f', '41755ed9-e098-4e6a-b99a-8d41fa231e52', 'SUCCESS: Getting personal info', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:13:51.504952', '8aa49d3c-42df-498c-90e5-eb515813b37b', '531455d0-7ff4-4a95-9a8f-3df4dc1a5a8c', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Getting personal info', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:16:50.688339', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'ec3078ef-f35a-4d02-867d-97ce1b449075', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Get users', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:17:17.294946', '8aa49d3c-42df-498c-90e5-eb515813b37b', '715cdabe-b3e6-4142-b6f6-2cd94f97b5b5', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Add user', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:18:24.62796', '8aa49d3c-42df-498c-90e5-eb515813b37b', '33b2c67d-5a96-455b-b535-0374d7e94e4b', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Get users', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:19:24.76949', '8aa49d3c-42df-498c-90e5-eb515813b37b', '1cc80a34-23de-4d46-a0c1-0b2a71389356', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'FAILED: Update user', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:19:40.017562', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'd667654e-6a7e-4b9c-a999-22131144034c', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Get users', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:19:51.41209', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'b37652fd-3136-4912-a2fd-f85772a4ed4b', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Update user', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:20:30.258913', '8aa49d3c-42df-498c-90e5-eb515813b37b', '8822bfd4-dbed-41ab-aff2-7d5445d0fbc5', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'FAILED: Update user', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:20:35.774582', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'de1f36d1-71f3-4b81-bb4b-724c0dfe79b3', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Get users', 'USER');
INSERT INTO audit.audit (dt_create, user_id, uuid, essence_id, text, type) VALUES ('2024-02-12 19:20:49.003973', '8aa49d3c-42df-498c-90e5-eb515813b37b', '944948f9-1c02-4b1d-b45b-1c9223c3dc7a', '8aa49d3c-42df-498c-90e5-eb515813b37b', 'SUCCESS: Update user', 'USER');


--
-- TOC entry 4853 (class 0 OID 17781)
-- Dependencies: 217
-- Data for Name: report; Type: TABLE DATA; Schema: audit; Owner: postgres
--

INSERT INTO audit.report (from_, to_, dt_create, dt_update, uuid, description, report_type, status, users_id) VALUES ('2024-02-12', '2024-02-12', '2024-02-12 19:28:50.737429', '2024-02-12 19:28:50.737429', '73c57fc6-0ead-4655-a5c3-d47da435f7b3', 'FROM 2024-02-12 TO 2024-02-12', 'JOURNAL_AUDIT', 'DONE', '{e39098a1-ae67-4aca-8b83-515e760208ef}');
INSERT INTO audit.report (from_, to_, dt_create, dt_update, uuid, description, report_type, status, users_id) VALUES ('2024-02-10', '2024-02-10', '2024-02-10 23:44:00.50596', '2024-02-10 23:44:00.50596', '45507983-e19e-4068-a50f-c9485a142d6d', 'FROM 2024-02-10 TO 2024-02-10', 'JOURNAL_AUDIT', 'DONE', '{e6b05e84-314f-4c2b-9390-dfe386ad2c7f}');
INSERT INTO audit.report (from_, to_, dt_create, dt_update, uuid, description, report_type, status, users_id) VALUES ('2024-02-10', '2024-02-10', '2024-02-10 23:46:22.286461', '2024-02-10 23:46:22.287466', '4fa98078-5e00-4399-8dca-809aeba2e145', 'FROM 2024-02-10 TO 2024-02-10', 'JOURNAL_AUDIT', 'DONE', '{e6b05e84-314f-4c2b-9390-dfe386ad2c7f,30d14098-926f-4eff-9260-2ddb4827eff3}');


--
-- TOC entry 4854 (class 0 OID 17792)
-- Dependencies: 218
-- Data for Name: user; Type: TABLE DATA; Schema: audit; Owner: postgres
--

INSERT INTO audit."user" (uuid, fio, mail, role) VALUES ('e6b05e84-314f-4c2b-9390-dfe386ad2c7f', 'Миклаш Дмитрий Владиславович', 'markmiklash@gmail.com', 'USER');
INSERT INTO audit."user" (uuid, fio, mail, role) VALUES ('30d14098-926f-4eff-9260-2ddb4827eff3', 'Скачков Дмитрий Игоревич', 'borisovskiy.trak@gmail.com', 'USER');
INSERT INTO audit."user" (uuid, fio, mail, role) VALUES ('15684970-1339-4c3b-ad33-572e6dfd46ae', 'Миклаш Дмитрий Владиславович', 'markmiklash@gmail.com', 'ADMIN');
INSERT INTO audit."user" (uuid, fio, mail, role) VALUES ('41755ed9-e098-4e6a-b99a-8d41fa231e52', 'Миклаш Дмитрий Владиславович', 'markmiklash@gmail.com', 'USER');
INSERT INTO audit."user" (uuid, fio, mail, role) VALUES ('8aa49d3c-42df-498c-90e5-eb515813b37b', 'Миклаш Дмитрий Владиславович', 'markmiklashh@gmail.com', 'ADMIN');


--
-- TOC entry 4701 (class 2606 OID 17778)
-- Name: audit audit_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit.audit
    ADD CONSTRAINT audit_pkey PRIMARY KEY (uuid);


--
-- TOC entry 4703 (class 2606 OID 17789)
-- Name: report report_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (uuid);


--
-- TOC entry 4705 (class 2606 OID 17791)
-- Name: report report_users_id_from__to__key; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit.report
    ADD CONSTRAINT report_users_id_from__to__key UNIQUE (users_id, from_, to_);


--
-- TOC entry 4707 (class 2606 OID 17799)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (uuid);


--
-- TOC entry 4708 (class 2606 OID 17800)
-- Name: audit fkai50jigynukoeyjove8u90je9; Type: FK CONSTRAINT; Schema: audit; Owner: postgres
--

ALTER TABLE ONLY audit.audit
    ADD CONSTRAINT fkai50jigynukoeyjove8u90je9 FOREIGN KEY (user_id) REFERENCES audit."user"(uuid);


-- Completed on 2024-06-26 15:28:04

--
-- PostgreSQL database dump complete
--

