-- MySQL dump 10.13  Distrib 5.5.43, for Linux (i686)
--
-- Host: localhost    Database: acv
-- ------------------------------------------------------
-- Server version	5.5.43-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary table structure for view `cdr_summary`
--

DROP TABLE IF EXISTS `cdr_summary`;
/*!50001 DROP VIEW IF EXISTS `cdr_summary`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `cdr_summary` (
  `id` tinyint NOT NULL,
  `average` tinyint NOT NULL,
  `total` tinyint NOT NULL,
  `count` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `rt_extension`
--

DROP TABLE IF EXISTS `rt_extension`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rt_extension` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `app` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appdata` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `context` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `exten` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `priority` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rt_moh`
--

DROP TABLE IF EXISTS `rt_moh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rt_moh` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rt_sip_buddy`
--

DROP TABLE IF EXISTS `rt_sip_buddy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rt_sip_buddy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `accountcode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `allow` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `allowoverlap` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `allowsubscribe` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `allowtransfer` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `amaflags` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `auth` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `autoframing` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `buggymwi` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `busylevel` int(11) DEFAULT NULL,
  `call-limit` int(11) DEFAULT NULL,
  `callbackextension` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `callcounter` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `callerid` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `callgroup` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `callingpres` enum('allowed_not_screened','allowed_passed_screen','allowed_failed_screen','allowed','prohib_not_screened','prohib_passed_screen','prohib_failed_screen','prohib') COLLATE utf8_unicode_ci DEFAULT NULL,
  `cid_number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `constantssrc` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `contactdeny` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contactpermit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `context` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `defaultip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `defaultuser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deny` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `directmedia` enum('yes','no','nonat','update') COLLATE utf8_unicode_ci DEFAULT NULL,
  `disallow` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dtmfmode` enum('rfc2833','info','shortinfo','inband','auto') COLLATE utf8_unicode_ci DEFAULT NULL,
  `dynamic` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `externalauth` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `faxdetect` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `fromdomain` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fromuser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullcontact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `g726nonstandard` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `hasvoicemail` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `host` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ignoresdpversion` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `insecure` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ipaddr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `language` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastms` int(11) DEFAULT NULL,
  `mailbox` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `maxcallbitrate` int(11) DEFAULT NULL,
  `md5secret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mohinterpret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mohsuggest` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nat` enum('yes','no','never','route','force_rport,comedia') COLLATE utf8_unicode_ci DEFAULT NULL,
  `outboundproxy` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parkinglot` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `permit` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pickupgroup` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `progressinband` enum('yes','no','never') COLLATE utf8_unicode_ci DEFAULT NULL,
  `promiscredir` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `qualify` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `qualifyfreq` int(11) DEFAULT NULL,
  `regexten` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `regseconds` int(11) DEFAULT NULL,
  `regserver` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remotesecret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rfc2833compensate` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rtpholdtimeout` int(11) DEFAULT NULL,
  `rtpkeepalive` int(11) DEFAULT NULL,
  `rtptimeout` int(11) DEFAULT NULL,
  `secret` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sendrpid` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `session-expires` int(11) DEFAULT NULL,
  `session-minse` int(11) DEFAULT NULL,
  `session-refresher` enum('uac','uas') COLLATE utf8_unicode_ci DEFAULT NULL,
  `session-timers` enum('accept','refuse','originate') COLLATE utf8_unicode_ci DEFAULT NULL,
  `setvar` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subscribemwi` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `t38pt_usertpsource` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `textsupport` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `timerb` int(11) DEFAULT NULL,
  `timert1` int(11) DEFAULT NULL,
  `transport` enum('udp','tcp','udp,tcp','tcp,udp') COLLATE utf8_unicode_ci DEFAULT NULL,
  `trunkname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trustrpid` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` enum('friend','user','peer') COLLATE utf8_unicode_ci DEFAULT NULL,
  `useclientcode` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `useragent` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `usereqphone` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `videosupport` enum('yes','no') COLLATE utf8_unicode_ci DEFAULT NULL,
  `vmexten` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `summary_range`
--

DROP TABLE IF EXISTS `summary_range`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summary_range` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cdr_end` datetime DEFAULT NULL,
  `cdr_start` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `summaryRange_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14C321B99343D3C3` (`summaryRange_id`),
  CONSTRAINT `FK14C321B99343D3C3` FOREIGN KEY (`summaryRange_id`) REFERENCES `summary_range` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `cdr_summary`
--

/*!50001 DROP TABLE IF EXISTS `cdr_summary`*/;
/*!50001 DROP VIEW IF EXISTS `cdr_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`acv`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `cdr_summary` AS select `b`.`name` AS `id`,avg(`c`.`billsec`) AS `average`,sum(`c`.`billsec`) AS `total`,count(0) AS `count` from ((`rt_sip_buddy` `b` join `cdr` `c` on((`b`.`name` = `c`.`src`))) join `summary_range` `r` on(((`c`.`start` > `r`.`cdr_start`) and (`c`.`end` < `r`.`cdr_end`)))) group by `b`.`name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-14 20:13:59
