-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: iki_pitanja_odgovori
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `odgovor`
--

DROP TABLE IF EXISTS `odgovor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `odgovor` (
  `sifra` int(11) NOT NULL AUTO_INCREMENT,
  `tocan` bit(1) DEFAULT NULL,
  `tekst` varchar(255) NOT NULL,
  `pitanje_sifra` int(11) DEFAULT NULL,
  PRIMARY KEY (`sifra`),
  KEY `FK6kddg1yqipam4nrbyy05wtind` (`pitanje_sifra`),
  CONSTRAINT `FK6kddg1yqipam4nrbyy05wtind` FOREIGN KEY (`pitanje_sifra`) REFERENCES `pitanje` (`sifra`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `odgovor`
--

LOCK TABLES `odgovor` WRITE;
/*!40000 ALTER TABLE `odgovor` DISABLE KEYS */;
INSERT INTO `odgovor` VALUES (1,_binary '','Edukacija o sigurnom i odgovornom ponašanju na internetu',1),(2,_binary '\0','Promocija najnovijih videoigara',1),(3,_binary '\0','Prodaja računalne opreme',1),(4,_binary '\0','Organizacija natjecanja u programiranju',1),(5,_binary '\0','Dijeljenje svih osobnih podataka na društvenim mrežama',2),(6,_binary '','Zaštita osobnih podataka i kontrola nad njima na internetu',2),(7,_binary '\0','Korištenje interneta samo u javnim prostorima',2),(8,_binary '\0','Slanje privatnih poruka nepoznatim osobama',2),(9,_binary '\0','Koristiti istu lozinku za sve račune',3),(10,_binary '\0','Čuvati ih u bilježnici pored računala',3),(11,_binary '','Koristiti jake, jedinstvene lozinke i po potrebi upravitelj lozinki',3),(12,_binary '\0','Dijeliti ih s prijateljima',3),(13,_binary '\0','Odmah ih proslijediti svima',4),(14,_binary '','Ignorirati i blokirati pošiljatelja, te obavijestiti odraslu osobu',4),(15,_binary '\0','Odgovoriti na poruke i pitati zašto šalju takve stvari',4),(16,_binary '\0','Izbrisati aplikaciju za dopisivanje',4),(17,_binary '','Dijeliti samo slike i informacije koje ne ugrožavaju tvoju privatnost',5),(18,_binary '\0','Dijeliti sve što ti padne na pamet',5),(19,_binary '\0','Dijeliti samo sadržaj koji je zabavan',5),(20,_binary '\0','Dijeliti informacije o drugim ljudima bez njihovog pristanka',5),(21,_binary '\0','Siguran način plaćanja na internetu',6),(22,_binary '','Pokušaj krađe osobnih podataka putem lažnih poruka ili web stranica',6),(23,_binary '\0','Igra na internetu',6),(24,_binary '\0','Slanje velikog broja e-mailova prijateljima',6),(25,_binary '\0','Ako ima puno reklama',7),(26,_binary '','Ako u adresi stranice piše “https” i ima simbol lokota',7),(27,_binary '\0','Ako traži tvoju lozinku odmah nakon ulaska',7),(28,_binary '\0','Ako stranica izgleda šareno i zabavno',7),(29,_binary '\0','10 godina',8),(30,_binary '','13 godina',8),(31,_binary '\0','7 godina',8),(32,_binary '\0','5 godina',8),(33,_binary '\0','Igranje online igara',9),(34,_binary '','Nasilje i vrijeđanje drugih putem interneta',9),(35,_binary '\0','Slanje poklona prijateljima online',9),(36,_binary '\0','Dijeljenje smiješnih videa',9),(37,_binary '','Provjeriti više izvora i vjerodostojne stranice',10),(38,_binary '\0','Vjerovati samo naslovu članka',10),(39,_binary '\0','Dijeliti vijest odmah nakon što je pročitaš',10),(40,_binary '\0','Vjerovati samo onome što vidiš na društvenim mrežama',10),(41,_binary '\0','Da bi uređaj radio sporije',11),(42,_binary '','Da bi se popravile sigurnosne rupe i spriječili napadi',11),(43,_binary '\0','Da bi se izgubili podaci',11),(44,_binary '\0','Korištenje samo lozinke za prijavu',12),(45,_binary '','Dodatna sigurnosna provjera uz lozinku, npr. kod na mobitelu',12),(46,_binary '\0','Dijeljenje lozinke s drugom osobom',12),(47,_binary '\0','Prijava na dvije različite stranice istovremeno',12),(48,_binary '','Upisati hotline prijava te prijaviti neprimjereni sadržaj na internetu',13),(49,_binary '\0','Otići na društvene mreže i podijeliti sadržaj',13),(50,_binary '\0','Treba ignorirati neprimjereni sadržaj',13),(51,_binary '\0','Spremiti neprimjereni sadržaj na svoje računalo ili mobitel',13),(52,_binary '','Promijeniti lozinku i obavijestiti odraslu osobu',14),(53,_binary '\0','Ignorirati problem',14),(54,_binary '\0','Obrisati račun bez promjene lozinke',14),(55,_binary '\0','Nije važno, svejedno je',11),(56,_binary '\0','Dijeliti svoje podatke s nepoznatima da ti pomognu',14),(57,_binary '\0','Zabava bez edukacije',15),(58,_binary '','Edukacija i podizanje svijesti o sigurnosti na internetu',15),(59,_binary '\0','Prodaja proizvoda za računala',15),(60,_binary '\0','Natjecanje u brzom tipkanju',15),(61,_binary '\0','Dijeljenjem lažnih vijesti',16),(62,_binary '','Poštovanjem drugih i prijavljivanjem neprimjerenog sadržaja',16),(63,_binary '\0','Ignoriranjem pravila ponašanja',16),(64,_binary '\0','Korištenjem tuđih lozinki',16);
/*!40000 ALTER TABLE `odgovor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pitanje`
--

DROP TABLE IF EXISTS `pitanje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pitanje` (
  `sifra` int(11) NOT NULL AUTO_INCREMENT,
  `tekst` varchar(255) NOT NULL,
  PRIMARY KEY (`sifra`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pitanje`
