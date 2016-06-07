package com.v2.lt.emplmgmt.form;

import com.v2.lt.emplmgmt.domain.Activity;

public class ActivityForm {
	
	private Activity activity;
	
	private Boolean isChecked = false;

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	
}
