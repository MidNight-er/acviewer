package ru.acviewer.av.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rt_sip_buddy")
public class RealtimeSipBuddy extends Realtime {
		
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "host")
	private String host;
	
	@Column(name = "ipaddr")
	private String ipaddr;
	
	@Column(name = "port")
	private Integer port;
	
	@Column(name = "regseconds")
	private Integer regseconds;
	
	@Column(name = "defaultuser")
	private String defaultuser;
	
	@Column(name = "fullcontact")
	private String fullcontact;
	
	@Column(name = "regserver")
	private String regserver;
	
	@Column(name = "useragent")
	private String useragent;
	
	@Column(name = "lastms")
	private Integer lastms;
	
	@Column(name = "type", columnDefinition="enum('friend','user','peer')")
	private String type;
	
	@Column(name = "context")
	private String context;
	
	@Column(name = "deny")
	private String deny;
	
	@Column(name = "permit")
	private String permit;
	
	@Column(name = "secret")
	private String secret;
	
	@Column(name = "md5secret")
	private String md5secret;
	
	@Column(name = "remotesecret")
	private String remotesecret;
	
	@Column(name = "transport", columnDefinition="enum('udp','tcp','udp,tcp','tcp,udp')")
	private String transport;
	
	@Column(name = "dtmfmode", columnDefinition="enum('rfc2833','info','shortinfo','inband','auto')")
	private String dtmfmode;
	
	@Column(name = "directmedia", columnDefinition="enum('yes','no','nonat','update')")
	private String directmedia;
	
	@Column(name = "nat", columnDefinition="enum('yes','no','never','route','force_rport,comedia')")
	private String nat;
	
	@Column(name = "callgroup")
	private String callgroup;
	
	@Column(name = "pickupgroup")
	private String pickupgroup;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "disallow")
	private String disallow;
	
	@Column(name = "allow")
	private String allow;
	
	@Column(name = "insecure")
	private String insecure;
	
	@Column(name = "trustrpid", columnDefinition="enum('yes','no')")
	private String trustrpid;
	
	@Column(name = "progressinband", columnDefinition="enum('yes','no','never')")
	private String progressinband;
	
	@Column(name = "promiscredir", columnDefinition="enum('yes','no')")
	private String promiscredir;

	@Column(name = "useclientcode", columnDefinition="enum('yes','no')")
	private String useclientcode;
	
	@Column(name = "accountcode")
	private String accountcode;
	
	@Column(name = "setvar")
	private String setvar;
	
	@Column(name = "callerid")
	private String callerid;
	
	@Column(name = "externalauth", columnDefinition="enum('yes','no')")
	private String externalauth;
	
	@Column(name = "amaflags")
	private String amaflags;
	
	@Column(name = "callcounter", columnDefinition="enum('yes','no')")
	private String callcounter;
	
	@Column(name = "busylevel")
	private Integer busylevel;

	@Column(name = "allowoverlap", columnDefinition="enum('yes','no')")
	private String allowoverlap;
	
	@Column(name = "allowsubscribe", columnDefinition="enum('yes','no')")
	private String allowsubscribe;
	
	@Column(name = "videosupport", columnDefinition="enum('yes','no')")
	private String videosupport;
	
	@Column(name = "maxcallbitrate")
	private Integer maxcallbitrate;
	
	@Column(name = "rfc2833compensate")
	private String rfc2833compensate;
		
	@Column(name = "mailbox")
	private String mailbox;
	
	@Column(name = "`session-timers`", columnDefinition="enum('accept','refuse','originate')")
	private String sessionTimers;

	@Column(name = "`session-expires`")
	private Integer sessionExpires;
	
	@Column(name = "`session-minse`")
	private Integer sessionMinse;

	@Column(name = "`session-refresher`", columnDefinition="enum('uac','uas')")
	private String sessionRefresher;

	@Column(name = "t38pt_usertpsource")
	private String t38ptUsertpsource;
	
	@Column(name = "regexten")
	private String regexten;
	
	@Column(name = "fromdomain")
	private String fromdomain;
	
	@Column(name = "fromuser")
	private String fromuser;
	
	@Column(name = "qualify")
	private String qualify;
	
	@Column(name = "defaultip")
	private String defaultip;
	
	@Column(name = "rtptimeout")
	private Integer rtptimeout;
	
	@Column(name = "rtpholdtimeout")
	private Integer rtpholdtimeout;
	
	@Column(name = "sendrpid", columnDefinition="enum('yes','no')")
	private String sendrpid;
	
	@Column(name = "outboundproxy")
	private String outboundproxy;
	
	@Column(name = "callbackextension")
	private String callbackextension;
	
	@Column(name = "timert1")
	private Integer timert1;
	
	@Column(name = "timerb")
	private Integer timerb;
	
	@Column(name = "qualifyfreq")
	private Integer qualifyfreq;
	
	@Column(name = "constantssrc", columnDefinition="enum('yes','no')")
	private String constantssrc;
	
	@Column(name = "contactpermit")
	private String contactpermit;
	
	@Column(name = "contactdeny")
	private String contactdeny;
	
	@Column(name = "usereqphone", columnDefinition="enum('yes','no')")
	private String usereqphone;
	
	@Column(name = "textsupport", columnDefinition="enum('yes','no')")
	private String textsupport;
	
	@Column(name = "faxdetect", columnDefinition="enum('yes','no')")
	private String faxdetect;
	
	@Column(name = "buggymwi", columnDefinition="enum('yes','no')")
	private String buggymwi;
	
	@Column(name = "auth")
	private String auth;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "trunkname")
	private String trunkname;
	
	@Column(name = "cid_number")
	private String cidNumber;
	
	@Column(name = "callingpres", columnDefinition="enum('allowed_not_screened','allowed_passed_screen','allowed_failed_screen','allowed','prohib_not_screened','prohib_passed_screen','prohib_failed_screen','prohib')")
	private String callingpres;
	
	@Column(name = "mohinterpret")
	private String mohinterpret;
	
	@Column(name = "mohsuggest")
	private String mohsuggest;
	
	@Column(name = "parkinglot")
	private String parkinglot;
	
	@Column(name = "hasvoicemail", columnDefinition="enum('yes','no')")
	private String hasvoicemail;
	
	@Column(name = "subscribemwi", columnDefinition="enum('yes','no')")
	private String subscribemwi;
	
	@Column(name = "vmexten")
	private String vmexten;
	
	@Column(name = "autoframing", columnDefinition="enum('yes','no')")
	private String autoframing;
	
	@Column(name = "rtpkeepalive")
	private Integer rtpkeepalive;
	
	@Column(name = "`call-limit`")
	private Integer callLimit;
	
	@Column(name = "g726nonstandard", columnDefinition="enum('yes','no')")
	private String g726nonstandard;
	
	@Column(name = "ignoresdpversion", columnDefinition="enum('yes','no')")
	private String ignoresdpversion;
	
	@Column(name = "allowtransfer", columnDefinition="enum('yes','no')")
	private String allowtransfer;
	
	@Column(name = "dynamic", columnDefinition="enum('yes','no')")
	private String dynamic;

	public RealtimeSipBuddy() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getFromUser() {
		return fromuser;
	}

	public void setFromUser(String fromuser) {
		this.fromuser = fromuser;
	}
	
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getMd5Secret() {
		return md5secret;
	}

	public void setMd5Secret(String md5secret) {
		this.md5secret = md5secret;
	}

	public String getDefaultuser() {
		return defaultuser;
	}

	public void setDefaultuser(String defaultuser) {
		this.defaultuser = defaultuser;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}