--

LOCK TABLES `pitanje` WRITE;
/*!40000 ALTER TABLE `pitanje` DISABLE KEYS */;
INSERT INTO `pitanje` VALUES (1,'Koji je glavni cilj Dana sigurnijeg interneta?'),(2,'Što znači pojam “digitalna privatnost”?'),(3,'Koji je najbolji način da zaštitiš svoje lozinke?'),(4,'Što trebaš učiniti ako primiš neprimjerene poruke na internetu?'),(5,'Koja je najvažnija stvar kod dijeljenja sadržaja na društvenim mrežama?'),(6,'Što znači “phishing”?'),(7,'Kako možeš provjeriti je li web/mrežna stranica sigurna?'),(8,'Koja je preporučena dob za otvaranje profila na Instagramu?'),(9,'Što je “cyberbullying”?'),(10,'Koji je najbolji način da prepoznaš lažne vijesti na internetu?'),(11,'Zašto je važno redovito ažurirati softver(programe) na uređajima?'),(12,'Što znači “dvofaktorska autentifikacija”?'),(13,'Što treba upisati u tražilicu kada pronađete neprimjereni sadržaj na internetu?'),(14,'Što trebaš napraviti ako primijetiš da je tvoj račun hakiran?'),(15,'Koja je svrha online kvizova i radionica na Dan sigurnijeg interneta?'),(16,'Kako možeš pomoći da internet bude sigurnije mjesto za sve?');
/*!40000 ALTER TABLE `pitanje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'iki_pitanja_odgovori'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-10 22:39:02
