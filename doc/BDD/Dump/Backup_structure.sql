--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2022-01-24 18:39:08

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
-- TOC entry 208 (class 1259 OID 77030)
-- Name: accueil_interface; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accueil_interface (
    video_youtube character varying NOT NULL
);


ALTER TABLE public.accueil_interface OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 77090)
-- Name: adresse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adresse (
    id_adresse numeric NOT NULL,
    adresse character varying NOT NULL,
    complement_adresse character varying,
    code_postal character varying NOT NULL,
    ville character varying NOT NULL
);


ALTER TABLE public.adresse OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 77088)
-- Name: adresse_id_seq_1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.adresse_id_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.adresse_id_seq_1 OWNER TO postgres;

--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 219
-- Name: adresse_id_seq_1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.adresse_id_seq_1 OWNED BY public.adresse.id_adresse;


--
-- TOC entry 215 (class 1259 OID 77057)
-- Name: album_interface; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.album_interface (
    id_album_interface numeric NOT NULL,
    sous_titre_1 character varying NOT NULL,
    sous_titre_2 character varying NOT NULL,
    photo_album character varying NOT NULL,
    photo_cd character varying NOT NULL,
    chanson_1 character varying NOT NULL,
    chanson_2 character varying NOT NULL,
    chanson_3 character varying NOT NULL,
    chanson_4 character varying NOT NULL,
    titre_chanson_1 character varying NOT NULL,
    titre_chanson_2 character varying NOT NULL,
    titre_chanson_3 character varying NOT NULL,
    titre_chanson_4 character varying NOT NULL,
    tarif_ep character varying NOT NULL
);


ALTER TABLE public.album_interface OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 77049)
-- Name: album_interface_id_album_interface_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.album_interface_id_album_interface_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.album_interface_id_album_interface_seq OWNER TO postgres;

--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 211
-- Name: album_interface_id_album_interface_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.album_interface_id_album_interface_seq OWNED BY public.album_interface.id_album_interface;


--
-- TOC entry 214 (class 1259 OID 77055)
-- Name: album_interface_photo_album_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.album_interface_photo_album_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.album_interface_photo_album_seq OWNER TO postgres;

--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 214
-- Name: album_interface_photo_album_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.album_interface_photo_album_seq OWNED BY public.album_interface.photo_album;


--
-- TOC entry 212 (class 1259 OID 77051)
-- Name: album_interface_sous_titre_1_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.album_interface_sous_titre_1_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.album_interface_sous_titre_1_seq OWNER TO postgres;

--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 212
-- Name: album_interface_sous_titre_1_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.album_interface_sous_titre_1_seq OWNED BY public.album_interface.sous_titre_1;


--
-- TOC entry 213 (class 1259 OID 77053)
-- Name: album_interface_sous_titre_2_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.album_interface_sous_titre_2_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.album_interface_sous_titre_2_seq OWNER TO postgres;

--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 213
-- Name: album_interface_sous_titre_2_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.album_interface_sous_titre_2_seq OWNED BY public.album_interface.sous_titre_2;


--
-- TOC entry 207 (class 1259 OID 77021)
-- Name: biographie_interface; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.biographie_interface (
    id_biographie_interface numeric NOT NULL,
    sous_titre_1 character varying NOT NULL,
    paragraphe_1 character varying NOT NULL,
    sous_titre_2 character varying NOT NULL,
    paragraphe_2 character varying NOT NULL,
    photo_1 character varying NOT NULL,
    photo_2 character varying NOT NULL,
    photo_3 character varying NOT NULL
);


ALTER TABLE public.biographie_interface OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 77019)
-- Name: biographie_interface_id_biographie_interface_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.biographie_interface_id_biographie_interface_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.biographie_interface_id_biographie_interface_seq OWNER TO postgres;

--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 206
-- Name: biographie_interface_id_biographie_interface_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.biographie_interface_id_biographie_interface_seq OWNED BY public.biographie_interface.id_biographie_interface;


