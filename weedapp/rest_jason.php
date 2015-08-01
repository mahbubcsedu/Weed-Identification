<?php

$data="method=Notify
&Signature=vH6ZbE0NhkF/hfNyxz2OgmzXYKs=
&Timestamp=2006-05-23T23:22:30Z
&Version=2006-05-05
&Event.1.EventType=AssignmentAccepted
&Event.1.EventTime=2006-04-21T18:55:23Z
&Event.1.HITTypeId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL
&Event.1.HITId=2NKNJQ2172Z9ST88HYCCYEBU7JCQY2
&Event.1.AssignmentId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL
&Event.2.EventType=AssignmentReturned
&Event.2.EventTime=2006-04-21T18:55:23Z
&Event.2.HITTypeId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL
&Event.2.HITId=2NKNJQ2172Z9ST88HYCCYEBU7JCQY2
&Event.2.AssignmentId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL";


parse_str($data, $arr);
print_r($arr);
?>
