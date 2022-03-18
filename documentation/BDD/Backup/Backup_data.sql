--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

-- Started on 2022-03-18 15:32:12

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
-- TOC entry 2943 (class 0 OID 77030)
-- Dependencies: 210
-- Data for Name: accueil_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.accueil_interface (video_youtube) VALUES ('https://www.youtube.com/embed/yRWCAFs1oY8?controls=0');


--
-- TOC entry 2955 (class 0 OID 77090)
-- Dependencies: 222
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.adresse (id_adresse, adresse, complement_adresse, code_postal, ville) VALUES (3, 'route de la musique', NULL, '69001', 'Lyon');
INSERT INTO public.adresse (id_adresse, adresse, complement_adresse, code_postal, ville) VALUES (90, '24 chemin de charrière blanche', '2', '69130', 'Ecully');
INSERT INTO public.adresse (id_adresse, adresse, complement_adresse, code_postal, ville) VALUES (86, '16 rue de la croix', '', '75001', 'Paris');
INSERT INTO public.adresse (id_adresse, adresse, complement_adresse, code_postal, ville) VALUES (98, '35 rue de la chartreuse', '', '38000', 'Grenoble');


--
-- TOC entry 2950 (class 0 OID 77057)
-- Dependencies: 217
-- Data for Name: album_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.album_interface (id_album_interface, sous_titre_1, sous_titre_2, photo_album, photo_cd, chanson_1, chanson_2, chanson_3, chanson_4, titre_chanson_1, titre_chanson_2, titre_chanson_3, titre_chanson_4, tarif_ep) VALUES (1, 'EP - La Luna', 'Commander l''EP', 'EP_front.png', 'rond_CD.png', 'LA-LUNA-MIX1.mp3', 'MON-AMOUR-MIX1.mp3', 'CANCION-MIX1.mp3', '90-PRINTEMPS-MIX1.mp3', 'La Luna', 'Mon Amour', 'Cancion Del Amanecer', '90 Printemps', 'Prix de l''EP : 5€');


--
-- TOC entry 2942 (class 0 OID 77021)
-- Dependencies: 209
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
-- TOC entry 2953 (class 0 OID 77079)
-- Dependencies: 220
-- Data for Name: concert_date; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (1, 'Rue des Clercs - Vienne (38)', 'SIMONE''S CAFÉ ', 'Gratuit', '2021-08-19');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (2, 'Monsols (69)', 'FESTIVAL "LE CRI DU COL"', 'Gratuit', '2019-08-03');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (5, 'St Romain en Gal (69)', 'NINKASI', 'Gratuit', '2019-01-26');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (7, 'Tignieu (38)', 'NINKASI', 'Gratuit', '2019-01-04');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (9, 'Barberaz (38)', 'BRIN DE ZINC', 'Gratuit', '2018-10-23');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (11, 'Vienne (38)', 'FESTIVAL OFF JAZZ A VIENNE', 'Gratuit', '2018-07-07');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (12, 'La Mulatière (69)', 'AUX BONS SAUVAGES', 'Gratuit', '2018-07-06');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (13, 'Lyon 8 (69)', 'LA FABRYK', 'Gratuit', '2018-04-25');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (14, 'Lyon 4 (69)', 'NINKASI', 'Gratuit', '2018-02-22');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (15, 'St Romain en Gal', 'NINKASI', 'Gratuit', '2018-02-10');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (16, 'Lyon 8 (69)', 'NINKASI', 'Gratuit', '2018-02-09');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (17, 'Villeurbanne (69)', 'NINKASI', 'Gratuit', '2017-11-24');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (18, 'Lyon 8 (69)', 'NINKASI', 'Gratuit', '2017-11-10');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (19, 'St Cyr au Mont d''Or (69)', 'LA CAVE DU CHÂTEAU', 'Gratuit', '2017-06-21');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (20, 'St Didier au Mont d''Or', 'SALLE DES FÊTES', 'Gratuit', '2017-01-20');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (10, 'Lyon 6 (69)', 'LE COMPTOIR DUQUESNE', 'Gratuit', '2018-10-19');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (8, 'Pont-Evêque (38)', 'LES INSTAN''TANNERIES', '5 euros', '2018-11-23');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (3, '108 Rue St Georges Lyon 5 (69)', 'L''ANTIDOTE', 'Gratuit', '2022-02-04');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (4, 'Lyon 1 (69)', 'SECOND SOUFFLE', 'Gratuit', '2019-02-28');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (61, '20 rue du lycée - Saint Romain en Gal', 'Ninkasi', 'Gratuit', '2022-06-21');
INSERT INTO public.concert_date (id_concert_date, adresse, nom_lieu, tarif, date) VALUES (62, '20 rue de la bière - Lyon 1er', 'Ninkasi', 'Gratuit', '2022-10-19');