/*
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(10) NOT NULL,
`host` varchar(40) DEFAULT NULL,
`ipaddr` varchar(45) DEFAULT NULL,
`port` int(5) DEFAULT NULL,
`regseconds` int(11) DEFAULT NULL,
`defaultuser` varchar(10) DEFAULT NULL,
`fullcontact` varchar(35) DEFAULT NULL,
`regserver` varchar(20) DEFAULT NULL,
`useragent` varchar(20) DEFAULT NULL,
`lastms` int(11) DEFAULT NULL,
`type` enum('friend','user','peer') DEFAULT NULL,
`context` varchar(40) DEFAULT NULL,
`deny` varchar(40) DEFAULT NULL,
`permit` varchar(40) DEFAULT NULL,
`secret` varchar(40) DEFAULT NULL,
`md5secret` varchar(40) DEFAULT NULL,
`remotesecret` varchar(40) DEFAULT NULL,
`transport` enum('udp','tcp','udp,tcp','tcp,udp') DEFAULT NULL,
`dtmfmode` enum('rfc2833','info','shortinfo','inband','auto') DEFAULT NULL,
`directmedia` enum('yes','no','nonat','update') DEFAULT NULL,
`nat` enum('yes','no','never','route','force_rport,comedia') DEFAULT NULL,
`callgroup` varchar(40) DEFAULT NULL,
`pickupgroup` varchar(40) DEFAULT NULL,
`language` varchar(40) DEFAULT NULL,
`disallow` varchar(40) DEFAULT NULL,
`allow` varchar(40) DEFAULT NULL,
`insecure` varchar(40) DEFAULT NULL,
`trustrpid` enum('yes','no') DEFAULT NULL,
`progressinband` enum('yes','no','never') DEFAULT NULL,
`promiscredir` enum('yes','no') DEFAULT NULL,
`useclientcode` enum('yes','no') DEFAULT NULL,
`accountcode` varchar(40) DEFAULT NULL,
`setvar` varchar(40) DEFAULT NULL,
`callerid` varchar(40) DEFAULT NULL,
`externalauth` enum('yes','no') DEFAULT NULL,
`amaflags` varchar(40) DEFAULT NULL,
`callcounter` enum('yes','no') DEFAULT NULL,
`busylevel` int(11) DEFAULT NULL,
`allowoverlap` enum('yes','no') DEFAULT NULL,
`allowsubscribe` enum('yes','no') DEFAULT NULL,
`videosupport` enum('yes','no') DEFAULT NULL,
`maxcallbitrate` int(11) DEFAULT NULL,
`rfc2833compensate` enum('yes','no') DEFAULT NULL,
`mailbox` varchar(40) DEFAULT NULL,
`session-timers` enum('accept','refuse','originate') DEFAULT NULL,
`session-expires` int(11) DEFAULT NULL,
`session-minse` int(11) DEFAULT NULL,
`session-refresher` enum('uac','uas') DEFAULT NULL,
`t38pt_usertpsource` varchar(40) DEFAULT NULL,
`regexten` varchar(40) DEFAULT NULL,
`fromdomain` varchar(40) DEFAULT NULL,
`fromuser` varchar(40) DEFAULT NULL,
`qualify` varchar(40) DEFAULT NULL,
`defaultip` varchar(40) DEFAULT NULL,
`rtptimeout` int(11) DEFAULT NULL,
`rtpholdtimeout` int(11) DEFAULT NULL,
`sendrpid` enum('yes','no') DEFAULT NULL,
`outboundproxy` varchar(40) DEFAULT NULL,
`callbackextension` varchar(40) DEFAULT NULL,
`timert1` int(11) DEFAULT NULL,
`timerb` int(11) DEFAULT NULL,
`qualifyfreq` int(11) DEFAULT NULL,
`constantssrc` enum('yes','no') DEFAULT NULL,
`contactpermit` varchar(40) DEFAULT NULL,
`contactdeny` varchar(40) DEFAULT NULL,
`usereqphone` enum('yes','no') DEFAULT NULL,
`textsupport` enum('yes','no') DEFAULT NULL,
`faxdetect` enum('yes','no') DEFAULT NULL,
`buggymwi` enum('yes','no') DEFAULT NULL,
`auth` varchar(40) DEFAULT NULL,
`fullname` varchar(40) DEFAULT NULL,
`trunkname` varchar(40) DEFAULT NULL,
`cid_number` varchar(40) DEFAULT NULL,
`callingpres` enum('allowed_not_screened','allowed_passed_screen','allowed_failed_screen','allowed','prohib_not_screened','prohib_passed_screen','prohib_failed_screen','prohib') DEFAULT NULL,
`mohinterpret` varchar(40) DEFAULT NULL,
`mohsuggest` varchar(40) DEFAULT NULL,
`parkinglot` varchar(40) DEFAULT NULL,
`hasvoicemail` enum('yes','no') DEFAULT NULL,
`subscribemwi` enum('yes','no') DEFAULT NULL,
`vmexten` varchar(40) DEFAULT NULL,
`autoframing` enum('yes','no') DEFAULT NULL,
`rtpkeepalive` int(11) DEFAULT NULL,
`call-limit` int(11) DEFAULT NULL,
`g726nonstandard` enum('yes','no') DEFAULT NULL,
`ignoresdpversion` enum('yes','no') DEFAULT NULL,
`allowtransfer` enum('yes','no') DEFAULT NULL,
`dynamic` enum('yes','no') DEFAULT NULL,
*/