--
-- TOC entry 226 (class 1259 OID 77123)
-- Name: commentaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.commentaire (
    id_commentaire character varying NOT NULL,
    commentaire character varying NOT NULL,
    date_ajout date NOT NULL,
    membre numeric NOT NULL,
    interlocuteur numeric NOT NULL
);


ALTER TABLE public.commentaire OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 77121)
-- Name: commentaire_id_commentaire_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.commentaire_id_commentaire_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.commentaire_id_commentaire_seq OWNER TO postgres;

--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 225
-- Name: commentaire_id_commentaire_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.commentaire_id_commentaire_seq OWNED BY public.commentaire.id_commentaire;


--
-- TOC entry 218 (class 1259 OID 77079)
-- Name: concert_date; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.concert_date (
    id_concert_date character varying NOT NULL,
    adresse character varying NOT NULL,
    nom_lieu character varying NOT NULL,
    tarif character varying NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.concert_date OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 77077)
-- Name: concert_date_id_concert_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.concert_date_id_concert_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.concert_date_id_concert_seq OWNER TO postgres;

--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 217
-- Name: concert_date_id_concert_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.concert_date_id_concert_seq OWNED BY public.concert_date.id_concert_date;


--
-- TOC entry 210 (class 1259 OID 77040)
-- Name: concert_interface; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.concert_interface (
    id_concert_interface numeric NOT NULL,
    photo_1 character varying NOT NULL,
    photo_2 character varying NOT NULL,
    photo_3 character varying NOT NULL
);


ALTER TABLE public.concert_interface OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 77038)
-- Name: concert_interface_id_concert_interface_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.concert_interface_id_concert_interface_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.concert_interface_id_concert_interface_seq OWNER TO postgres;

--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 209
-- Name: concert_interface_id_concert_interface_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.concert_interface_id_concert_interface_seq OWNED BY public.concert_interface.id_concert_interface;


--
-- TOC entry 216 (class 1259 OID 77069)
-- Name: email_newsletter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.email_newsletter (
    email character varying NOT NULL
);


ALTER TABLE public.email_newsletter OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 77101)
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id_utilisateur numeric NOT NULL,
    nom character varying NOT NULL,
    prenom character varying NOT NULL,
    email character varying NOT NULL,
    mot_de_passe character varying NOT NULL,
    date_de_creation_du_compte date NOT NULL,
    adresse_id numeric NOT NULL,
    username character varying NOT NULL
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 77099)
-- Name: membre_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.membre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.membre_id_seq OWNER TO postgres;

--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 221
-- Name: membre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.membre_id_seq OWNED BY public.utilisateur.id_utilisateur;


--
-- TOC entry 228 (class 1259 OID 77160)
-- Name: photo_interface; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.photo_interface (
    id_photo_interface numeric NOT NULL,
    photo_accueil character varying NOT NULL,
    photo_paralax character varying NOT NULL,
    photo_album character varying NOT NULL,
    photo_contact character varying NOT NULL,
    photo_creation_compte character varying NOT NULL,
    photo_conversation_membre character varying NOT NULL,
    photo_modification_compte character varying NOT NULL
);


ALTER TABLE public.photo_interface OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 77158)
-- Name: photo_interface_id_photo_interface_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.photo_interface_id_photo_interface_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.photo_interface_id_photo_interface_seq OWNER TO postgres;

--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 227
-- Name: photo_interface_id_photo_interface_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.photo_interface_id_photo_interface_seq OWNED BY public.photo_interface.id_photo_interface;


--
-- TOC entry 205 (class 1259 OID 77010)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id_role numeric NOT NULL,
    statut character varying NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 77008)
-- Name: role_id_role_seq_1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_role_seq_1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_role_seq_1 OWNER TO postgres;

--
-- TOC entry 2953 (class 0 OID 0)
-- Dependencies: 204
-- Name: role_id_role_seq_1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_role_seq_1 OWNED BY public.role.id_role;


