<html>

<html><head>
<meta name="keywords" content="">
<meta name="description" content="">
<meta http-equiv="content-type" content="text/html; charset=utf-8"><title>Grasslike Weeds Quiz Results</title>

<link href="http://fonts.googleapis.com/css?family=Oswald:400,300" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Abel%7CSatisfy" rel="stylesheet" type="text/css">
<link href="style.css" rel="stylesheet" type="text/css" media="screen">
</head>
<body>
<div id="wrapper">
<div id="header-wrapper">
<div id="header" class="container3">
<div id="logo">
<h1>Grasslike Weed Quiz Results</h1>
</div>
</div>
<div id="banner">
<div class="content"><img src="images/img02.jpg" alt="" height="400" width="1000"></div>
</div>
</div>

	<div id="page">
		
        <?php
            $totalQuestions = 10;
			
            $answer1 = $_POST['question-1-answers'];
			$answer2 = $_POST['question-2-answers'];
			$answer3 = $_POST['question-3-answers'];
			$answer4 = $_POST['question-4-answers'];
			$answer5 = $_POST['question-5-answers'];
			$answer6 = $_POST['question-6-answers'];
			$answer7 = $_POST['question-7-answers'];
			$answer8 = $_POST['question-8-answers'];
			$answer9 = $_POST['question-9-answers'];
			$answer10 = $_POST['question-10-answers'];
			
            $totalCorrect = 0;
            
            if ($answer1 == "A") { $totalCorrect++; }
			if ($answer2 == "B") { $totalCorrect++; }
			if ($answer3 == "B") { $totalCorrect++; }
			if ($answer4 == "A") { $totalCorrect++; }
			if ($answer5 == "B") { $totalCorrect++; }
			if ($answer6 == "A") { $totalCorrect++; }
			if ($answer7 == "A") { $totalCorrect++; }
			if ($answer8 == "B") { $totalCorrect++; }
			if ($answer9 == "A") { $totalCorrect++; }
			if ($answer10 == "A") { $totalCorrect++; }
			
			$score = number_format((($totalCorrect / $totalQuestions) * 100), 2);
			
			
            echo "<div id='results'> <br/>You got $totalCorrect out of $totalQuestions correct, for a score of $score. </div>";
            
        ?>
	
	</div>
	
</body>

<div id="footer">
<p>Copyright (c) 2013 MPSS. All rights reserved.</p>
</div>