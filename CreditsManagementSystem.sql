CREATE ROLE credits_manager WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD 'Credits123' IF NOT EXISTS;

--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-05-19 14:03:00

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'ISO-8859-1';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 637 (class 1247 OID 32814)
-- Name: Permission; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Permission" AS ENUM (
    'SYSADMIN',
    'PRODUCER',
    'CREDITS_MANAGER'
);


ALTER TYPE public."Permission" OWNER TO postgres;

--
-- TOC entry 634 (class 1247 OID 32792)
-- Name: Roles; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."Roles" AS ENUM (
    'ACTOR',
    'PRODUCER',
    'CAMERAMAN',
    'SOUNDTECHNICIAN',
    'LIGHTINGTECHNICIAN'
);


ALTER TYPE public."Roles" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 32771)
-- Name: contributors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contributors (
    "ID" integer NOT NULL,
    name character varying(255) NOT NULL,
    description text
);


ALTER TABLE public.contributors OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 32769)
-- Name: Contributors_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Contributors_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Contributors_ID_seq" OWNER TO postgres;

--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 200
-- Name: Contributors_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Contributors_ID_seq" OWNED BY public.contributors."ID";


--
-- TOC entry 203 (class 1259 OID 32782)
-- Name: productions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productions (
    "ID" integer NOT NULL,
    title character varying(255) NOT NULL,
    length_hours integer NOT NULL,
    length_minutes integer NOT NULL,
    release_date date NOT NULL,
    description text,
    owner integer NOT NULL,
    visible boolean NOT NULL
);


ALTER TABLE public.productions OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 32780)
-- Name: Production_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Production_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Production_ID_seq" OWNER TO postgres;

--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 202
-- Name: Production_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Production_ID_seq" OWNED BY public.productions."ID";


--
-- TOC entry 207 (class 1259 OID 32823)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    "ID" integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    permission public."Permission" NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 32821)
-- Name: Users_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Users_ID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Users_ID_seq" OWNER TO postgres;

--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 206
-- Name: Users_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Users_ID_seq" OWNED BY public.users."ID";


--
-- TOC entry 205 (class 1259 OID 32808)
-- Name: production_characters; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.production_characters (
    contributor_id integer NOT NULL,
    production_id integer NOT NULL,
    "character" character varying(255) NOT NULL
);


ALTER TABLE public.production_characters OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 32803)
-- Name: production_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.production_roles (
    contributor_id integer NOT NULL,
    production_id integer NOT NULL,
    role public."Roles" NOT NULL
);


ALTER TABLE public.production_roles OWNER TO postgres;

--
-- TOC entry 2879 (class 2604 OID 32774)
-- Name: contributors ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contributors ALTER COLUMN "ID" SET DEFAULT nextval('public."Contributors_ID_seq"'::regclass);


--
-- TOC entry 2880 (class 2604 OID 32785)
-- Name: productions ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productions ALTER COLUMN "ID" SET DEFAULT nextval('public."Production_ID_seq"'::regclass);


--
-- TOC entry 2881 (class 2604 OID 32826)
-- Name: users ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN "ID" SET DEFAULT nextval('public."Users_ID_seq"'::regclass);


--
-- TOC entry 3023 (class 0 OID 32771)
-- Dependencies: 201
-- Data for Name: contributors; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.contributors ( name, description) VALUES ('Jens Jensen', 'Dette er en beskrivelse');


--
-- TOC entry 3027 (class 0 OID 32808)
-- Dependencies: 205
-- Data for Name: production_characters; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.production_characters (contributor_id, production_id, "character") VALUES (1, 1, 'Flyvende Flemming');


--
-- TOC entry 3026 (class 0 OID 32803)
-- Dependencies: 204
-- Data for Name: production_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.production_roles (contributor_id, production_id, role) VALUES (1, 1, 'ACTOR');


--
-- TOC entry 3025 (class 0 OID 32782)
-- Dependencies: 203
-- Data for Name: productions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.productions ("ID", title, length_hours, length_minutes, release_date, description, owner, visible) VALUES
(1, 'Eventyr 37',2, 36, '2021-05-19', 'Eventyr 37 er et godt eventyr', 2, FALSE);


--
-- TOC entry 3029 (class 0 OID 32823)
-- Dependencies: 207
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (username, password, permission) VALUES ('root', 'root', 'SYSADMIN');
INSERT INTO public.users (username, password, permission) VALUES ('Hubert94', 'Pizza36', 'PRODUCER');


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 200
-- Name: Contributors_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Contributors_ID_seq"', 1, true);


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 202
-- Name: Production_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Production_ID_seq"', 1, true);


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 206
-- Name: Users_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Users_ID_seq"', 1, true);


--
-- TOC entry 2883 (class 2606 OID 32779)
-- Name: contributors Contributors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contributors
    ADD CONSTRAINT "Contributors_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 2889 (class 2606 OID 32812)
-- Name: production_characters ProductionCharacters_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.production_characters
    ADD CONSTRAINT "ProductionCharacters_pkey" PRIMARY KEY (contributor_id, production_id);


--
-- TOC entry 2887 (class 2606 OID 32807)
-- Name: production_roles ProductionRoles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.production_roles
    ADD CONSTRAINT "ProductionRoles_pkey" PRIMARY KEY (contributor_id, production_id);


--
-- TOC entry 2885 (class 2606 OID 32790)
-- Name: productions Production_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productions
    ADD CONSTRAINT "Production_pkey" PRIMARY KEY ("ID");


--
-- TOC entry 2891 (class 2606 OID 32831)
-- Name: users Users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY ("ID");


-- Completed on 2021-05-19 14:03:00

--
-- PostgreSQL database dump complete
--