--
-- TOC entry 224 (class 1259 OID 77112)
-- Name: utilisateur_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur_role (
    id_utilisateur_role character varying NOT NULL,
    utilisateur_id numeric NOT NULL,
    role_id numeric NOT NULL
);


ALTER TABLE public.utilisateur_role OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 77110)
-- Name: utilisateur_role_id_utilisateur_role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.utilisateur_role_id_utilisateur_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utilisateur_role_id_utilisateur_role_seq OWNER TO postgres;

--
-- TOC entry 2954 (class 0 OID 0)
-- Dependencies: 223
-- Name: utilisateur_role_id_utilisateur_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.utilisateur_role_id_utilisateur_role_seq OWNED BY public.utilisateur_role.id_utilisateur_role;


--
-- TOC entry 2777 (class 2604 OID 77093)
-- Name: adresse id_adresse; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresse ALTER COLUMN id_adresse SET DEFAULT nextval('public.adresse_id_seq_1'::regclass);


--
-- TOC entry 2772 (class 2604 OID 77060)
-- Name: album_interface id_album_interface; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.album_interface ALTER COLUMN id_album_interface SET DEFAULT nextval('public.album_interface_id_album_interface_seq'::regclass);


--
-- TOC entry 2773 (class 2604 OID 77061)
-- Name: album_interface sous_titre_1; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.album_interface ALTER COLUMN sous_titre_1 SET DEFAULT nextval('public.album_interface_sous_titre_1_seq'::regclass);


--
-- TOC entry 2774 (class 2604 OID 77062)
-- Name: album_interface sous_titre_2; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.album_interface ALTER COLUMN sous_titre_2 SET DEFAULT nextval('public.album_interface_sous_titre_2_seq'::regclass);


--
-- TOC entry 2775 (class 2604 OID 77063)
-- Name: album_interface photo_album; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.album_interface ALTER COLUMN photo_album SET DEFAULT nextval('public.album_interface_photo_album_seq'::regclass);


--
-- TOC entry 2770 (class 2604 OID 77024)
-- Name: biographie_interface id_biographie_interface; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.biographie_interface ALTER COLUMN id_biographie_interface SET DEFAULT nextval('public.biographie_interface_id_biographie_interface_seq'::regclass);


--
-- TOC entry 2780 (class 2604 OID 77126)
-- Name: commentaire id_commentaire; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire ALTER COLUMN id_commentaire SET DEFAULT nextval('public.commentaire_id_commentaire_seq'::regclass);


--
-- TOC entry 2776 (class 2604 OID 77082)
-- Name: concert_date id_concert_date; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.concert_date ALTER COLUMN id_concert_date SET DEFAULT nextval('public.concert_date_id_concert_seq'::regclass);


--
-- TOC entry 2771 (class 2604 OID 77043)
-- Name: concert_interface id_concert_interface; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.concert_interface ALTER COLUMN id_concert_interface SET DEFAULT nextval('public.concert_interface_id_concert_interface_seq'::regclass);


--
-- TOC entry 2781 (class 2604 OID 77163)
-- Name: photo_interface id_photo_interface; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo_interface ALTER COLUMN id_photo_interface SET DEFAULT nextval('public.photo_interface_id_photo_interface_seq'::regclass);


--
-- TOC entry 2769 (class 2604 OID 77013)
-- Name: role id_role; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id_role SET DEFAULT nextval('public.role_id_role_seq_1'::regclass);


--
-- TOC entry 2778 (class 2604 OID 77104)
-- Name: utilisateur id_utilisateur; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur ALTER COLUMN id_utilisateur SET DEFAULT nextval('public.membre_id_seq'::regclass);


--
-- TOC entry 2779 (class 2604 OID 77115)
-- Name: utilisateur_role id_utilisateur_role; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_role ALTER COLUMN id_utilisateur_role SET DEFAULT nextval('public.utilisateur_role_id_utilisateur_role_seq'::regclass);