--
-- TOC entry 2945 (class 0 OID 77040)
-- Dependencies: 212
-- Data for Name: concert_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.concert_interface (id_concert_interface, photo_1, photo_2, photo_3) VALUES (1, 'sof_piano.jpg', 'sof_dos.jpg', 'sof_cri_2.jpg');


--
-- TOC entry 2957 (class 0 OID 77101)
-- Dependencies: 224
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateur (id_utilisateur, nom, prenom, email, mot_de_passe, date_de_creation_du_compte, adresse_id, username) VALUES (37, 'Lardon', 'Alexandre', 'alexandre.lardon@yahoo.fr', '$2a$10$NFgxO9RWqeqc4MM/j1rDPexZY.4O1qAkvcblO5mTUZdjMdQYV6xJq', '2022-02-14', 90, 'Aquel');
INSERT INTO public.utilisateur (id_utilisateur, nom, prenom, email, mot_de_passe, date_de_creation_du_compte, adresse_id, username) VALUES (38, 'Simon', 'david', 'alexandre.lardon69@gmail.com', '$2a$10$4z1vHKtQMcRD5LGAViVXPuT/TMT1bE2cNU5pSw1ElBsu5SztX3LAa', '2022-02-17', 86, 'Simon');
INSERT INTO public.utilisateur (id_utilisateur, nom, prenom, email, mot_de_passe, date_de_creation_du_compte, adresse_id, username) VALUES (53, 'Dupont', 'Gerard', 'dupont.gerard@gmail.com', '$2a$10$LCG.0HLXM3nqg1VIajqxy.GfLk0eHaN17RhzftRkZxTpnSGcbAoBa', '2022-03-18', 98, 'Rastak');
INSERT INTO public.utilisateur (id_utilisateur, nom, prenom, email, mot_de_passe, date_de_creation_du_compte, adresse_id, username) VALUES (3, 'Rut', 'Sophie', 'serveursof@gmail.com', '$2a$10$n3b273bpwRkFiGnkFDoZHuGlJ14GPgvDH2x1rPA5IOWO.b7aB5J1y', '2022-01-14', 3, 'Sof');


--
-- TOC entry 2961 (class 0 OID 77123)
-- Dependencies: 228
-- Data for Name: conversation; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('36', 'Salut', '2022-02-24 19:03:58.262435+01', 37, 3);
INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('76', 'Salut', '2022-03-12 14:52:27.672543+01', 37, 37);
INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('96', 'Bonjour à vous', '2022-03-18 14:37:08.431914+01', 53, 53);
INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('97', 'Comment allez Vous ??', '2022-03-18 14:37:58.605547+01', 53, 3);
INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('98', 'Bonjour à vous', '2022-03-18 15:19:12.340113+01', 38, 3);
INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('31', 'RE', '2022-02-24 08:49:09.201349+01', 37, 37);
INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('29', 'Hello', '2022-02-24 08:39:42.543316+01', 38, 38);
INSERT INTO public.conversation (id_conversation, message, date_ajout, membre_id, interlocuteur_id) VALUES ('99', 'comment allez vous?', '2022-03-18 15:27:30.128312+01', 37, 3);


--
-- TOC entry 2951 (class 0 OID 77069)
-- Dependencies: 218
-- Data for Name: email_newsletter; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.email_newsletter (email) VALUES ('alexandre.lardon69@gmail.com');
INSERT INTO public.email_newsletter (email) VALUES ('but.sof@yahoo.com');


