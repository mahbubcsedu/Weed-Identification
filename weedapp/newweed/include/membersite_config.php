<?PHP
require_once("./include/fg_membersite.php");

$fgmembersite = new FGMembersite();

//Provide your site name here
$fgmembersite->SetWebsiteName('user11.com');

$fgmembersite->SetCapturedPath('http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/newweedimages/');
$fgmembersite->SetWeedDatabasePath('http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/weedimages/');

//Provide the email address where you want to get notifications
$fgmembersite->SetAdminEmail('mahbub2001@gmail.com');

//Provide your database login details here:
//hostname, user name, password, database name and table name
//note that the script will create the table (for example, fgusers in this case)
//by itself on submitting register.php for the first time
$fgmembersite->InitDB(/*hostname*/'**',
                      /*username*/'**',
                      /*password*/'**',
                      /*database name*/'weedidapp',
                      /*table name*/'newweedadmin');

//For better security. Get a random string from this link: http://tinyurl.com/randstr
// and put it here
$fgmembersite->SetRandomKey('qSRcVS6DrTzrPvr');

?>