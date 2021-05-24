package bsu.rfe.java.group8.lab9.Kalitin.varA4.tag;

import bsu.rfe.java.group8.lab9.Kalitin.varA4.entity.Ad;
import bsu.rfe.java.group8.lab9.Kalitin.varA4.entity.AdList;
import bsu.rfe.java.group8.lab9.Kalitin.varA4.entity.AdListHelper;
import bsu.rfe.java.group8.lab9.Kalitin.varA4.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class UpdateAd extends SimpleTagSupport {

    private Ad ad;

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public void doTag() throws JspException, IOException {
        String errorMessage = null;
        AdList adList = (AdList) getJspContext().getAttribute("ads", PageContext.APPLICATION_SCOPE);
        User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);


        if (errorMessage==null) {
            ad.setLastModified(Calendar.getInstance().getTimeInMillis());

            if (ad.getId()==0) {
                adList.addAd(currentUser, ad);

            } else {
                adList.updateAd(ad);
            }

            AdListHelper.saveAdList(adList);
        }

        getJspContext().setAttribute("errorMessage", errorMessage,PageContext.SESSION_SCOPE);
    }
}
