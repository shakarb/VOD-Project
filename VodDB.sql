--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-03-08 10:34:54

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
    password text,
    is_admin boolean DEFAULT false,
    is_active boolean,
    name text
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
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


--
-- TOC entry 214 (class 1259 OID 16558)
-- Name: statistics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.statistics (
    login_nr numeric,
    movies_nr numeric,
    orders_nr numeric
);


ALTER TABLE public.statistics OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16484)
-- Name: user_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_details (
    user_id text NOT NULL,
    name text,
    email text,
    phone_number text,
    wishlist text[],
    is_wishlist_updated text,
    credit_card text
);


ALTER TABLE public.user_details OWNER TO postgres;

--
-- TOC entry 3181 (class 2604 OID 16475)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- TOC entry 3329 (class 0 OID 16396)
-- Dependencies: 209
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.accounts (user_id, password, is_admin, is_active, name) FROM stdin;
123456	123456	f	f	\N
123	admin	t	f	kaminka
user	123456	f	f	Shaked
\.


--
-- TOC entry 3330 (class 0 OID 16415)
-- Dependencies: 210
-- Data for Name: movies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.movies (title, category, year, actors, is_available, popularity, running_time, price, listening_users) FROM stdin;
The Invisible Guest	{Crime,Drama,Mystery}	2016	{"'Mario Casas'","'Ana Wagener'","'Jose Coronado'","'Barbara Lennie'"}	t	0	106	25	{}
Spiderman No Way Home	{Action,Adventure,Fantasy}	2019	{"'Tom Holland'",'Zendaya',"'Benedict Cumberbatch'"}	t	0	148	25	{}
Titanic	{Drama,Romance}	1997	{"'Leonardo DiCaprio'","'Kate Winslet'","'Billy Zane'","'Kathy Bates'","'Gloria Stuart'"}	t	0	194	25	{user}
Hitch	{Comedy,Romance}	2005	{"'Eva Mendes'","'Will Smith'","'Amber Valletta'","'Kevin James'"}	t	0	118	25	{123456}
We're the Millers	{Comedy}	2013	{"'Jennifer Aniston'","'Jason Sudeikis'","'Ed Helms'","'Emma Roberts'","'Will Poulter'"}	t	0	110	25	{user}
The Sixth Sense	{Drama,Mystery,Thriller}	1999	{"'Bruce Willis'","'Haley Joel Osment'","'Toni Collette'"}	f	0	107	25	{}
The Others	{Horror,Mystery,Thriller}	2001	{"'Nicole Kidman'","'Christopher Eccleston'","'Alakina Mann'","'Fionnula Flanagan'","'James Bentley'","'Eric Sykes'"}	f	0	60	25	{}
Joker	{Crime,Drama,Thriller}	2019	{"'Joaquin Phoenix'","'Robert De Niro'","'Zazie Beetz'"}	f	0	122	25	{}
Me Before You	{Drama,Romance}	2016	{"'Emilia Clarke'","'Sam Claflin'","'Janet McTeer'","'Charles Dance'","'Brendan Coyle'"}	f	0	106	25	{user}
\.


--
-- TOC entry 3332 (class 0 OID 16472)
-- Dependencies: 212
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (order_id, user_id, movie_name, total_payment, time_order_made) FROM stdin;
4	123456	Titanic	0	07.03.2022 23:18
5	123456	The Sixth Sense	0	07.03.2022 23:18
6	123456	The Others	0	07.03.2022 23:18
7	123456	The Invisible Guest	0	07.03.2022 23:18
8	123456	Spiderman No Way Home	0	07.03.2022 23:18
9	123456	Joker	0	07.03.2022 23:18
10	123456	Titanic	0	07.03.2022 23:18
11	123456	The Sixth Sense	0	07.03.2022 23:18
12	123456	The Others	0	07.03.2022 23:18
13	123456	The Invisible Guest	0	07.03.2022 23:18
14	123456	Spiderman No Way Home	0	07.03.2022 23:18
15	123456	Joker	0	07.03.2022 23:18
16	user	Titanic	0	08.03.2022 01:58
17	123456	Titanic	0	08.03.2022 01:58
18	user	Titanic	0	08.03.2022 01:58
19	123456	Titanic	0	08.03.2022 01:58
20	user	Titanic	0	08.03.2022 08:48
21	123456	Titanic	0	08.03.2022 08:48
22	user	Titanic	0	08.03.2022 08:48
23	123456	Titanic	0	08.03.2022 08:48
24	user	Titanic	0	08.03.2022 08:49
25	123456	Titanic	0	08.03.2022 08:49
26	user	Titanic	0	08.03.2022 08:49
27	123456	Titanic	0	08.03.2022 08:49
28	user	Titanic	0	08.03.2022 08:52
29	123456	Titanic	0	08.03.2022 08:52
30	user	Titanic	0	08.03.2022 08:52
31	123456	Titanic	0	08.03.2022 08:52
32	user	Titanic	0	08.03.2022 08:53
33	123456	Titanic	0	08.03.2022 08:53
34	user	Titanic	0	08.03.2022 08:53
35	123456	Titanic	0	08.03.2022 08:53
36	user	Titanic	0	08.03.2022 08:53
37	123456	Titanic	0	08.03.2022 08:54
38	user	Titanic	0	08.03.2022 08:57
39	123456	Titanic	0	08.03.2022 08:57
40	user	Titanic	0	08.03.2022 09:01
41	123456	Titanic	0	08.03.2022 09:01
42	user	Titanic	0	08.03.2022 09:02
43	123456	Titanic	0	08.03.2022 09:02
44	user	Titanic	0	08.03.2022 09:02
45	123456	Titanic	0	08.03.2022 09:02
46	user	Titanic	0	08.03.2022 09:04
47	123456	Titanic	0	08.03.2022 09:04
48	user	Titanic	0	08.03.2022 09:06
49	123456	Titanic	0	08.03.2022 09:06
50	user	Titanic	0	08.03.2022 09:07
51	123456	Titanic	0	08.03.2022 09:07
52	user	The Invisible Guest	0	08.03.2022 10:31
53	123456	The Invisible Guest	0	08.03.2022 10:31
\.


--
-- TOC entry 3334 (class 0 OID 16558)
-- Dependencies: 214
-- Data for Name: statistics; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.statistics (login_nr, movies_nr, orders_nr) FROM stdin;
10	1	2
\.


--
-- TOC entry 3333 (class 0 OID 16484)
-- Dependencies: 213
-- Data for Name: user_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_details (user_id, name, email, phone_number, wishlist, is_wishlist_updated, credit_card) FROM stdin;
123456	user	User@gmail.com	0544287165	{}	your wishlist is up do date	4585-2011-4992-5111
user	Shaked	Shaked@gmail.com	0544285182	{"Me Before You",Titanic}	your wishlist is up do date	\N
\.


--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 53, true);


--
-- TOC entry 3183 (class 2606 OID 16492)
-- Name: accounts Accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT "Accounts_pkey" PRIMARY KEY (user_id);


--
-- TOC entry 3185 (class 2606 OID 16421)
-- Name: movies movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (title);


--
-- TOC entry 3187 (class 2606 OID 16479)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 3189 (class 2606 OID 16490)
-- Name: user_details user_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_details
    ADD CONSTRAINT user_details_pkey PRIMARY KEY (user_id);


-- Completed on 2022-03-08 10:34:55

--
-- PostgreSQL database dump complete
--

