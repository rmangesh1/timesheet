package com.v2.lt.empMgmt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.v2.lt.emplmgmt.domain.Organization;
import com.v2.lt.emplmgmt.domain.TSFactor;
import com.v2.lt.emplmgmt.service.OrganizationService;
import com.v2.lt.emplmgmt.service.TSFactorService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class OrganizationTest {
	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	TSFactorService tsFactorService;
	
	@Test
	@Rollback(value=false)
	public void testSaveOrganization(){
		
		Organization org = new Organization();
		org.setOrgName("Alaska Enterprises");
		org.setPrimaryPhone("1000 99 888");
		org.setPrimaryEmail("alaska@satkon.com");
		//org.setTsFactors(tsFactors);
		org = organizationService.saveOrUpdate(org);
		if(org.getOrgName().equalsIgnoreCase("Alaska Enterprises")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}
	
	@Test
	@Rollback(value=false)
	public void addTSFactors() {
		
		Set<TSFactor> tsFactors = new HashSet<TSFactor>();
		

		Organization org = organizationService.findOrganizationByName("Alaska Enterprises");
		
		TSFactor tsFactor = (TSFactor) tsFactorService.find(28L);
		/*tsFactor.setName("Department");
		tsFactor.setFactorOrderIndex(1);
		tsFactor.setEmpTSFactor("departments:name");
		tsFactor.setAmTSFactor("departments:name");*/
		tsFactors.add(tsFactor);
		
		tsFactor = (TSFactor) tsFactorService.find(29L);
		/*tsFactor.setName("Project");
		tsFactor.setFactorOrderIndex(2);
		tsFactor.setEmpTSFactor("projects:projectName");
		tsFactor.setAmTSFactor("departments:projects:projectName");*/
		tsFactors.add(tsFactor);
		
		tsFactor = (TSFactor) tsFactorService.find(30L);
		/*tsFactor.setName("Role");
		tsFactor.setFactorOrderIndex(3);
		tsFactor.setEmpTSFactor("roles:role");
		tsFactor.setAmTSFactor("roles:role");*/
		tsFactors.add(tsFactor);
		org.setTsFactors(tsFactors);
		org = organizationService.saveOrUpdate(org);
	}
	
	@Test
	@Rollback(value=false)
	public void testSaveOrganization2(){
		Organization org = new Organization();
		org.setOrgName("Lnt Infotech");
		org.setPrimaryPhone("1000 99 888");
		org.setPrimaryEmail("lnt@satkon.com");
		org = organizationService.saveOrUpdate(org);
		if(org.getOrgName().equalsIgnoreCase("Lnt Infotech")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}
	
	
	@Test
	@Rollback(value=false)
	public void testUpdateOrganization(){
		Organization org = organizationService.findOrganizationByName("Alaska Enterprises");
		//org.setTimeSheetFactors("departments:name.projects:projectName.grade:grade.roles:role");
		TSFactor tsFactor = new TSFactor();
		tsFactor.setName("Grade");
		tsFactor.setFactorOrderIndex(4);
		tsFactor.setEmpTSFactor("grade:grade");
		tsFactor.setAmTSFactor("grades:grade");
		
		org.addTSFactor(tsFactor);
		org.setPrimaryPhone("442255433");
		org = organizationService.saveOrUpdate(org);
		if(org.getOrgName().equalsIgnoreCase("Alaska Enterprises")){
			Assert.assertEquals(true, true);
		}
		else{
			Assert.assertEquals(true, false);
		}
	}
	
	

}
