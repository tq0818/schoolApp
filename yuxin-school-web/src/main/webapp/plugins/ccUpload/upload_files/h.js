(function(){var h={},mt={},c={id:"2218aea368af3339dbc1a33af92398e4",dm:["bokecc.com"],js:"tongji.baidu.com/hm-web/js/",etrk:[],icon:'',ctrk:false,align:-1,nv:-1,vdur:1800000,age:31536000000,rec:0,rootPath:[],trust:0,vcard:0,qiao:0,lxb:0,conv:0,comm:0,apps:''};var p=!0,q=null,r=!1;mt.i={};mt.i.Fa=/msie (\d+\.\d+)/i.test(navigator.userAgent);mt.i.cookieEnabled=navigator.cookieEnabled;mt.i.javaEnabled=navigator.javaEnabled();mt.i.language=navigator.language||navigator.browserLanguage||navigator.systemLanguage||navigator.userLanguage||"";mt.i.na=(window.screen.width||0)+"x"+(window.screen.height||0);mt.i.colorDepth=window.screen.colorDepth||0;mt.cookie={};
mt.cookie.set=function(a,b,e){var d;e.B&&(d=new Date,d.setTime(d.getTime()+e.B));document.cookie=a+"="+b+(e.domain?"; domain="+e.domain:"")+(e.path?"; path="+e.path:"")+(d?"; expires="+d.toGMTString():"")+(e.Ja?"; secure":"")};mt.cookie.get=function(a){return(a=RegExp("(^| )"+a+"=([^;]*)(;|$)").exec(document.cookie))?a[2]:q};mt.p={};mt.p.Aa=function(a){return document.getElementById(a)};mt.p.Ba=function(a,b){for(b=b.toUpperCase();(a=a.parentNode)&&1==a.nodeType;)if(a.tagName==b)return a;return q};
(mt.p.la=function(){function a(){if(!a.s){a.s=p;for(var b=0,e=d.length;b<e;b++)d[b]()}}function b(){try{document.documentElement.doScroll("left")}catch(d){setTimeout(b,1);return}a()}var e=r,d=[],k;document.addEventListener?k=function(){document.removeEventListener("DOMContentLoaded",k,r);a()}:document.attachEvent&&(k=function(){"complete"===document.readyState&&(document.detachEvent("onreadystatechange",k),a())});(function(){if(!e)if(e=p,"complete"===document.readyState)a.s=p;else if(document.addEventListener)document.addEventListener("DOMContentLoaded",
k,r),window.addEventListener("load",a,r);else if(document.attachEvent){document.attachEvent("onreadystatechange",k);window.attachEvent("onload",a);var d=r;try{d=window.frameElement==q}catch(n){}document.documentElement.doScroll&&d&&b()}})();return function(b){a.s?b():d.push(b)}}()).s=r;mt.event={};mt.event.e=function(a,b,e){a.attachEvent?a.attachEvent("on"+b,function(b){e.call(a,b)}):a.addEventListener&&a.addEventListener(b,e,r)};
mt.event.preventDefault=function(a){a.preventDefault?a.preventDefault():a.returnValue=r};mt.l={};mt.l.parse=function(){return(new Function('return (" + source + ")'))()};
mt.l.stringify=function(){function a(a){/["\\\x00-\x1f]/.test(a)&&(a=a.replace(/["\\\x00-\x1f]/g,function(a){var b=e[a];if(b)return b;b=a.charCodeAt();return"\\u00"+Math.floor(b/16).toString(16)+(b%16).toString(16)}));return'"'+a+'"'}function b(a){return 10>a?"0"+a:a}var e={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"};return function(d){switch(typeof d){case "undefined":return"undefined";case "number":return isFinite(d)?String(d):"null";case "string":return a(d);case "boolean":return String(d);
default:if(d===q)return"null";if(d instanceof Array){var e=["["],l=d.length,n,f,m;for(f=0;f<l;f++)switch(m=d[f],typeof m){case "undefined":case "function":case "unknown":break;default:n&&e.push(","),e.push(mt.l.stringify(m)),n=1}e.push("]");return e.join("")}if(d instanceof Date)return'"'+d.getFullYear()+"-"+b(d.getMonth()+1)+"-"+b(d.getDate())+"T"+b(d.getHours())+":"+b(d.getMinutes())+":"+b(d.getSeconds())+'"';n=["{"];f=mt.l.stringify;for(l in d)if(Object.prototype.hasOwnProperty.call(d,l))switch(m=
d[l],typeof m){case "undefined":case "unknown":case "function":break;default:e&&n.push(","),e=1,n.push(f(l)+":"+f(m))}n.push("}");return n.join("")}}}();mt.lang={};mt.lang.d=function(a,b){return"[object "+b+"]"==={}.toString.call(a)};mt.lang.Ga=function(a){return mt.lang.d(a,"Number")&&isFinite(a)};mt.lang.Ia=function(a){return mt.lang.d(a,"String")};mt.localStorage={};
mt.localStorage.w=function(){if(!mt.localStorage.f)try{mt.localStorage.f=document.createElement("input"),mt.localStorage.f.type="hidden",mt.localStorage.f.style.display="none",mt.localStorage.f.addBehavior("#default#userData"),document.getElementsByTagName("head")[0].appendChild(mt.localStorage.f)}catch(a){return r}return p};
mt.localStorage.set=function(a,b,e){var d=new Date;d.setTime(d.getTime()+e||31536E6);try{window.localStorage?(b=d.getTime()+"|"+b,window.localStorage.setItem(a,b)):mt.localStorage.w()&&(mt.localStorage.f.expires=d.toUTCString(),mt.localStorage.f.load(document.location.hostname),mt.localStorage.f.setAttribute(a,b),mt.localStorage.f.save(document.location.hostname))}catch(k){}};
mt.localStorage.get=function(a){if(window.localStorage){if(a=window.localStorage.getItem(a)){var b=a.indexOf("|"),e=a.substring(0,b)-0;if(e&&e>(new Date).getTime())return a.substring(b+1)}}else if(mt.localStorage.w())try{return mt.localStorage.f.load(document.location.hostname),mt.localStorage.f.getAttribute(a)}catch(d){}return q};
mt.localStorage.remove=function(a){if(window.localStorage)window.localStorage.removeItem(a);else if(mt.localStorage.w())try{mt.localStorage.f.load(document.location.hostname),mt.localStorage.f.removeAttribute(a),mt.localStorage.f.save(document.location.hostname)}catch(b){}};mt.sessionStorage={};mt.sessionStorage.set=function(a,b){if(window.sessionStorage)try{window.sessionStorage.setItem(a,b)}catch(e){}};
mt.sessionStorage.get=function(a){return window.sessionStorage?window.sessionStorage.getItem(a):q};mt.sessionStorage.remove=function(a){window.sessionStorage&&window.sessionStorage.removeItem(a)};mt.Q={};mt.Q.log=function(a,b){var e=new Image,d="mini_tangram_log_"+Math.floor(2147483648*Math.random()).toString(36);window[d]=e;e.onload=e.onerror=e.onabort=function(){e.onload=e.onerror=e.onabort=q;e=window[d]=q;b&&b(a)};e.src=a};mt.I={};
mt.I.da=function(){var a="";if(navigator.plugins&&navigator.mimeTypes.length){var b=navigator.plugins["Shockwave Flash"];b&&b.description&&(a=b.description.replace(/^.*\s+(\S+)\s+\S+$/,"$1"))}else if(window.ActiveXObject)try{if(b=new ActiveXObject("ShockwaveFlash.ShockwaveFlash"))(a=b.GetVariable("$version"))&&(a=a.replace(/^.*\s+(\d+),(\d+).*$/,"$1.$2"))}catch(e){}return a};
mt.I.ya=function(a,b,e,d,k){return'<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" id="'+a+'" width="'+e+'" height="'+d+'"><param name="movie" value="'+b+'" /><param name="flashvars" value="'+(k||"")+'" /><param name="allowscriptaccess" value="always" /><embed type="application/x-shockwave-flash" name="'+a+'" width="'+e+'" height="'+d+'" src="'+b+'" flashvars="'+(k||"")+'" allowscriptaccess="always" /></object>'};mt.url={};
mt.url.h=function(a,b){var e=a.match(RegExp("(^|&|\\?|#)("+b+")=([^&#]*)(&|$|#)",""));return e?e[3]:q};mt.url.Da=function(a){return(a=a.match(/^(https?:)\/\//))?a[1]:q};mt.url.aa=function(a){return(a=a.match(/^(https?:\/\/)?([^\/\?#]*)/))?a[2].replace(/.*@/,""):q};mt.url.K=function(a){return(a=mt.url.aa(a))?a.replace(/:\d+$/,""):a};mt.url.Ca=function(a){return(a=a.match(/^(https?:\/\/)?[^\/]*(.*)/))?a[2].replace(/[\?#].*/,"").replace(/^$/,"/"):q};
h.o={Ea:"http://tongji.baidu.com/hm-web/welcome/ico",P:"log.hm.baidu.com/hm.gif",T:"baidu.com",ga:"hmmd",ha:"hmpl",fa:"hmkw",ea:"hmci",ia:"hmsr",m:0,j:Math.round(+new Date/1E3),protocol:"https:"==document.location.protocol?"https:":"http:",Ha:0,va:6E5,wa:10,xa:1024,ua:1,O:2147483647,R:"cc cf ci ck cl cm cp cw ds ep et fl ja ln lo lt nv rnd si st su v cv lv api tt u".split(" ")};
(function(){var a={k:{},e:function(a,e){this.k[a]=this.k[a]||[];this.k[a].push(e)},q:function(a,e){this.k[a]=this.k[a]||[];for(var d=this.k[a].length,k=0;k<d;k++)this.k[a][k](e)}};return h.A=a})();
(function(){function a(a,d){var k=document.createElement("script");k.charset="utf-8";b.d(d,"Function")&&(k.readyState?k.onreadystatechange=function(){if("loaded"===k.readyState||"complete"===k.readyState)k.onreadystatechange=q,d()}:k.onload=function(){d()});k.src=a;var l=document.getElementsByTagName("script")[0];l.parentNode.insertBefore(k,l)}var b=mt.lang;return h.load=a})();
(function(){function a(){return function(){h.b.a.nv=0;h.b.a.st=4;h.b.a.et=3;h.b.a.ep=h.z.ba()+","+h.z.$();h.b.g()}}function b(){clearTimeout(x);var a;w&&(a="visible"==document[w]);y&&(a=!document[y]);f="undefined"==typeof a?p:a;if((!n||!m)&&f&&g)v=p,u=+new Date;else if(n&&m&&(!f||!g))v=r,s+=+new Date-u;n=f;m=g;x=setTimeout(b,100)}function e(a){var u=document,m="";if(a in u)m=a;else for(var s=["webkit","ms","moz","o"],b=0;b<s.length;b++){var g=s[b]+a.charAt(0).toUpperCase()+a.slice(1);if(g in u){m=
g;break}}return m}function d(a){if(!("focus"==a.type||"blur"==a.type)||!(a.target&&a.target!=window))g="focus"==a.type||"focusin"==a.type?p:r,b()}var k=mt.event,l=h.A,n=p,f=p,m=p,g=p,t=+new Date,u=t,s=0,v=p,w=e("visibilityState"),y=e("hidden"),x;b();(function(){var a=w.replace(/[vV]isibilityState/,"visibilitychange");k.e(document,a,b);k.e(window,"pageshow",b);k.e(window,"pagehide",b);"object"==typeof document.onfocusin?(k.e(document,"focusin",d),k.e(document,"focusout",d)):(k.e(window,"focus",d),
k.e(window,"blur",d))})();h.z={ba:function(){return+new Date-t},$:function(){return v?+new Date-u+s:s}};l.e("pv-b",function(){k.e(window,"unload",a())});return h.z})();
(function(){var a=mt.lang,b=h.o,e=h.load,d={ka:function(d){if((void 0===window._dxt||a.d(window._dxt,"Array"))&&"undefined"!==typeof h.b){var l=h.b.C();e([b.protocol,"//datax.baidu.com/x.js?si=",c.id,"&dm=",encodeURIComponent(l)].join(""),d)}},ta:function(b){if(a.d(b,"String")||a.d(b,"Number"))window._dxt=window._dxt||[],window._dxt.push(["_setUserId",b])}};return h.W=d})();
(function(){function a(m){for(var b in m)if({}.hasOwnProperty.call(m,b)){var d=m[b];e.d(d,"Object")||e.d(d,"Array")?a(d):m[b]=String(d)}}function b(a){return a.replace?a.replace(/'/g,"'0").replace(/\*/g,"'1").replace(/!/g,"'2"):a}var e=mt.lang,d=mt.l,k=h.o,l=h.A,n=h.W,f={L:q,n:[],t:0,M:r,init:function(){f.c=0;f.L={push:function(){f.G.apply(f,arguments)}};l.e("pv-b",function(){f.X();f.Y()});l.e("pv-d",f.Z);l.e("stag-b",function(){h.b.a.api=f.c||f.t?f.c+"_"+f.t:""});l.e("stag-d",function(){h.b.a.api=
0;f.c=0;f.t=0})},X:function(){var a=window._hmt;if(a&&a.length)for(var b=0;b<a.length;b++){var d=a[b];switch(d[0]){case "_setAccount":1<d.length&&/^[0-9a-z]{32}$/.test(d[1])&&(f.c|=1,window._bdhm_account=d[1]);break;case "_setAutoPageview":if(1<d.length&&(d=d[1],r===d||p===d))f.c|=2,window._bdhm_autoPageview=d}}},Y:function(){if("undefined"===typeof window._bdhm_account||window._bdhm_account===c.id){window._bdhm_account=c.id;var a=window._hmt;if(a&&a.length)for(var b=0,d=a.length;b<d;b++)e.d(a[b],
"Array")&&"_trackEvent"!==a[b][0]&&"_trackRTEvent"!==a[b][0]?f.G(a[b]):f.n.push(a[b]);window._hmt=f.L}},Z:function(){if(0<f.n.length)for(var a=0,b=f.n.length;a<b;a++)f.G(f.n[a]);f.n=q},G:function(a){if(e.d(a,"Array")){var b=a[0];if(f.hasOwnProperty(b)&&e.d(f[b],"Function"))f[b](a)}},_trackPageview:function(a){if(1<a.length&&a[1].charAt&&"/"==a[1].charAt(0)){f.c|=4;h.b.a.et=0;h.b.a.ep="";h.b.D?(h.b.a.nv=0,h.b.a.st=4):h.b.D=p;var b=h.b.a.u,d=h.b.a.su;h.b.a.u=k.protocol+"//"+document.location.host+a[1];
f.M||(h.b.a.su=document.location.href);h.b.g();h.b.a.u=b;h.b.a.su=d}},_trackEvent:function(a){2<a.length&&(f.c|=8,h.b.a.nv=0,h.b.a.st=4,h.b.a.et=4,h.b.a.ep=b(a[1])+"*"+b(a[2])+(a[3]?"*"+b(a[3]):"")+(a[4]?"*"+b(a[4]):""),h.b.g())},_setCustomVar:function(a){if(!(4>a.length)){var d=a[1],e=a[4]||3;if(0<d&&6>d&&0<e&&4>e){f.t++;for(var u=(h.b.a.cv||"*").split("!"),s=u.length;s<d-1;s++)u.push("*");u[d-1]=e+"*"+b(a[2])+"*"+b(a[3]);h.b.a.cv=u.join("!");a=h.b.a.cv.replace(/[^1](\*[^!]*){2}/g,"*").replace(/((^|!)\*)+$/g,
"");""!==a?h.b.setData("Hm_cv_"+c.id,encodeURIComponent(a),c.age):h.b.ma("Hm_cv_"+c.id)}}},_setReferrerOverride:function(a){1<a.length&&(h.b.a.su=a[1].charAt&&"/"==a[1].charAt(0)?k.protocol+"//"+window.location.host+a[1]:a[1],f.M=p)},_trackOrder:function(b){b=b[1];e.d(b,"Object")&&(a(b),f.c|=16,h.b.a.nv=0,h.b.a.st=4,h.b.a.et=94,h.b.a.ep=d.stringify(b),h.b.g())},_trackMobConv:function(a){if(a={webim:1,tel:2,map:3,sms:4,callback:5,share:6}[a[1]])f.c|=32,h.b.a.et=93,h.b.a.ep=a,h.b.g()},_trackRTPageview:function(b){b=
b[1];e.d(b,"Object")&&(a(b),b=d.stringify(b),512>=encodeURIComponent(b).length&&(f.c|=64,h.b.a.rt=b))},_trackRTEvent:function(b){b=b[1];if(e.d(b,"Object")){a(b);b=encodeURIComponent(d.stringify(b));var g=function(a){var b=h.b.a.rt;f.c|=128;h.b.a.et=90;h.b.a.rt=a;h.b.g();h.b.a.rt=b},t=b.length;if(900>=t)g.call(this,b);else for(var t=Math.ceil(t/900),u="block|"+Math.round(Math.random()*k.O).toString(16)+"|"+t+"|",s=[],v=0;v<t;v++)s.push(v),s.push(b.substring(900*v,900*v+900)),g.call(this,u+s.join("|")),
s=[]}},_setUserId:function(a){a=a[1];n.ka();n.ta(a)}};f.init();h.U=f;return h.U})();
(function(){function a(){"undefined"==typeof window["_bdhm_loaded_"+c.id]&&(window["_bdhm_loaded_"+c.id]=p,this.a={},this.D=r,this.init())}var b=mt.url,e=mt.Q,d=mt.I,k=mt.lang,l=mt.cookie,n=mt.i,f=mt.localStorage,m=mt.sessionStorage,g=h.o,t=h.A;a.prototype={F:function(a,b){a="."+a.replace(/:\d+/,"");b="."+b.replace(/:\d+/,"");var d=a.indexOf(b);return-1<d&&d+b.length==a.length},N:function(a,b){a=a.replace(/^https?:\/\//,"");return 0===a.indexOf(b)},r:function(a){for(var d=0;d<c.dm.length;d++)if(-1<
c.dm[d].indexOf("/")){if(this.N(a,c.dm[d]))return p}else{var e=b.K(a);if(e&&this.F(e,c.dm[d]))return p}return r},C:function(){for(var a=document.location.hostname,b=0,d=c.dm.length;b<d;b++)if(this.F(a,c.dm[b]))return c.dm[b].replace(/(:\d+)?[\/\?#].*/,"");return a},J:function(){for(var a=0,b=c.dm.length;a<b;a++){var d=c.dm[a];if(-1<d.indexOf("/")&&this.N(document.location.href,d))return d.replace(/^[^\/]+(\/.*)/,"$1")+"/"}return"/"},ca:function(){if(!document.referrer)return g.j-g.m>c.vdur?1:4;var a=
r;this.r(document.referrer)&&this.r(document.location.href)?a=p:(a=b.K(document.referrer),a=this.F(a||"",document.location.hostname));return a?g.j-g.m>c.vdur?1:4:3},getData:function(a){try{return l.get(a)||m.get(a)||f.get(a)}catch(b){}},setData:function(a,b,d){try{l.set(a,b,{domain:this.C(),path:this.J(),B:d}),d?f.set(a,b,d):m.set(a,b)}catch(e){}},ma:function(a){try{l.set(a,"",{domain:this.C(),path:this.J(),B:-1}),m.remove(a),f.remove(a)}catch(b){}},ra:function(){var a,b,d,e,f;g.m=this.getData("Hm_lpvt_"+
c.id)||0;13==g.m.length&&(g.m=Math.round(g.m/1E3));b=this.ca();a=4!=b?1:0;if(d=this.getData("Hm_lvt_"+c.id)){e=d.split(",");for(f=e.length-1;0<=f;f--)13==e[f].length&&(e[f]=""+Math.round(e[f]/1E3));for(;2592E3<g.j-e[0];)e.shift();f=4>e.length?2:3;for(1===a&&e.push(g.j);4<e.length;)e.shift();d=e.join(",");e=e[e.length-1]}else d=g.j,e="",f=1;this.setData("Hm_lvt_"+c.id,d,c.age);this.setData("Hm_lpvt_"+c.id,g.j);d=g.j==this.getData("Hm_lpvt_"+c.id)?"1":"0";if(0===c.nv&&this.r(document.location.href)&&
(""===document.referrer||this.r(document.referrer)))a=0,b=4;this.a.nv=a;this.a.st=b;this.a.cc=d;this.a.lt=e;this.a.lv=f},qa:function(){for(var a=[],b=0,d=g.R.length;b<d;b++){var e=g.R[b],f=this.a[e];"undefined"!=typeof f&&""!==f&&a.push(e+"="+encodeURIComponent(f))}b=this.a.et;this.a.rt&&(0===b?a.push("rt="+encodeURIComponent(this.a.rt)):90===b&&a.push("rt="+this.a.rt));return a.join("&")},sa:function(){this.ra();this.a.si=c.id;this.a.su=document.referrer;this.a.ds=n.na;this.a.cl=n.colorDepth+"-bit";
this.a.ln=n.language;this.a.ja=n.javaEnabled?1:0;this.a.ck=n.cookieEnabled?1:0;this.a.lo="number"==typeof _bdhm_top?1:0;this.a.fl=d.da();this.a.v="1.0.92";this.a.cv=decodeURIComponent(this.getData("Hm_cv_"+c.id)||"");1==this.a.nv&&(this.a.tt=document.title||"");var a=document.location.href;this.a.cm=b.h(a,g.ga)||"";this.a.cp=b.h(a,g.ha)||"";this.a.cw=b.h(a,g.fa)||"";this.a.ci=b.h(a,g.ea)||"";this.a.cf=b.h(a,g.ia)||""},init:function(){try{this.sa(),0===this.a.nv?this.pa():this.H(".*"),h.b=this,this.V(),
t.q("pv-b"),this.oa()}catch(a){var b=[];b.push("si="+c.id);b.push("n="+encodeURIComponent(a.name));b.push("m="+encodeURIComponent(a.message));b.push("r="+encodeURIComponent(document.referrer));e.log(g.protocol+"//"+g.P+"?"+b.join("&"))}},oa:function(){function a(){t.q("pv-d")}"undefined"===typeof window._bdhm_autoPageview||window._bdhm_autoPageview===p?(this.D=p,this.a.et=0,this.a.ep="",this.g(a)):a()},g:function(a){var b=this;b.a.rnd=Math.round(Math.random()*g.O);t.q("stag-b");var d=g.protocol+"//"+
g.P+"?"+b.qa();t.q("stag-d");b.S(d);e.log(d,function(d){b.H(d);k.d(a,"Function")&&a.call(b)})},V:function(){var a=document.location.hash.substring(1),d=RegExp(c.id),e=-1<document.referrer.indexOf(g.T)?p:r,f=b.h(a,"jn"),t=/^heatlink$|^select$/.test(f);a&&(d.test(a)&&e&&t)&&(a=document.createElement("script"),a.setAttribute("type","text/javascript"),a.setAttribute("charset","utf-8"),a.setAttribute("src",g.protocol+"//"+c.js+f+".js?"+this.a.rnd),f=document.getElementsByTagName("script")[0],f.parentNode.insertBefore(a,
f))},S:function(a){var b=m.get("Hm_unsent_"+c.id)||"",d=this.a.u?"":"&u="+encodeURIComponent(document.location.href),b=encodeURIComponent(a.replace(/^https?:\/\//,"")+d)+(b?","+b:"");m.set("Hm_unsent_"+c.id,b)},H:function(a){var b=m.get("Hm_unsent_"+c.id)||"";b&&((b=b.replace(RegExp(encodeURIComponent(a.replace(/^https?:\/\//,"")).replace(/([\*\(\)])/g,"\\$1")+"(%26u%3D[^,]*)?,?","g"),"").replace(/,$/,""))?m.set("Hm_unsent_"+c.id,b):m.remove("Hm_unsent_"+c.id))},pa:function(){var a=this,b=m.get("Hm_unsent_"+
c.id);if(b)for(var b=b.split(","),d=function(b){e.log(g.protocol+"//"+decodeURIComponent(b).replace(/^https?:\/\//,""),function(b){a.H(b)})},f=0,t=b.length;f<t;f++)d(b[f])}};return new a})();
(function(){var a=mt.p,b=mt.event,e=mt.url,d=mt.l;try{if(window.performance&&performance.timing&&"undefined"!==typeof h.b){var k=+new Date,l=function(a){var b=performance.timing,d=b[a+"Start"]?b[a+"Start"]:0;a=b[a+"End"]?b[a+"End"]:0;return{start:d,end:a,value:0<a-d?a-d:0}},n=q;a.la(function(){n=+new Date});var f=function(){var a,b,f;f=l("navigation");b=l("request");f={netAll:b.start-f.start,netDns:l("domainLookup").value,netTcp:l("connect").value,srv:l("response").start-b.start,dom:performance.timing.domInteractive-
performance.timing.fetchStart,loadEvent:l("loadEvent").end-f.start};a=document.referrer;var s=q;b=q;if("www.baidu.com"===(a.match(/^(http[s]?:\/\/)?([^\/]+)(.*)/)||[])[2])s=e.h(a,"qid"),b=e.h(a,"click_t");a=s;f.qid=a!=q?a:"";b!=q?(f.bdDom=n?n-b:0,f.bdRun=k-b,f.bdDef=l("navigation").start-b):(f.bdDom=0,f.bdRun=0,f.bdDef=0);h.b.a.et=87;h.b.a.ep=d.stringify(f);h.b.g()};b.e(window,"load",function(){setTimeout(f,500)})}}catch(m){}})();
(function(){var a=h.o,b={init:function(){try{if("http:"===a.protocol){var b=document.createElement("IFRAME");b.setAttribute("src","http://boscdn.bpc.baidu.com/v1/holmes-moplus/mp-cdn.html");b.style.display="none";b.style.width="1";b.style.height="1";b.za="0";document.body.appendChild(b)}}catch(e){}}},e=navigator.userAgent.toLowerCase();-1<e.indexOf("android")&&-1===e.indexOf("micromessenger")&&b.init()})();
(function(){var a=mt.lang,b=mt.event;if(c.comm&&"undefined"!==typeof h.b){var e=function(a){if(a.item){for(var b=a.length,d=Array(b);b--;)d[b]=a[b];return d}return[].slice.call(a)},d=/.*\/swt(\/)?([\?|#].*)?$/i,k={click:function(){for(var a=[],b=e(document.getElementsByTagName("a")),b=[].concat.apply(b,e(document.getElementsByTagName("area"))),b=[].concat.apply(b,e(document.getElementsByTagName("img"))),f=/openZoosUrl\(/,k=/\/LR\/Chatpre\.aspx/,g=0,m=b.length;g<m;g++){var l=b[g],n=l.getAttribute("onclick"),
l=l.getAttribute("href");(f.test(n)||k.test(l)||d.test(l))&&a.push(b[g])}return a}},l=function(a,b){for(var d in a)if(a.hasOwnProperty(d)&&b.call(a,d,a[d])===r)return r},n=function(a,b){if(b&&d.test(b.getAttribute("href"))){var e=b.getAttribute("href");a=["swt",e.replace?e.replace(/'/g,"'0").replace(/\*/g,"'1"):e,a].join("*")}h.b.a.et=86;h.b.a.ep=a;h.b.g();for(e=+new Date;500>=+new Date-e;);},f,m="/zoosnet"+(/\/$/.test("/zoosnet")?"":"/"),g=function(b,d){if(f===d)return n(m+b,d),r;if(a.d(d,"Array")||
a.d(d,"NodeList"))for(var e=0,g=d.length;e<g;e++)if(f===d[e])return n(m+b+"/"+(e+1),d[e]),r};b.e(document,"click",function(b){b=b||window.event;f=b.target||b.srcElement;var d={};for(l(k,function(b,e){d[b]=a.d(e,"Function")?e():document.getElementById(e)});f&&f!==document&&l(d,g)!==r;)f=f.parentNode})}})();})();
