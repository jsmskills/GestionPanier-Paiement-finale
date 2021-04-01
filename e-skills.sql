-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 25 mars 2021 à 21:29
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `e-skills`
--

-- --------------------------------------------------------

--
-- Structure de la table `actualite`
--

DROP TABLE IF EXISTS `actualite`;
CREATE TABLE IF NOT EXISTS `actualite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(20) NOT NULL,
  `objet` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `catégorie-cour`
--

DROP TABLE IF EXISTS `catégorie-cour`;
CREATE TABLE IF NOT EXISTS `catégorie-cour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `ref` int(11) NOT NULL AUTO_INCREMENT,
  `prix` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idpanier` int(11) DEFAULT NULL,
  `idpaiement` int(11) DEFAULT NULL,
  PRIMARY KEY (`ref`),
  KEY `idUser` (`idUser`),
  KEY `idpanier` (`idpanier`),
  KEY `idpaiement` (`idpaiement`)
) ENGINE=InnoDB AUTO_INCREMENT=100016 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`ref`, `prix`, `idUser`, `idpanier`, `idpaiement`) VALUES
(11141, 329, 1, 45, 100),
(100014, 327, 1, 32, 100),
(100015, 334, 1, 47, 100);

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `id` int(11) NOT NULL,
  `titrecours` int(11) NOT NULL,
  `description` int(11) NOT NULL,
  `durée` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `resultat` varchar(20) NOT NULL,
  `nomformateur` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

DROP TABLE IF EXISTS `formation`;
CREATE TABLE IF NOT EXISTS `formation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(11) NOT NULL,
  `description` text NOT NULL,
  `date-début` date NOT NULL,
  `date-fin` date NOT NULL,
  `prix` float NOT NULL,
  `nbre-place` int(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `titre`, `description`, `date-début`, `date-fin`, `prix`, `nbre-place`) VALUES
(1, 'Java', 'formation Java chaque lundi avec Mr X', '2021-03-08', '2021-03-16', 5, 4),
(2, 'C#', 'formation C# chaque lundi avec Mr X', '2021-03-02', '2021-03-24', 322, 5),
(3, 'Php', 'desc3', '2021-03-29', '2021-03-30', 7, 8);

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

DROP TABLE IF EXISTS `ligne_commande`;
CREATE TABLE IF NOT EXISTS `ligne_commande` (
  `idLigne` int(11) NOT NULL AUTO_INCREMENT,
  `idCommande` int(11) NOT NULL,
  `idFormation` int(11) NOT NULL,
  PRIMARY KEY (`idLigne`,`idCommande`,`idFormation`),
  KEY `idCommande` (`idCommande`),
  KEY `ligne_commande_ibfk_2` (`idFormation`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ligne_commande`
--

INSERT INTO `ligne_commande` (`idLigne`, `idCommande`, `idFormation`) VALUES
(8, 100014, 1),
(9, 100014, 2),
(12, 11141, 2),
(13, 11141, 3),
(14, 100015, 1),
(15, 100015, 2),
(16, 100015, 3);

-- --------------------------------------------------------

--
-- Structure de la table `ligne_panier`
--

DROP TABLE IF EXISTS `ligne_panier`;
CREATE TABLE IF NOT EXISTS `ligne_panier` (
  `idLigne` int(11) NOT NULL AUTO_INCREMENT,
  `idPanier` int(11) NOT NULL,
  `idFormation` int(11) NOT NULL,
  PRIMARY KEY (`idLigne`,`idPanier`,`idFormation`),
  KEY `idPanier` (`idPanier`),
  KEY `idFormation` (`idFormation`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ligne_panier`
--

INSERT INTO `ligne_panier` (`idLigne`, `idPanier`, `idFormation`) VALUES
(52, 32, 1),
(54, 32, 2),
(56, 33, 3),
(57, 34, 3),
(77, 45, 2),
(78, 45, 3),
(82, 47, 1),
(83, 47, 2),
(84, 47, 3),
(85, 48, 3);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
CREATE TABLE IF NOT EXISTS `paiement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modepaiement` varchar(255) NOT NULL,
  `numcarte` int(11) NOT NULL,
  `cryptograme` int(11) NOT NULL,
  `dateexp` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id`, `modepaiement`, `numcarte`, `cryptograme`, `dateexp`) VALUES
(100, 'rib', 4455886, 444, '44sqdd');

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prix` float DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  `commandé` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `idUser` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id`, `prix`, `idUser`, `commandé`) VALUES
(32, 327, 1, 1),
(33, 7, 1, 1),
(34, 7, 1, 1),
(45, 329, 1, 1),
(47, 334, 1, 1),
(48, 7, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `partenaire`
--

DROP TABLE IF EXISTS `partenaire`;
CREATE TABLE IF NOT EXISTS `partenaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `objet` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `statistique`
--

DROP TABLE IF EXISTS `statistique`;
CREATE TABLE IF NOT EXISTS `statistique` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utiliisateur`
--

DROP TABLE IF EXISTS `utiliisateur`;
CREATE TABLE IF NOT EXISTS `utiliisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `password` varchar(254) NOT NULL,
  `type` varchar(254) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utiliisateur`
--

INSERT INTO `utiliisateur` (`id`, `nom`, `prenom`, `mail`, `password`, `type`) VALUES
(1, 'raniaa', 'rania', 'a', '111', 'user'),
(2, 'admin', 'admin', 'x', '111', 'admin');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `utiliisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`idpanier`) REFERENCES `panier` (`id`),
  ADD CONSTRAINT `commande_ibfk_3` FOREIGN KEY (`idpaiement`) REFERENCES `paiement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD CONSTRAINT `ligne_commande_ibfk_1` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`ref`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ligne_commande_ibfk_2` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ligne_panier`
--
ALTER TABLE `ligne_panier`
  ADD CONSTRAINT `ck_formation_lignepanier` FOREIGN KEY (`idFormation`) REFERENCES `formation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ck_panier_lignepanier` FOREIGN KEY (`idPanier`) REFERENCES `panier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `panier_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `utiliisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