--
-- TOC entry 2787 (class 2606 OID 77037)
-- Name: accueil_interface accueil_interface_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accueil_interface
    ADD CONSTRAINT accueil_interface_pk PRIMARY KEY (video_youtube);


--
-- TOC entry 2797 (class 2606 OID 77098)
-- Name: adresse adresse_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresse
    ADD CONSTRAINT adresse_pk PRIMARY KEY (id_adresse);


--
-- TOC entry 2791 (class 2606 OID 77068)
-- Name: album_interface album_interface_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.album_interface
    ADD CONSTRAINT album_interface_pk PRIMARY KEY (id_album_interface);


--
-- TOC entry 2785 (class 2606 OID 77029)
-- Name: biographie_interface biographie_interface_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.biographie_interface
    ADD CONSTRAINT biographie_interface_pk PRIMARY KEY (id_biographie_interface);


--
-- TOC entry 2803 (class 2606 OID 77131)
-- Name: commentaire commentaire_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT commentaire_pk PRIMARY KEY (id_commentaire);


--
-- TOC entry 2795 (class 2606 OID 77087)
-- Name: concert_date concert_date_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.concert_date
    ADD CONSTRAINT concert_date_pk PRIMARY KEY (id_concert_date);


--
-- TOC entry 2789 (class 2606 OID 77048)
-- Name: concert_interface concert_interface_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.concert_interface
    ADD CONSTRAINT concert_interface_pk PRIMARY KEY (id_concert_interface);


--
-- TOC entry 2793 (class 2606 OID 77076)
-- Name: email_newsletter email_newsletter_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.email_newsletter
    ADD CONSTRAINT email_newsletter_pk PRIMARY KEY (email);


--
-- TOC entry 2805 (class 2606 OID 77168)
-- Name: photo_interface photo_interface_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photo_interface
    ADD CONSTRAINT photo_interface_pk PRIMARY KEY (id_photo_interface);


--
-- TOC entry 2783 (class 2606 OID 77018)
-- Name: role role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pk PRIMARY KEY (id_role);


--
-- TOC entry 2799 (class 2606 OID 77109)
-- Name: utilisateur utilisateur_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pk PRIMARY KEY (id_utilisateur);


--
-- TOC entry 2801 (class 2606 OID 77120)
-- Name: utilisateur_role utilisateur_role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT utilisateur_role_pk PRIMARY KEY (id_utilisateur_role);


--
-- TOC entry 2806 (class 2606 OID 77137)
-- Name: utilisateur adresse_membre_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT adresse_membre_fk FOREIGN KEY (adresse_id) REFERENCES public.adresse(id_adresse);


--
-- TOC entry 2809 (class 2606 OID 77142)
-- Name: commentaire membre_commentaire_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT membre_commentaire_fk FOREIGN KEY (membre) REFERENCES public.utilisateur(id_utilisateur);


--
-- TOC entry 2810 (class 2606 OID 77147)
-- Name: commentaire membre_commentaire_fk1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.commentaire
    ADD CONSTRAINT membre_commentaire_fk1 FOREIGN KEY (interlocuteur) REFERENCES public.utilisateur(id_utilisateur);


--
-- TOC entry 2807 (class 2606 OID 77132)
-- Name: utilisateur_role role_utilisateur_role_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT role_utilisateur_role_fk FOREIGN KEY (role_id) REFERENCES public.role(id_role);


--
-- TOC entry 2808 (class 2606 OID 77152)
-- Name: utilisateur_role utilisateur_utilisateur_role_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur_role
    ADD CONSTRAINT utilisateur_utilisateur_role_fk FOREIGN KEY (utilisateur_id) REFERENCES public.utilisateur(id_utilisateur);


-- Completed on 2022-01-24 18:39:08

--
-- PostgreSQL database dump complete
--

