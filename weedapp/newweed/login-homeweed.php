
<?PHP
require_once("./include/membersite_config.php");

if(!$fgmembersite->CheckLogin())
{
    $fgmembersite->RedirectToURL("login.php");
    exit;
}

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="ctl00_ctl00_Head1"><title>
	Introduction-Geospatial Technologies
</title><link rel="stylesheet" type="text/css" href="http://www.uaex.edu/stylesheet.css" /><link REL="SHORTCUT ICON" href="http://www.uaex.edu/favicon.ico" />
    
<meta name="Description" content="University of Arkansas Cooperative Extension Service Departments - Geospatial Technology - Introduction "/>
<meta name="Keywords" content="UofA,university,arkansas,division,agriculture,cooperative,extension,service,departments,Geospatial,GIS,GPS,introduction"/>


    <style type="text/css">
                    .rounded_STYLE
            {
            border: 1px solid black;
            -webkit-border-radius: 5px; /* for Safari */
            -moz-border-radius: 5px;
        }
    </style>
    
    <script type="text/javascript">
      function openLinkInNewWindowFB()
	    {
	        var pageURL = 'http://www.facebook.com/group.php?gid=108540139201652';
	        window.open(pageURL, "_blank");
	    }
	    function openLinkInNewWindowTw() {
	        var pageURL = 'http://twitter.com/UAEXGeospatial';
	        window.open(pageURL, "_blank");
	    }
	    function openLinkInNewWindowMobile() {
	        var pageURL = 'http://baegrisk.ddns.uark.edu/mobile/';
	        window.open(pageURL, "_blank");
	    }
    </script>
    
    <script type="text/javascript">//Google Analytics code ID
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-16986978-1']);
      _gaq.push(['_trackPageview']);

      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();

    </script>

</head>


<body id="ctl00_ctl00_MBody1">
    <form name="aspnetForm" method="post" action="default.aspx" id="aspnetForm">
<div>
<input type="hidden" name="ctl00_ctl00_ContentPlaceHolder1Main_ContentPlaceHolder1_ToolkitScriptManager1_HiddenField" id="ctl00_ctl00_ContentPlaceHolder1Main_ContentPlaceHolder1_ToolkitScriptManager1_HiddenField" value="" />
<input type="hidden" name="__EVENTTARGET" id="__EVENTTARGET" value="" />
<input type="hidden" name="__EVENTARGUMENT" id="__EVENTARGUMENT" value="" />
<input type="hidden" name="__VIEWSTATE" id="__VIEWSTATE" value="/wEPDwUKMTcwOTQ2MzcxMmQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgIFF2N0bDAwJGN0bDAwJGJ0bkZhY2Vib29rBRZjdGwwMCRjdGwwMCRidG5Ud2l0dGVy6JmQlhi7FNPkTBJ4+ytN3vNhP20=" />
</div>

