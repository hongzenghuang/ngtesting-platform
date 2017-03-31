package com.ngtesting.platform.vo;


public class GroupVo extends BaseVo {
	private static final long serialVersionUID = -8567752946862450791L;
	
	private String name;
    private String descr;
    
    private Long companyId;
    
    private Boolean selected;
    private Boolean checked;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
    
}
