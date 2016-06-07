
						
						<div class="col-lg-1" data-ng-repeat="activityMapping in timeSheetScreen.timeSheetForm track by $index"  data-ng-if="activityMapping.tsFactor.empToempTSFactorMultiplicity == 'multiple'">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">{{ activityMapping.tsFactor.name }}</label>
								<div class="col-lg-10">
							        <select data-ng-model="tsRecord.selectFactor[$index]" data-ng-options="value as value for value in activityMapping.values" class="form-control required" data-ng-change="onMappingChange(parentIndex)">
							        	<option value="">Select..</option>
							        </select>
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Activity</label>
								<div class="col-lg-10">
							        <select data-ng-model="tsRecord.selectedActivity.id" data-ng-options="activity.id as activity.activityName for activity in tsRecord.activities" class="form-control required">
							        	<option value="">Select..</option>
							        	<!-- <option data-ng-repeat="value in activityMapping.values" value="{{value}}">{{value}}</option> -->
							        </select>
								</div>
						    </div>
						</div>
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Task</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="taskDescription" id="taskDescription" data-ng-model="tsRecord.taskDescription">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Mon</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="hoursMon" id="hoursMon" data-ng-model="tsRecord.hoursMon">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Tue</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="hoursTue" id="hoursTue" data-ng-model="tsRecord.hoursTue">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Wed</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="hoursWed" id="hoursWed" data-ng-model="tsRecord.hoursWed">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Thu</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="hoursThur" id="hoursThur" data-ng-model="tsRecord.hoursThur">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Fri</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="hoursFri" id="hoursFri" data-ng-model="tsRecord.hoursFri">
								</div>
						    </div>
						</div>
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Sat</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="hoursSat" id="hoursSat" data-ng-model="tsRecord.hoursSat">
								</div>
						    </div>
						</div>
						
						
						<div class="col-lg-1">
						    <div class="form-group">
							    <label for="name" class="col-lg-10">Sun</label>
								<div class="col-lg-10">
							        <input type="text" class="form-control" name="hoursSun" id="hoursSun" data-ng-model="tsRecord.hoursSun">
								</div>
						    </div>
						</div>