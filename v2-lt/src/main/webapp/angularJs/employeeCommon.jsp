<div class="col-lg-10"><h4>Employee main details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">First Name</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required profileEditable" name="firstName" id="firstName" data-ng-model="newEmployee.firstName">
								</div>
						    </div>
						</div>

						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Last Name</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required profileEditable" name="lastName" id="lastName" data-ng-model="newEmployee.lastName">
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Address</label>
								<div class="col-lg-10">
							        <textarea class="form-control required profileEditable" name="address" id="address" rows="4" data-ng-model="newEmployee.address"></textarea>
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-10"><h4>Employee contact details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Primary Phone</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control required profileEditable" name="primaryPhone" id="primaryPhone" data-ng-model="newEmployee.primaryPhone">
								</div>
						    </div>
						</div>
						
						

						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Primary Email</label>
								<div class="col-lg-10">
							        <input type="email" class="form-control required" name="primaryEmail" id="primaryEmail" data-ng-model="newEmployee.primaryEmail">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Mobile</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control profileEditable" name="mobile" id="mobile" data-ng-model="newEmployee.mobile">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Website</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control profileEditable" name="website" id="website" data-ng-model="newEmployee.website">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Fax</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control profileEditable" name="fax" id="fax" data-ng-model="newEmployee.fax">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-10"><h4>Employee designation details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Type</label>
								<div class="col-lg-10">
							        <select name="deptSelect" data-ng-options="type as type for type in empScreenDetails.types" class="form-control required" data-ng-model="newEmployee.type" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Grade</label>
								<div class="col-lg-10">
							        <select name="deptSelect" data-ng-options="grade.id as grade.grade for grade in empScreenDetails.grades" class="form-control required" data-ng-model="newEmployee.grade.id" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						<div class="col-lg-10"></div>
						
						<!-- <div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Role</label>
								<div class="col-lg-10">
							        <select data-ng-model="newEmployee.roles" class="form-control required" data-ng-change="onMappingChange()">
							        	<option value="">Select..</option>
							        	<option data-ng-repeat="value in activityMapping.values" value="{{value}}">{{value}}</option>
							        </select>
								</div>
						    </div>
						</div> -->
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Roles</label>
								<div class="col-lg-10">
								   <div class="demo-section k-header">
								       <select kendo-multi-select k-data-source="rolesList" k-placeholder="'Select roles..'" k-data-text-field="'role'" k-data-value-field="'id'" class="form-control required" ng-model="newEmployee.selectedRoles"></select>
								       <!-- <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p> -->
								    </div>
								</div>
						    </div>
						</div>
						
					<!-- 	<div id="example">
						   <div class="demo-section k-header">
						       <h4>Select product</h4>
						       <select kendo-multi-select k-options="selectOptions" k-ng-model="selectedIds"></select>
						       <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p>
						    </div>
						</div> -->
						
						<div class="col-lg-10"><h4>Employee allocation details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Departments</label>
								<div class="col-lg-10">
								   <div class="demo-section k-header">
								       <select kendo-multi-select k-data-source="deptList" k-placeholder="'Select departments..'" k-data-text-field="'deptName'" k-data-value-field="'deptId'" class="form-control required" ng-model="newEmployee.selectedDepartments" data-ng-change="onDeptChange()"></select>
								       <!-- <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p> -->
								    </div>
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Projects</label>
								<div class="col-lg-10">
								   <div class="demo-section k-header">
								       <select kendo-multi-select k-data-source="projList" k-placeholder="'Select projects..'" k-data-text-field="'projectName'" k-data-value-field="'id'" class="form-control required" ng-model="newEmployee.selectedProjects"></select>
								       <!-- <p ng-show="selectedIds.length" style="padding-top: 1em;">Selected: {{ selectedIds }}</p> -->
								    </div>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Manager</label>
								<div class="col-lg-10">
							        <select name="managerSelect" data-ng-options="manager.managerId as manager.managerName for manager in empScreenDetails.managers" class="form-control" data-ng-model="newEmployee.managerId" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-10"><h4>Employee login details</h4></div>
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
						
						
						<label for="name" class="col-lg-10">Username : {{newEmployee.primaryEmail}}</label>
						
						<div class="col-lg-6">&nbsp;</div><div class="col-lg-6">&nbsp;</div>
												
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Password</label>
								<div class="col-lg-10">
							        <input type="password" class="form-control required profileEditable" name="password" id="password" data-ng-model="newEmployee.password">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-6">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">User Role</label>
								<div class="col-lg-10">
							        <select name="userRoleSelect" data-ng-options="userRole.id as userRole.userRole for userRole in empScreenDetails.userRoles" class="form-control required" data-ng-model="newEmployee.userRole.id" >
							     		<option value="">Select..</option>
   								 	</select>
								</div>
						    </div>
						</div>