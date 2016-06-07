<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>Activities Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" type="text/css" href="css/default.css"/>
	<link rel="stylesheet" type="text/css" href="css/defaultTable.css"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
		
	<script src="js/activities.js"></script>
		
    </head>
    <body ng-app="" ng-controller="activityController">    
    <input type="hidden" id="orgId" value="1" />
        <form action="" class="register" >
			
            <h1>Activity</h1>
            <div class="row-fluid">
            
                <legend>Activity Primary Details
                </legend>
                
                	<div class="span5">
                    <label>Activity Name *
                    </label>
                    <input type="text" ng-model="newActivity.activityName"/>
                    </div>
                    <div class="span5">
                    <label>Activity Code *
                    </label>
                    <input type="text"  ng-model="newActivity.activityCode" />
                    </div>
                    <div class="span5">
                     <label>Activity Description *
                    </label>
                    <input type="text"  ng-model="newActivity.activityDescription" />
                    </div>
                </p>
           
            </div>
	    <button class="button" ng-click="saveRecord()">Create or Update &raquo;</button>
		<button class="button" ng-click="newRecord()">New &raquo;</button>
	    <fieldset class="row4">
	   
	    <div class="tbl_container">
                <table border="1">
                <tr>
                    <th style="width:150px"><legend><b>Activity Name</b></legend></th>
                    <th style="width:150px"><legend>Activity Code</legend></th>
                    <th style="width:150px"><legend>Activity Description</legend></th>
                </tr>
                <tr ng-repeat="activity in activities">
                    <td><label>{{ activity.activityName }}</label></td>
                    <td><label>{{ activity.activityCode }}</label></td>
                    <td><label>{{ activity.activityDescription }}</label></td>
                    <td>
                        <a  href="#" ng-click="edit(activity.id)">edit</a> | 
                        <a href="#" ng-click="deleteActivity(activity.id)">delete</a>
                    </td>
                </tr>
            </table>
                </div>
                
            </fieldset>
            
            
	    
        </form>
	<br/>
	
	
	
    </body>
</html>