<script type="text/javascript">
//<![CDATA[
var theForm = document.forms['aspnetForm'];
if (!theForm) {
    theForm = document.aspnetForm;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
//]]>
</script>


<script src="/geospatial/WebResource.axd?d=-wjdgmtuWRPPz5J7tHZ6aaP8dLZTAeKTuCA7CbsKu6wdIID_hfbE5vP1Bjw_2n9p4MVl7yvSqW8Vrt9k1tpej27PZaA1&amp;t=634605294834856163" type="text/javascript"></script>


<script src="/geospatial/ScriptResource.axd?d=qrK9MH2dq3KFQVc7Cutlw0hd-oeZ4xYi57bU22tsHjvet_7_soeK3dhXrWbhb_LGVYUYbGAtVa6Q_YNSBp4pR4rIa-f6Kl7xErzVR4E1ZJ9MjcNhfQvQ42Mz9Gx9giiVuZ1xMA2&amp;t=ffffffffd40de07f" type="text/javascript"></script>
<script type="text/javascript">
//<![CDATA[
if (typeof(Sys) === 'undefined') throw new Error('ASP.NET Ajax client-side framework failed to load.');
//]]>
</script>

<script src="/geospatial/ScriptResource.axd?d=cMdxa2REgGvffkO4641TAy8gLsNUXiDDofWKq31mA_CPaFApMAnZgFsXXF9Go-f6BitlifjXtoSvAfE8OtQeLxOQ-NZbcjTC3QRg7yvrJXoMSQFjIcE6KJb-BcAUD3CVYKCKAw2&amp;t=ffffffffd40de07f" type="text/javascript"></script>
<script src="/geospatial/default.aspx?_TSM_HiddenField_=ctl00_ctl00_ContentPlaceHolder1Main_ContentPlaceHolder1_ToolkitScriptManager1_HiddenField&amp;_TSM_CombinedScripts_=%3b%3bAjaxControlToolkit%2c+Version%3d3.5.40412.0%2c+Culture%3dneutral%2c+PublicKeyToken%3d28f01b0e84b6d53e%3aen-US%3a1547e793-5b7e-48fe-8490-03a375b13a33%3af2c8e708%3ade1feab2%3af9cec9bc%3af4fab6f6" type="text/javascript"></script>
<div>

	<input type="hidden" name="__EVENTVALIDATION" id="__EVENTVALIDATION" value="/wEWAwKM9f3+BgKIwKbaAwKXzNOgC7v5V7OQhEamEZEyXbt3xPMtzFPK" />
</div>
    <div>
        <table border="0" id="Table1" cellpadding="0" style="border-collapse: collapse; width: 100%;">
        <tr> 
			<td width="50%" valign="top"> 
			<p class="whitealt"> 
			<a href="http://division.uaex.edu/"> 
            <img border="0" src="http://www.uaex.edu/_includes/images/DivLogo.jpg" alt="U of A University of Arkansas Division of Agriculture" width="271" height="40" /></a><br/> 
			<br/> 
			
			Pictures 
			of chickens, flowers, wheat, a boy looking through a magnifying glass, irrigation pipe, soybean pods, and fruits and vegetables.</td> 
			<td width="50%" valign="bottom"> 
			    <p class="header">Geospatial Technology</p>
			</td> 
		</tr>
		<tr> 
			<td align="center" width="100%" colspan="2"> 
			    <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="100%" height="3" alt=" "/>
			</td> 
		</tr> 
		 
		<tr> 
			<td width="100%" colspan="2" height="11" bgcolor="#980000"> 
			</td> 
		</tr>
		<div id="MainPageTopPicDiv"> 
		<tr> 
		    
			<td width="100%" colspan="2" class="ext" style="background-image: url('http://www.uaex.edu/_includes/images/ExtPhotos.jpg')"> 
			</td> 
			
		</tr> 
		<tr> 
			<td align="center" width="100%" colspan="2"> 
			<img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="100%" height="3" alt=" "/></td> 
		</tr>  
		<tr> 
			<td class="trshade" align="center" nowrap="nowrap"> 
			    <p class="white"> 
			        <a class="toplink" href="http://www.uaex.edu/">Cooperative Extension Service</a><br/> 
			        <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="100%" height="3" alt=" "/>
			    </p>
			</td> 
			<td class="trshade" align="center" nowrap="nowrap"> 
			    <a class="toplink" href="http://aaes.uark.edu">Agricultural Experiment Station</a><br/> 
			    <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="100%" height="3" alt=" "/>
			</td> 
		</tr> 
		<tr >
                <td colspan="2" align="right">
                    <p > 
		        	    <a class="anchor" href="http://www.uaex.edu/search.htm">Search</a> |
		        	    <a class="anchor" href="http://division.uaex.edu/publications.htm">Publications</a> | 
		        	    <a class="anchor" href="http://division.uaex.edu/jobs.htm">Jobs</a> | 
            		    <a class="anchor" href="http://personnel.uaex.edu/">Personnel Directory</a> |
            		    <a class="anchor" href="http://division.uaex.edu/links.htm">Links</a><br/>
            		    <a class="anchor" href="http://www.uaex.edu/findus/county_offices.htm">County Offices</a> |
            		    <a class="anchor" href="http://www.uaex.edu/depts/default.htm">Departments</a>
                    </p> 
                </td>
            </tr>       
		</div>
    </table>
    <br />
    <!--Middle Content -->
    <table border="0" cellpadding="4" style="border-collapse: collapse; width: 100%;"  
            cellspacing="0">
        <tr>
            
            <!-- Left navigation Menu -->
          <td width="20%" valign="top">
                <div id="LeftMenuDiv">
               <p >
                    <a class="menu" href="default.aspx">Login</a><br/>
                    <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="90%" height="1" alt=" "><br/> 
                </p>
              
                <p>
                    <a class="menu" href="MapSelectionKml.aspx"  >Registration</a><br/>
                    <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="90%" height="1" alt=" "><br/> 
                </p>
                <p>
                    <a class="menu" href="Geo_Res.aspx" >New Weeds</a><br/>
                  <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="90%" height="1" alt=" "><br/> 
                </p>
                <table>
                <tr>
                <td>&nbsp;</td>
                <td>
                    <span id="ctl00_ctl00_lblGab" style="display:inline-block;width:20px;"></span>
                </td>
                <td>&nbsp;</td>
                </tr>
                </table>
                
                
                </div>
            </td>
            
            <!-- Main content -->
            <td width="65%" valign="top">
                
    <table border="0" cellpadding="4" style="border-collapse: collapse; width: 100%;"  cellspacing="0">
   <tr>
    <td width="65%" valign="top">
                
    <!-- Main Content -->
    
    <h3 align="left">Available New Weeds</h3>
    <p class="body">There may have some new weeds available for review. After reviewing the the responses of the experts the admin can select the proper information and then submit to make this available for permanent use in weed database.     
    <p class="body">    
    <p class="body">Please first login to access the page.      <br />
      <br />
      <script type="text/javascript">
//<![CDATA[
Sys.WebForms.PageRequestManager._initialize('ctl00$ctl00$ContentPlaceHolder1Main$ContentPlaceHolder1$ToolkitScriptManager1', document.getElementById('aspnetForm'));
Sys.WebForms.PageRequestManager.getInstance()._updateControls([], [], [], 90);
//]]>
        </script>
      
      
    <br />
        <br />
    <h2>&nbsp;</h2>
    </td>
            <!-- Right cell contents -->
            
            <td valign="top" width="15%">
                        <div id="RightMenDiv">
                        <table frame="hsides" border="0" width="250px" height="200px" align="center" >
                            <tr >
                                <td colspan="2" align="center">
                                <center><h2>Geospatial News</h2>
                                            <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="190px" height="1" alt=" "> <br />
                                 </center>
                                </td>
                            
                            </tr>
                            <tr>
                                <td >
                                    <br/>
                                    <center>
                                    <marquee behavior='scroll' align='center'  direction='up'  height="210px" width="190px" scrollamount='1' scrolldelay='70' truespeed onmouseover=this.stop() onmouseout=this.start() > 
                                    <div id="ctl00_ctl00_ContentPlaceHolder1Main_newsTab"></div></marquee> 
                                    </center>
                                </td>
                            </tr>
                            <tr>
                                <td  align="center" colspan="2">
                                    <br/>
                                    <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="190px" height="1" alt=" "><br/> 
                                    <br/>
                                    <table width="190px" height="160px" align="center" style="padding-left: 4px; padding-top: 4px; padding-bottom: 4px;  background-position: center bottom; background-repeat: repeat-x; height: 170px; padding-right: 14px; padding-bottom: 4px; padding-left: 5px;"> 
                                        <tr>
                                            <td >
				                                    <center style="background-color: #FFFFFF"> 
                                                            <iframe  frameborder="1"  width="154px"  id="myenvironment01" scrolling="no" marginwidth="3" marginheight="0" src="http://www.epa.gov/enviro/widgets/myenv.html"> </iframe> 
                                                    </center> 
                                            </td>
                                        </tr>
                                        
                                        <!-- ContentPlaceHolder2 -->
                                        <tr>
                                            <td width="190px" valign="top" align="center" >
                                            <center>
                                                

                                              </center>
                                            </td>
                                        </tr>
                                    </table>

                                </td>
                            </tr> 
                            
                                        <tr>
                                            
                                            <td align="center" colspan="2"><br />
                                            <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="190px" height="1" alt=" ">
                                            <br /><br />
                                            <a target="_blank" class="body" href="http://geospatialextension.org/"> National Geospatial <br/>
                                            Technology Extension <br/>
                                            Network</a>
                                            <br/><br/>
                                            </td>                                        
                                        </tr>
                        </table>
                        </div>
            </td>   
   
   </tr>         
            
</table>



            </td>
            <!-- Right cell contents -->
            
        </tr>
        </table>
        
        <!-- Footer -->
        <table width="100%">
        <tr>
        <td>
        <div id="fotter" align="center">
            <table width=100%>
                <tr >     
                    <td colspan="3" width="100%" >
                        <img border="0" src="http://www.uaex.edu/_includes/images/hrline.jpg" width="100%" height="1" alt=" ">
                    </td>                
                </tr>
                <tr>
                    <td valign="top">
                        <p class="smaller" align="left" valign="top"> 
                            University of Arkansas <br/> 
		                    Division of Agriculture<br/> 
		                    All rights reserved.<br/>
		                    Last Date Modified 05/18/2010 <br/> 
		                    <a class="anchor" href="http://www.uaex.edu/webmaster_email.htm">Webmaster</a> 
		                </p>
                    </td>
                    <td>
                        <p class="smaller" align="center" valign="top">
                            University of Arkansas &#8226; Division of Agriculture<br/> 
                            Cooperative Extension Service<br/> 
                            2301 South University Avenue<br/> 
                            Little Rock, Arkansas 72204 &#8226 USA<br/> 
                            Phone (501) 671-2000<br/> 
                        </p>
                    </td>
                    <td valign="top">
                        <p align="center">
                            <a class="anchor"  href="http://division.uaex.edu/statements.htm">Mission</a>&#8226;
			                <a class="anchor"  href="http://division.uaex.edu/statements.htm#Disclaimer">Disclaimer</a>&#8226; 
		                    <a class="anchor"  href="http://division.uaex.edu/statements.htm#EEO">EEO</a>&#8226; <br/>
		                    <a class="anchor"  href="http://division.uaex.edu/statements.htm#Privacy Statement">Privacy</a>&#8226; 
		                    <a class="anchor"  href="http://division.uaex.edu/statements.htm#FOI">FOI</a>
                        </p>
                    </td>
                </tr>
            </table>
            </div>
            </td>
        </tr>
    </table>   
    </div>
    

<script type="text/javascript">
//<![CDATA[
(function() {var fn = function() {$get("ctl00_ctl00_ContentPlaceHolder1Main_ContentPlaceHolder1_ToolkitScriptManager1_HiddenField").value = '';Sys.Application.remove_init(fn);};Sys.Application.add_init(fn);})();Sys.Application.initialize();
Sys.Application.add_init(function() {
    $create(Sys.Extended.UI.SlideShowBehavior, {"autoPlay":true,"id":"ctl00_ctl00_ContentPlaceHolder1Main_ContentPlaceHolder1_SlideShowExtender2","loop":true,"slideShowServiceMethod":"GetSlides","slideShowServicePath":"SlidesService.asmx"}, null, null, $get("ctl00_ctl00_ContentPlaceHolder1Main_ContentPlaceHolder1_imgSlideShow"));
});
//]]>
</script>
</form>
</body>
</html>
