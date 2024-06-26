--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-06-26 13:20:22

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
-- TOC entry 4839 (class 1262 OID 17013)
-- Name: mailDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "mailDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';


ALTER DATABASE "mailDB" OWNER TO postgres;

\connect "mailDB"

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
-- TOC entry 6 (class 2615 OID 17014)
-- Name: verification; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA verification;


ALTER SCHEMA verification OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 17015)
-- Name: mail_queue; Type: TABLE; Schema: verification; Owner: postgres
--

CREATE TABLE verification.mail_queue (
    send_flag boolean NOT NULL,
    uuid uuid NOT NULL,
    code character varying(255) NOT NULL,
    mail character varying(255) NOT NULL
);


ALTER TABLE verification.mail_queue OWNER TO postgres;

--
-- TOC entry 4833 (class 0 OID 17015)
-- Dependencies: 216
-- Data for Name: mail_queue; Type: TABLE DATA; Schema: verification; Owner: postgres
--

INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '0d77a633-e83a-4b79-b734-4b761a980fe2', '5306', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'c20dc7b5-2880-46a0-8b75-6bdd88bed189', '1393', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'f370a119-b64a-4cc2-a238-64cd6abd7d28', '7197', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '58cff3c4-f4ce-4216-a278-f810876df3c4', '7493', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '363e9106-0cfa-4520-bfd7-1fa48a14f1a3', '2684', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'a2a7b6a1-f9f9-45d2-8c2d-eaed97fa8ce5', '6925', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'c922814a-8b46-4569-9b45-88f27f7eeb5d', '5457', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '63fadd3e-1598-401a-9d01-ed72ac61aaf5', '8097', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'bd6c9b69-7b3e-466f-837f-7c99742cd65b', '2437', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'b630e08d-4b7e-461d-a2c2-017f5d7c21c1', '3875', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '51e0bddb-88e4-423d-a593-39fa04a1d402', '7610', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'e8390507-695e-4b93-9a1f-b61e790cf9e6', '4543', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '09c33901-4f90-4e2c-ae8f-f219002b1fc8', '5604', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'ad0d015e-3be1-43e7-adcb-d7d1666541da', '4952', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '201f149b-2ea2-410c-8c22-9faafbd37921', '1324', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'ff0a5a93-78a7-4f58-9718-54bd77012ca4', '5656', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '6928fe25-a157-4b76-82cb-a5d4119db329', '1351', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'b951597c-9abb-4701-8259-0fca0a249468', '3987', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'ffcb4f6e-d4ce-45f2-ac91-72f3abc6a68b', '7902', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'ca0a0d8d-4849-4223-b1cf-608dfb296a8b', '1415', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '50f1ae76-7928-4c82-83cf-92d54e35db23', '6925', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '1548ed7b-392b-4a0f-9ce0-8778a055a568', '3714', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '47532e18-6a36-4a70-810d-55b8c757757d', '4557', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '84ddbabe-8809-4362-8c7a-c6a93c16f933', '8422', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '1c593682-9700-46d5-b3f1-7473ccd3d189', '4805', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'f5fcaec7-dc61-4ecf-bf1a-a0b11e7ef901', '2790', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '6cea6ef9-fa9d-4f55-b787-26ae7078e8e0', '2243', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '6944b9c5-c491-4489-9a82-8bc5a2d4e06a', '4571', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'db347365-453e-4417-be83-f66a4376c064', '4062', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '33215992-15e0-4fa8-aa0a-131a30cf105d', '7682', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '3e08c45b-e46c-4c3f-a94c-cbe89e6fa5a0', '7190', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '6549217f-7dfd-4706-bff8-4f2948af492f', '3085', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '43bdbca7-cc9a-4c1a-8a75-72eae7a4fabd', '7421', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '469b93b3-6156-4e96-bf86-f882555af0c5', '5140', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'a1bc866f-ac3b-4b95-9cc2-7018064ce67d', '7458', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'ef0f15a8-4657-46ac-b861-f5f2990d8917', '7018', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '74ceb46a-9c08-46a0-b11b-f195a23c4567', '4749', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '5a6ac6ab-99ad-4901-bcdf-b52efebd5380', '5737', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'e2538523-83c5-4131-be5f-12301ccd49e4', '8408', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '32f683f0-8d87-4b27-bd0d-c66c49604398', '3103', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'f5d4bf47-2387-4f31-8293-445c01dcf3bb', '2577', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'a4a7ba67-0d49-4ea9-bb3b-cc3a2cfb93cc', '4669', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '0d92d75c-9d9f-4e76-8f83-08ba922b35bb', '5069', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '14863e7e-4722-453d-9c9f-8a09dcf7bd23', '3486', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '078aca61-58dc-4e3f-b834-e11a022ec503', '6329', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '563035c3-ecc1-4a3c-aca9-7f4cdd1bce49', '4117', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '17c30039-386b-4184-84de-465ad35795d8', '4756', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'd7fedd8e-c134-44a3-9c07-828d726bd96c', '9300', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'aed750ff-02e8-475f-b71a-f1a32c569944', '4516', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'd2023239-fd12-4c9e-b8d6-6b8d9a71155b', '4159', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'a43721be-60ae-495e-b234-f97bd4f64d71', '7147', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '8766dbe2-2ffa-4cb5-beba-46bd74ea019d', '2155', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'cc463068-0af8-4fe8-b8ca-b65591eb7841', '8515', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'd2ce05cb-7738-4b18-8b5a-f559f9e62529', '8017', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '203759dc-9f28-47a1-ac8e-a922f9d43729', '2257', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'c3658c10-0e06-4b62-af38-839f6d7bbef9', '9899', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'b6353968-42fa-45c7-8550-27cb4368106d', '3357', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'd8591537-9270-4e5b-8289-ef746817f187', '2388', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '3d29c5d4-951e-4ab7-96ce-598d6e614d07', '6074', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'accf76d1-3b65-4f2e-8844-9ae9c50c0263', '6990', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '98fd26e1-1ce8-45e1-855d-e241ebab3bc0', '8495', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '43befbde-fedd-4b33-96c3-a4a892dfe4ab', '5324', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'f0cf713c-cbac-42f2-a81a-4dc00648417f', '2990', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'e78700d3-c7c0-4aa5-a9a2-829b2ac5d08b', '7927', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '5d93948e-e635-4f0e-99b3-e247a4df7e73', '4184', 'markmiklashh@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'e8d7783e-e98d-48d5-a8a1-81a538c03d8d', '2356', 'markmiklashh@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '917342d8-ca4a-4444-b185-db887d09d1ce', '9880', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '755391de-969e-4c6a-a30a-cab879193825', '6063', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '71438567-2298-4168-806d-231ad2a1f7a7', '3792', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, 'ffad0557-dc26-440b-9e08-d4077613c64a', '8148', 'markmiklash@gmail.com');
INSERT INTO verification.mail_queue (send_flag, uuid, code, mail) VALUES (true, '6a43418d-d59d-4470-8080-437e0e6bae22', '8264', 'markmiklash@gmail.com');


--
-- TOC entry 4689 (class 2606 OID 17021)
-- Name: mail_queue mail_queue_pkey; Type: CONSTRAINT; Schema: verification; Owner: postgres
--

ALTER TABLE ONLY verification.mail_queue
    ADD CONSTRAINT mail_queue_pkey PRIMARY KEY (uuid);


-- Completed on 2024-06-26 13:20:22

--
-- PostgreSQL database dump complete
--

