--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-03-02 22:46:25

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

DROP DATABASE "VodDB";
--
-- TOC entry 3334 (class 1262 OID 16394)
-- Name: VodDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "VodDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';


ALTER DATABASE "VodDB" OWNER TO postgres;

\connect "VodDB"

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16396)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    user_id text NOT NULL,
    password text
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16415)
-- Name: movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movies (
    title text NOT NULL,
    category text[],
    year numeric,
    actors text[],
    is_available boolean,
    popularity numeric,
    running_time numeric,
    price numeric,
    listening_users text[]
);


ALTER TABLE public.movies OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16472)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id integer NOT NULL,
    user_id text,
    movie_name text,
    total_payment numeric,
    time_order_made text
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16471)
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_order_id_seq OWNER TO postgres;

--
-- TOC entry 3335 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


--
-- TOC entry 213 (class 1259 OID 16484)
-- Name: user_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_details (
    user_id text NOT NULL,
    name text,
    email text,
    phone_number text,
    favorites_movies text[]
);


ALTER TABLE public.user_details OWNER TO postgres;

--
-- TOC entry 3176 (class 2604 OID 16475)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- TOC entry 3324 (class 0 OID 16396)
-- Dependencies: 209
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.accounts (user_id, password) VALUES ('admin', 'root');
INSERT INTO public.accounts (user_id, password) VALUES ('user1', '123456');


--
-- TOC entry 3325 (class 0 OID 16415)
-- Dependencies: 210
-- Data for Name: movies; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('Titanic', '{Drama,Romance}', 1997, '{"''Leonardo DiCaprio''","''Kate Winslet''","''Billy Zane''","''Kathy Bates''","''Gloria Stuart''"}', true, 0, 194, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('The Sixth Sense', '{Drama,Mystery,Thriller}', 1999, '{"''Bruce Willis''","''Haley Joel Osment''","''Toni Collette''"}', true, 0, 107, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('The Others', '{Horror,Mystery,Thriller}', 2001, '{"''Nicole Kidman''","''Christopher Eccleston''","''Alakina Mann''","''Fionnula Flanagan''","''James Bentley''","''Eric Sykes''"}', true, 0, 60, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('The Invisible Guest', '{Crime,Drama,Mystery}', 2016, '{"''Mario Casas''","''Ana Wagener''","''Jose Coronado''","''Barbara Lennie''"}', true, 0, 106, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('Spiderman No Way Home', '{Action,Adventure,Fantasy}', 2019, '{"''Tom Holland''",''Zendaya'',"''Benedict Cumberbatch''"}', true, 0, 148, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('Joker', '{Crime,Drama,Thriller}', 2019, '{"''Joaquin Phoenix''","''Robert De Niro''","''Zazie Beetz''"}', true, 0, 122, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('We''re the Millers', '{Comedy}', 2013, '{"''Jennifer Aniston''","''Jason Sudeikis''","''Ed Helms''","''Emma Roberts''","''Will Poulter''"}', false, 0, 110, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('Me Before You', '{Drama,Romance}', 2016, '{"''Emilia Clarke''","''Sam Claflin''","''Janet McTeer''","''Charles Dance''","''Brendan Coyle''"}', false, 0, 106, 25, '{}');
INSERT INTO public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) VALUES ('Hitch', '{Comedy,Romance}', 2005, '{"''Eva Mendes''","''Will Smith''","''Amber Valletta''","''Kevin James''"}', false, 0, 118, 25, '{}');


--
-- TOC entry 3327 (class 0 OID 16472)
-- Dependencies: 212
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (order_id, user_id, movie_name, total_payment, time_order_made) VALUES (3, 'user1', 'Titanic', 25, '02.03.2022 18:50');


--
-- TOC entry 3328 (class 0 OID 16484)
-- Dependencies: 213
-- Data for Name: user_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_details (user_id, name, email, phone_number, favorites_movies) VALUES ('user1', 'Shaked', 'shaked@gmail.com', '0544285182', '{}');


--
-- TOC entry 3336 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 3, true);


--
-- TOC entry 3178 (class 2606 OID 16492)
-- Name: accounts Accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT "Accounts_pkey" PRIMARY KEY (user_id);


--
-- TOC entry 3180 (class 2606 OID 16421)
-- Name: movies movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (title);


--
-- TOC entry 3182 (class 2606 OID 16479)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 3184 (class 2606 OID 16490)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (user_id);


-- Completed on 2022-03-02 22:46:25

--
-- PostgreSQL database dump complete
--

