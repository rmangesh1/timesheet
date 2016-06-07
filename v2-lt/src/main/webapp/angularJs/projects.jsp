<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>Activities Form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" type="text/css" href="css/default.css"/>
	<link rel="stylesheet" type="text/css" href="css/defaultTable.css"/>
	
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
		
	<script src="js/projects.js"></script>
		
    </head>
    <body ng-app="" ng-controller="projectController">    
    <input type="hidden" id="orgId" value="1" />
        <form action="" class="register" >
			
            <h1>Activity</h1>
            <div class="row-fluid">
            
                <legend>Project Primary Details
                </legend>
                
                 	<div class="span5">
                    <label>Project Name *
                    </label>
                    <input type="text" ng-model="newProject.projectName"/>
                    </div>
                    <div class="span5">
                    <label>Project Code *
                    </label>
                    <input type="text"  ng-model="newProject.projectCode" />
                    </div>
                    <div class="span5">
                     <label>Project Location 
                    </label>
                    <input type="text"  ng-model="newProject.location" />
                    </div>
                </p>
           
            </div>
	    <button class="button" ng-click="saveRecord()">Create or Update &raquo;</button>
		<button class="button" ng-click="newRecord()">New &raquo;</button>
	    <fieldset class="row4">
	   
	    <div class="tbl_container">
                <table border="1">
                <tr>
                    <th style="width:150px"><legend><b>Project Name</b></legend></th>
                    <th style="width:150px"><legend>Project Code</legend></th>
                    <th style="width:150px"><legend>Project Location</legend></th>
                </tr>
                <tr ng-repeat="project in projects">
                    <td><label>{{ project.projectName }}</label></td>
                    <td><label>{{ project.projectCode }}</label></td>
                    <td><label>{{ project.location }}</label></td>
                    <td>
                        <a  href="#" ng-click="edit(project.id)">edit</a> | 
                        <a href="#" ng-click="deleteProject(project.id)">delete</a>
                    </td>
                </tr>
            </table>
                </div>
                
            </fieldset>
            
            
	    
        </form>
	<br/>
	
	
	
    </body>
</html>