--
-- TOC entry 2963 (class 0 OID 77160)
-- Dependencies: 230
-- Data for Name: photo_interface; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.photo_interface (id_photo_interface, photo_accueil, photo_paralax, photo_album, photo_contact, photo_creation_compte, photo_conversation_membre, photo_gestion_compte, photo_desinscription_newsletter) VALUES (1, 'sof_accueil.jpg', 'sof_accueil.jpg', 'sof_album.jpg', 'sof_contact.jpg', 'sof_inscription.jpg', 'sof_conversation.jpg', 'sof_gestion.jpg', 'sof_desinscription_newsletter.jpg');


--
-- TOC entry 2940 (class 0 OID 77010)
-- Dependencies: 207
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.role (id_role, statut) VALUES (2, 'ROLE_MEMBER');
INSERT INTO public.role (id_role, statut) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role (id_role, statut) VALUES (3, 'ROLE_USER');


--
-- TOC entry 2959 (class 0 OID 77112)
-- Dependencies: 226
-- Data for Name: utilisateur_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.utilisateur_role (id_utilisateur_role, utilisateur_id, role_id) VALUES ('2', 3, 1);
INSERT INTO public.utilisateur_role (id_utilisateur_role, utilisateur_id, role_id) VALUES ('56', 37, 2);
INSERT INTO public.utilisateur_role (id_utilisateur_role, utilisateur_id, role_id) VALUES ('58', 38, 2);
INSERT INTO public.utilisateur_role (id_utilisateur_role, utilisateur_id, role_id) VALUES ('59', 53, 2);


--
-- TOC entry 2969 (class 0 OID 0)
-- Dependencies: 221
-- Name: adresse_id_seq_1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresse_id_seq_1', 98, true);


--
-- TOC entry 2970 (class 0 OID 0)
-- Dependencies: 213
-- Name: album_interface_id_album_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_id_album_interface_seq', 1, true);


--
-- TOC entry 2971 (class 0 OID 0)
-- Dependencies: 216
-- Name: album_interface_photo_album_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_photo_album_seq', 1, false);


--
-- TOC entry 2972 (class 0 OID 0)
-- Dependencies: 214
-- Name: album_interface_sous_titre_1_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_sous_titre_1_seq', 1, false);


--
-- TOC entry 2973 (class 0 OID 0)
-- Dependencies: 215
-- Name: album_interface_sous_titre_2_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.album_interface_sous_titre_2_seq', 1, false);


--
-- TOC entry 2974 (class 0 OID 0)
-- Dependencies: 208
-- Name: biographie_interface_id_biographie_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.biographie_interface_id_biographie_interface_seq', 1, true);


--
-- TOC entry 2975 (class 0 OID 0)
-- Dependencies: 227
-- Name: commentaire_id_commentaire_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.commentaire_id_commentaire_seq', 99, true);


--
-- TOC entry 2976 (class 0 OID 0)
-- Dependencies: 219
-- Name: concert_date_id_concert_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.concert_date_id_concert_seq', 63, true);


--
-- TOC entry 2977 (class 0 OID 0)
-- Dependencies: 211
-- Name: concert_interface_id_concert_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.concert_interface_id_concert_interface_seq', 1, true);


--
-- TOC entry 2978 (class 0 OID 0)
-- Dependencies: 223
-- Name: membre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.membre_id_seq', 53, true);


--
-- TOC entry 2979 (class 0 OID 0)
-- Dependencies: 229
-- Name: photo_interface_id_photo_interface_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.photo_interface_id_photo_interface_seq', 1, true);


--
-- TOC entry 2980 (class 0 OID 0)
-- Dependencies: 206
-- Name: role_id_role_seq_1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_role_seq_1', 3, true);


--
-- TOC entry 2981 (class 0 OID 0)
-- Dependencies: 225
-- Name: utilisateur_role_id_utilisateur_role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utilisateur_role_id_utilisateur_role_seq', 59, true);


-- Completed on 2022-03-18 15:32:12

--
-- PostgreSQL database dump complete
--

