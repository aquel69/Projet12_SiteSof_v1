--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2022-01-22 15:05:00

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
-- TOC entry 2941 (class 0 OID 77030)
-- Dependencies: 208
-- Data for Name: accueil_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.accueil_interface (video_youtube) VALUES ('https://www.youtube.com/embed/yRWCAFs1oY8?controls=0');


--
-- TOC entry 2953 (class 0 OID 77090)
-- Dependencies: 220
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.adresse (id_adresse, adresse, complement_adresse, code_postal, ville) VALUES (3, 'route de la musique', NULL, '69001', 'Lyon');


--
-- TOC entry 2948 (class 0 OID 77057)
-- Dependencies: 215
-- Data for Name: album_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.album_interface (id_album_interface, sous_titre_1, sous_titre_2, photo_album, photo_cd, chanson_1, chanson_2, chanson_3, chanson_4, titre_chanson_1, titre_chanson_2, titre_chanson_3, titre_chanson_4, tarif_ep) VALUES (1, 'EP - La Luna', 'Commander l''EP', 'EP_front.png', 'sof_album.jpg', 'LA-LUNA-MIX1.mp3', 'MON-AMOUR-MIX1.mp3', 'CANCION-MIX1.mp3', '90-PRINTEMPS-MIX1.mp3', 'La Luna', 'Mon Amour', 'Cancion Del Amanecer', '90 Printemps', 'Prix de l''EP : 5€');


--
-- TOC entry 2940 (class 0 OID 77021)
-- Dependencies: 207
-- Data for Name: biographie_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.biographie_interface (id_biographie_interface, sous_titre_1, paragraphe_1, sous_titre_2, paragraphe_2, photo_1, photo_2, photo_3) VALUES (1, 'Ses inspirations', 'Auteure compositrice, chanteuse guitariste, Sof s’inscrit dans un univers chanson/folk.
                Sa voix chaude et son jeu subtil délivrent une intimité vibrante et passionnée.
                 Elle compose ses musiques au piano, à la guitare et au ukulélé, ses textes sont à la fois légers et poétiques.', 'Ses musiciens', 'Elle fait appel sur scène à Cyrille Savoi, musicien multi-instrumentiste,
                qui l’accompagne aux saxophones, flûte traversière et harmonica,
                Régis Parazon à la batterie et aux percussions et Boris Mange aux claviers.
                Ils apportent des couleurs intenses et cuivrées à son répertoire,
                les influences de chacun se mêlent et donnent un cocktail explosif de fraîcheur !', 'concert_noir_blanc.jpg', 'sof_uku.png', 'musicien.jpg');


--
-- TOC entry 2955 (class 0 OID 77101)
-- Dependencies: 222
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateur (id_utilisateur, nom, prenom, email, mot_de_passe, date_de_creation_du_compte, adresse_id, username) VALUES (3, 'Rut', 'Sophie', 'sophierut@gmail.com', '$2a$10$n3b273bpwRkFiGnkFDoZHuGlJ14GPgvDH2x1rPA5IOWO.b7aB5J1y', '2022-01-14', 3, 'Sof');
INSERT INTO public.utilisateur (id_utilisateur, nom, prenom, email, mot_de_passe, date_de_creation_du_compte, adresse_id, username) VALUES (5, 'Lardon', 'Alexandre', 'alexandre.lardon@yahoo.fr', '$2a$10$LJt7JERxQt82cQ2pabzVm.OpiNFladmEOmipTxeZUDP4puoqKfNNC', '2022-01-17', 3, 'Aquel');


--
-- TOC entry 2959 (class 0 OID 77123)
-- Dependencies: 226
-- Data for Name: commentaire; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2951 (class 0 OID 77079)
-- Dependencies: 218
-- Data for Name: concert_date; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2943 (class 0 OID 77040)
-- Dependencies: 210
-- Data for Name: concert_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.concert_interface (id_concert_interface, photo_1, photo_2, photo_3) VALUES (1, 'sof_piano.jpg', 'sof_dos.jpg', 'sof_cri_2.jpg');


--
-- TOC entry 2949 (class 0 OID 77069)
-- Dependencies: 216
-- Data for Name: email_newsletter; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2961 (class 0 OID 77160)
-- Dependencies: 228
-- Data for Name: photo_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.photo_interface (id_photo_interface, photo_accueil, photo_paralax, photo_album, photo_contact, photo_creation_compte, photo_conversation_membre, photo_modification_compte) VALUES (1, 'sof_accueil.jpg', 'sof_accueil.jpg', 'sof_album.jpg', 'sof_contact.jpg', 'sof_inscription.jpg', 'sof_conversation.jpg', 'sof_conversation.jpg');


--
-- TOC entry 2938 (class 0 OID 77010)
-- Dependencies: 205
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id_role, statut) VALUES (2, 'ROLE_MEMBER');
INSERT INTO public.role (id_role, statut) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role (id_role, statut) VALUES (3, 'ROLE_USER');


--
-- TOC entry 2957 (class 0 OID 77112)
-- Dependencies: 224
-- Data for Name: utilisateur_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateur_role (id_utilisateur_role, utilisateur_id, role_id) VALUES ('2', 3, 1);
INSERT INTO public.utilisateur_role (id_utilisateur_role, utilisateur_id, role_id) VALUES ('3', 5, 3);


--
-- TOC entry 2967 (class 0 OID 0)
-- Dependencies: 219
-- Name: adresse_id_seq_1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresse_id_seq_1', 5, true);


--
-- TOC entry 2968 (class 0 OID 0)
-- Dependencies: 211
-- Name: album_interface_id_album_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_id_album_interface_seq', 1, true);


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 214
-- Name: album_interface_photo_album_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_photo_album_seq', 1, false);


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 212
-- Name: album_interface_sous_titre_1_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_sous_titre_1_seq', 1, false);


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 213
-- Name: album_interface_sous_titre_2_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_sous_titre_2_seq', 1, false);


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 206
-- Name: biographie_interface_id_biographie_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.biographie_interface_id_biographie_interface_seq', 1, true);


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 225
-- Name: commentaire_id_commentaire_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.commentaire_id_commentaire_seq', 1, false);


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 217
-- Name: concert_date_id_concert_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.concert_date_id_concert_seq', 1, false);


--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 209
-- Name: concert_interface_id_concert_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.concert_interface_id_concert_interface_seq', 1, true);


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 221
-- Name: membre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membre_id_seq', 5, true);


--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 227
-- Name: photo_interface_id_photo_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.photo_interface_id_photo_interface_seq', 1, true);


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 204
-- Name: role_id_role_seq_1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_role_seq_1', 3, true);


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 223
-- Name: utilisateur_role_id_utilisateur_role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utilisateur_role_id_utilisateur_role_seq', 3, true);


-- Completed on 2022-01-22 15:05:01

--
-- PostgreSQL database dump complete
--

