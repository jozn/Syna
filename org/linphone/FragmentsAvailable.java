package org.linphone;

import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public enum FragmentsAvailable {
    UNKNOW,
    DIALER,
    HISTORY,
    HISTORY_DETAIL,
    CONTACTS,
    CONTACT,
    EDIT_CONTACT,
    ABOUT,
    ABOUT_INSTEAD_OF_SETTINGS,
    ABOUT_INSTEAD_OF_CHAT,
    ACCOUNT_SETTINGS,
    SETTINGS,
    CHATLIST,
    CHAT;

    public boolean isRightOf(FragmentsAvailable fragmentsAvailable) {
        switch ($SWITCH_TABLE$org$linphone$FragmentsAvailable()[ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return EDIT_CONTACT.isRightOf(fragmentsAvailable) || fragmentsAvailable == EDIT_CONTACT;
            case Version.API03_CUPCAKE_15 /*3*/:
                return fragmentsAvailable == UNKNOW;
            case Version.API04_DONUT_16 /*4*/:
                return HISTORY.isRightOf(fragmentsAvailable) || fragmentsAvailable == HISTORY;
            case Version.API05_ECLAIR_20 /*5*/:
                return HISTORY_DETAIL.isRightOf(fragmentsAvailable) || fragmentsAvailable == HISTORY_DETAIL;
            case Version.API06_ECLAIR_201 /*6*/:
                return CONTACTS.isRightOf(fragmentsAvailable) || fragmentsAvailable == CONTACTS;
            case Version.API07_ECLAIR_21 /*7*/:
                return CONTACT.isRightOf(fragmentsAvailable) || fragmentsAvailable == CONTACT;
            case Version.API08_FROYO_22 /*8*/:
            case Version.API11_HONEYCOMB_30 /*11*/:
                return SETTINGS.isRightOf(fragmentsAvailable) || fragmentsAvailable == SETTINGS;
            case Version.API09_GINGERBREAD_23 /*9*/:
            case Version.API12_HONEYCOMB_MR1_31X /*12*/:
                return CHATLIST.isRightOf(fragmentsAvailable) || fragmentsAvailable == CHATLIST || fragmentsAvailable == ABOUT_INSTEAD_OF_CHAT;
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
            case Version.API13_HONEYCOMB_MR2_32 /*13*/:
                return DIALER.isRightOf(fragmentsAvailable) || fragmentsAvailable == DIALER;
            case Version.API14_ICE_CREAM_SANDWICH_40 /*14*/:
                return CHATLIST.isRightOf(fragmentsAvailable) || fragmentsAvailable == CHATLIST;
            default:
                return false;
        }
    }

    public boolean shouldAddItselfToTheRightOf(FragmentsAvailable fragmentsAvailable) {
        switch ($SWITCH_TABLE$org$linphone$FragmentsAvailable()[ordinal()]) {
            case Version.API04_DONUT_16 /*4*/:
                return fragmentsAvailable == HISTORY;
            case Version.API06_ECLAIR_201 /*6*/:
                return fragmentsAvailable == CONTACTS;
            case Version.API07_ECLAIR_21 /*7*/:
                return fragmentsAvailable == CONTACT || fragmentsAvailable == CONTACTS;
            case Version.API14_ICE_CREAM_SANDWICH_40 /*14*/:
                return fragmentsAvailable == CHATLIST;
            default:
                return false;
        }
    }

    public boolean shouldAddToBackStack() {
        return true;
    }

    public boolean shouldAnimate() {
        return true;
    }
}
